package it.unisa.di.ittraining.studente;

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
import it.unisa.di.ittraining.utente.UtenteService;
import it.unisa.di.ittraining.studente.MatricolaStudenteEsistenteException;
import it.unisa.di.ittraining.studente.MatricolaStudenteNonValidaException;

@Service
public class StudentiService {
	  
	  @Autowired
	  private StudenteRepository studenteRepository;
	  
	  @Autowired
	  private UtenteService utentiService;
	  
	  @Transactional(rollbackFor = Exception.class)
	  public Studente registraStudente(Studente studente) throws NomeNonValidoException, NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException, CognomeNonValidoException,
	  DataDiNascitaNonValidaException, UsernameNonValidoException, UsernameEsistenteException, EmailNonValidaException, EmailEsistenteException, SessoNonValidoException, TelefonoNonValidoException,
	  MatricolaStudenteNonValidaException, MatricolaStudenteEsistenteException, PasswordNonValidaException, PasswordNonCorrispondentiException {
		  
		  	studente.setNome(utentiService.validaNome(studente.getNome()));
		  	studente.setCognome(utentiService.validaCognome(studente.getCognome()));
		  	studente.setDataDiNascita(utentiService.validaDataDiNascita(studente.getDataDiNascita()));
		  	studente.setUsername(utentiService.validaUsername(studente.getUsername()));
		  	studente.setEmail(validaEmailStudente(studente.getEmail()));
		  	studente.setSesso(utentiService.validaSesso(studente.getSesso()));
		  	studente.setTelefono(utentiService.validaTelefono(studente.getTelefono()));
		  	studente.setMatricola(validaMatricolaStudente(studente.getMatricola()));
		  	studente.setPassword(utentiService.validaPassword(studente.getPassword()));
		    
		    studente = studenteRepository.save(studente);
		    
		    return studente;
		  
	  }
	  
	  
	  public String validaMatricolaStudente(String matricolaStudente)
		         throws MatricolaStudenteNonValidaException, MatricolaStudenteEsistenteException {
		    if (matricolaStudente == null) {
		    	
		      throw new MatricolaStudenteNonValidaException();
		      
		    } else {
		    	
		      matricolaStudente = matricolaStudente.trim();
		      
		      if (!matricolaStudente.matches(Studente.MATRICOLA_PATTERN)) {
		        throw new MatricolaStudenteNonValidaException();
		        
		      } else if (studenteRepository.existsByMatricola(matricolaStudente)) {
		        throw new MatricolaStudenteEsistenteException();
		        
		      } else {
		    	  
		        return matricolaStudente;
		      }
		    }
		  }
	  
	  public String validaEmailStudente(String email) throws EmailNonValidaException, EmailEsistenteException {
		  if(email == null) throw new EmailNonValidaException("Il campo email non può essere nullo");
		  
		  if(!email.matches(Studente.EMAIL_PATTERN_STUDENTE)) throw new EmailNonValidaException();
		  
		  if(studenteRepository.existsByEmail(email)) throw new EmailEsistenteException("L'email è già utilizzata da un altro studente");
		  
		  return email;
	  }
	  
}
