package com.dhlg.config;

import com.dhlg.utils.shiro.filter.KickoutSessionControlFilter;
import com.dhlg.utils.shiro.listenter.ShiroSessionListener;
import com.dhlg.utils.shiro.realm.UserRealm;
import com.dhlg.utils.shiro.session.ShiroSessionManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.hibernate.JDBCException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.mgt.SecurityManager;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.Filter;
/**
 * 描述
 * User:whfch
 * Date:2020/2/8
 * Time:15:59
 */
@Configuration
public class shiroConfig {

    @Value("${spring.redis.host}")
    private String redis_host;

    @Value("${spring.redis.port}")
    private Integer redis_port;

    @Value("${spring.redis.timeout}")
    private Integer redis_timeout;

    @Value("${spring.redis.password}")
    private String redis_password;

    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager)throws JDBCException{
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login.html");

        // 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 :这是一个坑呢，一不小心代码就不好使了;
        // ① authc:所有url都必须认证通过才可以访问; ② anon:所有url都都可以匿名访问

        //自定义拦截器
        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        //限制同一帐号同时在线的个数。
        filtersMap.put("kickout", kickoutSessionControlFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);

        //配置拦截器
        Map<String, String> map = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接 顺序判断
        map.put("/**", "anon");
//        map.put("/**", "authc,kickout");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    /**
     * 凭证匹配器，与自定义的密码加密要保持一致
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列2次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }
    /**
     * 身份认证realm,及用户权限数据的注入
     * @return
     */
    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        //启用身份验证缓存，数据就会进入redis
        userRealm.setCachingEnabled(true);//允许缓存
        return userRealm;
    }


    /**
     * 认证器
     */
//    @Bean
//    public AbstractAuthenticator abstractAuthenticator(UserRealm userRealm, PhoneRealm phoneRealm,MbUserRealm mbUserRealm){
//        // 自定义模块化认证器，用于解决多realm抛出异常问题
//        ModularRealmAuthenticator authenticator = new CustomModularRealmAuthenticator();
//        // 认证策略：AtLeastOneSuccessfulStrategy(默认)，AllSuccessfulStrategy，FirstSuccessfulStrategy
//        authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
//        // 加入realms
//        List<Realm> realms = new ArrayList<>();
//        realms.add(userRealm);
//        realms.add(phoneRealm);
//        realms.add(mbUserRealm);
//        authenticator.setRealms(realms);
//        return authenticator;
//    }
    /**
     * 安全管理器，管理所有Subject，可以配合内部安全组件，是shrio核心
     * http://shiro.apache.org/architecture.html其官网有详细的届时
     * @return
     */
    @Bean
    public SecurityManager securityManager(UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置多个realms,全部通过才通过
//        List<Realm> realms = new ArrayList<>();
//        realms.add(userRealm);
//
//        securityManager.setRealms(realms);
        securityManager.setRealm(userRealm);
//        配置缓存，存储登录成功后加载的权限数据
//        EhCacheManager ehCacheManager = new EhCacheManager();
//        ehCacheManager.setCacheManagerConfigFile("classpath:shiro/config/ehcache.xml");
        //配置redis缓存
//        securityManager.setCacheManager(cacheManager());
        //自定义session管理
        securityManager.setSessionManager(sessionManager());
        //注入记住我管理器;
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /**
     * shiro缓存管理器;cache
     * @return
     */
    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        //用户权限信息缓存时间
        redisCacheManager.setExpire(20000);
        return redisCacheManager;
    }


    @Bean
    public RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redis_host);
        redisManager.setPort(redis_port);
        redisManager.setTimeout(redis_timeout);
        redisManager.setPassword(redis_password);
        return redisManager;
    }


    //添加bean
    /**
     * 自定义sessionManager
     * @return
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {

        ShiroSessionManager manager=new ShiroSessionManager();

        //此处使用的shiro默认的session管理，执行Session持久代（CRUD）操作SessionManager
        manager.setSessionDAO(new EnterpriseCacheSessionDAO());
        //此处使用的是redis缓存
//        manager.setSessionDAO(sessionDAO());

        return manager;
    }

    @Bean
    public ShiroSessionListener sessionListener() {
        return new ShiroSessionListener();
    }

    /**
     * SessionDAO的作用是为Session提供CRUD并进行持久化的一个shiro组件
     * MemorySessionDAO 直接在内存中进行会话维护
     * EnterpriseCacheSessionDAO  提供了缓存功能的会话维护，默认情况下使用MapCache实现，内部使用ConcurrentHashMap保存缓存的会话。
     * @return
     */
    @Bean
    public RedisSessionDAO sessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        //session在redis中的保存时间,最好大于session会话超时时间
//        redisSessionDAO.setExpire(12000);
        return redisSessionDAO;
    }


    /**
     * cookie管理对象;记住我功能,rememberMe管理器
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(simpleCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }
    @Bean
    public SimpleCookie simpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie();

        //setcookie的httponly属性如果设为true的话，会增加对xss防护的安全系数。它有以下特点：
        //setcookie()的第七个参数
        //设为true后，只能通过http访问，javascript无法访问
        //防止xss读取cookie
//        simpleCookie.setHttpOnly(true);
        //设置cookie的名称
        simpleCookie.setName("shiro_cookie");
        simpleCookie.setMaxAge(-1);
        simpleCookie.setPath("/");

        return simpleCookie;
    }


    /**
     * 限制同一账号登录同时登录人数控制
     *
     * @return
     */
    @Bean
    public KickoutSessionControlFilter  kickoutSessionControlFilter () {
        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
        kickoutSessionControlFilter.setCacheManager(cacheManager());
        kickoutSessionControlFilter.setSessionManager(sessionManager());
        kickoutSessionControlFilter.setKickoutAfter(false);
        kickoutSessionControlFilter.setMaxSession(1);
        kickoutSessionControlFilter.setKickoutUrl("/api/system/sysUser/kickout");
        return kickoutSessionControlFilter;
    }

    /*
     * 开启shiro 注解模式
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
                new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * LifecycleBeanPostProcessor，这是个DestructionAwareBeanPostProcessor的子类，
     * 负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁。
     * 主要是AuthorizingRealm类的子类，以及EhCacheManager类。
     * static是为了不影响@Value("${xxx}")取不到值
     */
    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

}
