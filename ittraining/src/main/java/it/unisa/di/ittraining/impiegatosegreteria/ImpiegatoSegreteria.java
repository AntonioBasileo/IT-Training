package it.unisa.di.ittraining.impiegatosegreteria;

import javax.persistence.Entity;
import it.unisa.di.ittraining.utente.Utente;

@Entity
public class ImpiegatoSegreteria extends Utente {
	  
	/** Espressione regolare che definisce il formato del campo email per impiegati di segreteria e tutor accademici. */
	public static final String EMAIL_PATTERN_SEGRETERIA = "[A-z0-9\\.\\+_-]+@unisa.it";
	
	public ImpiegatoSegreteria() {
		
	}
	
	
}
