package it.unisa.di.ittraining.domandatirocinio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import it.unisa.di.ittraining.azienda.Azienda;
import it.unisa.di.ittraining.progettoformativo.ProgettoFormativo;
import it.unisa.di.ittraining.studente.Studente;
import it.unisa.di.ittraining.tutoraccademico.TutorAccademico;

@Entity
public class DomandaTirocinio {
	
	public DomandaTirocinio() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Studente studente;

	@ManyToOne
	private TutorAccademico tutorAccademico;
	
	@ManyToOne
	private Azienda azienda;
	  
	@OneToOne
	private ProgettoFormativo progettoFormativo;
	
	public TutorAccademico getTutorAccademico() {
		return tutorAccademico;
	}

	public void setTutorAccademico(TutorAccademico tutorAccademico) {
		this.tutorAccademico = tutorAccademico;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Studente getStudente() {
		return studente;
	}

	public void setStudente(Studente studente) {
		this.studente = studente;
	}

	public ProgettoFormativo getProgettoFormativo() {
		return progettoFormativo;
	}

	public void setProgettoFormativo(ProgettoFormativo progettoFormativo) {
		this.progettoFormativo = progettoFormativo;
	}
	
}
