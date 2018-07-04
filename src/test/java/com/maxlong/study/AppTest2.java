package com.maxlong.study;

import com.maxlong.study.datacollection.CollectionDataBase;
import com.maxlong.study.vo.TransientVo;
import com.maxlong.study.vo.User;
import com.maxlong.study.vo.VolatileTest;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.HibernateValidatorFactory;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.*;
import java.util.*;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2017/5/24 13:28
 *          类说明
 */
@Slf4j
public class AppTest2 {

    @Test
    public void test_call(){

        TransientVo transientVo = new TransientVo();
        transientVo.setName("123456");
        transientVo.setSchool("qwertyy");
        transientVo.setRoom("1402");

        log.info(transientVo.toString());
        try {
            FileOutputStream fos = new FileOutputStream("TransientVo");

            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(transientVo);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test_call2(){

        try {

            FileInputStream fis = new FileInputStream("TransientVo");

            ObjectInputStream ois = new ObjectInputStream(fis);

            TransientVo transientVo = (TransientVo) ois.readObject();

            log.info(transientVo.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_call3(){

        while (true){
            final VolatileTest volatileTest = new VolatileTest();
            new Thread(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                volatileTest.change();
            }).start();

            new Thread(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                volatileTest.print();
            }).start();

        }
    }

    @Test
    public void test_call4(){
        User user = new User();
        user.setName("1234566");
        HibernateValidatorFactory factroy = (HibernateValidatorFactory) Validation.buildDefaultValidatorFactory();
        factroy.usingContext().failFast(true);
        Validator validator = factroy.getValidator();
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        Map<String,String> map  = new HashMap<>();
        for(ConstraintViolation constraintViolation : constraintViolations){
            map.put(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage());
        }

        log.info("{}",map);
    }

    @Test
    public void test_call5(){

        List<String> lists = new ArrayList<>();
        lists.add("a");
        lists.add("b");
        lists.add("c");
        lists.add("d");
        lists.add("e");


        for (int i =0;i<lists.size();i++){
            System.out.println(i +  "--" + lists.get(i));
        }

        System.out.println("**********************************");

        lists.remove(3);

        for (int i =0;i<lists.size();i++){
            System.out.println(i +  "--" + lists.get(i));
        }

    }

    @Test
    public void test_call6(){
        for (int i =0;i<30000;i++){
            log.info("执行中。。。，序号：{}", i);
        }
    }

    @Test
    public void test_call7(){
        Vector<String> vector = new Vector<>();
        vector.add("fdfdfd");
        vector.add("qazwsx");
        log.info("结果1：{}",vector);
        System.out.println(vector.remove(0));
        log.info("结果2：{}",vector);
    }

    @Test
    public void test_call8(){
        List<Map<String,Object>> result = CollectionDataBase.getInstance().selectAllData();
        log.info("结果1：{}", result);

        Map<String,Object> map = new HashMap<>();
        map.put("name","malxong");
        CollectionDataBase.getInstance().insertData(map);
        result =  CollectionDataBase.getInstance().selectAllData();
        log.info("结果2：{}", result);

        Map<String,Object> map2 = new HashMap<>();
        map2.put("name","fangbb");
        CollectionDataBase.getInstance().insertData(map2);
        result =  CollectionDataBase.getInstance().selectAllData();
        log.info("结果3：{}", result);

        boolean i = CollectionDataBase.getInstance().deleteDataByMap(result.get(0));
        log.info("删除结果：{}",i);
        result =  CollectionDataBase.getInstance().selectAllData();
        log.info("结果4：{}", result);

        Map<String,Object> map3 = new HashMap<>();
        map3.put("name","huyaojun");
        map3.put("index",0);
        int j = CollectionDataBase.getInstance().updateDataByMap(map3);
        log.info("更新结果：{}",j);
        result =  CollectionDataBase.getInstance().selectAllData();
        log.info("结果5：{}", result);
    }

    @Test
    public void test_call9(){
        List<Map<String,Object>> result = CollectionDataBase.getInstance().selectAllData();
        log.info("结果1：{}", result);

        Map<String,Object> map = new HashMap<>();
        map.put("name","malxong");
        log.info("inset结果：{}",CollectionDataBase.getInstance().insertData(map));
        result =  CollectionDataBase.getInstance().selectAllData();
        log.info("结果2：{}", result);

        Map<String,Object> map2 = new HashMap<>();
        map2.put("name","fangbb");
        log.info("inset结果：{}",CollectionDataBase.getInstance().insertData(map2));
        result =  CollectionDataBase.getInstance().selectAllData();
        log.info("结果3：{}", result);


        Map<String,Object> map3 = new HashMap<>();
        map3.put("name","huanghh");
        map3.put("index",0);
        log.info("inset结果：{}",CollectionDataBase.getInstance().insertData(map3));
        result =  CollectionDataBase.getInstance().selectAllData();
        log.info("结果4：{}", result);

        Map<String,Object> map4 = new HashMap<>();
        map4.put("name","huanghh");
        map4.put("index",2);
        log.info("inset结果：{}",CollectionDataBase.getInstance().insertData(map4));
        result =  CollectionDataBase.getInstance().selectAllData();
        log.info("结果5：{}", result);
    }

}
