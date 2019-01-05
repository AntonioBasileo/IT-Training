package it.unisa.di.ittraining.domandatirocinio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisa.di.ittraining.azienda.Azienda;
import it.unisa.di.ittraining.azienda.AziendaNonEsistenteException;
import it.unisa.di.ittraining.azienda.AziendaNonValidaException;
import it.unisa.di.ittraining.azienda.AziendaRepository;
import it.unisa.di.ittraining.studente.Studente;
import it.unisa.di.ittraining.utente.DataDiNascitaNonValidaException;
import it.unisa.di.ittraining.utente.UtenteService;

@Service
public class DomandaTirocinioService {
	
	@Autowired
	private AziendaRepository rep;
	
	@Autowired
	private DomandaTirocinioRepository domandeRep;
	
	@Autowired
	private UtenteService utentiService;
	
	public List<DomandaTirocinio> elencaDomandeStudente(String username) {
		
		return domandeRep.findAllByStudenteUsername(username);
	}
	
	public List<DomandaTirocinio> elencaDomandeStudenteStatus(String username, int status) {
		
		return domandeRep.findAllByStudenteUsernameAndStatus(username, status);
	}
	
	public List<DomandaTirocinio> elencaDomandeAziendali(Azienda azienda) {
		
		return domandeRep.findAllByAzienda(azienda);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public DomandaTirocinio registraDomanda(DomandaTirocinio domanda, String nomeAzienda) throws AziendaNonValidaException, AziendaNonEsistenteException,
	DataDiNascitaNonValidaException, DataNonValidaException, DataFinePrecedenteDataInizioException, MassimoNumeroCfuCumulabiliException, NumeroCfuNonValidoException {

		Studente studente = (Studente)utentiService.getUtenteAutenticato();
		
		domanda.setAzienda(rep.findByNome(validaNomeAzienda(nomeAzienda)));
		domanda.setInizioTirocinio(validaDataInizio(domanda.getInizioTirocinio()));
		domanda.setFineTirocinio(validaDataFine(domanda.getInizioTirocinio(), domanda.getFineTirocinio()));
		domanda.setCfu(validaNumeroCfu(domanda.getCfu()));
	    domanda.setStudente(studente);
		
		
	    if(domanda.getCfu() == 6)
	    	domanda.setOreTotali(150);
	    
	    else if(domanda.getCfu() == 12)
	    	domanda.setOreTotali(300);
	    
	    else if(domanda.getCfu() == 18)
	    	domanda.setOreTotali(450);
		
		domandeRep.save(domanda);
		
		return domanda;
	}
	
	public DomandaTirocinio aggiornaStatoDomanda(long id, int status) {
		
		DomandaTirocinio domanda = domandeRep.findById(id);
		domanda.setStatus(status);
		
		return domandeRep.save(domanda);
	}

	public LocalDate validaDataInizio(LocalDate inizio) throws DataNonValidaException {
		if(inizio == null) throw new DataNonValidaException("Il campo Data inizio non può essere nullo");
		
		LocalDate oggi = LocalDate.now();
		
		if(inizio.isBefore(oggi)) throw new DataNonValidaException("La data di inizio non può essere precedente ad oggi");
		
		
		return inizio;
	}
	
	public LocalDate validaDataFine(LocalDate inizio, LocalDate fine) throws DataNonValidaException, DataFinePrecedenteDataInizioException {
		if(fine == null || inizio == null) throw new DataNonValidaException("Il campo Data fine oppure Data inizio non può essere nullo");
		
		long distanza_anni = ChronoUnit.YEARS.between(inizio, fine);
		
		long distanza_mesi = ChronoUnit.MONTHS.between(inizio, fine);
		
		long distanza_giorni = ChronoUnit.DAYS.between(inizio, fine);
		
		if(fine.isBefore(inizio)) throw new DataFinePrecedenteDataInizioException();
		
		if((distanza_anni >=1 && distanza_mesi >= 1 && distanza_giorni >= 1)) throw new DataNonValidaException("Il tirocinio può durare massimo un anno");
		
		return fine;
	}
	
	public String validaNomeAzienda(String azienda) throws AziendaNonValidaException, AziendaNonEsistenteException {
		if(azienda == null) throw new AziendaNonValidaException("Il campo azienda non può essere nullo");
		
		if(azienda.length() > Azienda.MAX_LUNGHEZZA_NOME || azienda.length() < Azienda.MIN_LUNGHEZZA_NOME) throw new AziendaNonValidaException();
		
		if(!rep.existsByNome(azienda)) throw new AziendaNonEsistenteException("L'azienda indicata non esiste");
		
		return azienda;
	}
	
	public int validaNumeroCfu(int cfu) throws MassimoNumeroCfuCumulabiliException, NumeroCfuNonValidoException {
		
		if(cfu == 0) throw new NumeroCfuNonValidoException();
		
		Studente studente = (Studente)utentiService.getUtenteAutenticato();
		
		if(studente.getCfuDomande() >= DomandaTirocinio.MAX_CFU) 
			throw new MassimoNumeroCfuCumulabiliException();
		
		if(cfu < DomandaTirocinio.MIN_CFU) throw new NumeroCfuNonValidoException();
		
		return cfu;
	}
}
