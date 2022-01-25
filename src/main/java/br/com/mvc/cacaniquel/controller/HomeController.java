package br.com.mvc.cacaniquel.controller;

import java.security.Principal;
import java.util.List;

import br.com.mvc.cacaniquel.dto.SlotMachineDto;
import br.com.mvc.cacaniquel.repository.UserRepository;
import br.com.mvc.cacaniquel.support.SessionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	UserRepository userRepository;

	@GetMapping
	public String home(Model model) {
		model.addAttribute("slotMachine", new SlotMachineDto());
		//TODO GUSTAVO problema de quando não estiver logado
//		model.addAttribute("user", userRepository.findByUsername(SessionSupport.getAuthentication().getName()));
		model.addAttribute("user", userRepository.findByUsername("carlos"));

		return "home";
	}

	public String bet(){
		return "home";
	}
	
}
