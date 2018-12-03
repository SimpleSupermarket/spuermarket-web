package org.simplesupermarket.web.app.controller;

import org.simplesupermarket.web.app.controller.common.AbstractSuperController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 董文强
 * @version 1.0
 * @date 2018年12月03日
 */
@RestController
@RequestMapping("/provider")
public class ProviderControllerImpl extends AbstractSuperController<ProviderControllerImpl> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProviderControllerImpl.class);
}
