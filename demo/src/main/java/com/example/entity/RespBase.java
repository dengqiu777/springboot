package com.example.entity;


/**
 * @author qx  on 2016/8/10.
 *         返回信息
 */
public class RespBase<T> {
    /**
     * success:true
     * code : 10000
     * msg :
     * data : {}
     */
    /**
     * 状态，true:成功，false:失败
     */
    private boolean success;
    /**
     * 状态码，10000:成功，其他状态码请参见服务端定义
     */
    private String code;
    /**
     * 返回信息，如成功提示信息，错误原因等
     */
    private String msg;
    /**
     * 业务相关的响应数据
     */
    private T data;

    public RespBase(Exception ex) {
        this.success = false;
        this.code = "500";
        this.msg = ex.getMessage();
    }

    public RespBase(ServiceException ex) {
        this.success = false;
        this.code = ex.getCode();
        this.msg = ex.getMessage();
    }

    public RespBase(T data) {
        this.success = true;
        this.code = "200";
        this.msg = "请求成功";
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
