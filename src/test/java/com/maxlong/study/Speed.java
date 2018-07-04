package com.maxlong.study;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 作者: maxlong E-mail: hu5624548@163.com
 * @version 创建时间：2018/3/28 22:27
 *          类说明:
 */
public class Speed {

    public static void main(String[] args) {
        int speeda = 40;
        int speedb = 60;
        int speedg = 400;
        BigDecimal s = new BigDecimal(100);

        BigDecimal st = new BigDecimal(100);
        BigDecimal totalt = new BigDecimal(0);
        boolean a = false;
        int speedTemp;
        while(st.compareTo(BigDecimal.ZERO)>0){
            speedTemp = a?speeda:speedb;

            totalt = totalt.add(st.divide(new BigDecimal(speedTemp + speedg),10, BigDecimal.ROUND_HALF_EVEN));
            st  = s.subtract(totalt.multiply(new BigDecimal(speeda + speedb)));
            System.out.println("totalt : " + totalt + ",st: " + st);
            a = !a;
        }
        System.out.println(totalt);
    }
}
