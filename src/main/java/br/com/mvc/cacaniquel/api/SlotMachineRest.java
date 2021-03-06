package br.com.mvc.cacaniquel.api;

import br.com.mvc.cacaniquel.business.SlotMachine;
import br.com.mvc.cacaniquel.dto.SlotMachineDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class SlotMachineRest {

    @GetMapping("betSlotMachine")
    public SlotMachineDto slotMachineHome() {
        SlotMachine slotMachine = new SlotMachine();
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
