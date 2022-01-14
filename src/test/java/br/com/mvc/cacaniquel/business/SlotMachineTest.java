package br.com.mvc.cacaniquel.business;

import br.com.mvc.cacaniquel.model.SlotMachineModel;
import br.com.mvc.cacaniquel.repository.CreditRepository;
import br.com.mvc.cacaniquel.repository.SlotMachineRepository;
import br.com.mvc.cacaniquel.repository.UserRepository;
import br.com.mvc.cacaniquel.support.SlotMachineSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.ArrayList;

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

        SlotMachineModel slotMachineModel = SlotMachineSupport.newSlotMachineModel(10.00, 1, userRepository);

        new SlotMachine().bet(slotMachineModel, creditRepository, slotMachineRepository);
        Assertions.assertEquals(sizeBetDataBase + 1, slotMachineRepository.findAll().size(), "Should have create one more bet data");
    }

    @Test
    @Transactional
    public void insufficientFundsInYourBalance(){
        SlotMachineModel slotMachineModel = SlotMachineSupport.newSlotMachineModel(1000.00, 1 , userRepository);

        Throwable exception = assertThrows(RuntimeException.class, () -> new SlotMachine().bet(slotMachineModel, creditRepository, slotMachineRepository));
        assertEquals("Insufficient funds in you balance", exception.getMessage());
    }

    @Test
    @Transactional
    public void totalBetAmountLesserThenMinimumAllowed(){
        SlotMachineModel slotMachineModel = SlotMachineSupport.newSlotMachineModel(0.5, 1, userRepository);

        Throwable exception = assertThrows(RuntimeException.class, () -> new SlotMachine().bet(slotMachineModel, creditRepository, slotMachineRepository));
        assertEquals("Minimum bet is 1.0 dollar", exception.getMessage());
    }

    @Test
    public void generateThreeRandomNumbers(){
        Assertions.assertEquals(3, new SlotMachine().generateRandomNumbers(3).size(), "Size should have be three");
    }
}
