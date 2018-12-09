package org.simplesupermarket.web.app.controller;

import org.simplesupermarket.web.app.controller.common.AbstractSuperController;
import org.simplesupermarket.web.app.exception.BusinessException;
import org.simplesupermarket.web.app.service.UserService;
import org.simplesupermarket.web.auth.UserDetail;
import org.simplesupermarket.web.db.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @version 1.0
 * @date 2018年12月03日
 */
@RestController
@RequestMapping("/user")
public class UserController extends AbstractSuperController<User> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/repassword")
    public Boolean rePassword(@RequestBody Map<String,String> password, UsernamePasswordAuthenticationToken user) {

        try {
            String oldPassword =  password.get("oldPassword");
            String newPassword = password.get("newPassword");
            String reNewPassword = password.get("reNewPassword");
            if (newPassword == null || !newPassword.equals(reNewPassword)) return false;
            UserDetail userDetail = (UserDetail) user.getPrincipal();
            return userService.repassword(oldPassword, newPassword, userDetail.getId());
        } catch (Exception e) {
            throw new BusinessException("9999", "修改密码失败");
        }
    }
}
