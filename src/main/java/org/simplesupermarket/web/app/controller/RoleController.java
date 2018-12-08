package org.simplesupermarket.web.app.controller;

import org.simplesupermarket.web.app.controller.common.AbstractSuperController;
import org.simplesupermarket.web.db.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 董文强
 * @Time 2018/12/9 1:42
 */
@RestController
@RequestMapping("/role")
public class RoleController extends AbstractSuperController<Role> {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);
}
