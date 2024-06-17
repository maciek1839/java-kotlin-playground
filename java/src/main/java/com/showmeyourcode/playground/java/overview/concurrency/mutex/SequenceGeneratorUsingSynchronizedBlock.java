package com.showmeyourcode.playground.java.overview.concurrency.mutex;

public class SequenceGeneratorUsingSynchronizedBlock extends SequenceGenerator {

    private final Object mutex = new Object();

    @Override
    public int getNextSequence() throws InterruptedException {
        synchronized (mutex) {
            return super.getNextSequence();
        }
    }
}
