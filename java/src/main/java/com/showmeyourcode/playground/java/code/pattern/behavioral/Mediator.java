package com.showmeyourcode.playground.java.code.pattern.behavioral;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

// Mediator interface
interface ChatMediator {
    void sendMessage(String msg, User user);
    void addUser(User user);
}

// Concrete Mediator
class ChatMediatorImpl implements ChatMediator {
    private final List<User> users;

    public ChatMediatorImpl() {
        this.users = new ArrayList<>();
    }

    @Override
    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public void sendMessage(String msg, User user) {
        for (User u : this.users) {
            if (u != user) {
                u.receive(msg);
            }
        }
    }
}

// Abstract Colleague
abstract class User {
    protected ChatMediator mediator;
    protected String name;

    User(ChatMediator med, String name) {
        this.mediator = med;
        this.name = name;
    }

    public abstract void send(String msg);
    public abstract void receive(String msg);
}

// Concrete Colleague
@Slf4j
class UserImpl extends User {
    public UserImpl(ChatMediator med, String name) {
        super(med, name);
    }

    @Override
    public void send(String msg) {
        log.info("{}: Sending Message = {}", this.name, msg);
        mediator.sendMessage(msg, this);
    }

    @Override
    public void receive(String msg) {
        log.info("{}: Received Message = {}", this.name, msg);
    }
}

// The Mediator pattern provides a way to define an object that encapsulates how a set of objects interact.
public class Mediator {

    private Mediator() {
    }

    public static void main(String[] args){
        ChatMediator mediator = new ChatMediatorImpl();

        User user1 = new UserImpl(mediator, "John");
        User user2 = new UserImpl(mediator, "Doe");
        User user3 = new UserImpl(mediator, "Smith");
        User user4 = new UserImpl(mediator, "Jane");

        mediator.addUser(user1);
        mediator.addUser(user2);
        mediator.addUser(user3);
        mediator.addUser(user4);

        user1.send("Hi All");
    }
}
