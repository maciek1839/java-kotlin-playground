package com.showmeyourcode.playground.java.code.pattern.behavioral;

import lombok.extern.slf4j.Slf4j;

// Command is behavioral design pattern that converts requests or simple operations into objects.
public class Command {

    private Command() {
    }

    public static void main(String[] args){
        Light light = new Light();

        CommandInterface turnOn = new TurnOnLightCommand(light);
        CommandInterface turnOff = new TurnOffLightCommand(light);

        RemoteControl remote = new RemoteControl();

        remote.setCommand(turnOn);
        remote.pressButton();

        remote.setCommand(turnOff);
        remote.pressButton();
    }
}

// Command interface
interface CommandInterface {
    void execute();
}

// Receiver
@Slf4j
class Light {
    public void turnOn() {
        log.info("Light is on");
    }

    public void turnOff() {
        log.info("Light is off");
    }
}

// Concrete Command 1
class TurnOnLightCommand implements CommandInterface {
    private final Light light;

    public TurnOnLightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

// Concrete Command 2
class TurnOffLightCommand implements CommandInterface {
    private final Light light;

    public TurnOffLightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

// Invoker
class RemoteControl {
    private CommandInterface command;

    public void setCommand(CommandInterface command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}
