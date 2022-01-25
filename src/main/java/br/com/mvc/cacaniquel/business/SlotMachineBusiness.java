package br.com.mvc.cacaniquel.business;

import br.com.mvc.cacaniquel.dto.SlotMachineDto;
import br.com.mvc.cacaniquel.model.BetModel;
import br.com.mvc.cacaniquel.model.Credit;
import br.com.mvc.cacaniquel.repository.CreditRepository;
import br.com.mvc.cacaniquel.repository.SlotMachineRepository;
import br.com.mvc.cacaniquel.repository.UserRepository;
import br.com.mvc.cacaniquel.support.SessionSupport;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;

public class SlotMachineBusiness extends Game {

    final double MINIMUM_BET = 1.00;
    private boolean win;
    private ArrayList<Integer> randomNumbers;

//    public SlotMachineBusiness(Credit credit) {
//        super(credit);
//    }

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

    @Override
    public void saveBet(BetModel bet, CreditRepository creditRepository, SlotMachineRepository slotMachineRepository) {
        //TODO GUSTAVO totalCreditUser talvez eu possa fazer diferente, pois está faltando o relacionamento de mapped nas classes, igual aprendi no curso da pós
        final double totalCreditUser = creditRepository.findByUser(bet.getUser()).getCreditValue();

        //TODO GUSTAVO talvez aqui fazer um try catch
        validateBet(bet.getBetValue(), bet.getMultiplier(), totalCreditUser);
//        slotMachineBusiness.verifyIfNumbersAreEquals(randomNumbers)

        verifyWinAndSetBothAttributes();

        updateCredit(bet, creditRepository);

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

    public BetModel newSlotMachineBet(double betValue, Integer multiplier, UserRepository userRepository) {
        String userName = SessionSupport.getAuthentication().getName();
        final double totalBet = betValue * multiplier;

        BetModel betModel = new BetModel();
        betModel.setBetValue(betValue);
        betModel.setMultiplier(multiplier);
        betModel.setDate(LocalDate.now());
        betModel.setTotalBet(totalBet);
        betModel.setUser(userRepository.findByUsername(userName));

        return betModel;
    }

    private void updateCredit(BetModel bet, CreditRepository creditRepository) {
        double totalCreditUser = creditRepository.findByUser(bet.getUser()).getCreditValue();
        double totalBetValue = bet.getBetValue() * bet.getMultiplier();

        Credit credit = creditRepository.findByUser(bet.getUser());

        if (this.getWin())
            credit.setCreditValue(totalCreditUser + totalBetValue);
        else
            credit.setCreditValue(totalCreditUser - totalBetValue);

        creditRepository.save(credit);
    }

    private void verifyWinAndSetBothAttributes() {
        ArrayList<Integer> randomNumbers = this.generateRandomNumbers(3);
        this.setWin(this.verifyIfNumbersAreEquals(randomNumbers));
//        this.setWin(true);
        this.setRandomNumbers(randomNumbers);

    }
}
