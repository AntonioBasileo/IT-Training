package it.unisa.di.ittraining.web;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.unisa.di.ittraining.azienda.AziendaService;
import it.unisa.di.ittraining.azienda.IndirizzoNonValidoException;
import it.unisa.di.ittraining.azienda.SedeNonValidaException;
import it.unisa.di.ittraining.azienda.TelefonoNonValidoException;
import it.unisa.di.ittraining.utente.NomeNonValidoException;
import it.unisa.di.ittraining.utente.UtenteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AggiungiEnteFormValidator implements Validator {
	  
	  @Autowired
	  private UtenteService utenteService;
	  
	  @Autowired
	  private AziendaService aziendaService;
	  
	  

	  
	  /**
	   * Permette di definire le classi cui il validatore è applicabile.
	   */
	  @Override
	  public boolean supports(Class<?> clazz) {
	    return AggiungiEnteForm.class.isAssignableFrom(clazz);
	  }
	  
	  @Override
	  public void validate(Object target, Errors errors) {
		  AggiungiEnteForm form = (AggiungiEnteForm) target;
		  
		  try {
			utenteService.validaNome(form.getNome());
		} catch (NomeNonValidoException e) {
			// TODO Auto-generated catch block
			errors.rejectValue("nome", "form.nome.nonValido");
		}
		  
		 try {
			aziendaService.validaIndirizzo(form.getIndirizzo());
		} catch (IndirizzoNonValidoException e) {
			// TODO Auto-generated catch block
			errors.rejectValue("indirizzo", "form.indirizzo.nonValido");
		}
		  
		 try {
			aziendaService.validaSede(form.getSede());
		} catch (SedeNonValidaException e) {
			// TODO Auto-generated catch block
			errors.rejectValue("sede", "form.sede.nonValido");
		}
		  
		 try {
			aziendaService.validaTelefono(form.getTelefono());
		} catch (TelefonoNonValidoException e) {
			// TODO Auto-generated catch block
			errors.rejectValue("telefono", "form.telefono.nonValido");
		} 			  
		 
	  }
}
