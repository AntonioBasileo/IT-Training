package it.unisa.di.ittraining.studente;

import it.unisa.di.ittraining.utente.Utente;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.registrotirocinio.Registro;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Studente extends Utente {
	//matricola dello studente
	private String matricola;
	  
	/** Espressione regolare che definisce il formato del campo matricola. */
    public static final String MATRICOLA_PATTERN = "^[0-9]{10}$";
	  
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "studente")
	private List<DomandaTirocinio> domandeTirocinio;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "studente")
	private List<Registro> registri;
	
	public Studente() {
		
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
	
}
