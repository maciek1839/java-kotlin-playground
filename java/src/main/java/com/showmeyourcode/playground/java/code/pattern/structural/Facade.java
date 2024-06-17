package com.showmeyourcode.playground.java.code.pattern.structural;

import lombok.extern.slf4j.Slf4j;

// The Facade pattern provides a simplified interface to a complex subsystem.
public class Facade {

    private Facade() {
    }

    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();
        computer.start();
    }
}

// Complex subsystem
@Slf4j
class CPU {
    public void start() {
        log.info("CPU started");
    }

    public void execute() {
        log.info("CPU executing");
    }

    public void load() {
        log.info("CPU loading");
    }

    public void free() {
        log.info("CPU freeing");
    }
}

@Slf4j
class Memory {
    public void load(long position, byte[] data) {
        log.info("Memory loaded - position: {} data: {}", position, data);
    }
}

@Slf4j
class HardDrive {
    public byte[] read(long lba, int size) {
        log.info("HardDrive reading, lba: {} size: {}", lba, size);
        return new byte[size];
    }
}

// Facade
@Slf4j
class ComputerFacade {
    private final CPU cpu;
    private final Memory memory;
    private final HardDrive hardDrive;

    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }

    public void start() {
        cpu.start();
        cpu.load();
        memory.load(0, hardDrive.read(0, 256));
        cpu.execute();
    }
}
