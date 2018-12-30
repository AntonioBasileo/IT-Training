package it.unisa.di.ittraining.utente;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisa.di.ittraining.azienda.TutorAziendaleRepository;
import it.unisa.di.ittraining.impiegatosegreteria.ImpiegatoSegreteriaRepository;
import it.unisa.di.ittraining.studente.StudenteRepository;
import it.unisa.di.ittraining.tutoraccademico.TutorAccademicoRepository;
import it.unisa.di.ittraining.utente.CognomeNonValidoException;
import it.unisa.di.ittraining.utente.NomeNonValidoException;
import it.unisa.di.ittraining.utente.SessoNonValidoException;
import it.unisa.di.ittraining.utente.DataDiNascitaNonValidaException;

@Service
public class UtenteService {

	 @Autowired
	 private UtenteRepository utenteRepository;
	 
	 @Autowired
	  private StudenteRepository studenteRepository;
	  
	 @Autowired
	 private TutorAziendaleRepository delegatoRepository;
	  
	 @Autowired
	 private ImpiegatoSegreteriaRepository impiegatoRepository;
	 
	 @Autowired
	 private TutorAccademicoRepository accademicoRepository;

	  
	 /** 
	  * Costante che rappresenta la minima distanza in anni dalla data corrente 
	  * per la data di nascita.
	  */
	 public static final int MIN_DISTANZA_ANNO_NASCITA = 17;
	 
	 /** 
	  * Costante che rappresenta la massima distanza in anni dalla data corrente 
	  * per la data di nascita.
	  */
	 public static final int MAX_DISTANZA_ANNO_NASCITA = 130;
	
	  
	  
	  public String validaNome(String nome) throws NomeNonValidoException {
	    if (nome == null) {
	      throw new NomeNonValidoException();
	    } else {
	      nome = nome.trim();
	      
	      if (nome.length() < Utente.MIN_LUNGHEZZA_NOME
	          || nome.length() > Utente.MAX_LUNGHEZZA_NOME) {
	        throw new NomeNonValidoException();
	      } else {
	        return nome;
	      }
	    }
	  }
	  
	  
	  public String validaCognome(String cognome) throws CognomeNonValidoException {
	    if (cognome == null) {
	      throw new CognomeNonValidoException();
	      
	    } else {
	      cognome = cognome.trim();
	      
	      if (cognome.length() < Utente.MIN_LUNGHEZZA_NOME
	          || cognome.length() > Utente.MAX_LUNGHEZZA_NOME) {
	        throw new CognomeNonValidoException();
	      } else {
	        return cognome;
	      }
	    }
	  }
	

	  public String validaSesso(String sesso) throws SessoNonValidoException {
		   if (sesso == null) {
		     throw new SessoNonValidoException();
		     
		   } else {
		     sesso = sesso.trim();
		      
		     if (!sesso.equals(Utente.SESSO_MASCHILE)
		         && !sesso.equals(Utente.SESSO_FEMMINILE)) {
		       throw new SessoNonValidoException();
		     } else {
		       return sesso;
		     }
		   }
		}
	

	 public LocalDate validaDataDiNascita(LocalDate dataDiNascita) 
	        throws DataDiNascitaNonValidaException {
	         
	    if (dataDiNascita == null) {
	      throw new DataDiNascitaNonValidaException();
	      
	    } else {
	    	
	      LocalDate oggi = LocalDate.now();
	      long distanza = ChronoUnit.YEARS.between(dataDiNascita, oggi);
	      
	      if (distanza < MIN_DISTANZA_ANNO_NASCITA || distanza > MAX_DISTANZA_ANNO_NASCITA) {
	        throw new DataDiNascitaNonValidaException();
	      } else {
	        return dataDiNascita;
	     } 
	  }
	}
		
	public String validaUsername(String username) throws UsernameNonValidoException, UsernameEsistenteException {
		if(username == null) throw new UsernameNonValidoException("Il campo username è nullo");
			
		else
		username = username.trim();
			
		if(!username.matches(Utente.USERNAME_PATTERN)) throw new UsernameNonValidoException("Lo username non rispetta il formato.");
			
		else if(utenteRepository.existsByUsername(username)) throw new UsernameEsistenteException("Username già utilizzato.");
			
		return username;
			
	}
	
