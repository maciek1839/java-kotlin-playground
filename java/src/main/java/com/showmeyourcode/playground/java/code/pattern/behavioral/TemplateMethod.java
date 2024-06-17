package com.showmeyourcode.playground.java.code.pattern.behavioral;

import lombok.extern.slf4j.Slf4j;

abstract class AbstractAlgorithm {
    public void execute() {
        stepOne();
        stepTwo();
        stepThree();
    }

    protected abstract void stepOne();
    protected abstract void stepTwo();
    protected abstract void stepThree();
}

@Slf4j
class ConcreteAlgorithmA extends AbstractAlgorithm {
    @Override
    protected void stepOne() {
        log.info("ConcreteAlgorithmA: Step One");
    }

    @Override
    protected void stepTwo() {
        log.info("ConcreteAlgorithmA: Step Two");
    }

    @Override
    protected void stepThree() {
        log.info("ConcreteAlgorithmA: Step Three");
    }
}

@Slf4j
class ConcreteAlgorithmB extends AbstractAlgorithm {
    @Override
    protected void stepOne() {
        log.info("ConcreteAlgorithmB: Step One");
    }

    @Override
    protected void stepTwo() {
        log.info("ConcreteAlgorithmB: Step Two");
    }

    @Override
    protected void stepThree() {
        log.info("ConcreteAlgorithmB: Step Three");
    }
}

@Slf4j
public class TemplateMethod {

    private TemplateMethod() {
    }

    public static void main(String[] args){
        AbstractAlgorithm algorithmA = new ConcreteAlgorithmA();
        AbstractAlgorithm algorithmB = new ConcreteAlgorithmB();

        log.info("Executing Algorithm A:");
        algorithmA.execute();

        log.info("\nExecuting Algorithm B:");
        algorithmB.execute();
    }
}
