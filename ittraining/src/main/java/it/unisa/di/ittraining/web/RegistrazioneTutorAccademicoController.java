package it.unisa.di.ittraining.web;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.unisa.di.ittraining.tutoraccademico.TutorAccademico;
import it.unisa.di.ittraining.tutoraccademico.TutorAccademicoService;
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
public class RegistrazioneTutorAccademicoController {
	
	@Autowired
	private RegistrazioneSTTFormValidator validator;
	
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private TutorAccademicoService tutorService;

	@RequestMapping(value = "/richiesta-registrazione-accademico", method = RequestMethod.POST)
	public String elaboraRichiestaIscrizione(@ModelAttribute("registrazioneAccademico") RegistrazioneSTTForm registrazioneAccademico, BindingResult result) throws PasswordNonValidaException,
	PasswordNonCorrispondentiException, UsernameNonValidoException, UsernameEsistenteException, EmailEsistenteException, EmailNonValidaException, NomeNonValidoException,
	SessoNonValidoException, CognomeNonValidoException, DataDiNascitaNonValidaException {
		
		validator.validate(registrazioneAccademico, result);
		
	    // Genera un oggetto LocalDate a partire dagli interi presenti nel form
	    LocalDate date = LocalDate.of(registrazioneAccademico.getAnnoNascita(),
	    		registrazioneAccademico.getMeseNascita(),
	    		registrazioneAccademico.getGiornoNascita());
	    
	    if(utenteService.validaPasswords(registrazioneAccademico.getPassword(), registrazioneAccademico.getConfermaPassword())) {
	    	
	    	TutorAccademico tutor = new TutorAccademico();
	    	tutor.setNome(registrazioneAccademico.getNome());
	    	tutor.setCognome(registrazioneAccademico.getCognome());
	    	tutor.setEmail(registrazioneAccademico.getEmail());
	    	tutor.setDataDiNascita(date);
	    	tutor.setUsername(registrazioneAccademico.getUsername());
	    	tutor.setPassword(registrazioneAccademico.getPassword());
	    	tutor.setSesso(registrazioneAccademico.getSesso());
			
	    	tutorService.registraTutorAccademico(tutor);
	    	
	    }
	    
	    return "Home";
	}
}
