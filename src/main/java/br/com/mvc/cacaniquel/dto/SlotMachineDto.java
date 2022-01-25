package br.com.mvc.cacaniquel.dto;

import java.util.ArrayList;

public class SlotMachineDto {

    private double betValue;
    private int multiplier;
    private boolean win;
    private ArrayList<Integer> randomNumbers;
    private double credit;

    public double getBetValue() {
        return betValue;
    }

    public void setBetValue(double betValue) {
        this.betValue = betValue;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public boolean getWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public ArrayList<Integer> getRandomNumbers() {
        return randomNumbers;
    }

    public void setRandomNumbers(ArrayList<Integer> randomNumbers) {
        this.randomNumbers = randomNumbers;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }
}
