package br.com.mvc.cacaniquel.business;

import br.com.mvc.cacaniquel.model.SlotMachineModel;
import br.com.mvc.cacaniquel.repository.CreditRepository;
import br.com.mvc.cacaniquel.repository.SlotMachineRepository;
import br.com.mvc.cacaniquel.repository.UserRepository;
import br.com.mvc.cacaniquel.support.SessionSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;

public class SlotMachine extends Game {

    @Autowired
    UserRepository userRepository;

    final double MINIMUM_BET = 1.00;

//    public SlotMachine(Credit credit) {
//        super(credit);
//    }

    @Override
    public void saveBet(SlotMachineModel bet, CreditRepository creditRepository, SlotMachineRepository slotMachineRepository) {
        //TODO GUSTAVO totalCreditUser talvez eu possa fazer diferente, pois está faltando o relacionamento de mapped nas classes, igual aprendi no curso da pós
        final double totalCreditUser = creditRepository.findByUser(bet.getUser()).getCreditValue();

        //TODO GUSTAVO talvez aqui fazer um try catch
        validateBet(bet.getBetValue(), bet.getMultiplier(), totalCreditUser);

        slotMachineRepository.save(bet);
    }

    @Override
    public boolean verifyIfNumbersAreEquals(ArrayList<Integer> randomNumbers) {
        int index = 0;
        while (index != randomNumbers.size() - 1){
            if (randomNumbers.get(index).compareTo(randomNumbers.get(index + 1)) != 0)
                return false;
            index++;
        }
        return true;
    }

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

    public ArrayList<Integer> generateRandomNumbers(int howManyNumbersGenerate) {
        ArrayList<Integer> randomValues = new ArrayList<>();
        SecureRandom randomNumbers = new SecureRandom();

        for (int i = 0; i < howManyNumbersGenerate; i++)
            randomValues.add(1 + randomNumbers.nextInt(10));

        return randomValues;
    }

    public SlotMachineModel newSlotMachineBet(double betValue, Integer multiplier, UserRepository userRepository) {
        String userName = SessionSupport.getAuthentication().getName();
        final double totalBet = betValue * multiplier;

        SlotMachineModel slotMachineModel = new SlotMachineModel();
        slotMachineModel.setBetValue(betValue);
        slotMachineModel.setMultiplier(multiplier);
        slotMachineModel.setDate(LocalDate.now());
        slotMachineModel.setTotalBet(totalBet);
        slotMachineModel.setUser(userRepository.findByUsername(userName));

        return slotMachineModel;
    }
}
