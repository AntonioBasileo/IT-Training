package it.unisa.di.ittraining.progettoformativo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisa.di.ittraining.azienda.TutorAziendale;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinioRepository;
import it.unisa.di.ittraining.utente.UtenteService;

@Service
public class ProgettoFormativoService {

	@Autowired
	private ProgettoFormativoRepository progettoRep;
	
	@Autowired
	private DomandaTirocinioRepository domandeRep;
	
	@Autowired
	UtenteService utentiService;
	
	@Transactional(rollbackFor = Exception.class)
	public ProgettoFormativo inserisciProgetto(ProgettoFormativo progetto, long id) {
		
		DomandaTirocinio domanda = domandeRep.findById(id);
		domanda.setStatus(DomandaTirocinio.ACCETTATA_AZIENDA);
		domanda.setProgettoFormativo(progetto);
		
		progetto.setTutorAziendale((TutorAziendale)utentiService.getUtenteAutenticato());
		progetto.setAzienda(((TutorAziendale)utentiService.getUtenteAutenticato()).getAzienda());
		
		progettoRep.save(progetto);
		
		return progetto;
	}
}
