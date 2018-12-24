package it.unisa.di.ittraining.web;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.unisa.di.ittraining.azienda.AziendaService;
import it.unisa.di.ittraining.azienda.TutorAziendale;
import it.unisa.di.ittraining.utente.CognomeNonValidoException;
import it.unisa.di.ittraining.utente.DataDiNascitaNonValidaException;
import it.unisa.di.ittraining.utente.EmailEsistenteException;
import it.unisa.di.ittraining.utente.EmailNonValidaException;
import it.unisa.di.ittraining.utente.NomeNonValidoException;
import it.unisa.di.ittraining.utente.PasswordNonCorrispondentiException;
import it.unisa.di.ittraining.utente.PasswordNonValidaException;
import it.unisa.di.ittraining.utente.SessoNonValidoException;
import it.unisa.di.ittraining.utente.UsernameEsistenteException;
import it.unisa.di.ittraining.utente.UsernameNonValidoException;
import it.unisa.di.ittraining.utente.UtenteService;

@Controller
public class RegistrazioneTutorAziendaleController {

	@Autowired
	private RegistrazioneSTTFormValidator validator;
	
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private AziendaService tutorService;
	
	@RequestMapping(value = "/richiesta-registrazione-aziendale", method = RequestMethod.POST)
	public String elaboraRichiestaIscrizione(@ModelAttribute("registrazioneAziendale") RegistrazioneSTTForm registrazioneAziendale, BindingResult result) throws PasswordNonValidaException,
	PasswordNonCorrispondentiException, UsernameNonValidoException, UsernameEsistenteException, EmailEsistenteException, EmailNonValidaException,
	NomeNonValidoException, CognomeNonValidoException, SessoNonValidoException, DataDiNascitaNonValidaException {
		
		validator.validate(registrazioneAziendale, result);
		
		// Genera un oggetto LocalDate a partire dagli interi presenti nel form
	    LocalDate date = LocalDate.of(registrazioneAziendale.getAnnoNascita(),
	    		registrazioneAziendale.getMeseNascita(),
	    		registrazioneAziendale.getGiornoNascita());
	    
	    if(utenteService.validaPasswords(registrazioneAziendale.getPassword(), registrazioneAziendale.getConfermaPassword())) {
	    	
	    	TutorAziendale tutor = new TutorAziendale();
	    	tutor.setNome(registrazioneAziendale.getNome());
	    	tutor.setCognome(registrazioneAziendale.getCognome());
	    	tutor.setEmail(registrazioneAziendale.getEmail());
	    	tutor.setDataDiNascita(date);
	    	tutor.setUsername(registrazioneAziendale.getUsername());
	    	tutor.setPassword(registrazioneAziendale.getPassword());
	    	tutor.setSesso(registrazioneAziendale.getSesso());
			
	    	tutorService.registraTutorAziendale(tutor);
	    	
	    }
	    
	    return "Home";
	}
}
