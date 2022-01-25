package br.com.mvc.cacaniquel.api;

import br.com.mvc.cacaniquel.business.SlotMachineBusiness;
import br.com.mvc.cacaniquel.dto.SlotMachineDto;
import br.com.mvc.cacaniquel.model.BetModel;
import br.com.mvc.cacaniquel.repository.CreditRepository;
import br.com.mvc.cacaniquel.repository.SlotMachineRepository;
import br.com.mvc.cacaniquel.repository.UserRepository;
import br.com.mvc.cacaniquel.support.SessionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        SlotMachineBusiness slotMachineBusiness = new SlotMachineBusiness();
        saveBet(betValue, multiplier, slotMachineBusiness);
        SlotMachineDto slotMachineDto = newSlotMachineDto(slotMachineBusiness);

        return slotMachineDto;
    }

    @GetMapping("initialVariables")
    public SlotMachineDto initialVariables() {
        SlotMachineDto slotMachineDto = new SlotMachineDto();
        slotMachineDto.setRandomNumbers(new SlotMachineBusiness().generateRandomNumbers(3));

        return slotMachineDto;
    }

    private void saveBet(Double betValue, Integer multiplier, SlotMachineBusiness slotMachineBusiness) {
        BetModel betModel = slotMachineBusiness.newSlotMachineBet(betValue, multiplier, userRepository);
        slotMachineBusiness.saveBet(betModel, creditRepository, slotMachineRepository);
    }

    private SlotMachineDto newSlotMachineDto(SlotMachineBusiness slotMachineBusiness) {
        SlotMachineDto slotMachineDto = new SlotMachineDto();
        slotMachineDto.setRandomNumbers(slotMachineBusiness.getRandomNumbers());
        slotMachineDto.setWin(slotMachineBusiness.getWin());
        slotMachineDto.setCredit(userRepository.findByUsername(SessionSupport.getAuthentication().getName()).getCredit().getCreditValue());
        return slotMachineDto;
    }
}
