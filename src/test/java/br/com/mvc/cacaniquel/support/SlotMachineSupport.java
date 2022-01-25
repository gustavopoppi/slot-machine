package br.com.mvc.cacaniquel.support;

import br.com.mvc.cacaniquel.model.BetModel;
import br.com.mvc.cacaniquel.repository.UserRepository;

import java.time.LocalDate;

public class SlotMachineSupport {

    public static BetModel newBetModelObject(double betValue, int multiplier, UserRepository userRepository) {
        BetModel betModel = new BetModel();
        betModel.setBetValue(betValue);
        betModel.setMultiplier(multiplier);
        betModel.setDate(LocalDate.now());
        betModel.setUser(userRepository.findByUsername("carlos"));
        betModel.getUser().getCredit().setCreditValue(100);
        return betModel;
    }
}
