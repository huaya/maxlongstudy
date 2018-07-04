package com.maxlong.study;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2017/5/18 16:50
 *          类说明
 */
public class DateTimeApiTest {

    @Test
    public void test_call1(){
        LocalDate taday = LocalDate.now();
        System.out.println("Current Date=" + taday);

        LocalDate first_1989 = LocalDate.of(1989, Month.APRIL,11);
        System.out.println("my_birthday = " + first_1989);

        LocalDate cn_curday = LocalDate.now(ZoneId.of("Asia/Shanghai"));
        System.out.println("shanghai_now=" + cn_curday);

        LocalDate dateFromBase = LocalDate.ofEpochDay(365);
        System.out.println("365th day from base date= "+dateFromBase);

        LocalDate hundredDay2014 = LocalDate.ofYearDay(2014, 100);
        System.out.println("100th day of 2014="+hundredDay2014);
    }

    @Test
    public void test_call2(){
        LocalTime time = LocalTime.now();
        System.out.println("Current Time="+time.format(DateTimeFormatter.ofPattern("HHmmss")));

        LocalTime specificTime = LocalTime.of(12,20,25,40);
        System.out.println("Specific Time of Day="+specificTime);

        //Current date in "Asia/Kolkata", you can get it from ZoneId javadoc
        LocalTime timeKolkata = LocalTime.now(ZoneId.of("Asia/Kolkata"));
        System.out.println("Current Time in IST="+timeKolkata);

        //Getting date from the base date i.e 01/01/1970
        LocalTime specificSecondTime = LocalTime.ofSecondOfDay(10000);
        System.out.println("10000th second time= "+specificSecondTime);

    }

    @Test
    public void test_call4(){
        LocalTime time = LocalTime.now();
        System.out.println("Current Time=" + time);

        LocalTime beginTime = LocalTime.of(11,0,0,0);
        System.out.println("beginTime of Day=" + beginTime);

        LocalTime endTime = LocalTime.of(22,0,0,0);
        System.out.println("endTime of Day=" + endTime);

        System.out.println(time.isBefore(beginTime) || time.isAfter(endTime));
    }

}
