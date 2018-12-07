package org.simplesupermarket.web.app.service.datahandle;


import org.simplesupermarket.web.app.service.datahandle.standard.StandardCodeBuilder;
import org.simplesupermarket.web.auth.UserDetail;

/**
 * @author 董文强
 * @version 1.0
 * @date 2018年12月07日
 */
public interface CodeBuilder {

    default public CodeBuilder createStandardCodeBuild(){
           return new StandardCodeBuilder();
    }
    CodeBuilder setName(String name);
    CodeBuilder setUser(UserDetail user);
    CodeBuilder setLength(Integer length);
    String build();

}
