package it.unisa.di.ittraining.azienda;

import it.unisa.di.ittraining.utente.Utente;
import javax.persistence.Entity;
import javax.persistence.OneToOne;


@Entity
public class TutorAziendale extends Utente {

  @OneToOne

  private Azienda azienda;

  public TutorAziendale() {

  }

  public Azienda getAzienda() {
    return azienda;
  }

  /**
  *Metodo che permette di instaurare una relazione bidirezionale
  *tra il turo aziendale e l'azienda a cui è
  *associato.
  */
  public void setAzienda(Azienda azienda) {
    if (this.azienda != azienda) {
      this.azienda = azienda;
      //azienda.setTutorAziendale(this);
    }
  }

}
