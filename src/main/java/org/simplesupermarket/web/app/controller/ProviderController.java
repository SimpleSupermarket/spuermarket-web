package org.simplesupermarket.web.app.controller;

import org.simplesupermarket.web.app.controller.common.AbstractSuperController;
import org.simplesupermarket.web.db.model.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @date 2018年12月03日
 */
@RestController
@RequestMapping("/provider")
public class ProviderController extends AbstractSuperController<Provider> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProviderController.class);
}
