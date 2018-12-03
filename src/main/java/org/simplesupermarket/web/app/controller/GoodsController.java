package org.simplesupermarket.web.app.controller;
import org.simplesupermarket.web.app.controller.common.AbstractSuperController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date 2018年12月03日
 * @version 1.0
 */
@RestController
@RequestMapping("/goods")
public class GoodsController extends AbstractSuperController {
 private static final Logger LOGGER = LoggerFactory.getLogger(GoodsController.class);
}
