package com.example.service;

import com.example.entity.ServiceException;
import com.example.entity.Users;
import com.example.entity.VueUserToken;
import com.example.entity.VueUsers;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public interface UserService {


    VueUsers login(String userName, String passWord) throws ServiceException;


    /**
     * 生成token
     *
     * @param vueUsers
     * @return
     */
    public VueUserToken generateToken(VueUsers vueUsers);

    /**
     * 根据token查询用户信息
     * @param token
     * @return
     */
    public VueUsers getUserByToken(String token);

    /**
     * 获取用户角色信息
     *
     * @param token
     * @return
     */
    public Map getRoleResult(String token);
}
