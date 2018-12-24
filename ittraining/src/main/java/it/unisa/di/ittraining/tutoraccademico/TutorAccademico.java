package it.unisa.di.ittraining.tutoraccademico;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.registrotirocinio.Registro;
import it.unisa.di.ittraining.utente.Utente;

@Entity
public class TutorAccademico extends Utente {
	
	@OneToMany
	private List<DomandaTirocinio> domande;
	
	@OneToMany
	private List<Registro> registri;
	
	public TutorAccademico() {
		
	}

	public List<DomandaTirocinio> getDomande() {
		return domande;
	}

	public void setDomande(List<DomandaTirocinio> domande) {
		this.domande = domande;
	}

	public List<Registro> getRegistri() {
		return registri;
	}

	public void setRegistri(List<Registro> registri) {
		this.registri = registri;
	}
	
}
