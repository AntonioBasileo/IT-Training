package it.unisa.di.ittraining.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		
		if(!model.containsAttribute("listaDomandeAccettate"))
				model.addAttribute("listaDomandeAccettate", domandeService.elencaDomandeStudenteStatus((String)session.getAttribute("username"), DomandaTirocinio.IN_ATTESA));
		
		return "registri";
	}
}
