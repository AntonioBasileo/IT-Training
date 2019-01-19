package it.unisa.di.ittraining.domandatirocinio;

import it.unisa.di.ittraining.azienda.Azienda;
import it.unisa.di.ittraining.progettoformativo.ProgettoFormativo;
import it.unisa.di.ittraining.registrotirocinio.Registro;
import it.unisa.di.ittraining.studente.Studente;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class DomandaTirocinio {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  private Studente studente;

  @ManyToOne
  private Azienda azienda;

  @OneToOne
  private ProgettoFormativo progettoFormativo;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "domanda")
  private List<Registro> registri;

  private LocalDate data;
  private LocalDate inizioTirocinio;
  private LocalDate fineTirocinio;
  private int status;
  private int cfu;
  private int oreTotali;

  public DomandaTirocinio() {
    this.registri = new ArrayList<>();
  }

  /**
 * Costante che rappresenta lo stato "in attesa" di una domanda di tirocinio.
 * Una domanda di tirocinio si trova in questo stato quando è stata inviata dallo studente ma
 * non è ancora stata esaminata e gestita dall'azienda che offre il progetto formativo ad essa
 * associato.
 */
  public static final int IN_ATTESA = 0;

  /**
 * Costante che rappresenta lo stato "accettata da parte del tutor aziendale" 
 * di una domanda di tirocinio.
 * Una domanda di tirocinio si trova in questo stato quando è 
 * stata gestita ed accettata 
 * dall'azienda che offre il progetto formativo ad essa associato 
 * ed è in attesa che il tutor accademico accetti
 * il progetto formativo.
 */
  public static final int ACCETTATA_AZIENDA = 1;

  /**
 * Costante che rappresenta lo stato "rifiutata" di una domanda di tirocinio.
 * Una domanda di tirocinio si trova in questo stato quando è stata gestita e rifiutata 
 * dall'azienda che offre il progetto formativo ad essa associato.
 */
  public static final int RIFIUTATA_AZIENDA = 2;

  /**
 * Una domanda si trova in questo stato quando il tutor accademico non 
 * ha approvato il progetto formativo 
 * associato alla domanda.
 */
  public static final int PROGETTO_RIFIUTATO = 3;

  /**
 * Costante che rappresenta lo stato "accettata" di una domanda di tirocinio.
 * Una domanda di tirocinio si trova in questo stato quando il 
 * tutor accademico ha approvato il progetto formativo
 * associato alla domanda di tirocinio. Da questo momento il tirocinio 
 * può avere inizio.
 */
  public static final int APPROVATA = 4; 
  /**
 * Costante che rappresenta lo stato di una domanda di tirocinio 
 * quando il registro è stato compilato ed approvato
 * dal tutor aziendale.
 */
  public static final int REGISTRO_APPROVATO_AZIENDALE = 5;

  /**
 * Costante che rappresenta lo stato di una domanda di tirocinio 
 * quando il registro è stato compilato ed approvato
 * dal tutor accademico.
 */
  public static final int REGISTRO_APPROVATO_ACCADEMICO = 6;

  /**
 * Costante che rappresenta lo stato di una domanda di tirocinio 
 * quando il registro è stato compilato ed approvato
 * dall'impiegato di segreteria.
 */
  public static final int REGISTRO_APPROVATO_SEGRETERIA = 7;


  /** Costante che definisce il numero massimo di CFU da poter associare 
  * ad una domanda. */
  public static final int MAX_CFU = 18;


  /** Costante che definisce il numero massimo di CFU da poter associare ad una domanda. */
  public static final int MIN_CFU = 2;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Studente getStudente() {
    return studente;
  }

  /**
* Permette di instaurare una relazione bidirezionale a livello di database
* tra lo studente e la domanda.
*/
  public void setStudente(Studente studente) {
    if (this.studente != studente) {
      this.studente = studente;
      studente.addDomandaTirocinio(this);
    }
  }

  public ProgettoFormativo getProgettoFormativo() {
    return progettoFormativo;
  }

  /**
* Permette di instaurare una relazione bidirezionale a livello di database
* tra il progetto formativo e la domanda.
*/
  public void addProgettoFormativo(ProgettoFormativo progettoFormativo) {
    if (this.progettoFormativo != progettoFormativo) {
      this.progettoFormativo = progettoFormativo;
      progettoFormativo.setDomanda(this);
    }
  }

  public Azienda getAzienda() {
    return azienda;
  }

  /**
* Permette di instaurare una relazione bidirezionale a livello di database
* tra l'azienda e la domanda.
*/
  public void setAzienda(Azienda azienda) {
    if (this.azienda != azienda) {
      this.azienda = azienda;
      azienda.addDomanda(this);
    }
  }

  public LocalDate getData() {
    return data;
  }

  public void setData(LocalDate data) {
    this.data = data;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public int getCfu() {
    return cfu;
  }

  public void setCfu(int cfu) {
    this.cfu = cfu;
  }

  public LocalDate getInizioTirocinio() {
    return inizioTirocinio;
  }

  public void setInizioTirocinio(LocalDate inizioTirocinio) {
    this.inizioTirocinio = inizioTirocinio;
  }

  public LocalDate getFineTirocinio() {
    return fineTirocinio;
  }

  public void setFineTirocinio(LocalDate fineTirocinio) {
    this.fineTirocinio = fineTirocinio;
  }

  public int getOreTotali() {
    return oreTotali;
  }

  public void setOreTotali(int oreTotali) {
    this.oreTotali = oreTotali;
  }

  public List<Registro> getRegistri() {
    return registri;
  }

  /**
* Permette di instaurare una relazione bidirezionale a livello di database
* tra il registro e la domanda.
*/
  public void addRegistro(Registro registro) {
    if (!registri.contains(registro)) {
      registri.add(registro);
      registro.setDomanda(this);
    }
  }

  /**
* Permette di risalire al numero di ore effettuate
* durante il tirocinio.
*/
  public long getNumeroOre() {
    long somma = 0;

    for (Registro r: registri) {
      somma += r.getNumero_minuti();
    }
    
    return somma;
  }
  
}
