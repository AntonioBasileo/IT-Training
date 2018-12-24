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
import it.unisa.di.ittraining.utente.UsernameEsistenteException;
import it.unisa.di.ittraining.utente.UsernameNonValidoException;
import it.unisa.di.ittraining.utente.UtenteService;
import it.unisa.di.ittraining.web.RegistrazioneStudenteForm;

import java.time.DateTimeException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrazioneStudenteFormValidator implements Validator {
	  
	  @Autowired
	  private UtenteService utenteService;

	  
	  /**
	   * Permette di definire le classi cui il validatore è applicabile.
	   */
	  @Override
	  public boolean supports(Class<?> clazz) {
	    return RegistrazioneStudenteForm.class.isAssignableFrom(clazz);
	  }
	  
	  @Override
	  public void validate(Object target, Errors errors) {
		  RegistrazioneStudenteForm form = (RegistrazioneStudenteForm) target;
		  
		  try {
			utenteService.validaNome(form.getNome());
		} catch (NomeNonValidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  try {
			utenteService.validaCognome(form.getCognome());
		} catch (CognomeNonValidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  try {
			utenteService.validaUsername(form.getUsername());
		} catch (UsernameNonValidoException | UsernameEsistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  try {
			utenteService.validaEmail(form.getEmail());
		} catch (EmailEsistenteException | EmailNonValidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  try {
			utenteService.validaPasswords(form.getPassword(), form.getConfermaPassword());
		} catch (PasswordNonValidaException | PasswordNonCorrispondentiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  try {
			utenteService.validaSesso(form.getSesso());
		} catch (SessoNonValidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		      
		      utenteService.validaDataDiNascita(data);
		    } catch (DataDiNascitaNonValidaException | DateTimeException e) {
		      e.printStackTrace();
		    } 
	  }
}
