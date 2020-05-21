package com.dhlg.utils.shiro.token;

import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

import java.io.Serializable;

/**
 * 描述
 * User:whfch
 * Date:2020/3/11
 * Time:21:52
 */
public class PhoneToken implements  HostAuthenticationToken, RememberMeAuthenticationToken, Serializable {

    // 手机号码
    private String phone;

    private boolean rememberMe=false;

    private String host;

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public boolean isRememberMe() {
        return rememberMe;
    }

    @Override
    public Object getPrincipal() {
        return phone;
    }

    @Override
    public Object getCredentials() {
        return phone;
    }

    public PhoneToken() { this.rememberMe = false; }

    public PhoneToken(String phone) { this(phone, false, null); }

    public PhoneToken(String phone, boolean rememberMe) { this(phone, rememberMe, null); }

    public PhoneToken(String phone, boolean rememberMe, String host) {
        this.phone = phone;
        this.rememberMe = rememberMe;
        this.host = host;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
