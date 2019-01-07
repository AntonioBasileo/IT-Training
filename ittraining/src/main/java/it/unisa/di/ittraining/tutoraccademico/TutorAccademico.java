package it.unisa.di.ittraining.tutoraccademico;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.studente.Studente;
import it.unisa.di.ittraining.utente.Utente;

@Entity
public class TutorAccademico extends Utente {
	  
	/** Espressione regolare che definisce il formato del campo email per impiegati di segreteria e tutor accademici. */
	public static final String EMAIL_PATTERN_ACCADEMICO = "[A-z0-9\\.\\+_-]+@unisa.it";
	
	@OneToMany
	private List<Studente> studenti;
	
	public TutorAccademico() {
		this.studenti = new ArrayList<>();
	}

	public List<Studente> getStudenti() {
		return studenti;
	}

	public void addStudente(Studente studente) {
		if(!studenti.contains(studente)) {
			studenti.add(studente);
			studente.setTutor(this);
		}
	}
	
	public List<DomandaTirocinio> getAllDomande() {
		List<DomandaTirocinio> newList = new ArrayList<>();
		
		for(Studente s: studenti) 
			newList.addAll(s.getDomandeTirocinio());
		
		return newList;
	}
	
	
}
