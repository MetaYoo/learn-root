package com.kotall.learn.state;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class HasCard implements ATMState {
    ATMMachine atmMachine;

    public HasCard(ATMMachine atmMachine) {
        this.atmMachine = atmMachine;
    }

    @Override
    public void insertCard() {
        System.out.println("You can't enter more than one card!");
    }

    @Override
    public void ejectCard() {
        System.out.print("Card ejected");
        atmMachine.setAtmState(atmMachine.getNoCardState());
    }

    @Override
    public void insertPin(int pinEntered) {
        if (pinEntered == 1234) {
            System.out.println("Correct Pin");
            atmMachine.correctPinEntered = true;
            atmMachine.setAtmState(atmMachine.getHasCorrectPinState());
        } else {
            System.out.println("Wrong Pin");
            atmMachine.correctPinEntered = false;
            System.out.println("Card ejected");
            atmMachine.setAtmState(atmMachine.getNoCardState());
        }
    }

    @Override
    public void requestCash(int cashToWithdraw) {
        System.out.println("Enter pin first");
    }

}
