package br.com.mvc.cacaniquel.business;

import br.com.mvc.cacaniquel.model.BetModel;
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
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class SlotMachineBusinessTest {

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

        BetModel betModel = SlotMachineSupport.newBetModelObject(10.00, 1, userRepository);

        new SlotMachineBusiness().saveBet(betModel, creditRepository, slotMachineRepository);
        Assertions.assertEquals(sizeBetDataBase + 1, slotMachineRepository.findAll().size(), "Should have create one more bet data");
    }

    @Test
    @Transactional
    public void insufficientFundsInYourBalance(){
        BetModel betModel = SlotMachineSupport.newBetModelObject(1000.00, 1 , userRepository);

        Throwable exception = assertThrows(RuntimeException.class, () -> new SlotMachineBusiness().saveBet(betModel, creditRepository, slotMachineRepository));
        assertEquals("Insufficient funds in you balance", exception.getMessage());
    }

    @Test
    @Transactional
    public void totalBetAmountLesserThenMinimumAllowed(){
        BetModel betModel = SlotMachineSupport.newBetModelObject(0.5, 1, userRepository);

        Throwable exception = assertThrows(RuntimeException.class, () -> new SlotMachineBusiness().saveBet(betModel, creditRepository, slotMachineRepository));
        assertEquals("Minimum bet is 1.0 dollar", exception.getMessage());
    }

    @Test
    public void generateThreeRandomNumbers(){
        Assertions.assertEquals(3, new SlotMachineBusiness().generateRandomNumbers(3).size(), "Size should have be three");
    }

    @Test
    public void gerenateThreeEqualsIntegerNumbersSoYouWin(){
        ArrayList<Integer> equalNumbers = new ArrayList<>(Arrays.asList(1, 1, 1));

        Assertions.assertTrue(new SlotMachineBusiness().verifyIfNumbersAreEquals(equalNumbers), "Should be true");
    }

    @Test
    public void generateThreeDiferenteIntegerNumbersSoYouLose(){
        ArrayList<Integer> equalNumbers = new ArrayList<>(Arrays.asList(1, 2, 1));

        Assertions.assertFalse(new SlotMachineBusiness().verifyIfNumbersAreEquals(equalNumbers), "Should be false");
    }

    @Test
    @Transactional
    public void afterLoseBetUpdateCredit(){
        BetModel betModelObject = SlotMachineSupport.newBetModelObject(10.00, 1, userRepository);
        new SlotMachineBusiness().saveBet(betModelObject, creditRepository, slotMachineRepository);

        Assertions.assertEquals(90, userRepository.findByUsername("carlos").getCredit().getCreditValue(), "Credit should have been updated");
    }
}
