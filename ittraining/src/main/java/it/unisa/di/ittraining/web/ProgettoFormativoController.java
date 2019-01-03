package it.unisa.di.ittraining.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinioService;
import it.unisa.di.ittraining.progettoformativo.ProgettoFormativo;
import it.unisa.di.ittraining.progettoformativo.ProgettoFormativoService;
import it.unisa.di.ittraining.utente.UtenteService;

@Controller
public class ProgettoFormativoController {

	@Autowired
	private ProgettoFormativoService progettiService;

	@Autowired
	private UtenteService utentiService;
	
	@Autowired
	private DomandaTirocinioService domandeService;
	
	@RequestMapping(value = "/inserisci-progetto", method = RequestMethod.POST)
	public String inserisciProgetto(@ModelAttribute("progettoFormAccetta") ProgettoFormativoForm progettoForm, RedirectAttributes redirectAttributes) {

		if(utentiService.getUtenteAutenticato() == null || !(utentiService.getUtenteAutenticato().getClass().getSimpleName().equals("TutorAziendale")))
			return "not-available";
		
		
		ProgettoFormativo progetto = new ProgettoFormativo();
		progetto.setDescrizione(progettoForm.getDescrizione());
		
		progettiService.inserisciProgetto(progetto, progettoForm.getIdDomanda());
		
		redirectAttributes.addFlashAttribute("testoNotifica", "toast.domanda.accettata");
		
		return "redirect:/mostra-domande-aziendale";
	}
	
	@RequestMapping(value = "/approva-progetto", method = RequestMethod.GET)
	public String approvaProgetto(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		

		if(utentiService.getUtenteAutenticato() == null || !(utentiService.getUtenteAutenticato().getClass().getSimpleName().equals("TutorAccademico")))
			return "not-available";
		

		domandeService.aggiornaStatoDomanda(id, DomandaTirocinio.APPROVATA);
		
		redirectAttributes.addFlashAttribute("testoNotifica", "toast.progettoFormativo.approvato");
		
		return "redirect:/mostra-domande-accademico";
	}
	
	@RequestMapping(value = "/rifiuta-progetto", method = RequestMethod.GET)
	public String rifiutaProgetto(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		

		if(utentiService.getUtenteAutenticato() == null || !(utentiService.getUtenteAutenticato().getClass().getSimpleName().equals("TutorAccademico")))
			return "not-available";
		
		
		domandeService.aggiornaStatoDomanda(id, DomandaTirocinio.PROGETTO_RIFIUTATO);
		
		redirectAttributes.addFlashAttribute("testoNotifica", "toast.progettoFormativo.rifiutato");
		
		return "redirect:/mostra-domande-accademico";
	}
}
