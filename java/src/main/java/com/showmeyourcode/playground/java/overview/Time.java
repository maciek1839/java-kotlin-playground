package com.showmeyourcode.playground.java.overview;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Time {

    public static void main(String[] args) {
        simpleSystemTimeMeasureWhenMethodStarAndEnd();
        javaInstant();
    }

    private static void javaInstant() {
        Instant instantParsing = Instant.parse("2018-12-30T19:34:50.63Z");
        log.info("Parsing: {}", instantParsing);
        Instant start = Instant.now();
        log.info("Instant now: {}", start);

        long diff = start.toEpochMilli() - instantParsing.toEpochMilli();
        long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(diff);
        long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diff);
        long diffInHours = TimeUnit.MILLISECONDS.toHours(diff);
        long diffInDays = TimeUnit.MILLISECONDS.toDays(diff);

        long chronoSeconds = ChronoUnit.SECONDS.between(instantParsing, start);
        long chronoYears = Math.abs(
                ChronoUnit.YEARS.between(
                        start.atZone(ZoneId.systemDefault()),
                        instantParsing.atZone(ZoneId.systemDefault())
                )
        );

        log.info(
                String.format("Difference (TimeUnit): seconds - %d, minutes - %d, hours - %d, days - %d", diffInSeconds, diffInMinutes, diffInHours, diffInDays)
        );

        log.info(
                String.format("Difference (ChronoUnit): seconds - %d, years: %d", chronoSeconds, chronoYears)
        );


        Instant exampleTime = Instant.parse("2018-12-30T19:34:50.63Z");
        Instant exampleTime3SecondsLater = Instant.parse("2018-12-30T19:37:50.63Z");
        long diffExamples = ChronoUnit.SECONDS.between(exampleTime3SecondsLater, exampleTime);
        log.info("Count difference 3 seconds: {}", diffExamples);
    }

    private static void simpleSystemTimeMeasureWhenMethodStarAndEnd() {
        long start = System.nanoTime();

        // do stuff

        long end = System.nanoTime();

        log.info("Done! {}", end - start);
    }
}
