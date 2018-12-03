package org.simplesupermarket.web.app.controller;
import org.simplesupermarket.web.app.controller.common.AbstractSuperController;
import org.simplesupermarket.web.app.service.UserService;
import org.simplesupermarket.web.db.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date 2018年12月03日
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserControllerImpl extends AbstractSuperController<User> {
 private static final Logger LOGGER = LoggerFactory.getLogger(UserControllerImpl.class);
    @Autowired
    protected UserControllerImpl(UserService service) {

    }
}
