package it.unisa.di.ittraining.progettoformativo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProgettoFormativoService {

	@Autowired
	private ProgettoFormativoRepository progettoRep;
	
	@Transactional(rollbackFor = Exception.class)
	public ProgettoFormativo inserisciProgetto(ProgettoFormativo progetto) {
		
		progettoRep.save(progetto);
		
		return progetto;
	}
}
