package br.com.mvc.cacaniquel.api;

import br.com.mvc.cacaniquel.business.SlotMachine;
import br.com.mvc.cacaniquel.dto.SlotMachineDto;
import br.com.mvc.cacaniquel.model.SlotMachineModel;
import br.com.mvc.cacaniquel.repository.CreditRepository;
import br.com.mvc.cacaniquel.repository.SlotMachineRepository;
import br.com.mvc.cacaniquel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class SlotMachineRest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CreditRepository creditRepository;

    @Autowired
    SlotMachineRepository slotMachineRepository;

    @GetMapping("betSlotMachine")
    public SlotMachineDto betSlotMachine(Double betValue, Integer multiplier) {
        if (betValue == null || multiplier == null)
            throw new RuntimeException("ERRO");//TODO GUSTAVO pensei em adicionar no atributo de mensagens de erro do slotMachineDto

        SlotMachine slotMachine = new SlotMachine();
        SlotMachineModel slotMachineModel = slotMachine.newSlotMachineBet(betValue, multiplier, userRepository);
        slotMachine.saveBet(slotMachineModel, creditRepository, slotMachineRepository);

        ArrayList<Integer> randomNumbers = slotMachine.generateRandomNumbers(3);

        SlotMachineDto slotMachineDto = new SlotMachineDto();
        slotMachineDto.setRandomNumbers(randomNumbers);
        slotMachineDto.setWin(slotMachine.verifyIfNumbersAreEquals(randomNumbers));

        return slotMachineDto;
    }

    @GetMapping("initialRandomNumbers")
    public SlotMachineDto initialRandomNumbers() {
        SlotMachineDto slotMachineDto = new SlotMachineDto();
        slotMachineDto.setRandomNumbers(new SlotMachine().generateRandomNumbers(3));
        return slotMachineDto;
    }
}
