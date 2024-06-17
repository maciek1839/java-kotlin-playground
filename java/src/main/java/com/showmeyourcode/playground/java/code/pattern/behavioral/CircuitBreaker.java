package com.showmeyourcode.playground.java.code.pattern.behavioral;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class CircuitBreaker {

    private CircuitBreaker() {
    }

    public static void main(String[] args){
        CircuitBreakerPattern circuitBreaker = new CircuitBreakerPattern(3, Duration.ofSeconds(10));

        for (int i = 0; i < 10; i++) {
            try {
                if (circuitBreaker.allowRequest()) {
                    callExternalService(circuitBreaker);
                } else {
                    log.warn("Request blocked by Circuit Breaker");
                }
            } catch (Exception e) {
                circuitBreaker.recordFailure();
                log.error("Service call failed: {}", e.getMessage());
            }

            try {
                Thread.sleep(1000); // Simulate time between requests
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @SuppressWarnings("java:S112")
    private static void callExternalService(CircuitBreakerPattern circuitBreaker) throws Exception {
        // Simulate external service call
        boolean serviceSuccess = Math.random() > 0.7;
        if (serviceSuccess) {
            circuitBreaker.recordSuccess();
            log.info("Service call succeeded");
        } else {
            throw new Exception("Service call failed");
        }
    }
}

enum CircuitBreakerState {
    CLOSED, OPEN, HALF_OPEN
}

class CircuitBreakerPattern {
    private final int failureThreshold;
    private final Duration timeout;
    private final AtomicInteger failureCount;
    private CircuitBreakerState state;
    private LocalDateTime lastFailureTime;

    public CircuitBreakerPattern(int failureThreshold, Duration timeout) {
        this.failureThreshold = failureThreshold;
        this.timeout = timeout;
        this.state = CircuitBreakerState.CLOSED;
        this.failureCount = new AtomicInteger(0);
    }

    public synchronized boolean allowRequest() {
        return switch (state) {
            case OPEN -> {
                if (Duration.between(lastFailureTime, LocalDateTime.now()).compareTo(timeout) > 0) {
                    state = CircuitBreakerState.HALF_OPEN;
                    yield true;
                }
                yield false;
            }
            case HALF_OPEN -> true;
            default -> true;
        };
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
}
