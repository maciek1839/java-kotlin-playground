package com.showmeyourcode.playground.java.effectivejava._2;


// Use of the relational operators < and > in compareTo methods is verbose and error-prone and no longer recommended.
// If a class has multiple significant fields, the order in which you compare them is critical.
// Start with the most significant field and work your way down.

import java.util.Comparator;

public class ComparableObject implements Comparable<ComparableObject>{
    private static final Comparator<ComparableObject> COMPARATOR =
            Comparator.comparingInt((ComparableObject p)->p.a)
                    .thenComparingInt(p -> p.b)
                    .thenComparing(p -> p.o);

    public ComparableObject(int a, int b, ComparableObject o) {
        this.a = a;
        this.b = b;
        this.o = o;
    }

    private int a;
    private int b;
    private ComparableObject o;

    @Override
    public int compareTo(ComparableObject other){
        return COMPARATOR.compare(this, other);
    }
}
