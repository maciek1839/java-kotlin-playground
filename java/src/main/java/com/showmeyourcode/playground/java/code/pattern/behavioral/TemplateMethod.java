package com.showmeyourcode.playground.java.code.pattern.behavioral;

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

class ConcreteAlgorithmA extends AbstractAlgorithm {
    @Override
    protected void stepOne() {
        System.out.println("ConcreteAlgorithmA: Step One");
    }

    @Override
    protected void stepTwo() {
        System.out.println("ConcreteAlgorithmA: Step Two");
    }

    @Override
    protected void stepThree() {
        System.out.println("ConcreteAlgorithmA: Step Three");
    }
}

class ConcreteAlgorithmB extends AbstractAlgorithm {
    @Override
    protected void stepOne() {
        System.out.println("ConcreteAlgorithmB: Step One");
    }

    @Override
    protected void stepTwo() {
        System.out.println("ConcreteAlgorithmB: Step Two");
    }

    @Override
    protected void stepThree() {
        System.out.println("ConcreteAlgorithmB: Step Three");
    }
}

public class TemplateMethod {

    public static void main(){
        AbstractAlgorithm algorithmA = new ConcreteAlgorithmA();
        AbstractAlgorithm algorithmB = new ConcreteAlgorithmB();

        System.out.println("Executing Algorithm A:");
        algorithmA.execute();

        System.out.println("\nExecuting Algorithm B:");
        algorithmB.execute();
    }
}
