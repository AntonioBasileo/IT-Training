package it.unisa.di.ittraining.tutoraccademico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TutorAccademicoService {

	@Autowired
	private TutorAccademicoRepository rep;
	
	@Transactional(rollbackFor = Exception.class)
	public TutorAccademico registraTutorAccademico(TutorAccademico tutor) {
		
		rep.save(tutor);
		
		return tutor;
	}
}
