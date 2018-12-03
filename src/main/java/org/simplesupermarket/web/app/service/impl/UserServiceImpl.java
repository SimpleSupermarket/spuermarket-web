package org.simplesupermarket.web.app.service.impl;
import org.simplesupermarket.web.app.service.AbstractSuperServiceImpl;
import org.simplesupermarket.web.app.service.BillService;
import org.simplesupermarket.web.app.service.OwnService;
import org.simplesupermarket.web.app.service.UserService;
import org.simplesupermarket.web.auth.UserDetail;
import org.simplesupermarket.web.db.model.Bill;
import org.simplesupermarket.web.db.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @date 2018年12月03日
 * @version 1.0
 */
@Service
public class UserServiceImpl extends AbstractSuperServiceImpl<User> implements OwnService,UserService {
 private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public List<User> getList() {
        return null;
    }

    @Override
    public UserDetails getManageUserLogin(String username, String password) {
        User user = new UserDetail();
        user.setUsername("abc");
        user.setUserpassword("123");
        return null;
    }
}
