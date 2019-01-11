package it.unisa.di.ittraining.impiegatosegreteria;

import it.unisa.di.ittraining.utente.Utente;
import javax.persistence.Entity;

@Entity
public class ImpiegatoSegreteria extends Utente {

  /** Espressione regolare che definisce il formato del campo email per impiegato di segreteria. */
  public static final String EMAIL_PATTERN_SEGRETERIA = "[A-z0-9\\.\\+_-]+@unisa.it";

  public ImpiegatoSegreteria() {

  }

}
