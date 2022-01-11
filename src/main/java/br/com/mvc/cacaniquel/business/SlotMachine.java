package br.com.mvc.cacaniquel.business;

import br.com.mvc.cacaniquel.model.SlotMachineModel;
import br.com.mvc.cacaniquel.repository.CreditRepository;
import br.com.mvc.cacaniquel.repository.SlotMachineRepository;
import br.com.mvc.cacaniquel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SlotMachine extends Bet {

    @Autowired
    UserRepository userRepository;

    final double MINIMUM_BET = 1.00;

//    public SlotMachine(Credit credit) {
//        super(credit);
//    }

    @Override
    public void bet(SlotMachineModel bet, CreditRepository creditRepository, SlotMachineRepository slotMachineRepository) {
//        validateBet(this.getCredit(), valueBet, multiplier);
        final double totalCreditUser = creditRepository.findByUser(bet.getUser()).getCreditValue();

        validateBet(bet.getBetValue(), bet.getMultiplier(), totalCreditUser);

        slotMachineRepository.save(bet);
    }

    @Override
    public void verifyIfWon(Bet bet) {}

    @Override
    public void validateBet(double betValue, int multiplier, double totalCreditUser) {
        final double totalBetAmount = betValue * multiplier;

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
