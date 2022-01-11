package br.com.mvc.cacaniquel.business;

import br.com.mvc.cacaniquel.model.Credit;
import br.com.mvc.cacaniquel.repository.UserRepository;
import br.com.mvc.cacaniquel.support.CreditSupport;
import br.com.mvc.cacaniquel.support.UserSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SlotMachineTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void doABet(){
        Credit credit = CreditSupport.newCredit(UserSupport.newUser());
        SlotMachine slotMachine = new SlotMachine(credit);
        slotMachine.bet(10.00, 1);

        Assertions.assertEquals(10.00, slotMachine.getValueBet());
        Assertions.assertEquals(1, slotMachine.getMultiplier());
    }

    @Test
    public void insufficientFundsInYourBalance(){
        Credit credit = CreditSupport.newCredit(UserSupport.newUser());
        SlotMachine slotMachine = new SlotMachine(credit);

        Throwable exception = assertThrows(RuntimeException.class, () -> slotMachine.bet(1000.00, 1));
        assertEquals("Insufficient funds in you balance", exception.getMessage());
    }

    @Test
    public void totalBetAmountLesserThenMinimumAllowed(){
        Credit credit = CreditSupport.newCredit(UserSupport.newUser());
        SlotMachine slotMachine = new SlotMachine(credit);

        Throwable exception = assertThrows(RuntimeException.class, () -> slotMachine.bet(0.5, 1));
        assertEquals("Minimum bet is 1.0 dollar", exception.getMessage());
    }
}
