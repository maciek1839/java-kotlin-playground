package com.showmeyourcode.playground.java.code.pattern.structural;

// Complex subsystem
class CPU {
    public void start() {
        System.out.println("CPU started");
    }

    public void execute() {
        System.out.println("CPU executing");
    }

    public void load() {
        System.out.println("CPU loading");
    }

    public void free() {
        System.out.println("CPU freeing");
    }
}

class Memory {
    public void load(long position, byte[] data) {
        System.out.println("Memory loaded");
    }
}

class HardDrive {
    public byte[] read(long lba, int size) {
        System.out.println("HardDrive reading");
        return new byte[size];
    }
}

// Facade
class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;

    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }

    public void start() {
        cpu.start();
        cpu.load();
        memory.load(0, hardDrive.read(0, 1024));
        cpu.execute();
    }
}

// The Facade pattern provides a simplified interface to a complex subsystem.
public class Facade {

    public static void main() {
        ComputerFacade computer = new ComputerFacade();
        computer.start();
    }
}
