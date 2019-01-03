package it.unisa.di.ittraining.impiegatosegreteria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

@Service
public class ImpiegatoSegreteriaService {

	@Autowired
	private ImpiegatoSegreteriaRepository rep;
	
	@Autowired
	private UtenteService utentiService;
	
	@Transactional(rollbackFor = Exception.class)
	public ImpiegatoSegreteria registraImpiegato(ImpiegatoSegreteria impiegato) throws NomeNonValidoException, NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException,
	CognomeNonValidoException, EmailNonValidaException, EmailEsistenteException, PasswordNonValidaException, PasswordNonCorrispondentiException, DataDiNascitaNonValidaException,
	UsernameNonValidoException, UsernameEsistenteException, SessoNonValidoException, TelefonoNonValidoException {
		
			impiegato.setNome(utentiService.validaNome(impiegato.getNome()));
			impiegato.setCognome(utentiService.validaCognome(impiegato.getCognome()));
			impiegato.setEmail(validaEmailImpiegato(impiegato.getEmail()));
			impiegato.setPassword(utentiService.validaPassword(impiegato.getPassword()));
			impiegato.setDataDiNascita(utentiService.validaDataDiNascita(impiegato.getDataDiNascita()));
			impiegato.setUsername(utentiService.validaUsername(impiegato.getUsername()));
			impiegato.setSesso(utentiService.validaSesso(impiegato.getSesso()));
			impiegato.setTelefono(utentiService.validaTelefono(impiegato.getTelefono()));
		    
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
