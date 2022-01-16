package br.com.mvc.cacaniquel.controller;

import java.security.Principal;
import java.util.List;

import br.com.mvc.cacaniquel.dto.SlotMachineDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/home")
public class HomeController {

	@GetMapping
	public String home(Model model) {
		model.addAttribute("slotMachine", new SlotMachineDto());

		return "home";
	}

	public String bet(){
		return "home";
	}
	
}
