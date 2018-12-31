package it.unisa.di.ittraining.studente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisa.di.ittraining.studente.Studente;
import it.unisa.di.ittraining.studente.StudenteRepository;
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
	  
}
