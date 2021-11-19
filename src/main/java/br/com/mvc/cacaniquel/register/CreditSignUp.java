package br.com.mvc.cacaniquel.register;

import br.com.mvc.cacaniquel.dto.UserDto;
import br.com.mvc.cacaniquel.model.Credit;
import br.com.mvc.cacaniquel.repository.CreditRepository;
import br.com.mvc.cacaniquel.repository.UserRepository;

public class CreditSignUp {

    public void newOne(UserDto userDto, CreditRepository creditRepository, UserRepository userRepository) {
        Credit credit = new Credit();
        credit.setCreditValue(0.00);
        credit.setUser(userRepository.findByUsername(userDto.getName()));

        creditRepository.save(credit);
    }
}
