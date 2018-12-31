package it.unisa.di.ittraining.impiegatosegreteria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisa.di.ittraining.utente.EmailEsistenteException;
import it.unisa.di.ittraining.utente.EmailNonValidaException;

@Service
public class ImpiegatoSegreteriaService {

	@Autowired
	private ImpiegatoSegreteriaRepository rep;
	
	@Transactional(rollbackFor = Exception.class)
	public ImpiegatoSegreteria registraImpiegato(ImpiegatoSegreteria impiegato) {
		    
		    impiegato = rep.save(impiegato);
		    
		    return impiegato;
		  
	  }
	
	public String validaEmailImpiegato(String email) throws EmailNonValidaException, EmailEsistenteException {
		if(email == null) throw new EmailNonValidaException("Il campo email non può essere nullo");
		
		if(!email.matches(ImpiegatoSegreteria.EMAIL_PATTERN_SEGRETERIA)) throw new EmailNonValidaException("Il campo email non rispetta il formato indicato");
		
		if(rep.existsByEmail(email)) throw new EmailEsistenteException("L'email è già utilizzata da un altro impiegato");
		
		return email;
	}
}
