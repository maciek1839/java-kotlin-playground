package com.showmeyourcode.playground.java.code.pattern.behavioral;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Observer {

    private Observer() {
    }

    public static void main(String[] args){
        WeatherData weatherData = new WeatherData();
        // If no observers are present, won't do much.
        // For code coverage purposes, all methods are invoked in the main method.
        weatherData.notifyObservers();

        // each of observers automatically subscribes to a subject
        new CurrentConditionsDisplay(weatherData);
        new StatisticsDisplay(weatherData);
        new ForecastDisplay(weatherData);

        ObserverPattern anonymousObserver = (temperature, humidity, pressure) -> log.info("I am an anonymous class observer!");
        ObserverPattern anonymousObserver2 = (temperature, humidity, pressure) -> log.info("I am an anonymous class observer too!");
        weatherData.registerObserver(anonymousObserver);
        // It won't remove the 2nd observer as equals is not overwritten so the default behaviour applies.
        // The log statement for the 2nd was updated to remove SonarLint complains.
        weatherData.removeObserver(anonymousObserver2);

        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}

// Step 1: Define the Subject interface
interface Subject {
    void registerObserver(ObserverPattern observer);
    void removeObserver(ObserverPattern observer);
    void notifyObservers();
}

// Step 2: Define the Observer interface

interface ObserverPattern {
    void update(float temperature, float humidity, float pressure);
}

// Step 3: Implement the ConcreteSubject

class WeatherData implements Subject {
    private final List<ObserverPattern> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(ObserverPattern observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ObserverPattern observer) {
        int index = observers.indexOf(observer);
        if (index >= 0) {
            observers.remove(index);
        }
    }

    @Override
    public void notifyObservers() {
        for (ObserverPattern observer : observers) {
            observer.update(temperature, humidity, pressure);
        }
    }

    public void measurementsChanged() {
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}

// Step 4: Implement ConcreteObservers
@Slf4j
class CurrentConditionsDisplay implements ObserverPattern {
    private float temperature;
    private float humidity;

    public CurrentConditionsDisplay(Subject weatherData) {
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }

    public void display() {
        log.info("[Current] Current conditions: {}F degrees and {}% humidity",  temperature, humidity);
    }
}

@Slf4j
class StatisticsDisplay implements ObserverPattern {
    private float maxTemp = 0.0f;
    private float minTemp = 200;
    private float tempSum = 0.0f;
    private int numReadings;

    public StatisticsDisplay(Subject weatherData) {
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        tempSum += temperature;
        numReadings++;

        if (temperature > maxTemp) {
            maxTemp = temperature;
        }

        if (temperature < minTemp) {
            minTemp = temperature;
        }

        display();
    }

    public void display() {
        log.info("[Statistics] Avg/Max/Min temperature = {} / {} / {}", (tempSum / numReadings), maxTemp, minTemp);
    }
}

@Slf4j
class ForecastDisplay implements ObserverPattern {
    private float currentPressure = 29.92f;
    private float lastPressure;

    public ForecastDisplay(Subject weatherData) {
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        lastPressure = currentPressure;
        currentPressure = pressure;

        display();
    }

    public void display() {
        log.info("Forecast: ");
        if (currentPressure > lastPressure) {
            log.info("Improving weather on the way!");
        } else if (currentPressure == lastPressure) {
            log.info("More of the same");
        } else if (currentPressure < lastPressure) {
            log.info("Watch out for cooler, rainy weather");
        }
    }
}
