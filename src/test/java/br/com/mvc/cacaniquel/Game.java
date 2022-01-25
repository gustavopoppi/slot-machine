package br.com.mvc.cacaniquel;

import org.junit.jupiter.api.Test;


public class Game {


    //TODO GUSTAVO preciso estudar como que faz validação com request para enviar uma requisição e pegar o id do login que foi feito e ai sim começar a validar credito e outras coisas
    // todos os testes eu preciso pegar o ID do user para diminuir o crédito do user em questão, por isso preciso estudar o que está acima.
    @Test
    void insertCredit(){
        /*Game credit = new Game(500.00);
        credit.insertCredit(150.00);*/

        //Assertions.assertEquals(650.00, credit.getCreditValue());
    }

    @Test
    void insertCreditsAndDoMinimumBetWithoutMultiplier(){
        /*Game credit = new Game(500.00);
        credit.bet(10.00, 1);*/

        //Assertions.assertEquals(490.00, credit.getCreditValue());
    }

    @Test
    void betWithoutMultiplier(){
//        SlotMachineBusiness slotMachine = new SlotMachineBusiness();
//        slotMachine.bet(10.00, 1);
    }
}
