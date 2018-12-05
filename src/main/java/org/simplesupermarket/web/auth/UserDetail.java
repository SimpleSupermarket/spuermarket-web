package org.simplesupermarket.web.auth;

import org.simplesupermarket.web.db.model.Role;
import org.simplesupermarket.web.db.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author 董文强
 * @version 1.0
 * @date 2018年12月03日
 */
public class UserDetail extends User implements UserDetails {

    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> ro = new ArrayList<>();
        this.roles.forEach(v -> {
            ro.add(new SimpleGrantedAuthority(v.getName()));
        });
        return ro;
    }

    @Override
    public String getPassword() {
        return "no password!";
    }

    @Override
    public String getUsername() {
        return super.getCode();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
