package com.dhlg.utils.shiro.token;

import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

import java.io.Serializable;

/**
 * 描述
 * User:whfch
 * Date:2020/3/12
 * Time:0:35
 */
public class MbUsernamePasswordToken implements HostAuthenticationToken, RememberMeAuthenticationToken, Serializable {

    private String username;

    private char[] password;

    private boolean rememberMe= false;;

    private String host;


    public char[] getPassword() {
        return password;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public boolean isRememberMe() {
        return rememberMe;
    }


    public String getUsername(){
        return username;
    }
    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return password;
    }


    public MbUsernamePasswordToken() { this.rememberMe = false; }

    private MbUsernamePasswordToken(final String username, final char[] password,
                                    final boolean rememberMe, final String host) {

        this.username = username;
        this.password = password;
        this.rememberMe = rememberMe;
        this.host = host;
    }

    public MbUsernamePasswordToken(final String username, final String password, final boolean rememberMe) {
        this(username, password != null ? password.toCharArray() : null, rememberMe, null);
    }


    public MbUsernamePasswordToken(final String username, final String password) {
        this(username, password != null ? password.toCharArray() : null, false, null);
    }
}
