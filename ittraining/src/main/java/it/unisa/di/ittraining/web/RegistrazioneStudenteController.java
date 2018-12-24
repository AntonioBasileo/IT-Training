package it.unisa.di.ittraining.web;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.unisa.di.ittraining.studente.StudentiService;
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
import it.unisa.di.ittraining.studente.MatricolaStudenteEsistenteException;
import it.unisa.di.ittraining.studente.MatricolaStudenteNonValidaException;
import it.unisa.di.ittraining.studente.Studente;

@Controller
public class RegistrazioneStudenteController {
	
	@Autowired
	private StudentiService studentiService;
	
	@Autowired
	private RegistrazioneStudenteFormValidator validator;
	
	@Autowired
	private UtenteService utentiService;

	@RequestMapping(value = "/richiesta-registrazione-studente", method = RequestMethod.POST)
	public String elaboraRichiestaIscrizione(@ModelAttribute("RegistrazioneStudenteForm") RegistrazioneStudenteForm RegistrazioneStudenteForm, BindingResult result) 
			throws UsernameNonValidoException, UsernameEsistenteException, PasswordNonValidaException, 
			PasswordNonCorrispondentiException, EmailEsistenteException, EmailNonValidaException, NomeNonValidoException, 
			CognomeNonValidoException, SessoNonValidoException, MatricolaStudenteNonValidaException, MatricolaStudenteEsistenteException, DataDiNascitaNonValidaException {
		
		validator.validate(RegistrazioneStudenteForm, result);
		
	    // Genera un oggetto LocalDate a partire dagli interi presenti nel form
	    LocalDate date = LocalDate.of(RegistrazioneStudenteForm.getAnnoNascita(),
	    		RegistrazioneStudenteForm.getMeseNascita(),
	    		RegistrazioneStudenteForm.getGiornoNascita());
	    
	    if(utentiService.validaPasswords(RegistrazioneStudenteForm.getPassword(), RegistrazioneStudenteForm.getConfermaPassword())) {
	    	
	    	Studente studente = new Studente();
		    studente.setNome(RegistrazioneStudenteForm.getNome());
		    studente.setCognome(RegistrazioneStudenteForm.getCognome());
		    studente.setEmail(RegistrazioneStudenteForm.getEmail());
		    studente.setDataDiNascita(date);
		    studente.setMatricola(RegistrazioneStudenteForm.getMatricola());
		    studente.setUsername(RegistrazioneStudenteForm.getUsername());
		    studente.setPassword(RegistrazioneStudenteForm.getPassword());
		    studente.setSesso(RegistrazioneStudenteForm.getSesso());
			
			studentiService.registraStudente(studente);
			
			return "redirect:/registrazione-successo";
	    	
	    }
	    
	    
		return "redirect:/home";	
	}
}
