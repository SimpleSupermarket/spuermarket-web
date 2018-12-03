package org.simplesupermarket.web;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 董文强
 * @Time 2018/12/2 19:19
 */
@SpringBootApplication
@MapperScan(basePackages = "org.simplesupermarket.web.db.mapper")
public class SupermarketWebApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(SupermarketWebApp.class);

    public static void main(String[] args) {
        SpringApplication.run(SupermarketWebApp.class,args);
    }

}
