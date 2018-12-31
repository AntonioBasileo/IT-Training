package it.unisa.di.ittraining.studente;

import it.unisa.di.ittraining.utente.Utente;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.registrotirocinio.Registro;
import it.unisa.di.ittraining.tutoraccademico.TutorAccademico;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Studente extends Utente {
	//matricola dello studente
	private String matricola;
	  
	/** Espressione regolare che definisce il formato del campo matricola. */
    public static final String MATRICOLA_PATTERN = "^[0-9]{10}$";
	  
	/** Espressione regolare che definisce il formato del campo email per gli studenti. */
	public static final String EMAIL_PATTERN_STUDENTE = "[A-z0-9\\.\\+_-]+@studenti.unisa.it";
	  
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "studente")
	private List<DomandaTirocinio> domandeTirocinio;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "studente")
	private List<Registro> registri;

	@OneToOne
	private TutorAccademico tutor;
	
	public Studente() {
		this.domandeTirocinio = new ArrayList<DomandaTirocinio>();
	}
	
	public Studente(String nome, String cognome, String matricola, LocalDate dataDiNascita) {
		this.nome = nome;
		this.cognome = cognome;
		this.matricola = matricola;
		this.dataDiNascita = dataDiNascita;
	}
	
	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}
	
	public String getMatricola() {
		return matricola;
	}

	public List<DomandaTirocinio> getDomandeTirocinio() {
		return domandeTirocinio;
	}

	public void setDomandeTirocinio(List<DomandaTirocinio> domandeTirocinio) {
		this.domandeTirocinio = domandeTirocinio;
	}

	public List<Registro> getRegistri() {
		return registri;
	}

	public void setRegistri(List<Registro> registri) {
		this.registri = registri;
	}
	
	public void addDomandaTirocinio(DomandaTirocinio domandaTirocinio) {
	    if (!domandeTirocinio.contains(domandaTirocinio)) {
	    	
	      domandeTirocinio.add(domandaTirocinio);
	      
	      domandaTirocinio.setStudente(this);
	    }
	  }
	
	public int getCfu() {
		int somma = 0;
		
		for(DomandaTirocinio t: domandeTirocinio) {
			
			 somma += t.getCfu();
		}
		
		return somma;
	}
	
}
