package br.com.mvc.cacaniquel.controller;

import br.com.mvc.cacaniquel.business.SlotMachine;
import br.com.mvc.cacaniquel.dto.SlotMachineDto;
import br.com.mvc.cacaniquel.model.SlotMachineModel;
import br.com.mvc.cacaniquel.repository.CreditRepository;
import br.com.mvc.cacaniquel.repository.SlotMachineRepository;
import br.com.mvc.cacaniquel.repository.UserRepository;
import br.com.mvc.cacaniquel.support.SessionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/slotMachine")
public class SlotMachineController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CreditRepository creditRepository;

    @Autowired
    SlotMachineRepository slotMachineRepository;

    @PostMapping("bet")
    public String bet(@Valid SlotMachineDto slotMachineDto, BindingResult result){
        if (result.hasErrors())
            return "/home";

        SlotMachineModel slotMachineModel = newSlotMachineBet(slotMachineDto, userRepository);

        SlotMachine slotMachine = new SlotMachine();
        slotMachine.bet(slotMachineModel, creditRepository, slotMachineRepository);

        //TODO gustavo criar algo para dar um feedback de aposta feita
        return "/home";
    }

    private SlotMachineModel newSlotMachineBet(SlotMachineDto slotMachineDto, UserRepository userRepository) {
        String userName = SessionSupport.getAuthentication().getName();
        final double totalBet = slotMachineDto.getBetValue() * slotMachineDto.getMultiplier();

        SlotMachineModel slotMachineModel = new SlotMachineModel();
        slotMachineModel.setBetValue(slotMachineDto.getBetValue());
        slotMachineModel.setMultiplier(slotMachineDto.getMultiplier());
        slotMachineModel.setDate(LocalDate.now());
        slotMachineModel.setTotalBet(totalBet);
        slotMachineModel.setUser(userRepository.findByUsername(userName));

        return slotMachineModel;
    }
}
