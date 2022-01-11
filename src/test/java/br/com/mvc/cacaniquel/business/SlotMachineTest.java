package br.com.mvc.cacaniquel.business;

import br.com.mvc.cacaniquel.model.SlotMachineModel;
import br.com.mvc.cacaniquel.repository.CreditRepository;
import br.com.mvc.cacaniquel.repository.SlotMachineRepository;
import br.com.mvc.cacaniquel.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class SlotMachineTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CreditRepository creditRepository;

    @Autowired
    SlotMachineRepository slotMachineRepository;

    @Test
    @Transactional
    public void doABet(){
        final int sizeBetDataBase = slotMachineRepository.findAll().size();

        SlotMachineModel slotMachineModel = new SlotMachineModel();
        slotMachineModel.setBetValue(10.00);
        slotMachineModel.setMultiplier(1);
        slotMachineModel.setUser(userRepository.findByUsername("carlos"));

        new SlotMachine().bet(slotMachineModel, creditRepository, slotMachineRepository);
        Assertions.assertEquals(sizeBetDataBase + 1, slotMachineRepository.findAll().size(), "Should have create one more bet data");
    }

    @Test
    @Transactional
    public void insufficientFundsInYourBalance(){
        SlotMachineModel slotMachineModel = new SlotMachineModel();
        slotMachineModel.setBetValue(1000.00);
        slotMachineModel.setMultiplier(1);
        slotMachineModel.setUser(userRepository.findByUsername("carlos"));

        Throwable exception = assertThrows(RuntimeException.class, () -> new SlotMachine().bet(slotMachineModel, creditRepository, slotMachineRepository));
        assertEquals("Insufficient funds in you balance", exception.getMessage());
    }

    @Test
    public void totalBetAmountLesserThenMinimumAllowed(){
        SlotMachineModel slotMachineModel = new SlotMachineModel();
        slotMachineModel.setBetValue(0.5);
        slotMachineModel.setMultiplier(1);
        slotMachineModel.setUser(userRepository.findByUsername("carlos"));

        Throwable exception = assertThrows(RuntimeException.class, () -> new SlotMachine().bet(slotMachineModel, creditRepository, slotMachineRepository));
        assertEquals("Minimum bet is 1.0 dollar", exception.getMessage());
    }
}
