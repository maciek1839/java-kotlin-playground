package com.showmeyourcode.playground.java.effectivejava._3;

// Constant interface anti-pattern (only constants) are a poor use of interfaces.
// Implementing a constant interface causes this implementation detail to leak into the class's exported API.
public class PhysicalConstants {
    private PhysicalConstants() { }  // Prevents instantiation

    public static final double AVOGADROS_NUMBER = 6.022_140_857e23;
    public static final double BOLTZMANN_CONST  = 1.380_648_52e-23;
    public static final double ELECTRON_MASS    = 9.109_383_56e-31;
}
