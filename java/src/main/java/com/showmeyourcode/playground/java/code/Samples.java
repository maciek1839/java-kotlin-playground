package com.showmeyourcode.playground.java.code;

import com.showmeyourcode.playground.kotlin.common.Descriptions;

import static com.showmeyourcode.playground.java.Application.LOGGER;

public class Samples {

    private Samples(){}

    public static void main(){
        LOGGER.info(Descriptions.header(Descriptions.CODE_SAMPLES));
        Arrays.main();
        Lists.main();
        Classes.main();
        Functions.main();
    }
}
