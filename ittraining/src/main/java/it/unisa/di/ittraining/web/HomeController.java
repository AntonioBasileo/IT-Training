package it.unisa.di.ittraining.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getHome(Model model) {
			if (!model.containsAttribute("RegistrazioneStudenteForm")) {
				model.addAttribute("RegistrazioneStudenteForm", new RegistrazioneStudenteForm());
			}
			
			if (!model.containsAttribute("registrazioneSegreteria")) {
				model.addAttribute("registrazioneSegreteria", new RegistrazioneSTTForm());
			}
			
			if (!model.containsAttribute("registrazioneAccademico")) {
				model.addAttribute("registrazioneAccademico", new RegistrazioneSTTForm());
			}
			
			if (!model.containsAttribute("registrazioneAziendale")) {
				model.addAttribute("registrazioneAziendale", new RegistrazioneSTTForm());
			}
		
		return "Home";
	}
}
