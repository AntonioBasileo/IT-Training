package it.unisa.di.ittraining.azienda;

import it.unisa.di.ittraining.azienda.TutorAziendale;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity

public class Azienda {

  @Id

  private String nome;
  private String sede;
  private String indirizzo;
  private String telefono;
  private String email;

  @OneToOne(cascade = CascadeType.ALL, mappedBy = "azienda")

  private TutorAziendale tutor;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "azienda")

  private List<DomandaTirocinio> domande;

  /** Costante che definisce la minima lunghezza del campo nome. */
  public static final int MIN_LUNGHEZZA_NOME = 2;

  /** Costante che definisce la massima lunghezza del campo nome. */
  public static final int MAX_LUNGHEZZA_NOME = 255;

  /** Costante che definisce la minima lunghezza del campo nome. */
  public static final int MIN_LUNGHEZZA_SEDE = 2;

  /** Costante che definisce la massima lunghezza del campo nome. */
  public static final int MAX_LUNGHEZZA_SEDE = 35;

  /** Costante che definisce la minima lunghezza del campo indirizzo. */
  public static final int MIN_LUNGHEZZA_INDIRIZZO = 2;

  /** Costante che definisce la massima lunghezza del campo indirizzo. */
  public static final int MAX_LUNGHEZZA_INDIRIZZO = 255;

  /** Costante che definisce il formato del campo telefono.*/
  public static final String TELEFONO_PATTERN = "^[0-9]{2,4}[0-9]{4,7}$";

  /** Espressione regolare che definisce il formato del campo email aziendale. */
  public static final String EMAIL_PATTERN_AZIENDALE = "[A-z0-9\\.\\+_-]+"
      + "@[A-z0-9\\._-]+\\.[A-z]{2,6}";

  public Azienda() {

    this.domande = new ArrayList<>();
  }

  public TutorAziendale getTutorAziendale() {
    return tutor;
  }

  /**
 * Metodo che permettere di instaurare una relazione bidirezionale a
 * livello di Database tra il tutor e l'azienda alla quale
 * appartiene.
 */
  public void setTutorAziendale(TutorAziendale tutor) {

    if (this.tutor != tutor) {

      this.tutor = tutor;
      tutor.setAzienda(this);
    }
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

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public List<DomandaTirocinio> getDomande() {
    return domande;
  }

  /**
 * Metodo che permettere di instaurare una relazione bidirezionale a
 * livello di Database tra l'azienda e le domande che vengono
 * effettuatae verso di essere.
 */
  public void addDomanda(DomandaTirocinio domanda) {

    if (!domande.contains(domanda)) {

      domande.add(domanda);
      domanda.setAzienda(this);
    }
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
