package br.com.mvc.cacaniquel.support;

import br.com.mvc.cacaniquel.model.Credit;
import br.com.mvc.cacaniquel.model.User;

public class CreditSupport {

    public static Credit newCredit(User user){
        Credit credit = new Credit();
        credit.setUser(user);
        credit.setCreditValue(100.00);
        return credit;
    }
}
