package it.unisa.di.ittraining.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.unisa.di.ittraining.azienda.Azienda;
import it.unisa.di.ittraining.azienda.AziendaEsistenteException;
import it.unisa.di.ittraining.azienda.AziendaNonValidaException;
import it.unisa.di.ittraining.azienda.AziendaService;
import it.unisa.di.ittraining.azienda.EmailAziendaEsistenteException;
import it.unisa.di.ittraining.azienda.IndirizzoNonValidoException;
import it.unisa.di.ittraining.azienda.SedeNonValidaException;
import it.unisa.di.ittraining.azienda.TelefonoNonValidoException;
import it.unisa.di.ittraining.utente.EmailNonValidaException;
import it.unisa.di.ittraining.utente.NomeNonValidoException;
import it.unisa.di.ittraining.utente.UtenteService;

@Controller
public class ConvenzioneController {
	
	@Autowired
	private ConvenzioneFormValidator validator;
	
	@Autowired
	private AziendaService aziendaService;
	
	@Autowired
	private UtenteService utentiService;
	
	@RequestMapping(value = "/lista-enti", method = RequestMethod.GET)
	public String showListaEnti(Model model) {
		

		if(utentiService.getUtenteAutenticato() == null || !(utentiService.getUtenteAutenticato().getClass().getSimpleName().equals("Studente")))
			return "not-available";
		
		if(!model.containsAttribute("listaAziende"))
			model.addAttribute("listaAziende", aziendaService.elancaAziende());
		
		
		return "lista-enti";
	}
	
	@RequestMapping(value = "/convenzione", method = RequestMethod.GET)
	public String mostraFormConvenzione(Model model) {
		

		if(utentiService.getUtenteAutenticato() == null || !(utentiService.getUtenteAutenticato().getClass().getSimpleName().equals("ImpiegatoSegreteria")))
			return "not-available";
		
		if (!model.containsAttribute("convenzioneForm")) {
			model.addAttribute("convenzioneForm", new ConvenzioneForm());
		}
		
		return "aggiungi-ente";
	}

	@RequestMapping( value = "/aggiungi-ente", method = RequestMethod.POST)
	public String aggiungiEnte(@ModelAttribute("convenzioneForm") ConvenzioneForm convenzioneForm, Model model, BindingResult result, RedirectAttributes redirectAttributes) 
			throws IndirizzoNonValidoException, NomeNonValidoException, SedeNonValidaException, AziendaNonValidaException, AziendaEsistenteException,
			EmailNonValidaException, TelefonoNonValidoException, EmailAziendaEsistenteException {
		
		validator.validate(convenzioneForm, result);
		
		if (result.hasErrors()) {
		      redirectAttributes
		          .addFlashAttribute("org.springframework.validation.BindingResult.convenzioneForm",
		                             result);
		      redirectAttributes.addFlashAttribute("convenzioneForm", convenzioneForm);
		      
				
		      redirectAttributes.addFlashAttribute("testoNotifica", "toast.convenzione.nonValida");
		      
		      return "redirect:/convenzione";
		 }
		
		Azienda azienda = new Azienda();
		
		azienda.setNome(convenzioneForm.getNome());
		azienda.setIndirizzo(convenzioneForm.getIndirizzo());
		azienda.setSede(convenzioneForm.getSede());
		azienda.setTelefono(convenzioneForm.getTelefono());
		azienda.setEmail(convenzioneForm.getEmail());
		
		aziendaService.registraAzienda(azienda);
		
		redirectAttributes.addFlashAttribute("testoNotifica", "toast.convenzione.valida");
		
		return "redirect:/home";
	}
}
