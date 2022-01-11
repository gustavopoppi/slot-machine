package br.com.mvc.cacaniquel.business;

import br.com.mvc.cacaniquel.model.Credit;

public class SlotMachine extends Bet {


    public SlotMachine(Credit credit) {
        super(credit);
    }

    @Override
    public void bet(double valueBet, int multiplier) {
        if (!validateBet(this.getCredit(), valueBet, multiplier))
            throw new RuntimeException("Insufficient funds in you balance");

        this.setValueBet(valueBet);
        this.setMultiplier(multiplier);
    }

    @Override
    public void verifyIfWon(Bet bet) {

    }

    @Override
    public boolean validateBet(Credit credit, double valueBet, int multiplier) {
        final double totalBetAmount = valueBet * multiplier;
        final double totalCreditUser = credit.getCreditValue();

        return totalCreditUser >= totalBetAmount;
    }

    @Override
    public void raiseBet(double value) {}

    @Override
    public void decreaseBet(double value) {}
}
