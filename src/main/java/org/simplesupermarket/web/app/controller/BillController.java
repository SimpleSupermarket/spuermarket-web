package org.simplesupermarket.web.app.controller;
import org.simplesupermarket.web.app.controller.common.AbstractSuperController;
import org.simplesupermarket.web.db.model.Bill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date 2018年12月03日
 * @version 1.0
 */
@RestController
@RequestMapping("/bill")
public class BillController extends AbstractSuperController<Bill> {
 private static final Logger LOGGER = LoggerFactory.getLogger(BillController.class);

}
