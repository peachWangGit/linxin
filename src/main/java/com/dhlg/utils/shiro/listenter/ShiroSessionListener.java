package com.dhlg.utils.shiro.listenter;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**
 * 描述
 * User:whfch
 * Date:2020/2/21
 * Time:11:39
 */
public class ShiroSessionListener implements SessionListener {

    /**
     * 回话创建触发
     */
    @Override
    public void onStart(Session session) {

    }

    /**
     * 退出会话时触发
     */
    @Override
    public void onStop(Session session) {

    }

    /**
     * 会话过期时触发
     */
    @Override
    public void onExpiration(Session session) {

    }
}
