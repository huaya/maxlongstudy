package com.maxlong.study;


import com.maxlong.study.test.Student;
import com.maxlong.study.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.MDC;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashSet;
import java.util.Set;

/**
 * Unit test for simple App.
 */
@Slf4j
public class AppTest {

    private int count = 0;

    @Test
    public void test_call(){
        int j=0;
        int k=1;
        for(int i=3;i<1000000000;i++){
            j=2;
            while (j<i){
                if(i%j==0) break;
                j++;
            }
            if(i==j){
                System.out.print(i+ ",");
                if(k%6==0) System.out.println();
                k++;
            }
        }
    }

    @Test
    public void test_call2(){
        User user = new User("1","2");
        System.out.println(user.toString());
    }

    @Test
    public void test_call4(){
        MDC.put("TRACE_ID", String.valueOf(Thread.currentThread().getId()));
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        context.start();
        Student student = context.getBean(Student.class);
        student.eat();
    }

    @Test
    public void test_call5(){
        Object o1 = true? new Integer(1):new Double(2.0);
        Object o2;
        if(true){
            o2 = new Integer(1);
        }else {
            o2 = new Double(2.0);
        }
        System.out.println(o1);
        System.out.println(o2);
    }

    @Test
    public void test_call6(){
        int[] a ={6,5,4,3,2,0};
        int[] b ={8,7,5,3,2,1,0};

        Set<Integer> set1 = new HashSet<Integer>();
        String fff = "重复的元素：";

        int k = a.length+b.length;
        int cout = 0;
        for(int i =0;i<k;i++){
            if(i<a.length){
                set1.add(a[i]);
            }else {
              int temp = b[i-a.length];
              if(set1.contains(temp)){
                  fff += temp + ", ";
              }
            }
            cout++;
        }

        System.out.println(fff);
        System.out.println();
        System.out.println("循环次数：" + cout);
    }

    @Test
    public void test_call7(){
        int[] a ={6,5,4,3,2,0};
        int[] b ={8,7,5,3,2,1,0};

        int i =0;
        int j =0;
        int cout = 0;
        System.out.print("重复的元素：");
        while (i<a.length && j<b.length){
            if(a[i] > b[j]){
                i++;
            }else if(a[i] < b[j]){
                j++;
            }else if(a[i] == b[j]){
                System.out.print(a[i] + ", ");
                i++;
                j++;
            }
            cout ++;
        }
        System.out.println();
        System.out.println("循环次数：" + cout);
    }

    @Test
    public void test_call8(){
        int[] a ={1,2,3,4,5,6,7};

        for(int i=0;i<a.length;i++){
            if(a[i]==1){
                System.out.println("1");
            }else if(a[i]==2){
                System.out.println("2");
            }
        }

    }

    public void test_gc(long a, long b,long c){
        count++;
        test_gc(a,b,c);
    }

    @Test
    public void test_call9(){
        long a=0, b=0, c=0;
        try {
            test_gc(a,b,c);
        } catch (Throwable t) {
            log.info("deep of stack is {}" ,count);
            t.printStackTrace();
        }
    }

    @Test
    public void test_call10(){
        { long a = 0;}
        long b = 0;
    }

    @Test
    public void test_call11(){
        {
            byte[] b = new byte[6 * 1024 * 1024];
            b = null;
        }
        System.gc();
        System.out.printf("1111111111111");
    }

}
