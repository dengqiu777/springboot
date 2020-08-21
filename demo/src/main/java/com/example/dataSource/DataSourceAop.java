package com.example.dataSource;

/**
 * Author DQ
 * Date 2020/6/11
 **/

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAop {

    @Pointcut("!@annotation(com.example.dataSource.Master) " +
            "&& (execution(* com.example.service..*.select*(..)) " +
            "|| execution(* com.example.service..*.get*(..)))")
    public void readPointcut() {

    }

    @Pointcut("@annotation(com.example.dataSource.Master) " +
            "|| execution(* com.example.service..*.insert*(..)) " +
            "|| execution(* com.example.service..*.add*(..)) " +
            "|| execution(* com.example.service..*.update*(..)) " +
            "|| execution(* com.example.service..*.edit*(..)) " +
            "|| execution(* com.example.service..*.delete*(..)) " +
            "|| execution(* com.example.service..*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }


    /**
     * 另一种写法：if...else...  判断哪些需要读从数据库，其余的走主数据库
     */
//    @Before("execution(* com.cjs.example.service.impl.*.*(..))")
//    public void before(JoinPoint jp) {
//        String methodName = jp.getSignature().getName();
//
//        if (StringUtils.startsWithAny(methodName, "get", "select", "find")) {
//            DBContextHolder.slave();
//        }else {
//            DBContextHolder.master();
//        }
//    }
}
