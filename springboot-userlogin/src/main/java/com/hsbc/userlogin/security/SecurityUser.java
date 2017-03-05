package com.hsbc.userlogin.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hsbc.userlogin.domain.User;



/**
 * 系统认证用户principle
 * 需要继承User 并且实现UserDetails 接口
 * 实现为实现的方法  这里的授权我们暂时不做处理。
 *
 * 
 *       
 */
public class SecurityUser extends User implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public SecurityUser(User user) {
        if (user != null) {
           
            this.setName(user.getName());
            this.setPassword(user.getPassword());
            
          
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //TODO 授权暂时未实现
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        return null;
    }

    @Override
    public String getUsername() {
        return super.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return  true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return  true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return  true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else {
            if (obj instanceof SecurityUser) {
                SecurityUser other = (SecurityUser) obj;
                if (this.getUsername().equals(other.getUsername())) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    @Override
    public int hashCode() {
        return getUsername().hashCode();
    }
}
