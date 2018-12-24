package it.unisa.di.ittraining.azienda;

import java.util.List;

import javax.persistence.Entity;
import it.unisa.di.ittraining.azienda.TutorAziendale;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;

import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Azienda {
	
	@Id
	private String nome;
	private String sede;
	private String indirizzo;
	
	@OneToMany
	private List<TutorAziendale> tutorAziendali;
	
	@OneToMany
	private List<DomandaTirocinio> domande;

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

}
