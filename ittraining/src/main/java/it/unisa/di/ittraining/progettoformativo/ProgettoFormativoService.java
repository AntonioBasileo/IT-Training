package it.unisa.di.ittraining.progettoformativo;

import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProgettoFormativoService {

  @Autowired
  private ProgettoFormativoRepository progettoRep;

  @Autowired
  private DomandaTirocinioRepository domandeRep;

  /**
* Metodo che permette di inserire il progetto formativo, associato
* alla domanda accettata dal tutor aziendale, all'interno del Database.
*/
  @Transactional(rollbackFor = Exception.class)
  public ProgettoFormativo inserisciProgetto(ProgettoFormativo progetto, long id) {

    DomandaTirocinio domanda = domandeRep.findById(id);
    domanda.setData(domanda.getData().plusDays(1));
    domanda.setInizioTirocinio(domanda.getInizioTirocinio().plusDays(1));
    domanda.setFineTirocinio(domanda.getFineTirocinio().plusDays(1));
    domanda.setStatus(DomandaTirocinio.ACCETTATA_AZIENDA);

    progetto.setDomanda(domanda);

    progetto = progettoRep.save(progetto);

    return progetto;
  }

}
