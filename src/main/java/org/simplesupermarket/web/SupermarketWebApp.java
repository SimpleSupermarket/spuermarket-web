package org.simplesupermarket.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Time 2018/12/2 19:19
 */
@SpringBootApplication
@MapperScan(basePackages = "org.simplesupermarket.web.db.mapper")
public class SupermarketWebApp {
    public static void main(String[] args) {
        SpringApplication.run(SupermarketWebApp.class,args);
    }

}
