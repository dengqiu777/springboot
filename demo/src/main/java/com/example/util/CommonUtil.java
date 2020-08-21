package com.example.util;

import com.github.pagehelper.util.StringUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author xiong.qiu
 * @create 2017-11-04 9:45
 **/
public class CommonUtil {
    private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);


    private static Pattern NUMBER_PATTERN = Pattern.compile("^[-\\+]?[\\d]*$");

    private static final Integer SECOND = 60;

    private static final Integer MINUTES = 3600;

    /**
     * 生成32位随机码
     *
     * @return
     */
    public static String randomToken() {
        Long random = System.currentTimeMillis();
        String token = EncryptUtil.md5(String.valueOf(random));
        return token;
    }

    /**
     * 生成32位随机订单号
     *
     * @return
     */
    public static String randomOrderNo() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "");
        return uuid;
    }

    /**
     * 生成6位数字随机码
     *
     * @return
     */
    public static String randomValidateCode() {
        Random r = new Random();
        StringBuilder s = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            int num = Math.abs(r.nextInt()) % 10;
            s.append(num);
        }

        return s.toString();
    }

    /**
     * 序列化对象
     *
     * @param o
     * @return
     */
    public static String serializeJSON(Object o) {
        try {
            return new ObjectMapper().writeValueAsString(o);
        } catch (IOException e) {
            e.printStackTrace();
            return "{}";
        }
    }

    /**
     * 反序列化复杂对象list
     *
     * @param clazz
     * @param json
     * @param <T>
     * @return
     */
    public static <T> List<T> deserializeJSONToList(Class<T> clazz, String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JavaType listType = mapper.getTypeFactory().constructParametricType(List.class, clazz);
            return mapper.readValue(json, listType);
        } catch (IOException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 反序列化对象
     *
     * @param clazz
     * @param json
     * @param <T>
     * @return
     */
    public static <T> T deserializeJSON(Class<T> clazz, String json) {
        try {
            return new ObjectMapper().readValue(json, clazz);
        } catch (IOException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 格式化日期
     *
     * @param date
     * @return
     */
    public static String formatTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     *
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s) {
        if (StringUtil.isEmpty(s)) {
            return s;
        }
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    public static String formatDate(Date date) {
        return formatDate(date, "yyyy-MM-dd");
    }

    public static String formatDate(Date date, String format) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat format1 = new SimpleDateFormat(format);
        return format1.format(date);
    }

    public static Date parseDate(String date) throws Exception {
        return parseDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date parseTime(String date) throws Exception {
        return parseDate(date, "yyyy-MM-dd");
    }

    public static Date parseDate(String date, String s) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(s);
        return format.parse(date);
    }

    /**
     * 格式化 yyyy-MM-dd
     * @param date
     * @return
     */
    public static String formatTimeYYYYMMDD(Date date) {
        return formatDate(date, "yyyy-MM-dd");
    }
   


    /**
     * 判断是否位数字
     *
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        return NUMBER_PATTERN.matcher(str).matches();
    }

    /**
     * 复制list，公用方法
     *
     * @param o     原始list
     * @param clazz 目标类型
     * @param <T>   目标类型
     * @param <O>   原始类型
     * @return
     */
    public <T, O> List<T> copyList(List<O> o, Class<T> clazz) {
        if (o == null || o.size() == 0) {
            return null;
        }
        List<T> t = new ArrayList<T>();
        try {
            for (O item : o) {
                T target = clazz.newInstance();
                BeanUtils.copyProperties(item, target);
                t.add(target);
            }
            return t;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return null;
    }

    public static String addDay(Integer datCount) {
        Calendar calendar1 = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        calendar1.add(Calendar.DATE, datCount * -1);
        String threeDaysAgo = sdf1.format(calendar1.getTime());
        return threeDaysAgo;
    }

    /**
     * 秒转分 时  天
     *
     * @param totalTime
     * @return
     */
    public static String time(String totalTime) {
        Integer runTime = Integer.valueOf(totalTime);
        DecimalFormat df = new DecimalFormat("0.00");
        if (runTime < CommonUtil.SECOND) {
            return runTime + "秒";
        }
        if (runTime < CommonUtil.MINUTES) {
            return df.format((float) runTime / 60) + "分";
        }
        return "";
    }

    /**
     * 毫秒转时间
     *
     * @param ms
     * @return
     */
    public static String formatTime(long ms) {

        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        String strDay = day < 10 ? "0" + day : "" + day; //天
        String strHour = hour < 10 ? "0" + hour : "" + hour;//小时
        String strMinute = minute < 10 ? "0" + minute : "" + minute;//分钟
        String strSecond = second < 10 ? "0" + second : "" + second;//秒
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;//毫秒
        if (!"00".equals(strDay)) {
            return strDay + " 天 " + strHour + " 小时 " + strMinute + " 分钟 " + strSecond + " 秒";
        }
        if (!"00".equals(strHour)) {
            return strHour + " 小时 " + strMinute + " 分钟 " + strSecond + " 秒";
        }
        if (!"00".equals(strMinute)) {
            return strMinute + " 分钟 " + strSecond + " 秒";
        }
        return strSecond + " 秒";
    }

    public static void main(String[] arg) {
        System.out.println(formatTime(90000000L));
    }
}
