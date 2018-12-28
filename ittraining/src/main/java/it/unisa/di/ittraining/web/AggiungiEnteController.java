package it.unisa.di.ittraining.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.unisa.di.ittraining.azienda.Azienda;
import it.unisa.di.ittraining.azienda.AziendaService;
import it.unisa.di.ittraining.azienda.IndirizzoNonValidoException;
import it.unisa.di.ittraining.azienda.SedeNonValidaException;
import it.unisa.di.ittraining.utente.NomeNonValidoException;

@Controller
public class AggiungiEnteController {
	
	@Autowired
	private AggiungiEnteFormValidator validator;
	
	@Autowired
	private AziendaService aziendaService;
	
	@RequestMapping("/convenzione")
	public String mostraFormConvenzione(Model model) {
		
		if (!model.containsAttribute("convenzioneForm")) {
			model.addAttribute("convenzioneForm", new AggiungiEnteForm());
		}
		
		return "aggiungi-ente";
	}

	@RequestMapping("/segreteria/aggiungi-ente")
	public String aggiungiEnte(@ModelAttribute("convenzioneForm") AggiungiEnteForm convenzioneForm, BindingResult result, RedirectAttributes redirectAttributes) 
			throws IndirizzoNonValidoException, NomeNonValidoException, SedeNonValidaException {
		
		validator.validate(convenzioneForm, result);
		
		if (result.hasErrors()) {
		      redirectAttributes
		          .addFlashAttribute("org.springframework.validation.BindingResult.convenzioneForm",
		                             result);
		      redirectAttributes.addFlashAttribute("convenzioneForm", convenzioneForm);
		      
		      return "redirect:/convenzione";
		 }
		
		Azienda azienda = new Azienda();
		
		azienda.setNome(convenzioneForm.getNome());
		azienda.setIndirizzo(convenzioneForm.getIndirizzo());
		azienda.setSede(convenzioneForm.getSede());
		azienda.setTelefono(convenzioneForm.getTelefono());
		
		aziendaService.registraAzienda(azienda);
		
		return "redirect:/convenzione";
	}
}
