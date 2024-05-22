package com.showmeyourcode.playground.java.code.pattern.behavioral;

import java.util.ArrayList;
import java.util.List;

// Memento class
class MementoClass {
    private String state;

    public MementoClass(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

// Originator class
class Originator {
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public MementoClass saveStateToMemento() {
        return new MementoClass(state);
    }

    public void getStateFromMemento(MementoClass memento) {
        state = memento.getState();
    }
}

// Caretaker class
class Caretaker {
    private List<MementoClass> mementoList = new ArrayList<>();

    public void add(MementoClass state) {
        mementoList.add(state);
    }

    public MementoClass get(int index) {
        return mementoList.get(index);
    }
}

// The Memento pattern provides the ability to restore an object to its previous state.
public class Memento {

    public static void main(){
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();

        originator.setState("State #1");
        originator.setState("State #2");
        caretaker.add(originator.saveStateToMemento());

        originator.setState("State #3");
        caretaker.add(originator.saveStateToMemento());

        originator.setState("State #4");
        System.out.println("Current State: " + originator.getState());

        originator.getStateFromMemento(caretaker.get(0));
        System.out.println("First saved State: " + originator.getState());

        originator.getStateFromMemento(caretaker.get(1));
        System.out.println("Second saved State: " + originator.getState());
    }
}
