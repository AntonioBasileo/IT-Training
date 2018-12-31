package it.unisa.di.ittraining.web;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.unisa.di.ittraining.azienda.AziendaService;
import it.unisa.di.ittraining.azienda.IndirizzoNonValidoException;
import it.unisa.di.ittraining.azienda.SedeNonValidaException;
import it.unisa.di.ittraining.azienda.TelefonoNonValidoException;
import it.unisa.di.ittraining.utente.NomeNonValidoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConvenzioneFormValidator implements Validator {
	  
	  @Autowired
	  private AziendaService aziendaService;

	  
	  /**
	   * Permette di definire le classi cui il validatore è applicabile.
	   */
	  @Override
	  public boolean supports(Class<?> clazz) {
	    return ConvenzioneForm.class.isAssignableFrom(clazz);
	  }
	  
	  @Override
	  public void validate(Object target, Errors errors) {
		  ConvenzioneForm form = (ConvenzioneForm) target;
		  
		  try {
			  aziendaService.validaNome(form.getNome());
		} catch (NomeNonValidoException e) {
			// TODO Auto-generated catch block
			errors.rejectValue("nome", "formConvenzione.nome.nonValido");
		}
		  
		 try {
			aziendaService.validaIndirizzo(form.getIndirizzo());
		} catch (IndirizzoNonValidoException e) {
			// TODO Auto-generated catch block
			errors.rejectValue("indirizzo", "formConvenzione.indirizzo.nonValido");
		}
		  
		 try {
			aziendaService.validaSede(form.getSede());
		} catch (SedeNonValidaException e) {
			// TODO Auto-generated catch block
			errors.rejectValue("sede", "formConvenzione.sede.nonValida");
		}
		  
		 try {
			aziendaService.validaTelefono(form.getTelefono());
		} catch (TelefonoNonValidoException e) {
			// TODO Auto-generated catch block
			errors.rejectValue("telefono", "formConvenzione.telefono.nonValido");
		} 			  
		 
	  }
}
