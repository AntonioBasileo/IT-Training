package it.unisa.di.ittraining.azienda;


import javax.persistence.Entity;
import javax.persistence.OneToOne;

import it.unisa.di.ittraining.utente.Utente;

@Entity
public class TutorAziendale extends Utente {
	
	@OneToOne
	private Azienda azienda;

	public TutorAziendale() {
		
	}

	public Azienda getAzienda() {
		return azienda;
	}

	public void setAzienda(Azienda azienda) {
		if(this.azienda != azienda) {
			this.azienda = azienda;
			//azienda.setTutorAziendale(this);
		}
	}

}
