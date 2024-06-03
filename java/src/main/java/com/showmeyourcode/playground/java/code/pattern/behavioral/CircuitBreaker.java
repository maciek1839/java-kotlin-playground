package com.showmeyourcode.playground.java.code.pattern.behavioral;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

enum CircuitBreakerState {
    CLOSED, OPEN, HALF_OPEN
}

class CircuitBreakerPattern {
    private final int failureThreshold;
    private final Duration timeout;
    private CircuitBreakerState state;
    private AtomicInteger failureCount;
    private LocalDateTime lastFailureTime;

    public CircuitBreakerPattern(int failureThreshold, Duration timeout) {
        this.failureThreshold = failureThreshold;
        this.timeout = timeout;
        this.state = CircuitBreakerState.CLOSED;
        this.failureCount = new AtomicInteger(0);
    }

    public synchronized boolean allowRequest() {
        switch (state) {
            case OPEN:
                if (Duration.between(lastFailureTime, LocalDateTime.now()).compareTo(timeout) > 0) {
                    state = CircuitBreakerState.HALF_OPEN;
                    return true;
                }
                return false;
            case HALF_OPEN:
                return true;
            default:
                return true;
        }
    }

    public synchronized void recordSuccess() {
        state = CircuitBreakerState.CLOSED;
        failureCount.set(0);
    }

    public synchronized void recordFailure() {
        int failures = failureCount.incrementAndGet();
        lastFailureTime = LocalDateTime.now();
        if (failures >= failureThreshold) {
            state = CircuitBreakerState.OPEN;
        }
    }

    public CircuitBreakerState getState() {
        return state;
    }
}

public class CircuitBreaker {

    public static void main(){
        CircuitBreakerPattern circuitBreaker = new CircuitBreakerPattern(3, Duration.ofSeconds(10));

        for (int i = 0; i < 10; i++) {
            try {
                if (circuitBreaker.allowRequest()) {
                    callExternalService(circuitBreaker);
                } else {
                    System.out.println("Request blocked by Circuit Breaker");
                }
            } catch (Exception e) {
                circuitBreaker.recordFailure();
                System.out.println("Service call failed: " + e.getMessage());
            }

            try {
                Thread.sleep(1000); // Simulate time between requests
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static void callExternalService(CircuitBreakerPattern circuitBreaker) throws Exception {
        // Simulate external service call
        boolean serviceSuccess = Math.random() > 0.7;
        if (serviceSuccess) {
            circuitBreaker.recordSuccess();
            System.out.println("Service call succeeded");
        } else {
            throw new Exception("Service call failed");
        }
    }
}
