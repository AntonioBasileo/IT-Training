package it.unisa.di.ittraining.web;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.unisa.di.ittraining.utente.CognomeNonValidoException;
import it.unisa.di.ittraining.utente.DataDiNascitaNonValidaException;
import it.unisa.di.ittraining.utente.EmailEsistenteException;
import it.unisa.di.ittraining.utente.EmailNonValidaException;
import it.unisa.di.ittraining.utente.NomeNonValidoException;
import it.unisa.di.ittraining.utente.PasswordNonCorrispondentiException;
import it.unisa.di.ittraining.utente.PasswordNonValidaException;
import it.unisa.di.ittraining.utente.SessoNonValidoException;
import it.unisa.di.ittraining.utente.TelefonoNonValidoException;
import it.unisa.di.ittraining.utente.UsernameEsistenteException;
import it.unisa.di.ittraining.utente.UsernameNonValidoException;
import it.unisa.di.ittraining.utente.UtenteService;

import java.time.DateTimeException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrazioneFormValidator implements Validator {
	  
	  @Autowired
	  private UtenteService utentiService;

	  
	  /**
	   * Permette di definire le classi cui il validatore è applicabile.
	   */
	  @Override
	  public boolean supports(Class<?> clazz) {
	    return RegistrazioneForm.class.isAssignableFrom(clazz);
	  }

	  @Override
	  public void validate(Object target, Errors errors) {
		  RegistrazioneForm form = (RegistrazioneForm) target;
		  
		  try {
			  utentiService.validaNome(form.getNome());
		} catch (NomeNonValidoException e) {
			// TODO Auto-generated catch block
			errors.rejectValue("nome", "formRegistrazione.nome.nonValido");
		}
		  
		  try {
			  utentiService.validaCognome(form.getCognome());
		} catch (CognomeNonValidoException e) {
			// TODO Auto-generated catch block
			errors.rejectValue("cognome", "formRegistrazione.cognome.nonValido");
		}
		  
		  try {
			utentiService.validaTelefono(form.getTelefono());
		} catch (TelefonoNonValidoException e1) {
			// TODO Auto-generated catch block
			errors.rejectValue("telefono", "formRegistrazione.telefono.nonValido");
		}
		  
		  try {
			  utentiService.validaUsername(form.getUsername());
		} catch (UsernameNonValidoException e) {
			// TODO Auto-generated catch block
			errors.rejectValue("username", "formRegistrazione.username.nonValido");
		} catch (UsernameEsistenteException e) {
			// TODO Auto-generated catch block
			errors.rejectValue("username", "formRegistrazione.username.usernameEsistente");
		}
		  
		  try {
			  utentiService.validaEmail(form.getEmail());
		} catch (EmailEsistenteException e) {
			// TODO Auto-generated catch block
			errors.rejectValue("email", "formRegistrazioneEsistente.email.emailEsistente");
		} catch (EmailNonValidaException e) {
			// TODO Auto-generated catch block
			errors.rejectValue("email", "formRegistrazione.email.nonValida");
		}
		  
		  try {
			  utentiService.validaPasswords(form.getPassword(), form.getConfermaPassword());
		} catch (PasswordNonValidaException e) {
			// TODO Auto-generated catch block
			errors.rejectValue("password", "formRegistrazione.password.passwordsNonValide");
		} catch (PasswordNonCorrispondentiException e) {
			// TODO Auto-generated catch block
			errors.rejectValue("password", "formRegistrazione.confermaPassword.nonCorrispondenti");
		}
		  
		  try {
			  utentiService.validaSesso(form.getSesso());
		} catch (SessoNonValidoException e) {
			// TODO Auto-generated catch block
			errors.rejectValue("sesso", "formRegistrazione.sesso.nonValido");
		}
		  
		  try {
		      if (form.getAnnoNascita() == null
		          || form.getMeseNascita() == null
		          || form.getGiornoNascita() == null) {
		        throw new DataDiNascitaNonValidaException();
		      }
		      
		      LocalDate data = LocalDate.of(form.getAnnoNascita(),
		                                    form.getMeseNascita(), 
		                                    form.getGiornoNascita());
		      
		      utentiService.validaDataDiNascita(data);
		    } catch (DataDiNascitaNonValidaException | DateTimeException e) {
		    	errors.rejectValue("giornoNascita", "formRegistrazione.data.nonValida");
		    } 
	  }
}
