package it.unisa.di.ittraining.progettoformativo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinioRepository;

@Service
public class ProgettoFormativoService {

	@Autowired
	private ProgettoFormativoRepository progettoRep;
	
	@Autowired
	private DomandaTirocinioRepository domandeRep;
	
	@Transactional(rollbackFor = Exception.class)
	public ProgettoFormativo inserisciProgetto(ProgettoFormativo progetto, long id) {
		
		DomandaTirocinio domanda = domandeRep.findById(id);
		domanda.setStatus(DomandaTirocinio.ACCETTATA_AZIENDA);
		
		progetto.setDomanda(domanda);
		
		progettoRep.save(progetto);
		
		return progetto;
	}
	
}