	public String validaEmail(String email) throws EmailEsistenteException, EmailNonValidaException {
		if(email ==  null) throw new EmailNonValidaException("Il campo email è nullo.");
		
		if(utenteRepository.existsByEmail(email)) throw new EmailEsistenteException("Email già in uso.");
		
		if(!email.matches(Utente.EMAIL_PATTERN)) throw new EmailNonValidaException("Il campo email non rispetta il formato stabilito.");
		
		return email;
	}
	
	public boolean validaPasswords(String password, String confirmPassword) throws PasswordNonValidaException, PasswordNonCorrispondentiException {
		if(password == null || confirmPassword == null) throw new PasswordNonValidaException("Il campo password oppure il campo conferma password sono nulli");
		
		if(!password.matches(Utente.PASSWORD_PATTERN) || !confirmPassword.matches(Utente.PASSWORD_PATTERN)) throw new PasswordNonValidaException("Il campo password oppure il campo conferma password non rispettano il formato previsto");
		
		if(!password.equals(confirmPassword)) throw new PasswordNonCorrispondentiException("Le due password non corrispondono");
		
		return true;
	}
	
	public String validaPassword(String password) throws PasswordNonValidaException, PasswordNonCorrispondentiException {
		if(password == null) throw new PasswordNonValidaException("STUDENTE SERVICE - Il campo password non è valido");
		
		if(!password.matches(Utente.PASSWORD_PATTERN)) throw new PasswordNonValidaException("STUDENTE SERVICE - Non rispecchia il formato");
		
		return password;
	}
	
	public Utente getUtenteAutenticato() {  
	    // Ottieni l'username dell'utente autenticato e restituisci null se non è presente alcun utente in sessione
	    String username = (AutenticazioneHolder.getUtente());
	    if (username == null) {
	    	
	      return null;
	    }
	    
	    Utente utente;
	    
	    // Controlla se l'username è associato ad un impiegato di segreteria
	    utente = impiegatoRepository.findByUsername(username);
	    if (utente != null) {
	    	
	      return utente;
	    }
	    
	    // Controlla se l'username è associato ad un tutor aziendale
	    utente = delegatoRepository.findByUsername(username);
	    if (utente != null) {
	    	
	      return utente;
	    }
	    
	    // Controlla se l'username è associato ad un tutor accademico
	    utente = accademicoRepository.findByUsername(username);
	    if(utente != null) {
	    	
	    	return utente;
	    }
	    
	    // Controlla se l'username è associato ad uno studente
	    utente = studenteRepository.findByUsername(username);
	    if (utente != null) {
	    	
	      return utente;
	    }
	    
	    // Dead code
	    return null;
	  }
	  
	  
	  public void setUtenteAutenticato(String username) {
	    // Se username è null, rimuovi la variabile di thread per prevenire memory leak
	    if (username == null) {
	      AutenticazioneHolder.setUtente(null);
	      return;
	    }
	    
	    if (utenteRepository.existsByUsername(username)) {
	      AutenticazioneHolder.setUtente(username);
	    }
	    
	  }
	  
	  public void login(String username, String password) throws UsernameNonEsistenteException, PasswordErrataException {
		  Utente utente;
		  
		  if(!utenteRepository.existsByUsername(username)) throw new UsernameNonEsistenteException();
		  
		  utente = studenteRepository.findByUsernameAndPassword(username, password);
		  
		  if(utente != null) {
			  AutenticazioneHolder.setUtente(username);
			  
			  return;
		  }
		  
		  utente = impiegatoRepository.findByUsernameAndPassword(username, password);
		  
		  if(utente != null) {
			  AutenticazioneHolder.setUtente(username);
			  
			  return;
		  }
		  
		  utente = accademicoRepository.findByUsernameAndPassword(username, password);
		  
		  if(utente != null) {
			  AutenticazioneHolder.setUtente(username);
			  
			  return;
		  }
		  
		  utente = delegatoRepository.findByUsernameAndPassword(username, password);
		  
		  if(utente != null) {
			  AutenticazioneHolder.setUtente(username);
			  
			  return;
		  }
		  
		  throw new PasswordErrataException();
	  }
	  
	  /**
	   * Permette la rimozione dell'utente dalla sessione.
	   */
	  public void logout() { 
		  AutenticazioneHolder.setUtente(null);
	  }
	
}
