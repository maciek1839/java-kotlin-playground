package com.showmeyourcode.playground.java.code.pattern.behavioral;

// Command interface
interface CommandInterface {
    void execute();
}

// Receiver
class Light {
    public void turnOn() {
        System.out.println("Light is on");
    }

    public void turnOff() {
        System.out.println("Light is off");
    }
}

// Concrete Command 1
class TurnOnLightCommand implements CommandInterface {
    private Light light;

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
    private Light light;

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

// Command is behavioral design pattern that converts requests or simple operations into objects.
public class Command {

    public static void main(){
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
