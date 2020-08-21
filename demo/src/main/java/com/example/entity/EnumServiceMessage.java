package com.example.entity;

/**
 * @author qx  on 2016/8/10.
 */
public enum EnumServiceMessage {
    /**
     * 数据校验
     */
    USER_NOT_EXIST("401", "用户不存在"),
    USER_ERROR_PASSWORD("402", "用户密码错误"),
    USER_LOGIN_PARAMETER_CANT_NULL("403", "用户名，密码不能为空"),
    USER_TOKEN_NOT_NULL("404", "请重新登录"),
    USER_REGISTER_USERNAME_EXIST("405", "用户名已存在"),
    USER_REGISTER_LENGTH("405", "用户名密码长度必须为6位到18位"),
    USER_REGISTER_USER_PHONE_EXIST("410", "用户手机号码已存在"),
    USER_REGISTER_PASSWORD_NOT_MATCH("406", " 两次填写的密码不一致"),
    USER_VALIDATE_ERROR("407", "验证码错误"),
    UPDATE_ERROR("409", "更新运营信息失败"),
    LIST_NOT_EXIST("410", "记录不存在"),
    USER_IMEI_NULL("411", "IMEI不能为空"),
    DATA_NOT_NULL("412", "请检查你输入的内容"),
    DATA_MOBILE_NO("413", "请检查你输入的机型是否正确"),
    IP_ERROR("414", "请检查你输入的机型是否正确"),
    NOT_NO_NULL("415", "不能为空"),
    CHANNEL_EXIST("416", "该编号已存在"),
    TOKEN_NOT_EXIST("417", "TOKEN不存在"),
    TOKEN_CANT_NULL("417", "TOKEN不存在"),
    POWERTIMER_NO_MIN_1("418", "开机周期不能小于1"),
    SENDDATATIME_NO_MIN_80("422", "发错数据时间不能小于80"),
    PROTECTTIMER_NO_GREATER_POWERTIMER("419", "单片机异常重启时间只能小于开机周期"),
    PROTECTTIMER_NO_MAX_100("421", "单片机异常重启时间不能超过100"),
    OFF_DELAY_MIN_POWERTIME("420", "关机时间不能小于间隔时长"),
    POWERTIMER_OFFDELAY("421", "单片机异常重启时间必须大于关机和供电关机时间加十秒");

    private String code;
    private String message;

    EnumServiceMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
