package br.com.mvc.cacaniquel.business;

import br.com.mvc.cacaniquel.model.Credit;

public class SlotMachine extends Bet {

    final double MINIMUM_BET = 1.00;

    public SlotMachine(Credit credit) {
        super(credit);
    }

    @Override
    public void bet(double valueBet, int multiplier) {
        validateBet(this.getCredit(), valueBet, multiplier);

        this.setValueBet(valueBet);
        this.setMultiplier(multiplier);
    }

    @Override
    public void verifyIfWon(Bet bet) {}

    @Override
    public void validateBet(Credit credit, double valueBet, int multiplier) {
        final double totalBetAmount = valueBet * multiplier;
        final double totalCreditUser = credit.getCreditValue();

        if (!haveCredit(totalBetAmount, totalCreditUser))
            throw new RuntimeException("Insufficient funds in you balance");

        if (!totalBetAmountGreaterOrEqualThenMinimum(totalBetAmount))
            throw new RuntimeException("Minimum bet is " + MINIMUM_BET + " dollar");
    }

    @Override
    public void raiseBet(double value) {}

    @Override
    public void decreaseBet(double value) {}

    private boolean haveCredit(double totalBetAmount, double totalCreditUser) {
        return totalCreditUser >= totalBetAmount;
    }

    private boolean totalBetAmountGreaterOrEqualThenMinimum(double totalBetAmount) {
        return totalBetAmount >= MINIMUM_BET;
    }
}
