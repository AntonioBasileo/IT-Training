package it.unisa.di.ittraining.progettoformativo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;

@Entity
public class ProgettoFormativo {

	public ProgettoFormativo() {
		
	}
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Long id;
	 
	 @OneToOne(cascade = CascadeType.ALL, mappedBy = "progettoFormativo")
	 private DomandaTirocinio domanda;
	 
	 private String descrizione;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DomandaTirocinio getDomanda() {
		return domanda;
	}

	public void setDomanda(DomandaTirocinio domanda) {
		if(this.domanda != domanda) {
			this.domanda = domanda;
			domanda.addProgettoFormativo(this);
		}
		
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	 
}
