package org.simplesupermarket.web.app.controller;

import org.simplesupermarket.web.app.controller.common.AbstractSuperController;
import org.simplesupermarket.web.app.service.UserService;
import org.simplesupermarket.web.auth.UserDetail;
import org.simplesupermarket.web.db.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @date 2018年12月03日
 */
@RestController
@RequestMapping("/user")
public class UserController extends AbstractSuperController<User> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @PatchMapping("RePassword")
    public Boolean rePassword(String password, UsernamePasswordAuthenticationToken user) {
        UserDetail userDetail = (UserDetail) user.getPrincipal();
        return false;
    }
}
