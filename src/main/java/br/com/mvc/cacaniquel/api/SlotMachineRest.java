package br.com.mvc.cacaniquel.api;

import br.com.mvc.cacaniquel.business.SlotMachine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class SlotMachineRest {

    @GetMapping("betSlotMachine")
    public ArrayList<Integer> slotMachineHome(Integer mes) {

        SlotMachine slotMachine = new SlotMachine();

        return slotMachine.generateRandomNumbers(3);
    }
}
