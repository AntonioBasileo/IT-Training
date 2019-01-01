package it.unisa.di.ittraining.progettoformativo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import it.unisa.di.ittraining.azienda.Azienda;
import it.unisa.di.ittraining.azienda.TutorAziendale;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;

@Entity
public class ProgettoFormativo {

	public ProgettoFormativo() {
		
	}
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Long id;
	 
	 @ManyToOne
	 private TutorAziendale tutorAziendale;
	 
	 @OneToOne(cascade = CascadeType.ALL, mappedBy = "progettoFormativo")
	 private DomandaTirocinio domanda;
	 
	 @ManyToOne
	 private Azienda azienda;
	 
	 private String descrizione;

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

	public Azienda getAzienda() {
		return azienda;
	}

	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	 
}
