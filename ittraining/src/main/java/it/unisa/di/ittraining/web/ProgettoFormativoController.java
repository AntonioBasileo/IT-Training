package it.unisa.di.ittraining.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public String inserisciProgetto(@ModelAttribute("progettoForm") ProgettoFormativoForm progettoForm) {

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
		
		
		return "redirect:/lista-domande-aziendale";
	}
}
