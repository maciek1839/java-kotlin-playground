package com.showmeyourcode.playground.java.effectivejava;

import com.showmeyourcode.playground.java.effectivejava._1.SingletonEnum;
import com.showmeyourcode.playground.java.effectivejava._1.SingletonStaticFactory;
import com.showmeyourcode.playground.java.effectivejava._1.StaticFactoryMethod;
import com.showmeyourcode.playground.java.effectivejava._1.TooManyArguments;
import com.showmeyourcode.playground.java.effectivejava._2.CloneExample;
import com.showmeyourcode.playground.java.effectivejava._2.ComparableObject;
import com.showmeyourcode.playground.java.effectivejava._2.EqualsAndHashCode;
import com.showmeyourcode.playground.java.effectivejava._3.EnumInstanceField;
import com.showmeyourcode.playground.java.effectivejava._3.EnumMapExample;
import com.showmeyourcode.playground.java.effectivejava._3.EnumsInsteadOfConstants;
import com.showmeyourcode.playground.java.effectivejava._3.MySet;
import com.showmeyourcode.playground.java.effectivejava._3.PhysicalConstants;
import com.showmeyourcode.playground.java.effectivejava._4.CheckParametersForValidity;
import com.showmeyourcode.playground.java.effectivejava._4.DocCommentsForAllExposedAPIElements;
import com.showmeyourcode.playground.java.effectivejava._5.LazyInitializationInstanceField;
import com.showmeyourcode.playground.java.effectivejava._5.LazyInitializationStaticField;

public class EffectiveJavaOverview {

    public static void main(String[] args) {
        // 1
        SingletonEnum.INSTANCE.doSomething();
        SingletonStaticFactory.getInstance().doSomething();
        StaticFactoryMethod.valueOf(1).doSomething();
        new TooManyArguments.Builder().setA("A").setB("B").build();

        // 2
        CloneExample.newInstance(new CloneExample("a","b", new CloneExample("a2", "b2", null)));
        new ComparableObject(1,2,null).compareTo(
                new ComparableObject(1,2,new ComparableObject(2, 2, null))
        );

        var a1 = new EqualsAndHashCode("A", "B", null);
        a1.equals(new EqualsAndHashCode("A", "B", null));
        a1.hashCode();
        a1.toString();

        // 3
        EnumInstanceField.Ensemble.DUET.numberOfMusicians();
        EnumMapExample.main(new String[0]);

        EnumsInsteadOfConstants.Operation.PLUS.apply(2,3);
        EnumsInsteadOfConstants.Operation2.PLUS.apply(2,3);
        EnumsInsteadOfConstants.Operation3.PLUS.apply(2,3);

        MySet.main(new String[0]);

        var c1 = PhysicalConstants.AVOGADROS_NUMBER;
        var c2 = PhysicalConstants.BOLTZMANN_CONST;
        var c3 = PhysicalConstants.ELECTRON_MASS;

        // 4
        new CheckParametersForValidity().manipulateString("String");
        new DocCommentsForAllExposedAPIElements();

        // 5
        LazyInitializationInstanceField instance = new LazyInitializationInstanceField();
        // Access the field for the first time - it will be initialized
        instance.getField().doSomething();
        // Access it again - no reinitialization
        instance.getField().doSomething();

        System.out.println("Calling getField() from external class...");
        LazyInitializationStaticField.getField().doSomething();

        // This call will NOT trigger reinitialization
        System.out.println("Calling getField() again...");
        LazyInitializationStaticField.getField().doSomething();
    }
}
