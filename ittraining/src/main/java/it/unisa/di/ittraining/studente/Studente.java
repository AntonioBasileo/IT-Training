package it.unisa.di.ittraining.studente;

import it.unisa.di.ittraining.utente.Utente;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.tutoraccademico.TutorAccademico;

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
  
  /** Espressione regolare che indicare la media ponderata dello studente. */
  public static final String MEDIA_PATTERN = "^[0-9]{2}(\\.[0-9}{1,}$";

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "studente")
  private List<DomandaTirocinio> domandeTirocinio;

  @OneToOne
  private TutorAccademico tutor;

  public Studente() {
    this.domandeTirocinio = new ArrayList<>();
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
  
  public void addDomandaTirocinio(DomandaTirocinio domandaTirocinio) {
    if (!domandeTirocinio.contains(domandaTirocinio)) {
      domandeTirocinio.add(domandaTirocinio);
      domandaTirocinio.setStudente(this);
    }
  }

  public TutorAccademico getTutor() {
    return tutor;
  }

  public void setTutor(TutorAccademico tutor) {
    if (this.tutor != tutor) {
      this.tutor = tutor;
      tutor.addStudente(this);
    }

  }

  public int getCfuDomande() {
    int somma = 0;

    for (DomandaTirocinio d: domandeTirocinio) {
      if (d.getStatus() == DomandaTirocinio.IN_ATTESA || d.getStatus() == DomandaTirocinio.APPROVATA
          || d.getStatus() == DomandaTirocinio.ACCETTATA_AZIENDA 
          || d.getStatus() == DomandaTirocinio.REGISTRO_APPROVATO_ACCADEMICO
          || d.getStatus() == DomandaTirocinio.REGISTRO_APPROVATO_AZIENDALE 
          || d.getStatus() == DomandaTirocinio.REGISTRO_APPROVATO_SEGRETERIA) {
     
        somma += d.getCfu();
      }
    }

    
    return somma;
  }
}
