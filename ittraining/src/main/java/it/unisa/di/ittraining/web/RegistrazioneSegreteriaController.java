package it.unisa.di.ittraining.web;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.unisa.di.ittraining.impiegatosegreteria.ImpiegatoSegreteria;
import it.unisa.di.ittraining.impiegatosegreteria.ImpiegatoSegreteriaService;
import it.unisa.di.ittraining.studente.MatricolaStudenteEsistenteException;
import it.unisa.di.ittraining.studente.MatricolaStudenteNonValidaException;
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
public class RegistrazioneSegreteriaController {
	
	@Autowired
	private RegistrazioneSTTFormValidator validator;

	@Autowired
	private ImpiegatoSegreteriaService segreteriaService;
	
	@Autowired
	private UtenteService utentiService;

	@RequestMapping(value = "/richiesta-registrazione-segreteria", method = RequestMethod.POST)
	public String elaboraRichiestaIscrizione(@ModelAttribute("registrazioneSegreteria") RegistrazioneSTTForm registrazioneForm, BindingResult result) throws UsernameNonValidoException, 
	UsernameEsistenteException, PasswordNonValidaException, PasswordNonCorrispondentiException, EmailEsistenteException, EmailNonValidaException, NomeNonValidoException, 
	CognomeNonValidoException, SessoNonValidoException, MatricolaStudenteNonValidaException, MatricolaStudenteEsistenteException, DataDiNascitaNonValidaException {
		
		validator.validate(registrazioneForm, result);
		
		// Genera un oggetto LocalDate a partire dagli interi presenti nel form
	    LocalDate date = LocalDate.of(registrazioneForm.getAnnoNascita(),
	    		registrazioneForm.getMeseNascita(),
	    		registrazioneForm.getGiornoNascita());
	    
		if(utentiService.validaPasswords(registrazioneForm.getPassword(), registrazioneForm.getConfermaPassword())) {
			
			ImpiegatoSegreteria impiegato = new ImpiegatoSegreteria();
			impiegato.setNome(registrazioneForm.getNome());
			impiegato.setCognome(registrazioneForm.getCognome());
			impiegato.setSesso(registrazioneForm.getSesso());
			impiegato.setEmail(registrazioneForm.getEmail());
			impiegato.setUsername(registrazioneForm.getUsername());
			impiegato.setPassword(registrazioneForm.getPassword());
			impiegato.setDataDiNascita(date);
			
			segreteriaService.registraImpiegato(impiegato);
			
		}
		
		
		return "redirect:/home";
	}
}
