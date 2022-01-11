package br.com.mvc.cacaniquel.support;

import br.com.mvc.cacaniquel.model.SlotMachineModel;
import br.com.mvc.cacaniquel.repository.UserRepository;

import java.time.LocalDate;

public class SlotMachineSupport {

    public static SlotMachineModel newSlotMachineModel(double betValue, int multiplier, UserRepository userRepository) {
        SlotMachineModel slotMachineModel = new SlotMachineModel();
        slotMachineModel.setBetValue(betValue);
        slotMachineModel.setMultiplier(multiplier);
        slotMachineModel.setDate(LocalDate.now());
        slotMachineModel.setUser(userRepository.findByUsername("carlos"));
        return slotMachineModel;
    }
}
