package com.showmeyourcode.playground.java.overview.concurrency.mutex;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MutexTest {

    private record Pair<K, V>(K key, V value) {
        // intentionally empty
    }

    @Test
    void givenUnsafeSequenceGenerator_whenRaceCondition_thenUnexpectedBehavior() throws Exception {
        int count = 1000;
        var futures = getUniqueSequences(new SequenceGenerator(), count).value;
        assertEquals(count, futures.size());
    }

    @Test
    void givenSafeSequenceGeneratorSynchronized_whenRaceCondition_theExpectedBehavior() throws Exception {
        int count = 1000;
        var futures = getUniqueSequences(new SequenceGeneratorUsingSynchronizedBlock(), count).value;
        assertEquals(count, futures.size());
    }

    @Test
    void givenSafeSequenceGeneratorWithSemaphore_whenRaceCondition_theExpectedBehavior() throws Exception {
        int count = 1000;
        var futures = getUniqueSequences(new SequenceGeneratorUsingSemaphore(), count).value;
        assertEquals(count, futures.size());
    }

    // Note that SequenceGenerator is used which is not thread safe that's why the returned Set might not contain 1000 entries because of duplicates and race conditions.
    private Pair<Set<Integer>,List<Future<Integer>>> getUniqueSequences(SequenceGenerator generator, int count) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Set<Integer> uniqueSequences = new LinkedHashSet<>();
        List<Future<Integer>> futures = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            futures.add(executor.submit(generator::getNextSequence));
        }

        for (Future<Integer> future : futures) {
            uniqueSequences.add(future.get());
        }

        executor.close();
        // Reference: https://stackoverflow.com/questions/18425026/shutdown-and-awaittermination-which-first-call-have-any-difference
        try {
            if (!executor.awaitTermination(3500, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            throw e;
        }

        return new Pair<>(uniqueSequences, futures);
    }
}
