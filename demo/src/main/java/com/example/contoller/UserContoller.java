package com.example.contoller;

import com.example.entity.*;
import com.example.service.UserService;
import com.example.service.dao.UsersDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author DQ
 * Date 2020/6/4
 **/
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserContoller {

    private final UsersDao usersDao;

    private final UserService userService;

    public UserContoller(UsersDao usersDao, UserService userService) {
        this.usersDao = usersDao;
        this.userService = userService;
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public RespBase<String> addUser(
            Model model, @RequestHeader("X-Token") String token,String id, String name, String age, String sex, String birth, String addr) {
        RespBase<String> r = null;
        try {
            VueUsers users = userService.getUserByToken(token);
            if (users == null) {
                throw new ServiceException(EnumServiceMessage.TOKEN_NOT_EXIST);
            }
            Users user = new Users();
            user.setAge(age);
            user.setName(name);
            user.setSex(sex);
            user.setBirth(birth);
            user.setAddr(addr);
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            user.setIsDelete(0);
            usersDao.insertSelective(user);
        } catch (ServiceException e) {
            r = new RespBase<String>(e);
        }
        r = new RespBase<String>("ok");
        return r;
    }


    @RequestMapping(value = "getUserList", method = RequestMethod.GET)
    public RespBase<PageInfo> getUserList(
            Model model, String page, String name) {
        RespBase<PageInfo> r = null;
        PageHelper.startPage(Integer.valueOf(page), 10);
        Map param = new HashMap();
        param.put("name", name);
        List<Users> list = usersDao.select("getUserList",param,"readonly");
        PageInfo<Users> data = new PageInfo(list);
        /*Pages<Users> pages = new Pages<Users>();
        pages.setItems(data.getList());
        pages.setTotal(Integer.valueOf((int) data.getTotal()));
        pages.setIndex(Integer.valueOf(page));
        pages.setPageCount(data.getPages());*/
        /*JSONObject result = new JSONObject();
        result.put("users",data.getList());
        result.put("page",data.getPages());
        result.put("total",data.getTotal());
        String jsonStr = JSONObject.toJSONString(result);*/
        r = new RespBase<PageInfo>(data);
        return r;
    }

    @RequestMapping(value = "batchRemoveUser", method = RequestMethod.GET)
    public RespBase<String> batchRemoveUser(
            Model model, String page, String ids) {
        RespBase<String> r = null;
        //List<Users> list = usersDao.getUserList(param);
        usersDao.updateByObject("deleteById",ids);
        r = new RespBase<String>("ok");
        return r;
    }

    @RequestMapping(value = "removeUser", method = RequestMethod.GET)
    public RespBase<String> removeUser(
            Model model, String page, String id) {
        RespBase<String> r = null;
        //List<Users> list = usersDao.getUserList(param);
        //usersDao.deleteById(id);
        usersDao.updateByObject("deleteById",id);
        r = new RespBase<String>("ok");
        return r;
    }

    @RequestMapping(value = "editUser", method = RequestMethod.GET)
    public RespBase<String> editUser(
            Model model, String id, String name, String age, String sex, String birth, String addr) {
        RespBase<String> r = null;
        Users users =  usersDao.selectByPrimaryKey(Integer.valueOf(id));
        if(users!=null){
            users.setName(name);
            users.setAddr(addr);
            users.setAge(age);
            users.setSex(sex);
            users.setBirth(birth);
            users.setUpdateTime(new Date());
            usersDao.updateByPrimaryKeySelective(users);
        }
        r = new RespBase<String>("ok");
        return r;
    }



}
