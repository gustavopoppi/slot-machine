package br.com.mvc.cacaniquel.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.mvc.cacaniquel.dto.UserDto;
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
import org.springframework.security.core.userdetails.User;
import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	JdbcUserDetailsManager jdbcUserDetailsManager;

	@GetMapping("form")
	public String formNewUser(Model model){
		model.addAttribute("userDto", new UserDto());

		return "user/signup";
//		return ModelAndView()
	}

	@PostMapping("signup")
	@Transactional
	public String signUp(@Valid UserDto userDto, BindingResult result){
		if (result.hasErrors())
			return"/user/signup";

		//TODO GUSTAVO lógica de adicionar um novo usuário
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		User user = new User(userDto.getName(), encoder.encode(userDto.getPassword()), authorities);

		jdbcUserDetailsManager.createUser(user);


		return "/home";
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/usuario/home";
	}
}
