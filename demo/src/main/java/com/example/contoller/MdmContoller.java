package com.example.contoller;

import com.example.entity.*;
import com.example.service.UserService;
import com.github.pagehelper.util.StringUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Author DQ
 * Date 2020/5/26
 **/
@RestController
@CrossOrigin
public class MdmContoller {

    private final UserService userService;

    public MdmContoller(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public RespBase<LoginResult> login(
                        Model model, @RequestBody LoginResult vo){
            RespBase<LoginResult> r = null;
            try {
                if (StringUtil.isEmpty(vo.getUsername()) || StringUtil.isEmpty(vo.getPassword())) {
                    throw new ServiceException(EnumServiceMessage.USER_LOGIN_PARAMETER_CANT_NULL);
                }
                VueUsers vueUsers = userService.login(vo.getUsername(), vo.getPassword());
                LoginResult loginResult = new LoginResult();
                loginResult.setId(vueUsers.getId());
                loginResult.setUsername(vueUsers.getLoginName());
                loginResult.setToken(userService.generateToken(vueUsers).getToken());
                r = new RespBase<LoginResult>(loginResult);
            } catch (ServiceException e) {
                r = new RespBase<LoginResult>(e);
            }
            return r;
        }

    @RequestMapping(value = "user/token", method = RequestMethod.GET)
    public RespBase<UserInfo> getUser(
                                      Model model, String token) {
        RespBase<UserInfo> r = null;
        try {
            VueUsers vueUsers = userService.getUserByToken(token);
            if (vueUsers == null) {
                throw new ServiceException(EnumServiceMessage.USER_TOKEN_NOT_NULL);
            }
            Map map = userService.getRoleResult(token);
            UserInfo userInfo = new UserInfo();
            userInfo.setToken(token);
            userInfo.setName(vueUsers.getName());
            userInfo.setIntroduction(vueUsers.getIntroduction());
            userInfo.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
            String[] role = new String[1];
            role[0] = (String) map.get("role_name");
            userInfo.setRoles(role);
            r = new RespBase<UserInfo>(userInfo);
        } catch (ServiceException e) {
            r = new RespBase<UserInfo>(e);
        }
        return r;
    }
}
