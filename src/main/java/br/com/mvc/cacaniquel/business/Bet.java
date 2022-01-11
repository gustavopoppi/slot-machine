package br.com.mvc.cacaniquel.business;

import br.com.mvc.cacaniquel.model.Credit;

public abstract class Bet {

    private double valueBet;
    private int multiplier;
    private Credit credit;

    public Bet(Credit credit) {
        this.credit = credit;
    }

    public double getValueBet() {
        return valueBet;
    }

    public void setValueBet(double valueBet) {
        this.valueBet = valueBet;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public abstract void bet(double valueBet, int multiplier);

    public abstract void verifyIfWon(Bet bet);

    public abstract void validateBet(Credit credit, double valueBet, int multiplier);

    public abstract void raiseBet(double value);

    public abstract void decreaseBet(double value);
}
