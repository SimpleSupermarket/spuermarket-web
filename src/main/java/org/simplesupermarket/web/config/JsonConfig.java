package org.simplesupermarket.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 董文强
 * @Time 2018/12/6 21:58
 */
@Configuration
public class JsonConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonConfig.class);


    @Bean
    public JsonParser jsonParser(){
        return JsonParserFactory.getJsonParser();
    }
}
