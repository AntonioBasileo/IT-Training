package it.unisa.di.ittraining.web;

import java.time.DateTimeException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.unisa.di.ittraining.tutoraccademico.TutorAccademicoService;
import it.unisa.di.ittraining.utente.CognomeNonValidoException;
import it.unisa.di.ittraining.utente.DataDiNascitaNonValidaException;
import it.unisa.di.ittraining.utente.EmailEsistenteException;
import it.unisa.di.ittraining.utente.EmailNonValidaException;
import it.unisa.di.ittraining.utente.NomeCognomeTroppoCortoException;
import it.unisa.di.ittraining.utente.NomeCognomeTroppoLungoException;
import it.unisa.di.ittraining.utente.NomeNonValidoException;
import it.unisa.di.ittraining.utente.PasswordNonCorrispondentiException;
import it.unisa.di.ittraining.utente.PasswordNonValidaException;
import it.unisa.di.ittraining.utente.SessoNonValidoException;
import it.unisa.di.ittraining.utente.TelefonoNonValidoException;
import it.unisa.di.ittraining.utente.UsernameEsistenteException;
import it.unisa.di.ittraining.utente.UsernameNonValidoException;

@Component
public class RegistrazioneAccademicoFormValidator implements Validator {
	  
	  @Autowired
	  private TutorAccademicoService tutorService;

	  
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
			  tutorService.validaNome(form.getNome());
		} catch (NomeNonValidoException e) {
			// TODO Auto-generated catch block
			errors.rejectValue("nome", "formRegistrazione.nome.nonValido");
		} catch (NomeCognomeTroppoLungoException e) {
			// TODO Auto-generated catch block
			errors.rejectValue("nome", "fformRegistrazione.nome.lungo");
		} catch (NomeCognomeTroppoCortoException e) {
			// TODO Auto-generated catch block
			errors.rejectValue("nome", "formRegistrazione.nome.corto");
		}
		  
		  try {
			  tutorService.validaCognome(form.getCognome());
		} catch (CognomeNonValidoException e) {
			// TODO Auto-generated catch block
			errors.rejectValue("cognome", "formRegistrazione.cognome.nonValido");
		} catch (NomeCognomeTroppoLungoException e) {
			// TODO Auto-generated catch block
			errors.rejectValue("cognome", "formRegistrazione.cognome.lungo");
		} catch (NomeCognomeTroppoCortoException e) {
			// TODO Auto-generated catch block
			errors.rejectValue("cognome", "formRegistrazione.cognome.corto");
		}
		  
		  try {
			  tutorService.validaTelefono(form.getTelefono());
		} catch (TelefonoNonValidoException e1) {
			// TODO Auto-generated catch block
			errors.rejectValue("telefono", "formRegistrazione.telefono.nonValido");
		}
		  
		  try {
			  tutorService.validaUsername(form.getUsername());
		} catch (UsernameNonValidoException e) {
			// TODO Auto-generated catch block
			errors.rejectValue("username", "formRegistrazione.username.nonValido");
		} catch (UsernameEsistenteException e) {
			// TODO Auto-generated catch block
			errors.rejectValue("username", "formRegistrazione.username.usernameEsistente");
		}
		  
		  try {
			  tutorService.validaEmailAccademico(form.getEmail());
		} catch (EmailEsistenteException e) {
			// TODO Auto-generated catch block
			errors.rejectValue("email", "formRegistrazioneEsistente.email.accademico.emailEsistente");
		} catch (EmailNonValidaException e) {
			// TODO Auto-generated catch block
			errors.rejectValue("email", "formRegistrazione.email.accademico.nonValida");
		}
		  
		  try {
			  tutorService.validaPasswords(form.getPassword(), form.getConfermaPassword());
		} catch (PasswordNonValidaException e) {
			// TODO Auto-generated catch block
			errors.rejectValue("password", "formRegistrazione.password.passwordsNonValide");
		} catch (PasswordNonCorrispondentiException e) {
			// TODO Auto-generated catch block
			errors.rejectValue("password", "formRegistrazione.confermaPassword.nonCorrispondenti");
		}
		  
		  try {
			  tutorService.validaSesso(form.getSesso());
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
		      
		      tutorService.validaDataDiNascita(data);
		    } catch (DataDiNascitaNonValidaException | DateTimeException e) {
		    	errors.rejectValue("giornoNascita", "formRegistrazione.data.nonValida");
		    } 
	  }

}
