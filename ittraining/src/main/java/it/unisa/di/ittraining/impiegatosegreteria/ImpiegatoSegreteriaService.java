package it.unisa.di.ittraining.impiegatosegreteria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisa.di.ittraining.studente.MatricolaStudenteEsistenteException;
import it.unisa.di.ittraining.studente.MatricolaStudenteNonValidaException;
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

@Service
public class ImpiegatoSegreteriaService {

	@Autowired
	private ImpiegatoSegreteriaRepository rep;
	
	@Autowired
	private UtenteService service;
	
	@Transactional(rollbackFor = Exception.class)
	public ImpiegatoSegreteria registraImpiegato(ImpiegatoSegreteria impiegato) throws UsernameNonValidoException, UsernameEsistenteException, PasswordNonValidaException, 
	  PasswordNonCorrispondentiException, EmailEsistenteException, EmailNonValidaException, NomeNonValidoException, CognomeNonValidoException, SessoNonValidoException, 
	  MatricolaStudenteNonValidaException, MatricolaStudenteEsistenteException, DataDiNascitaNonValidaException {
		  
		    // Valida i campi dell'impiegato
			impiegato.setUsername(service.validaUsername(impiegato.getUsername()));
		    impiegato.setPassword(service.validaPassword(impiegato.getPassword()));
		    impiegato.setEmail(service.validaEmail(impiegato.getEmail()));
		    impiegato.setNome(service.validaNome(impiegato.getNome()));
		    impiegato.setCognome(service.validaCognome(impiegato.getCognome()));
		    impiegato.setSesso(service.validaSesso(impiegato.getSesso()));
		    impiegato.setDataDiNascita(service.validaDataDiNascita(impiegato.getDataDiNascita()));
		    
		    impiegato = rep.save(impiegato);
		    
		    return impiegato;
		  
	  }
}
