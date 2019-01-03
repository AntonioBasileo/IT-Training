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
import javax.persistence.OneToOne;

@Entity
public class Azienda {
	
	@Id
	private String nome;
	private String sede;
	private String indirizzo;
	private String telefono;
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL)
	private TutorAziendale tutor;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "azienda")
	private List<DomandaTirocinio> domande;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "azienda")
	private List<ProgettoFormativo> progetti;

	  
	/** Costante che definisce la minima lunghezza del campo nome. */
	public static final int MIN_LUNGHEZZA_NOME = 3;
	  
	/** Costante che definisce la massima lunghezza del campo nome. */
	public static final int MAX_LUNGHEZZA_NOME = 255;
	
	  
	/** Costante che definisce la minima lunghezza del campo sede. */
	public static final int MIN_LUNGHEZZA_SEDE = 3;
		  
	/** Costante che definisce la massima lunghezza del campo sede. */
	public static final int MAX_LUNGHEZZA_SEDE = 35;
	  
	/** Costante che definisce la minima lunghezza del campo indirizzo. */
	public static final int MIN_LUNGHEZZA_INDIRIZZO = 8;
	  
    /** Costante che definisce la massima lunghezza del campo indirizzo. */
	public static final int MAX_LUNGHEZZA_INDIRIZZO = 255;
	
	/** Costante che definisce il formato del campo telefono*/
	public static final String TELEFONO_PATTERN = "^[0-9]{2,4}[0-9]{4,7}$";
	
	/** Espressione regolare che definisce il formato del campo email aziendale. */
	public static final String EMAIL_PATTERN_AZIENDALE = "[A-z0-9\\.\\+_-]+@[A-z0-9\\._-]+\\.[A-z]{2,6}";

	public Azienda() {
		
	}
	
	public TutorAziendale getTutorAziendale() {
		return tutor;
	}

	public void setTutorAziendale(TutorAziendale tutor) {
		this.tutor = tutor;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TutorAziendale getTutor() {
		return tutor;
	}

	public void setTutor(TutorAziendale tutor) {
		this.tutor = tutor;
	}

	public List<ProgettoFormativo> getProgetti() {
		return progetti;
	}

	public void setProgetti(List<ProgettoFormativo> progetti) {
		this.progetti = progetti;
	}

}
