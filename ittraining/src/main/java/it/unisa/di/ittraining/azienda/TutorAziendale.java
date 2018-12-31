package it.unisa.di.ittraining.azienda;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import it.unisa.di.ittraining.progettoformativo.ProgettoFormativo;
import it.unisa.di.ittraining.registrotirocinio.Registro;
import it.unisa.di.ittraining.utente.Utente;

@Entity
public class TutorAziendale extends Utente {
	
	@OneToMany
	private List<Registro> registri;
	
	@OneToMany
	private List<ProgettoFormativo> progetti;
	
	@OneToOne
	private Azienda azienda;
	
	/** Espressione regolare che definisce il formato del campo email per impiegati di segreteria e tutor accademici. */
	public static final String EMAIL_PATTERN_AZIENDALE = "[A-z0-9\\.\\+_-]+@[A-z0-9\\._-]+\\.[A-z]{2,6}";

	public TutorAziendale() {
		
	}

	public List<Registro> getRegistri() {
		return registri;
	}

	public void setRegistri(List<Registro> registri) {
		this.registri = registri;
	}

	public List<ProgettoFormativo> getProgetti() {
		return progetti;
	}

	public void setProgetti(List<ProgettoFormativo> progetti) {
		this.progetti = progetti;
	}

	public Azienda getAzienda() {
		return azienda;
	}

	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
	}

}
