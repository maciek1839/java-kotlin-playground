package com.showmeyourcode.playground.java.overview;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
public class Precision {

    public static void main(String[] args){
        double a=0.7;
        double b = 0.9;
        double x = a +0.1;
        double y = b-0.1;
        log.info("a= {} b={} x={} y={}",a,b,x, y);

        BigDecimal bigDecimal = BigDecimal.valueOf(x).setScale(2, RoundingMode.HALF_UP);
        log.info("BigDecimal(x): {}", bigDecimal);
    }
}
