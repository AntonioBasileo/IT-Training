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
import it.unisa.di.ittraining.utente.NomeNonValidoException;
import it.unisa.di.ittraining.utente.PasswordNonCorrispondentiException;
import it.unisa.di.ittraining.utente.PasswordNonValidaException;
import it.unisa.di.ittraining.utente.SessoNonValidoException;
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
	  private UtenteService utenteService;
	  
	  @Transactional(rollbackFor = Exception.class)
	  public Studente registraStudente(Studente studente) throws UsernameNonValidoException, UsernameEsistenteException, PasswordNonValidaException, 
	  PasswordNonCorrispondentiException, EmailEsistenteException, EmailNonValidaException, NomeNonValidoException, CognomeNonValidoException, SessoNonValidoException, 
	  MatricolaStudenteNonValidaException, MatricolaStudenteEsistenteException, DataDiNascitaNonValidaException {
		  
		    // Valida i campi dello studente
		    studente.setUsername(utenteService.validaUsername(studente.getUsername()));
		    studente.setPassword(utenteService.validaPassword(studente.getPassword()));
		    studente.setEmail(utenteService.validaEmail(studente.getEmail()));
		    studente.setNome(utenteService.validaNome(studente.getNome()));
		    studente.setCognome(utenteService.validaCognome(studente.getCognome()));
		    studente.setSesso(utenteService.validaSesso(studente.getSesso()));
		    studente.setMatricola(validaMatricolaStudente(studente.getMatricola()));
		    studente.setDataDiNascita(utenteService.validaDataDiNascita(studente.getDataDiNascita()));
		    
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
