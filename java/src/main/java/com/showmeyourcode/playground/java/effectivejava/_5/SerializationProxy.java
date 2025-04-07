package com.showmeyourcode.playground.java.effectivejava._5;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

// Making classes implement Serializable increases the likelihood of bugs and security problems as it allows instances
// to be created using extralinguistic mechanism in place of ordinary constructors.
// There is a technique that greatly reduces the risks called serialisation proxy pattern.
class SerializationProxyExample{

    private String a;

    private Object writeReplace() {
        return new SerializationProxy(this);
    }

    private static class SerializationProxy implements Serializable {
        private final Date start;
        private final String a;

        SerializationProxy(SerializationProxyExample p) {
            this.start = Date.from(Instant.now());
            this.a = p.a;
        }

        private static final long serialVersionUID = 234098243823485285L; // Any number will do (Item  87)
    }
}
