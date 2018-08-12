package com.kotall.learn.state;

/**
 * @author zpwang
 * @version 1.0.0
 */
public interface ATMState {
    void insertCard();
    void ejectCard();
    void insertPin(int pinEntered);
    void requestCash(int cashToWithdraw);
}
