package it.unisa.di.ittraining.tutoraccademico;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisa.di.ittraining.studente.Studente;
import it.unisa.di.ittraining.studente.StudenteRepository;
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
import it.unisa.di.ittraining.utente.Utente;
import it.unisa.di.ittraining.utente.UtenteRepository;
import it.unisa.di.ittraining.utente.UtenteService;

@Service
public class TutorAccademicoService {

	@Autowired
	private TutorAccademicoRepository rep;
	
	@Autowired
	private UtenteService utentiService;
	
	@Autowired
	private StudenteRepository studenteRep;
	
	@Autowired
	private UtenteRepository utenteRep;

	  
	  
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
	  
	
	@Transactional(rollbackFor = Exception.class)
	public TutorAccademico registraTutorAccademico(TutorAccademico tutor) throws NomeNonValidoException, NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException,
	CognomeNonValidoException, EmailNonValidaException, EmailEsistenteException, TelefonoNonValidoException, DataDiNascitaNonValidaException, PasswordNonValidaException,
	PasswordNonCorrispondentiException, SessoNonValidoException, UsernameNonValidoException, UsernameEsistenteException {
		
		tutor.setNome(validaNome(tutor.getNome()));
		tutor.setCognome(validaCognome(tutor.getCognome()));
		tutor.setEmail(validaEmailAccademico(tutor.getEmail()));
		tutor.setTelefono(validaTelefono(tutor.getTelefono()));
		tutor.setDataDiNascita(validaDataDiNascita(tutor.getDataDiNascita()));
		tutor.setPassword(validaPassword(tutor.getPassword()));
		tutor.setSesso(validaSesso(tutor.getSesso()));
		tutor.setUsername(validaUsername(tutor.getUsername()));
		
		rep.save(tutor);
		
		return tutor;
	}
	
	public void associaTutorAccademico(String op) {
		
		TutorAccademico tutor = rep.findByUsername(op);
		Studente studente = (Studente)utentiService.getUtenteAutenticato();	
		
		studente.setTutor(tutor);
		
		studenteRep.save(studente);
		
	}
	
	public List<TutorAccademico> elencaTutorAccademici() {
		
		return rep.findAll();
	}
	
	public TutorAccademico findByUsername(String username) {
		
		return rep.findByUsername(username);
	}
	
	public String validaEmailAccademico(String email) throws EmailNonValidaException, EmailEsistenteException {
		if(email == null) throw new EmailNonValidaException("Il campo email non può essere nullo");
		
		if(!email.matches(TutorAccademico.EMAIL_PATTERN_ACCADEMICO)) throw new EmailNonValidaException("Il campo email non rispetta il formato indicato");
		
		if(rep.existsByEmail(email)) throw new EmailEsistenteException("L'email è già associata ad un altro tutor accademico");
		
		return email;
	}
	
	  
	  
	  public String validaNome(String nome) throws NomeNonValidoException, NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException {
		  if(nome == null) throw new NomeNonValidoException();
		  
		  if(nome.length() > Utente.MAX_LUNGHEZZA_NOME) throw new NomeCognomeTroppoLungoException();
		  
		  if(nome.length() < Utente.MIN_LUNGHEZZA_NOME) throw new NomeCognomeTroppoCortoException();
		  
		  if(!nome.matches(Utente.NOME_PATTERN)) throw new NomeNonValidoException("Il campo nome non rispetta il formato indicato");
		  
		  return nome;
	  }
	  
	  
	  public String validaCognome(String cognome) throws CognomeNonValidoException, NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException {
	    if (cognome == null) throw new CognomeNonValidoException();
		  
		  if(cognome.length() > Utente.MAX_LUNGHEZZA_NOME) throw new NomeCognomeTroppoLungoException();
		  
		  if(cognome.length() < Utente.MIN_LUNGHEZZA_NOME) throw new NomeCognomeTroppoCortoException();
	    
	    if(!cognome.matches(Utente.COGNOME_PATTERN)) throw new CognomeNonValidoException("Il cognome non rispetta il formato indicato");
	    
	    return cognome;
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
	 
	public String validaTelefono(String telefono) throws TelefonoNonValidoException {
		if(telefono == null) throw new TelefonoNonValidoException("Il campo telefono non può essere nullo");
		
		if(!telefono.matches(Utente.TELEFONO_PATTERN)) throw new TelefonoNonValidoException("Il campo telefono non è valido");
		
		return telefono;
	}
		
	public String validaUsername(String username) throws UsernameNonValidoException, UsernameEsistenteException {
		if(username == null) throw new UsernameNonValidoException("Il campo username è nullo");
			
		else
		username = username.trim();
			
		if(!username.matches(Utente.USERNAME_PATTERN)) throw new UsernameNonValidoException("Lo username non rispetta il formato.");
			
		else if(utenteRep.existsByUsername(username)) throw new UsernameEsistenteException("Username già utilizzato.");
			
		return username;
			
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
}
