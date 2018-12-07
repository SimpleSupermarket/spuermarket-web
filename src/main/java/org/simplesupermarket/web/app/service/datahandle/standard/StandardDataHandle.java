package org.simplesupermarket.web.app.service.datahandle.standard;

import org.simplesupermarket.web.app.service.datahandle.CodeBuilder;
import org.simplesupermarket.web.app.service.datahandle.DataHandle;
import org.simplesupermarket.web.auth.UserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * @author 董文强
 * @version 1.0
 * @date 2018年12月07日
 */
public class StandardDataHandle implements DataHandle {

    private static final Logger LOGGER = LoggerFactory.getLogger(StandardDataHandle.class);

    private Class dataClass = null;


    private UserDetail user;

    protected <T> T setCode(T data) throws NoSuchFieldException, IllegalAccessException {
        Field code = dataClass.getDeclaredField("code");
        code.setAccessible(true);
        if (code.get(data) != null && !((String) code.get(data)).isEmpty()) {
            return data;
        }
        String name = null;
        try {
            Field nameField = dataClass.getDeclaredField("name");
            nameField.setAccessible(true);
            name = (String) nameField.get(data);
        }catch (NoSuchFieldException  e){
            name = null;
        }
        CodeBuilder codeBuilder = new StandardCodeBuilder();
        codeBuilder = codeBuilder.setName(name).setUser(user).setLength(30);
        code.set(data, codeBuilder.build());
        return data;
    }

    protected <T> T setThisUserId(T data, String propertyName) throws NoSuchFieldException, IllegalAccessException {
        Field createdby = dataClass.getDeclaredField(propertyName);
        createdby.setAccessible(true);
        createdby.set(data, this.user.getId());
        return data;
    }

    protected <T> T setNowDate(T data, String propertyName) throws NoSuchFieldException, IllegalAccessException {
        Field creationdate = dataClass.getDeclaredField(propertyName);
        creationdate.setAccessible(true);
        creationdate.set(data, new Date());
        return data;
    }


    public <T> T handleData(T data, UserDetail user) {
        this.dataClass = data.getClass();
        this.user = user;
        try {
            this.setCode(data);
            this.setThisUserId(data, "createdby");
            this.setNowDate(data, "creationdate");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            if(LOGGER.isErrorEnabled()){
                e.printStackTrace();
            }
            throw new IllegalArgumentException("参数异常");
        }
        return data;
    }
}
