package com.showmeyourcode.playground.java.overview.concurrency;

import java.util.concurrent.Exchanger;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ConcurrencyMechanismsOther {

    class ExchangerMultipleTimesExample {
        public static void main(String[] args) {
            Exchanger<String> exchanger = new Exchanger<>();

            Thread worker1 = new Thread(() -> {
                try {
                    for (int i = 1; i <= 3; i++) {
                        String data = "Data from Worker1 - " + i;
                        System.out.println("Worker1 sending: " + data);
                        String received = exchanger.exchange(data);
                        System.out.println("Worker1 received: " + received);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });

            Thread worker2 = new Thread(() -> {
                try {
                    for (int i = 1; i <= 3; i++) {
                        String data = "Data from Worker2 - " + i;
                        System.out.println("Worker2 sending: " + data);
                        String received = exchanger.exchange(data);
                        System.out.println("Worker2 received: " + received);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });

            worker1.start();
            worker2.start();
        }
    }

    // ABA problem solved
    class AtomicStampedReferenceExample {
        public static void main(String[] args) {
            AtomicStampedReference<String> atomicStampedRef = new AtomicStampedReference<>("Initial", 1);

            int stamp = atomicStampedRef.getStamp();
            String oldValue = atomicStampedRef.getReference();

            System.out.println("Current Value: " + oldValue + ", Stamp: " + stamp);

            boolean updated = atomicStampedRef.compareAndSet(oldValue, "Updated", stamp, stamp + 1);

            if (updated) {
                System.out.println("Update successful: " + atomicStampedRef.getReference() + ", New Stamp: " + atomicStampedRef.getStamp());
            } else {
                System.out.println("Update failed");
            }
        }
    }

    //Similar to AtomicStampedReference but more efficient when just a boolean marker is needed.
    class AtomicMarkableReferenceExample {
        public static void main(String[] args) {
            AtomicMarkableReference<String> markableRef = new AtomicMarkableReference<>("Initial", false);

            boolean[] markHolder = new boolean[1];
            String oldValue = markableRef.get(markHolder);
            boolean oldMark = markHolder[0];

            System.out.println("Current Value: " + oldValue + ", Mark: " + oldMark);

            boolean updated = markableRef.compareAndSet(oldValue, "Updated", oldMark, true);

            if (updated) {
                System.out.println("Update successful: " + markableRef.getReference() + ", New Mark: " + markableRef.isMarked());
            } else {
                System.out.println("Update failed");
            }
        }
    }

    class AtomicReferenceExample {

        static class SharedData {
            private String value;

            public SharedData(String value) {
                this.value = value;
            }

            @Override
            public String toString() {
                return value;
            }
        }

        public static void main(String[] args) {
            AtomicReference<SharedData> atomicRef = new AtomicReference<>(new SharedData("Initial"));

            // CAS (Compare-And-Swap) operation
            SharedData oldData = atomicRef.get();
            SharedData newData = new SharedData("Updated");

            if (atomicRef.compareAndSet(oldData, newData)) {
                System.out.println("Updated successfully: " + atomicRef.get());
            } else {
                System.out.println("Update failed");
            }
        }
    }
}
