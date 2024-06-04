package com.showmeyourcode.playground.java.overview;

import java.math.BigDecimal;

public class Precision {

    public static void main(String[] args){
        double a=0.7;
        double b = 0.9;
        double x = a +0.1;
        double y = b-0.1;
        System.out.println("a="+a+" b="+b+" x="+x+" y="+y);

        BigDecimal bigDecimal = BigDecimal.valueOf(x).setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println("BigDecimal(x): "+bigDecimal);
    }
}
