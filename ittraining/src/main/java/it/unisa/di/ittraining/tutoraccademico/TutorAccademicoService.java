package it.unisa.di.ittraining.tutoraccademico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisa.di.ittraining.utente.EmailEsistenteException;
import it.unisa.di.ittraining.utente.EmailNonValidaException;

@Service
public class TutorAccademicoService {

	@Autowired
	private TutorAccademicoRepository rep;
	
	@Transactional(rollbackFor = Exception.class)
	public TutorAccademico registraTutorAccademico(TutorAccademico tutor) {
		
		rep.save(tutor);
		
		return tutor;
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
