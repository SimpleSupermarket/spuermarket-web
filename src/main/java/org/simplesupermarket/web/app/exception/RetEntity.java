package org.simplesupermarket.web.app.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RetEntity<T> {
    /**
     * 返回码
     */
    private String code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;


    /**
     * 成功返回信息构造方法,code指定为成功
     * @param code 返回码
     *
     */
    public RetEntity() {
        this.code = RetCode.SUCCESS.getRetCode();
        this.msg = "执行成功";
    }
    
    /**
     * 返回信息构造方法
     * @param code 返回码
     *
     */
    public RetEntity(String code) {
        this.code = code;
    }

    /**
     * 返回信息构造方法
     * @param code 返回码
     * @param msg 返回信息
     */
    public RetEntity(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(T data) {
        this.data = data;
    }

}
