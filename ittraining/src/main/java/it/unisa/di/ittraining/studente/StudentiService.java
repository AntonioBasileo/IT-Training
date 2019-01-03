package it.unisa.di.ittraining.studente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisa.di.ittraining.studente.Studente;
import it.unisa.di.ittraining.studente.StudenteRepository;
import it.unisa.di.ittraining.utente.EmailEsistenteException;
import it.unisa.di.ittraining.utente.EmailNonValidaException;
import it.unisa.di.ittraining.studente.MatricolaStudenteEsistenteException;
import it.unisa.di.ittraining.studente.MatricolaStudenteNonValidaException;

@Service
public class StudentiService {
	  
	  @Autowired
	  private StudenteRepository studenteRepository;
	  
	  @Transactional(rollbackFor = Exception.class)
	  public Studente registraStudente(Studente studente) {
		    
		    studente = studenteRepository.save(studente);
		    
		    return studente;
		  
	  }
	  
	  public void cancellaStudente(Studente studente) {
		  studenteRepository.delete(studente);
	  }
	  
	  public boolean existsByMatricola(String matricola) {
		  return studenteRepository.existsByMatricola(matricola);
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
