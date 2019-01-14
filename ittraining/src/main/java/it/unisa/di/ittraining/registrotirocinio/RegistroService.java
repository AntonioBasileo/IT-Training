package it.unisa.di.ittraining.registrotirocinio;

import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinioRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class RegistroService {

  @Autowired
  private RegistroRepository registriRep;

  @Autowired
  private DomandaTirocinioRepository domandeRep;


  /**
* Permette di associare un'attività alla specifica domanda, inserendo l'attività
* all'interno del Database.
*/
  @Transactional(rollbackFor = Exception.class)
  public Registro registraTirocinio(Registro registro, long id)
      throws DataRegistroSuccessivaFineException,DataRegistroPrecedenteInizioException,
      DataRegistroNonValidaException,OrarioNonValidoException,
      OrarioFinePrecedenteInizioException, MassimoNumeroOreException, OrePrevisteSuperateException {
    DomandaTirocinio domanda = domandeRep.findById(id);

    registro.setData(validaDataRegistro(registro.getData(), domanda.getInizioTirocinio(), 
        domanda.getFineTirocinio()));
    registro.setData(registro.getData().plusDays(1));
    registro.setInizio(validaOrarioInizio(registro.getInizio(), registro.getFine()));
    registro.setFine(validaOrarioFine(registro.getInizio(), registro.getFine()));
    validaNumeroOreTirocinio(registro.getInizio(), registro.getFine());
    verificaNumeroOreRegistro(registro.getInizio(), registro.getFine(), id);
    
    registro.setDomanda(domanda);
    
    registro = registriRep.save(registro);

    return registro;
  }

  /**
* Permette di eliminare un'attività di tirocinio dal Database.
*/
  @Transactional(rollbackFor = Exception.class)
  public void cancellaTirocinio(long id) {
    Registro registro = registriRep.findById(id);

    registriRep.delete(registro);
  }

  /**
* Permette di validare la data dell'attiività, assicurandosi
* che non sia nè prima dell'inizio del tirocinio nè dopo
* la fine del tirocinio.
*/
  public LocalDate validaDataRegistro(LocalDate data, LocalDate inizio, LocalDate fine)
      throws DataRegistroSuccessivaFineException, DataRegistroPrecedenteInizioException,
      DataRegistroNonValidaException {

    if (data == null || inizio == null || fine == null) {
      throw new DataRegistroNonValidaException("Il campo data del registro non può essere nullo");
    }
    if (data.isBefore(inizio)) {
      throw new DataRegistroPrecedenteInizioException();
    }

    if (data.isAfter(fine)) {
      throw new DataRegistroSuccessivaFineException();
    }

    return data;
  }
  
  /**
  * Permette di validare l'orario di inizio del tirocinio.
  */
  public LocalTime validaOrarioInizio(LocalTime inizio, LocalTime orarioFine) 
      throws OrarioNonValidoException, OrarioFinePrecedenteInizioException {
    if (orarioFine == null) {
      throw new OrarioNonValidoException();
    }

    if (orarioFine.isBefore(inizio)) {
      throw new OrarioFinePrecedenteInizioException();
    }

    return inizio;
  }

  /**
* Permette di validare l'orario di fine del tirocinio.
*/
  public LocalTime validaOrarioFine(LocalTime inizio, LocalTime orarioFine) 
      throws OrarioNonValidoException, OrarioFinePrecedenteInizioException {
    if (orarioFine == null) {
      throw new OrarioNonValidoException();
    }

    if (orarioFine.isBefore(inizio)) {
      throw new OrarioFinePrecedenteInizioException();
    }

    return orarioFine;
  }
 
  /**
* Metodo che notifica allo studente il limite di attività di tirocinio
* è di otto ore.
*/
  public float validaNumeroOreTirocinio(LocalTime inizio, LocalTime fine) 
      throws MassimoNumeroOreException {
    float x = ((ChronoUnit.MILLIS.between(inizio, fine) / 1000) / 60);

    if (x > 480) {
      throw new MassimoNumeroOreException();
    }

    return x;
  }

  /**
* Metodo che notifica allo studente ha raggiunto oppure superato il numero di ore
* di tirocinio previste.
*/
  public long verificaNumeroOreRegistro(LocalTime inizio, LocalTime fine, long id) 
      throws OrePrevisteSuperateException {

    DomandaTirocinio domanda = domandeRep.findById(id);
    long x = ((ChronoUnit.MILLIS.between(inizio, fine) / 1000) / 60);

    if (domanda.getCfu() == 6) {

      if ((domanda.getNumeroOre() + x) > 9000) {
    
        throw new OrePrevisteSuperateException();
      }
    }
    
    if (domanda.getCfu() == 12) {

      if ((domanda.getNumeroOre() + x) > 18000) {
    
        throw new OrePrevisteSuperateException();
      }
    }
    
    if (domanda.getCfu() == 18) {

      if ((domanda.getNumeroOre() + x) > 27000) {
    
        throw new OrePrevisteSuperateException();
      }
    }

    return (domanda.getNumeroOre() + x);
  }
}
