package it.unisa.di.ittraining.tutoraccademico;

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
import it.unisa.di.ittraining.utente.UtenteService;

@Service
public class TutorAccademicoService {

	@Autowired
	private TutorAccademicoRepository rep;
	
	@Autowired
	private UtenteService utentiService;
	
	@Autowired
	private StudenteRepository studenteRep;
	
	@Transactional(rollbackFor = Exception.class)
	public TutorAccademico registraTutorAccademico(TutorAccademico tutor) throws NomeNonValidoException, NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException,
	CognomeNonValidoException, EmailNonValidaException, EmailEsistenteException, TelefonoNonValidoException, DataDiNascitaNonValidaException, PasswordNonValidaException,
	PasswordNonCorrispondentiException, SessoNonValidoException, UsernameNonValidoException, UsernameEsistenteException {
		
		tutor.setNome(utentiService.validaNome(tutor.getNome()));
		tutor.setCognome(utentiService.validaCognome(tutor.getCognome()));
		tutor.setEmail(validaEmailAccademico(tutor.getEmail()));
		tutor.setTelefono(utentiService.validaTelefono(tutor.getTelefono()));
		tutor.setDataDiNascita(utentiService.validaDataDiNascita(tutor.getDataDiNascita()));
		tutor.setPassword(utentiService.validaPassword(tutor.getPassword()));
		tutor.setSesso(utentiService.validaSesso(tutor.getSesso()));
		tutor.setUsername(utentiService.validaUsername(tutor.getUsername()));
		
		rep.save(tutor);
		
		return tutor;
	}
	
	public void associaTutorAccademico(String op) {
		
		TutorAccademico tutor = rep.findByUsername(op);
		Studente studente = (Studente)utentiService.getUtenteAutenticato();	
		
		studente.setTutor(tutor);
		tutor.getStudenti().add(studente);
		
		studenteRep.save(studente);
		rep.save(tutor);
		
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
}
