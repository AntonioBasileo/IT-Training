package it.unisa.di.ittraining.azienda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class AziendaService {

	@Autowired
	private TutorAziendaleRepository repTutor;
	
	@Autowired
	private AziendaRepository repAzienda;
	
	@Autowired
	private UtenteService service;
	
	@Transactional(rollbackFor = Exception.class)
	public TutorAziendale registraTutorAziendale(TutorAziendale tutor) throws UsernameNonValidoException, UsernameEsistenteException, PasswordNonValidaException,
	PasswordNonCorrispondentiException, EmailEsistenteException, EmailNonValidaException, NomeNonValidoException, CognomeNonValidoException,
	SessoNonValidoException, DataDiNascitaNonValidaException {
		
		//Valida i campi del tutor
		tutor.setUsername(service.validaUsername(tutor.getUsername()));
		tutor.setPassword(service.validaPassword(tutor.getPassword()));
		tutor.setEmail(service.validaEmail(tutor.getEmail()));
		tutor.setNome(service.validaNome(tutor.getNome()));
		tutor.setCognome(service.validaCognome(tutor.getCognome()));
		tutor.setSesso(service.validaSesso(tutor.getSesso()));
		tutor.setDataDiNascita(service.validaDataDiNascita(tutor.getDataDiNascita()));
				
		repTutor.save(tutor);
				
		return tutor;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Azienda registraAzienda(Azienda azienda) throws IndirizzoNonValidoException, NomeNonValidoException, SedeNonValidaException {
		
		//valida i campi dell'azienda
		azienda.setNome(service.validaNome(azienda.getNome()));
		azienda.setIndirizzo(validaIndirizzo(azienda.getIndirizzo()));
		azienda.setSede(validaSede(azienda.getSede()));
		azienda.setTelefono(azienda.getTelefono());
		
		repAzienda.save(azienda);
		
		return azienda;
	}
	
	public String validaTelefono(String telefono) throws TelefonoNonValidoException {
		if(telefono == null) throw new TelefonoNonValidoException("Il campo telefono non può essere nullo.");
		
		if(!telefono.matches(Azienda.TELEFONO_PATTERN)) throw new TelefonoNonValidoException("Il campo telefono non è valido.");
		
		return telefono;
	}
	
	public String validaIndirizzo(String indirizzo) throws IndirizzoNonValidoException {
		if(indirizzo == null) throw new IndirizzoNonValidoException("Il campo indirizzo non può essere nullo");
		
		if(indirizzo.length() < Azienda.MIN_LUNGHEZZA_INDIRIZZO || indirizzo.length() > Azienda.MAX_LUNGHEZZA_INDIRIZZO) throw new IndirizzoNonValidoException("Il campo indirizzo non è valido.");
		
		return indirizzo;
	}
	
	public String validaSede(String sede) throws SedeNonValidaException {
		if(sede == null) throw new SedeNonValidaException("Il campo sede non può essere nullo.");
		
		if(sede.length() < Azienda.MIN_LUNGHEZZA_SEDE || sede.length() > Azienda.MAX_LUNGHEZZA_SEDE) throw new SedeNonValidaException("Il campo sede non è valido.");
		
		return sede;
	}
	
}
