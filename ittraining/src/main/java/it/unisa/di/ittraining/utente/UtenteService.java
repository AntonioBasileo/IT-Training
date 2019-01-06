package it.unisa.di.ittraining.utente;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisa.di.ittraining.azienda.TutorAziendaleRepository;
import it.unisa.di.ittraining.impiegatosegreteria.ImpiegatoSegreteriaRepository;
import it.unisa.di.ittraining.studente.StudenteRepository;
import it.unisa.di.ittraining.tutoraccademico.TutorAccademicoRepository;

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

	

		
		public boolean validaPasswords(String password, String confirmPassword) throws PasswordNonValidaException, PasswordNonCorrispondentiException {
			if(password == null || confirmPassword == null) throw new PasswordNonValidaException("Il campo password oppure il campo conferma password sono nulli");
			
			if(!password.matches(Utente.PASSWORD_PATTERN) || !confirmPassword.matches(Utente.PASSWORD_PATTERN)) throw new PasswordNonValidaException("Il campo password oppure il campo conferma password non rispettano il formato previsto");
			
			if(!password.equals(confirmPassword)) throw new PasswordNonCorrispondentiException("Le due password non corrispondono");
			
			return true;
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
