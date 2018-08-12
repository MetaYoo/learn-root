package com.kotall.learn.state;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class ATMMachine {

    // 有卡
    ATMState hasCard;
    // 没有卡
    ATMState noCard;
    // 密码正确
    ATMState hasCorrectPin;
    // ATM钱不足
    ATMState atmOutOfMoney;

    // ATM 状态
    ATMState atmState;

    int cashInMachine = 2000;
    boolean correctPinEntered = false;

    public ATMMachine() {
        hasCard = new HasCard(this);
        noCard = new NoCard(this);
        hasCorrectPin = new HasPin(this);
        atmOutOfMoney = new NoCash(this);

        atmState = noCard;

        if (cashInMachine < 0) {
            atmState = atmOutOfMoney;
        }
    }

    void setAtmState(ATMState newAtmState) {
        atmState = newAtmState;
    }

    public void setCashInMachine(int newCashInMachine) {
        cashInMachine = newCashInMachine;
    }

    public void insertCard() {
        atmState.insertCard();
    }

    public void ejectCard() {
        atmState.ejectCard();
    }

    public void requestCash(int cashToWithdraw) {
        atmState.requestCash(cashToWithdraw);
    }

    public void insertPin(int pinEntered) {
        atmState.insertPin(pinEntered);
    }

    public ATMState getYesCardState() {
        return hasCard;
    }

    public ATMState getNoCardState() {
        return noCard;
    }

    public ATMState getHasCorrectPinState() {
        return hasCorrectPin;
    }

    public ATMState getAtmOutOfMoneyState() {
        return atmOutOfMoney;
    }
}
