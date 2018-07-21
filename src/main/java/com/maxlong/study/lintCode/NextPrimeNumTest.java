package com.maxlong.study.lintCode;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2018/7/17 17:54
 * 类说明:
 */
public class NextPrimeNumTest {

    public static int i = 0;

    public static void main(String[] args) {

        int kk = FindNextPrime(977);
        System.out.println(kk);
    }

    public static int FindNextPrime(int i) {

        if(i<2){
            i=1;
        }
        int result = 0;

        while (result==0){
            boolean isprime = true;
            i++;
            int len = i/2;
            for(int m=2; m<=len; m++){
                if(i%m==0){
                    isprime = false;
                    break;
                }
            }

            if(isprime){
                result = i;
            }
        }

        return result;

    }

}
