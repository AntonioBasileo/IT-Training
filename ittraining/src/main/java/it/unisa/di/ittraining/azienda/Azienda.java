package it.unisa.di.ittraining.azienda;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import it.unisa.di.ittraining.azienda.TutorAziendale;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.progettoformativo.ProgettoFormativo;

import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Azienda {
	
	@Id
	private String nome;
	private String sede;
	private String indirizzo;
	private String telefono;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "azienda")
	private List<TutorAziendale> tutorAziendali;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "azienda")
	private List<DomandaTirocinio> domande;
	
	@OneToMany
	private List<ProgettoFormativo> progetti;

	  
	/** Costante che definisce la minima lunghezza del campo nome. */
	public static final int MIN_LUNGHEZZA_NOME = 2;
	  
	/** Costante che definisce la massima lunghezza del campo nome. */
	public static final int MAX_LUNGHEZZA_NOME = 255;
	
	  
	/** Costante che definisce la minima lunghezza del campo nome. */
	public static final int MIN_LUNGHEZZA_SEDE = 2;
		  
	/** Costante che definisce la massima lunghezza del campo nome. */
	public static final int MAX_LUNGHEZZA_SEDE = 35;
	  
	/** Costante che definisce la minima lunghezza del campo indirizzo. */
	public static final int MIN_LUNGHEZZA_INDIRIZZO = 2;
	  
    /** Costante che definisce la massima lunghezza del campo indirizzo. */
	public static final int MAX_LUNGHEZZA_INDIRIZZO = 255;
	
	/** Costante che definisce il formato del campo telefono*/
	public static final String TELEFONO_PATTERN = "^[0-9]{2,4}[0-9]{4,7}$";

	public Azienda() {
		
	}
	
	public List<TutorAziendale> getTutorAziendali() {
		return tutorAziendali;
	}

	public void setTutorAziendali(List<TutorAziendale> tutorAziendali) {
		this.tutorAziendali = tutorAziendali;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSede() {
		return sede;
	}
	
	public void setSede(String sede) {
		this.sede = sede;
	}
	
	public String getIndirizzo() {
		return indirizzo;
	}
	
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<DomandaTirocinio> getDomande() {
		return domande;
	}

	public void setDomande(List<DomandaTirocinio> domande) {
		this.domande = domande;
	}

}
