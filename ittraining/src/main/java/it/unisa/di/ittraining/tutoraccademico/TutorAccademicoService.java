package it.unisa.di.ittraining.tutoraccademico;

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
public class TutorAccademicoService {

	@Autowired
	private TutorAccademicoRepository rep;
	
	@Autowired
	private UtenteService service;
	
	@Transactional(rollbackFor = Exception.class)
	public TutorAccademico registraTutorAccademico(TutorAccademico tutor) throws UsernameNonValidoException, UsernameEsistenteException, PasswordNonValidaException,
	PasswordNonCorrispondentiException, EmailEsistenteException, EmailNonValidaException, NomeNonValidoException, SessoNonValidoException,
	CognomeNonValidoException, DataDiNascitaNonValidaException {
		
		//Valida i campi del tutor
		tutor.setUsername(service.validaUsername(tutor.getUsername()));
		tutor.setPassword(service.validaPassword(tutor.getPassword()));
		tutor.setEmail(service.validaEmail(tutor.getEmail()));
		tutor.setNome(service.validaNome(tutor.getNome()));
		tutor.setCognome(service.validaCognome(tutor.getCognome()));
		tutor.setSesso(service.validaSesso(tutor.getSesso()));
		tutor.setDataDiNascita(service.validaDataDiNascita(tutor.getDataDiNascita()));
		
		rep.save(tutor);
		
		return tutor;
	}
}
