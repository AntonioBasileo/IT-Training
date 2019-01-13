package it.unisa.di.ittraining.domandatirocinio;

import it.unisa.di.ittraining.azienda.Azienda;
import it.unisa.di.ittraining.azienda.AziendaNonEsistenteException;
import it.unisa.di.ittraining.azienda.AziendaNonValidaException;
import it.unisa.di.ittraining.azienda.AziendaRepository;
import it.unisa.di.ittraining.studente.Studente;
import it.unisa.di.ittraining.utente.DataDiNascitaNonValidaException;
import it.unisa.di.ittraining.utente.UtenteService;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DomandaTirocinioService {

  @Autowired
  private AziendaRepository rep;

  @Autowired
  private DomandaTirocinioRepository domandeRep;

  @Autowired
  private UtenteService utentiService;

  /**
* Metodo che permette di validare i campi del modulo della domanda lato server
* e di inserire la stessa all'interno del Database.
*/
  @Transactional(rollbackFor = Exception.class)
  public DomandaTirocinio registraDomanda(DomandaTirocinio domanda,
      String nomeAzienda) throws AziendaNonValidaException,
      AziendaNonEsistenteException, DataDiNascitaNonValidaException,
      DataNonValidaException, DataFinePrecedenteDataInizioException,
      MassimoNumeroCfuCumulabiliException, NumeroCfuNonValidoException {

    Studente studente = (Studente)utentiService.getUtenteAutenticato();

    domanda.setAzienda(rep.findByNome(validaNomeAzienda(nomeAzienda)));
    domanda.setInizioTirocinio(validaDataInizio(domanda.getInizioTirocinio()));
    domanda.setFineTirocinio(validaDataFine(domanda.getInizioTirocinio(),
        domanda.getFineTirocinio()));
    domanda.setCfu(validaNumeroCfu(domanda.getCfu()));
    domanda.setStudente(studente);

    if (domanda.getCfu() == 6) {
      domanda.setOreTotali(150);
    } else if (domanda.getCfu() == 12) {
      domanda.setOreTotali(300);
    } else if (domanda.getCfu() == 18) {
      domanda.setOreTotali(450);
    }

    domanda = domandeRep.save(domanda);

    return domanda;
  }

  /**
* Permette di aggiornare lo stato della domanda con l'id indicato.
*/
  public DomandaTirocinio aggiornaStatoDomanda(long id, int status) {

    DomandaTirocinio domanda = domandeRep.findById(id);
    domanda.setStatus(status);

    return domandeRep.save(domanda);
  }

  public List<DomandaTirocinio> elencaDomandeStudente(String username) {

    return domandeRep.findAllByStudenteUsername(username);
  }

  /**
* Permette di risalire alle domande di tirocinio di un determinato utente con
* un determinato status.
*/
  public List<DomandaTirocinio> elencaDomandeStudenteStatus(String username, int status) {

 
    return domandeRep.findAllByStudenteUsernameAndStatus(username, status);
  }

  public List<DomandaTirocinio> elencaDomandeAziendali(Azienda azienda) {

    return domandeRep.findAllByAzienda(azienda);
  }

  public DomandaTirocinio getDomandaById(long id) {
    return domandeRep.findById(id);
  }

  public List<DomandaTirocinio> getAllByStatus(int status) {

    return domandeRep.findAllByStatus(status);
  }

  /**
* Permette al tutor aziendale di visualizzare i registri
* una volta che le domande sono definitivamente approvate.
*/
  public List<DomandaTirocinio> getAllRegistriAzienda() {

    List<DomandaTirocinio> newList = new ArrayList<>();

    newList.addAll(domandeRep.findAllByStatus(DomandaTirocinio.APPROVATA));
    newList.addAll(domandeRep.findAllByStatus(DomandaTirocinio.REGISTRO_APPROVATO_AZIENDALE));
    newList.addAll(domandeRep.findAllByStatus(DomandaTirocinio.REGISTRO_APPROVATO_ACCADEMICO));
    newList.addAll(domandeRep.findAllByStatus(DomandaTirocinio.REGISTRO_APPROVATO_SEGRETERIA));

    return newList;
  }

  /**
* Permette al tutor accademico di visualizzare i registri
* una volta che questi sono stati approvati dai tutor aziendali.
*/
  public List<DomandaTirocinio> getAllRegistriAccademico() {

    List<DomandaTirocinio> newList = new ArrayList<>();

    newList.addAll(domandeRep.findAllByStatus(DomandaTirocinio.REGISTRO_APPROVATO_AZIENDALE));
    newList.addAll(domandeRep.findAllByStatus(DomandaTirocinio.REGISTRO_APPROVATO_ACCADEMICO));
    newList.addAll(domandeRep.findAllByStatus(DomandaTirocinio.REGISTRO_APPROVATO_SEGRETERIA));

    return newList;
  }

  /**
 * Permette all'impiegato di segreteria di visualizzare i registri
 * una volta che questi sono stati approvati dai tutor accademici.
 */
  public List<DomandaTirocinio> getAllRegistriSegreteria() {

    List<DomandaTirocinio> newList = new ArrayList<>();

    newList.addAll(domandeRep.findAllByStatus(DomandaTirocinio.REGISTRO_APPROVATO_ACCADEMICO));
    newList.addAll(domandeRep.findAllByStatus(DomandaTirocinio.REGISTRO_APPROVATO_SEGRETERIA));

    return newList;
  }

  public List<DomandaTirocinio> getAll() {

    return domandeRep.findAll();
  }

  /**
* Permette di validare la data di inizio del tirocinio
* assicurandosi che non sia precedente ad oggi.
*/
  public LocalDate validaDataInizio(LocalDate inizio) throws DataNonValidaException {

    if (inizio == null) {
      throw new DataNonValidaException("Il campo Data inizio non può essere nullo");
    }

    LocalDate oggi = LocalDate.now();

    if (inizio.isBefore(oggi)) {
      throw new DataNonValidaException("La data di inizio non può essere precedente ad oggi");
    }


    return inizio;
  }

  /**
* Permette di validare la data di fine del tirocinio,
* assicurandosi che non sia precedente a quella di inizio e che il periodo di tirocinio
* duri massimo un anno.
*/
  public LocalDate validaDataFine(LocalDate inizio, LocalDate fine) throws DataNonValidaException,
      DataFinePrecedenteDataInizioException {

    if (fine == null || inizio == null) {
      throw 
        new DataNonValidaException("Il campo Data fine oppure Data inizio non può essere nullo");
    }

    long distanzaanni = ChronoUnit.YEARS.between(inizio, fine);

    long distanzamesi = ChronoUnit.MONTHS.between(inizio, fine);

    long distanzagiorni = ChronoUnit.DAYS.between(inizio, fine);
 
    if (fine.isBefore(inizio)) {
      throw new DataFinePrecedenteDataInizioException();
    }

    if ((distanzaanni >= 1 && distanzamesi >= 1 && distanzagiorni >= 1)) {
      throw new DataNonValidaException("Il tirocinio può durare massimo un anno");
    }

    return fine;
  }

  /**
* Permette di validare il nome dell'azienda verso la quale si vuole
* svolgere il tirocinio, assicurandosi che esista.
*/
  public String validaNomeAzienda(String azienda) throws AziendaNonValidaException,
      AziendaNonEsistenteException {

    if (azienda == null) {
      throw new AziendaNonValidaException("Il campo azienda non può essere nullo");
    }

    if (azienda.length() > Azienda.MAX_LUNGHEZZA_NOME 
        || azienda.length() < Azienda.MIN_LUNGHEZZA_NOME) {
      throw new AziendaNonValidaException();
    }

    if (!rep.existsByNome(azienda)) {
      throw new AziendaNonEsistenteException("L'azienda indicata non esiste");
    }

    return azienda;
  }

  /**
* Permette di notificare allo studente se ha raggiunto il massimo numero di CFU 
* cumulabili attraverso i tirocini. In caso contrario la richiesta sarà elaborata.
*/
  public int validaNumeroCfu(int cfu) throws MassimoNumeroCfuCumulabiliException,
      NumeroCfuNonValidoException {

    if (cfu == 0) {
      throw new NumeroCfuNonValidoException();
    }

    Studente studente = (Studente)utentiService.getUtenteAutenticato();

    if ((studente.getCfuDomande() + cfu) > DomandaTirocinio.MAX_CFU) {
      throw new MassimoNumeroCfuCumulabiliException();
    }

    if (cfu < DomandaTirocinio.MIN_CFU) {
      throw new NumeroCfuNonValidoException();
    }

    return cfu;
  }

}
