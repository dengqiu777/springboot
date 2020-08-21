package com.example.service.impl;



import com.example.dataSource.Master;
import com.example.entity.*;
import com.example.service.UserService;
import com.example.service.dao.UsersDao;
import com.example.service.dao.VueRoleDao;
import com.example.service.dao.VueUserTokenDao;
import com.example.service.dao.VueUsersDao;
import com.example.util.CalendarUtil;
import com.example.util.CommonUtil;
import com.example.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{


    private  final VueRoleDao vueRoleDao;

    private  final VueUsersDao vueUsersDao;

    private  final VueUserTokenDao vueUserTokenDao;

    private  final UsersDao usersDao;


    @Autowired
    public UserServiceImpl(VueRoleDao vueRoleDao, VueUsersDao vueUsersDao, VueUserTokenDao vueUserTokenDao,  UsersDao usersDao) {
        this.vueRoleDao = vueRoleDao;
        this.vueUsersDao = vueUsersDao;
        this.vueUserTokenDao = vueUserTokenDao;
        this.usersDao = usersDao;
    }


    @Override
    public VueUsers login(String userName, String passWord) throws ServiceException {
        Map map = new HashMap(16);
        map.put("login_name", userName);
        try {
            List<VueUsers> selectByName = vueUsersDao.selectBy("selectByName", map);
            if(selectByName.size()>0){
                VueUsers vueUsers = selectByName.get(0);
                String salt = vueUsers.getSalt();
                String pwd = EncryptUtil.md5(passWord + salt);
                if (!vueUsers.getPassword().equalsIgnoreCase(pwd)) {
                    throw new ServiceException(EnumServiceMessage.USER_ERROR_PASSWORD);
                }
                vueUsers.setLastTime(new Date());
                //vueUsersDao.updateByPrimaryKey(vueUsers);
                return vueUsers;
            }else{
                throw new ServiceException(EnumServiceMessage.USER_NOT_EXIST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Master
    @Override
    public Map getRoleResult(String token) {
        Map map = new HashMap(1);
        map.put("token", token);
        try {
            return (Map) vueRoleDao.selectsBy("selectUserRoleByToken", map).get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public VueUsers getUserByToken(String token) {
        Map map = new HashMap(1);
        map.put("token", token);
        map.put("expireTime",CommonUtil.formatTime(new Date()));
        List<VueUsers> list = null;
        try {
            list = vueUsersDao.selectBy("selectByToken", map);
            if (list != null && list.size() > 0) {
                return list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public VueUserToken generateToken(VueUsers vueUsers) {
        Map map = new HashMap(16);
        map.put("userId", String.valueOf(vueUsers.getId()));
        map.put("channel", vueUsers.getChannel());
        map.put("expireTime", CommonUtil.formatTime(new Date()));
        List<VueUserToken> list = null;
        try {
            list = vueUserTokenDao.selectBy("selectUserToken", map);
            if (list != null && list.size() > 0) {
                VueUserToken vo = list.get(0);
                vo.setExpireTime(CalendarUtil.nextMonthDay(new Date()));
                vo.setToken(CommonUtil.randomToken());
                vo.setUpdateTime(new Date());
                vueUserTokenDao.updateByPrimaryKeySelective(vo);
                return vo;
            } else {
                VueUserToken vueUserToken = new VueUserToken();
                vueUserToken.setUserId(vueUsers.getId().intValue());
                vueUserToken.setExpireTime(CalendarUtil.nextMonthDay(new Date()));
                vueUserToken.setToken(CommonUtil.randomToken());
                vueUserToken.setCreateTime(new Date());
                vueUserToken.setIsDelete(0);
                vueUserTokenDao.insertSelective(vueUserToken);
                return vueUserToken;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
