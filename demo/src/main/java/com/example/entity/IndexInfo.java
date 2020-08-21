package com.example.entity;

import java.util.List;

/**
 * Author DQ
 * Date 2020/6/17
 **/
public class IndexInfo {
    private Integer  todayActive ;
    private Integer todayAdd ;
    private Integer yesterdayActive;
    private Integer yesterdayAdd;
    private List<Users> list;


    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Integer getTodayActive() {
        return todayActive;
    }

    public void setTodayActive(Integer todayActive) {
        this.todayActive = todayActive;
    }

    public Integer getTodayAdd() {
        return todayAdd;
    }

    public void setTodayAdd(Integer todayAdd) {
        this.todayAdd = todayAdd;
    }

    public Integer getYesterdayActive() {
        return yesterdayActive;
    }

    public void setYesterdayActive(Integer yesterdayActive) {
        this.yesterdayActive = yesterdayActive;
    }

    public Integer getYesterdayAdd() {
        return yesterdayAdd;
    }

    public void setYesterdayAdd(Integer yesterdayAdd) {
        this.yesterdayAdd = yesterdayAdd;
    }
}
