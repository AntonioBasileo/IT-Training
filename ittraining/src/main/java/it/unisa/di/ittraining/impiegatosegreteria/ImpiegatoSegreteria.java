package it.unisa.di.ittraining.impiegatosegreteria;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import it.unisa.di.ittraining.registrotirocinio.Registro;
import it.unisa.di.ittraining.utente.Utente;

@Entity
public class ImpiegatoSegreteria extends Utente {

	@OneToMany
	private List<Registro> registri;
	
	public ImpiegatoSegreteria() {
		
	}

	public List<Registro> getRegistri() {
		return registri;
	}

	public void setRegistri(List<Registro> registri) {
		this.registri = registri;
	}
	
}
