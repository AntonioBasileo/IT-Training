package it.unisa.di.ittraining.web;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinioService;
import it.unisa.di.ittraining.registrotirocinio.DataRegistroNonValidaException;
import it.unisa.di.ittraining.registrotirocinio.DataRegistroPrecedenteInizioException;
import it.unisa.di.ittraining.registrotirocinio.DataRegistroSuccessivaFineException;
import it.unisa.di.ittraining.registrotirocinio.OrarioFinePrecedenteInizioException;
import it.unisa.di.ittraining.registrotirocinio.OrarioNonValidoException;
import it.unisa.di.ittraining.registrotirocinio.Registro;
import it.unisa.di.ittraining.registrotirocinio.RegistroService;
import it.unisa.di.ittraining.utente.UtenteService;

@Controller
public class RegistriController {

	@Autowired
	private RegistroService registriService;
	
	@Autowired
	private DomandaTirocinioService domandeService;
	
	@Autowired
	private RegistroFormValidator validator;
	
	@Autowired
	private UtenteService utentiService;
	
	
	@RequestMapping(value = "/domande-registri", method = RequestMethod.GET)
	public String showRegistri(HttpSession session, Model model) {

		if(utentiService.getUtenteAutenticato() == null || !(utentiService.getUtenteAutenticato().getClass().getSimpleName().equals("Studente")))
			return "not-available";
		
		if(!model.containsAttribute("listaDomandeApprovate")) {
			List<DomandaTirocinio> domande = domandeService.elencaDomandeStudenteStatus((String)session.getAttribute("username"), DomandaTirocinio.APPROVATA);
			
			model.addAttribute("listaDomandeApprovate", domande);
			
			model.addAttribute("registroForm", new RegistroForm());
		}
			
		
		return "registri-domande";
	}
	
	@RequestMapping(value = "/registro-form", method = RequestMethod.GET)
	public String showRegistroForm(@RequestParam Long id, Model model) {

		if(utentiService.getUtenteAutenticato() == null || !(utentiService.getUtenteAutenticato().getClass().getSimpleName().equals("Studente")))
			return "not-available";
		
		DomandaTirocinio domanda = domandeService.getDomandaById(id);
		
		model.addAttribute("domanda", domanda);
		model.addAttribute("registroForm", new RegistroForm());
		
		return "compila-registro";
		
	}
	
	@RequestMapping(value = "/compila-registro", method = RequestMethod.POST)
	public String compilaRegistro(@ModelAttribute("registroForm") RegistroForm registroForm, BindingResult result, Model model, RedirectAttributes redirectAttributes, HttpSession session)
			throws DataRegistroSuccessivaFineException, DataRegistroPrecedenteInizioException, DataRegistroNonValidaException, OrarioNonValidoException, OrarioFinePrecedenteInizioException {
		
		if(!model.containsAttribute("listaDomandeApprovate")) {
			List<DomandaTirocinio> domande = domandeService.elencaDomandeStudenteStatus((String)session.getAttribute("username"), DomandaTirocinio.APPROVATA);
			
			model.addAttribute("listaDomandeApprovate", domande);
		}
		
		validator.validate(registroForm, result);
		
	    if (result.hasErrors()) {
		  
	      redirectAttributes
	          .addFlashAttribute("org.springframework.validation.BindingResult.registroForm",
	                             result);
	      
	      redirectAttributes.addFlashAttribute("registroForm", registroForm);
	      
	      if(!model.containsAttribute("testoNotifica"))
	    	  model.addAttribute("testoNotifica", "toast.registro.nonValido");
	      
	      return "compila-registro";
	    }
	    
	    Registro registro = new Registro();
		
	    LocalDate data = LocalDate.of(registroForm.getAnno(), registroForm.getMese(), registroForm.getGiorno());
		LocalTime inizio = LocalTime.of(registroForm.getOraInizio(), registroForm.getMinutoInizio());
		LocalTime fine = LocalTime.of(registroForm.getOraFine(), registroForm.getMinutoFine());
		
		
	    registro.setData(data);
	    registro.setDescrizione(registroForm.getDescrizione());
	    registro.setInizio(inizio);
	    registro.setFine(fine);
	    
	    registro.setNumero_minuti(((ChronoUnit.MILLIS.between(inizio, fine) / 1000) / 60));
		
	    registriService.registraTirocinio(registro, registroForm.getId_domanda());
	    
		redirectAttributes.addFlashAttribute("testoNotifica", "toast.registro.valido");
		
		return "redirect:/registro-form?id=" + registroForm.getId_domanda();
	}
	
	
	@RequestMapping(value = "/registri-segreteria", method = RequestMethod.GET)
	public String showRegistriSegreteria(HttpSession session, Model model) {

		if(utentiService.getUtenteAutenticato() == null || !(utentiService.getUtenteAutenticato().getClass().getSimpleName().equals("ImpiegatoSegreteria")))
			return "not-available";
		
		if(!model.containsAttribute("listaDomandeRegistri")) {
			List<DomandaTirocinio> domande = domandeService.getAllByStatus(DomandaTirocinio.APPROVATA);
			
			model.addAttribute("listaDomandeRegistri", domande);
		}
			
		
		return "registri-segreteria";
	}
}
