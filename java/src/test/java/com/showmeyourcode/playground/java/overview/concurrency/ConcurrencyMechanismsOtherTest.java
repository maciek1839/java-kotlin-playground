package com.showmeyourcode.playground.java.overview.concurrency;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConcurrencyMechanismsOtherTest {

    @Test
    void shouldRunMainMethodsWithoutError(){
        Assertions.assertDoesNotThrow(()->{
            ConcurrencyMechanismsOther.AtomicStampedReferenceExample.main(new String[0]);
            ConcurrencyMechanismsOther.ExchangerMultipleTimesExample.main(new String[0]);
            ConcurrencyMechanismsOther.AtomicMarkableReferenceExample.main(new String[0]);
            ConcurrencyMechanismsOther.AtomicReferenceExample.main(new String[0]);
        });
    }
}
