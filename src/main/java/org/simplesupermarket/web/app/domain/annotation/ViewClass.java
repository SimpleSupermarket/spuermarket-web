package org.simplesupermarket.web.app.domain.annotation;


import java.lang.annotation.*;


@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewClass {
    Class<?> value(); //TODO not null
}
