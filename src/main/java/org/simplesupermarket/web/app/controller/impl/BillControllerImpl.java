package org.simplesupermarket.web.app.controller.impl;
import org.simplesupermarket.web.app.controller.AbstractSuperController;
import org.simplesupermarket.web.app.service.AbstractSuperServiceImpl;
import org.simplesupermarket.web.app.service.BillService;
import org.simplesupermarket.web.db.model.Bill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author 董文强
 * @date 2018年12月03日
 * @version 1.0
 */
@RestController
@RequestMapping("/bill")
public class BillControllerImpl extends AbstractSuperController<Bill> {
 private static final Logger LOGGER = LoggerFactory.getLogger(BillControllerImpl.class);


    protected BillControllerImpl(BillService service) {
        super(service);
    }
}
