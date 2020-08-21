package com.example.contoller;

import com.example.entity.*;
import com.example.service.UserService;
import com.example.service.dao.UsersDao;
import com.example.util.CommonUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Author DQ
 * Date 2020/6/17
 **/
@RestController
@RequestMapping("/index")
@CrossOrigin
public class IndexContoller {
    private final UsersDao usersDao;

    private final UserService userService;

    public IndexContoller(UsersDao usersDao, UserService userService) {
        this.usersDao = usersDao;
        this.userService = userService;
    }
    @RequestMapping(value = "getIndexInfo", method = RequestMethod.GET)
    public RespBase<IndexInfo> getIndexInfo(
            Model model, @RequestHeader("X-Token") String token) {
        RespBase<IndexInfo> r = null;
        List<Users> list = new ArrayList();
        IndexInfo indexInfo = new IndexInfo();
        Map map = new HashMap(16);
        Date startTime = new Date();
        Date endTime = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(startTime);
        c.add(Calendar.DATE, -7);
        startTime = c.getTime();
        map.put("endTime", CommonUtil.formatTimeYYYYMMDD(endTime));
        map.put("startTime", CommonUtil.formatTimeYYYYMMDD(startTime));
        try {
            VueUsers users = userService.getUserByToken(token);
            if (users == null) {
                throw new ServiceException(EnumServiceMessage.TOKEN_NOT_EXIST);
            }
            indexInfo.setTodayActive(10);
            indexInfo.setTodayAdd(2);
            indexInfo.setYesterdayActive(20);
            indexInfo.setYesterdayAdd(5);
            list = usersDao.select("getIndexInfo",map);
            indexInfo.setList(list);
        } catch (ServiceException e) {
            r = new RespBase<IndexInfo>(e);
        }
        r = new RespBase<IndexInfo>(indexInfo);
        return r;
    }
}
