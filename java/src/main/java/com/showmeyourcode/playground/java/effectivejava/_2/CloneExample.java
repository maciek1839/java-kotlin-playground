package com.showmeyourcode.playground.java.effectivejava._2;

// The Cloneable architecture is incompatible with normal use of final fields referring to mutable objects.
// Public clone methods should omit the throws clause, as methods that don't throw checked exceptions are easier to use.
// A better approach to object copying is to provide a copy constructor or copy factory.
public class CloneExample {
    private String a;
    private String b;
    private CloneExample object;

    public CloneExample(String a, String b, CloneExample object) {
        this.a = a;
        this.b = b;
        this.object = object;
    }

    // a copy constructor or copy factory.
    public static CloneExample newInstance(CloneExample object) {
        var o = object.object != null ? CloneExample.newInstance(object.object) : null;
        return new CloneExample(object.a, object.b, o);
    }
}
