package com.example.entity;

/**
 * @author qx  on 2016/8/10.
 */
public class ServiceException extends Exception{
    private static final long serialVersionUID = 2269761074623898466L;
    private String code;

    public ServiceException(String message) {
        super(message);
        System.out.println(message);
        code = "500";
    }

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(EnumServiceMessage message) {
        super(message.getMessage());
        this.code = message.getCode();
    }

    public String getCode() {
        return this.code;
    }
}
