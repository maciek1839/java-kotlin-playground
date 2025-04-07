package com.showmeyourcode.playground.java.effectivejava._4;

import java.util.Objects;

public class CheckParametersForValidity {

    public int manipulateString(String s){
        Objects.requireNonNull(s);

        return s.length();
    }
}
