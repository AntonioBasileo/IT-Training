package it.unisa.di.ittraining.utente;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisa.di.ittraining.utente.CognomeNonValidoException;
import it.unisa.di.ittraining.utente.NomeNonValidoException;
import it.unisa.di.ittraining.utente.SessoNonValidoException;
import it.unisa.di.ittraining.utente.DataDiNascitaNonValidaException;

@Service
public class UtenteService {

	 @Autowired
	 private UtenteRepository utenteRepository;
	
	  
	  /**
	   * Controlla che il nome di un utente sia specificato e che la sua lunghezza rispetti i parametri
	   * prestabiliti.
	   * 
	   * @param nome Stringa che rappresenta il nome da controllare
	   * 
	   * @return La stringa che rappresenta il nome da controllare bonificata
	   * 
	   * @throws NomeNonValidoException se il nome è nullo oppure se la sua lunghezza non rientra
	   *         nell'intervallo che va da {@link UtenteRegistrato#MIN_LUNGHEZZA_NOME} a
	   *         {@link UtenteRegistrato#MAX_LUNGHEZZA_NOME}
	   */
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
	  
	  /**
	   * Controlla che il cognome di un utente sia specificato e che la sua lunghezza rispetti i
	   * parametri prestabiliti.
	   * 
	   * @param cognome Stringa che rappresenta il cognome da controllare
	   * 
	   * @return La stringa che rappresenta il cognome da controllare bonificata
	   * 
	   * @throws CognomeNonValidoException se il cognome è nullo oppure se la sua lunghezza non rientra
	   *         nell'intervallo che va da {@link UtenteRegistrato#MIN_LUNGHEZZA_NOME} a
	   *         {@link UtenteRegistrato#MAX_LUNGHEZZA_NOME}
	   */
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
	
}
