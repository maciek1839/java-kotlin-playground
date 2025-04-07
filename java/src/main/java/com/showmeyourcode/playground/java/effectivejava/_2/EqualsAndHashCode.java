package com.showmeyourcode.playground.java.effectivejava._2;

import java.util.Objects;

public class EqualsAndHashCode {
    private String a;
    private String b;
    private EqualsAndHashCode object;

    public EqualsAndHashCode(String a, String b, EqualsAndHashCode object) {
        this.a = a;
        this.b = b;
        this.object = object;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EqualsAndHashCode that = (EqualsAndHashCode) o;
        return Objects.equals(a, that.a) && Objects.equals(b, that.b) && Objects.equals(object, that.object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, object);
    }

    @Override
    public String toString() {
        return "EqualsAndHashCode{" +
                "a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", object=" + object +
                '}';
    }

}
