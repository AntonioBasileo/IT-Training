package it.unisa.di.ittraining.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.unisa.di.ittraining.azienda.TutorAziendale;
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
	public String inserisciProgetto(@ModelAttribute("progettoFormAccetta") ProgettoFormativoForm progettoForm) {

		if(utentiService.getUtenteAutenticato() == null || !(utentiService.getUtenteAutenticato().getClass().getSimpleName().equals("TutorAziendale")))
			return "not-available";
		
		DomandaTirocinio domanda = domandeService.getDomandaById(progettoForm.getIdDomanda());
		ProgettoFormativo progetto = new ProgettoFormativo();
		
		
		progetto.setTutorAziendale((TutorAziendale)utentiService.getUtenteAutenticato());
		progetto.setDescrizione(progettoForm.getDescrizione());
		progetto.setAzienda(((TutorAziendale)utentiService.getUtenteAutenticato()).getAzienda());
		
		progettiService.inserisciProgetto(progetto);
		
		domanda.setStatus(DomandaTirocinio.ACCETTATA_AZIENDA);
		domanda.setProgettoFormativo(progetto);
		
		domandeService.registraDomanda(domanda);
		
		
		return "redirect:/mostra-domande-accademico";
	}
	
	@RequestMapping(value = "/approva-progetto", method = RequestMethod.GET)
	public String approvaProgetto(@RequestParam Long id) {
		

		if(utentiService.getUtenteAutenticato() == null || !(utentiService.getUtenteAutenticato().getClass().getSimpleName().equals("TutorAccademico")))
			return "not-available";
		
		
		DomandaTirocinio domanda = domandeService.getDomandaById(id);
		
		domanda.setStatus(DomandaTirocinio.APPROVATA);
		domandeService.registraDomanda(domanda);
		
		return "redirect:/mostra-domande-accademico";
	}
	
	@RequestMapping(value = "/rifiuta-progetto", method = RequestMethod.GET)
	public String rifiutaProgetto(@RequestParam Long id) {
		

		if(utentiService.getUtenteAutenticato() == null || !(utentiService.getUtenteAutenticato().getClass().getSimpleName().equals("TutorAccademico")))
			return "not-available";
		
		
		DomandaTirocinio domanda = domandeService.getDomandaById(id);
		
		domanda.setStatus(DomandaTirocinio.PROGETTO_RIFIUTATO);
		domandeService.registraDomanda(domanda);
		
		return "redirect:/mostra-domande-accademico";
	}
}
