package com.kotall.learn.state;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class TestATMMachine {

    public static void main(String[] args) {
        ATMMachine atmMachine = new ATMMachine();
        atmMachine.insertCard();
        atmMachine.ejectCard();
        atmMachine.insertCard();
        atmMachine.insertPin(1234);
        atmMachine.requestCash(2000);
        atmMachine.requestCash(100);
        atmMachine.insertCard();
        atmMachine.insertPin(1234);
    }

}
