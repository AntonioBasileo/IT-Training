package it.unisa.di.ittraining.progettoformativo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import it.unisa.di.ittraining.azienda.TutorAziendale;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;

@Entity
public class ProgettoFormativo {

	public ProgettoFormativo() {
		
	}
	
	 @Id
	 private Long id;
	 
	 @ManyToOne
	 private TutorAziendale tutorAziendale;
	 
	 @OneToOne
	 private DomandaTirocinio domanda;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TutorAziendale getTutorAziendale() {
		return tutorAziendale;
	}

	public void setTutorAziendale(TutorAziendale tutorAziendale) {
		this.tutorAziendale = tutorAziendale;
	}

	public DomandaTirocinio getDomanda() {
		return domanda;
	}

	public void setDomanda(DomandaTirocinio domanda) {
		this.domanda = domanda;
	}
	 
}
