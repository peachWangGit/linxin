package com.dhlg.aop;

import com.dhlg.module.system.sysLoginLog.entity.SysLoginLog;
import com.dhlg.module.system.sysLoginLog.service.ISysLoginLogService;
import com.dhlg.module.system.sysUser.service.ISysUserService;
import com.dhlg.utils.common.AddressUtils;
import com.dhlg.utils.common.GetLoginUser;
import com.dhlg.utils.common.Result;
import com.dhlg.utils.common.loginAgentUtils;
import net.sf.json.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * 描述 /usr/java/jdk/jdk1.8.0_251/
 * User:whfch
 * Date:2020/2/22
 * Time:12:12
 */
@Aspect
@Component
public class LoginAop {

    @Autowired
    ISysLoginLogService loginLogService;

    @Autowired
    ISysUserService userService;

//    static VCache cache;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    final static String ak = "VYLpZYzmTaujCBGTmBLnK5YLrtIljB3R";

    @Pointcut("execution(* com.dhlg.module.system.sysUser.controller.SysUserController.login(..))")//断点
    public void aspectaMethod() {
    }

    @AfterReturning(value = "aspectaMethod()", returning = "result")
    public void doAfterReturning(JoinPoint point, Object result) throws ClassNotFoundException,
            UnsupportedEncodingException {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();

        //操作人
        String userId = GetLoginUser.getCurrentUserId();

        //登录日志对象
        SysLoginLog loginLog = new SysLoginLog();

        //浏览器名称
        loginLog.setBrowserName(loginAgentUtils.getBrowserName(request));

        //操作系统
        loginLog.setOperationSystem(loginAgentUtils.getSystemName(request));

        //获取登录的地理位置信息
        Object city = "xx";
        String ip = loginAgentUtils.getInternetIp();

        //ps: 局域网的ip不能获取到地址。正常ip，例如  = "219.136.134.157";
        String ipInfo = AddressUtils.getAddresses("ip=" + ip + "&ak=" + ak, "utf-8");
        if (null != ipInfo && !"0".equals(ipInfo)) {
            //有效ip
            JSONObject location = JSONObject.fromObject(ipInfo);
            if (null != location) {
                Object dataObject = location.get("content");
                JSONObject data = JSONObject.fromObject(dataObject);
                //地区(省份)
                city = data.get("address");
            }
        }

        //生成一个登录日志对象，保存到数据库
        if (null != userId) loginLog.setUserId(userId);
        loginLog.setLoginIp(ip);
        loginLog.setLoginAddress(city + "");

        Result logResult = (Result) result;
        if ("200".equals(logResult.getCode())) {
            loginLog.setLoginStatus("1");
            loginLog.setOperationInfo("登录成功");
        } else {
            loginLog.setLoginStatus("0");
            loginLog.setOperationInfo("登录失败");
        }
        loginLog.setLoginTime(new Date());
        loginLogService.save(loginLog);
        //logger.info("登录人 " + user == null ? "" : user.getUserName() + "登录时间：" + new Date() + "\t登录状态记录：" + loginLog
        // .getOperationInfo());
    }

}
