package org.simplesupermarket.web.app.service;


import org.springframework.security.core.userdetails.UserDetails;

public interface OwnService {
    UserDetails getManageUserLogin(String username, String password);
}
