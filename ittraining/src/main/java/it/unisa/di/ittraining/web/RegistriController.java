package it.unisa.di.ittraining.web;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinioService;
import it.unisa.di.ittraining.registrotirocinio.RegistroService;

@Controller
public class RegistriController {

	@Autowired
	private RegistroService registriService;
	
	@Autowired
	private DomandaTirocinioService domandeService;
	
	
	@RequestMapping(value = "/registro-form", method = RequestMethod.GET)
	public String showRegistri(HttpSession session, Model model) {
		
		if(!model.containsAttribute("listaDomandeApprovate")) {
			List<DomandaTirocinio> domande = domandeService.elencaDomandeStudenteStatus((String)session.getAttribute("username"), DomandaTirocinio.APPROVATA);
			
			model.addAttribute("listaDomandeApprovate", domande);
			
			model.addAttribute("registroForm", new RegistroForm());
		}
			
		
		return "registri";
	}
	
	
	@RequestMapping(value = "/compila-registro", method = RequestMethod.POST)
	public String compilaRegistro(@ModelAttribute("registroForm") RegistroForm registroForm, BindingResult result) {

		System.out.println("Oggi è: " + LocalDateTime.of(registroForm.getAnno(), registroForm.getMese(), registroForm.getGiorno(), registroForm.getOraInizio(), registroForm.getMinutoInizio()));
		
		return "redirect:/registro-form";
	}
}
