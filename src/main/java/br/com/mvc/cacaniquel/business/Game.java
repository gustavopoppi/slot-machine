package br.com.mvc.cacaniquel.business;

import br.com.mvc.cacaniquel.model.SlotMachineModel;
import br.com.mvc.cacaniquel.model.User;
import br.com.mvc.cacaniquel.repository.CreditRepository;
import br.com.mvc.cacaniquel.repository.SlotMachineRepository;

import java.util.ArrayList;

public abstract class Game {

    private double betValue;
    private int multiplier;
    private User user;

//    public SlotMachineModel(Credit credit) {
//        this.credit = credit;
//    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

public abstract void saveBet(SlotMachineModel bet, CreditRepository creditRepository, SlotMachineRepository slotMachineRepository);

    public abstract boolean verifyIfNumbersAreEquals(ArrayList<Integer> randomNumbers);

    public abstract void validateBet(double valueBet, int multiplier, double totalCreditUser);

    public abstract void raiseBet(double value);

    public abstract void decreaseBet(double value);
}
