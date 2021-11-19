package br.com.mvc.cacaniquel.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.mvc.cacaniquel.dto.UserDto;
import br.com.mvc.cacaniquel.model.Credit;
import br.com.mvc.cacaniquel.register.CreditSignUp;
import br.com.mvc.cacaniquel.register.UserSignUp;
import br.com.mvc.cacaniquel.repository.CreditRepository;
import br.com.mvc.cacaniquel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	CreditRepository creditRepository;

	@Autowired
	JdbcUserDetailsManager jdbcUserDetailsManager;

	@GetMapping("form")
	public String formNewUser(Model model){
		model.addAttribute("userDto", new UserDto());

		return "user/signup";
		//TODO GUSTAVO dar uma pesquisada que provávelmente esse método da p retornar um ModelAndView()
//		return ModelAndView()
	}

	@PostMapping("signup")
	@Transactional
	public String signUp(@Valid UserDto userDto, BindingResult result){
		if (result.hasErrors())
			return"/user/signup";

		//TODO GUSTAVO lógica de adicionar um novo usuário
		new UserSignUp().newOne(userDto, jdbcUserDetailsManager);
		new CreditSignUp().newOne(userDto, creditRepository, userRepository);

		//TODO GUSTAVO da para implementar caso tudo funcione vá para uma página "Cadastro realizado" e depois para home;

		return "/home";
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/usuario/home";
	}
}
