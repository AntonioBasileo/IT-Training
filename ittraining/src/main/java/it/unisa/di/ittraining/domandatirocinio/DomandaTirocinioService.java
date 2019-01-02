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
	
	public void cancellaDomanda(DomandaTirocinio domanda) {
		domandeRep.delete(domanda);
	}
	
	public boolean existsByStudenteAndAzienda(Studente studente, Azienda azienda) {
		List<DomandaTirocinio> domande = domandeRep.findAllByAzienda(azienda);
		for(DomandaTirocinio d: domande) {
			if(d.getStudente().equals(studente)) {
				return true;
			}
		}
		return false;
	}
	
	public List<DomandaTirocinio> elencaDomandeStudente(String username) {
		
		return domandeRep.findAllByStudenteUsername(username);
	}
	
	public List<DomandaTirocinio> elencaDomandeStudenteStatus(String username, int status) {
		
		return domandeRep.findAllByStudenteUsernameAndStatus(username, status);
	}
	
	public List<DomandaTirocinio> elencaDomandeAziendali(Azienda azienda) {
		
		return domandeRep.findAllByAzienda(azienda);
	}
	
	public DomandaTirocinio getDomandaById(long id) {
		
		return domandeRep.findById(id);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public DomandaTirocinio registraDomanda(DomandaTirocinio domanda) {
		domandeRep.save(domanda);
		
		return domanda;
	}

	public LocalDate validaDataInizio(LocalDate inizio) throws DataDiNascitaNonValidaException {
		if(inizio == null) throw new DataDiNascitaNonValidaException("Il campo Data inizio non può essere nullo");
		
		LocalDate oggi = LocalDate.now();
		
		if(inizio.isBefore(oggi)) throw new DataDiNascitaNonValidaException("La data di inizio non può essere precedente ad oggi");
		
		return inizio;
	}
	
	public LocalDate validaDataFine(LocalDate inizio, LocalDate fine) throws DataNonValidaException, DataFinePrecedenteDataInizioException {
		if(fine == null || inizio == null) throw new DataNonValidaException("Il campo Data fine oppure Data inizio non può essere nullo");
		
		if(fine.isBefore(inizio)) throw new DataFinePrecedenteDataInizioException();
			
		long distanza_anni = ChronoUnit.YEARS.between(inizio, fine);
		
		long distanza_mesi = ChronoUnit.MONTHS.between(inizio, fine);
		
		long distanza_giorni = ChronoUnit.DAYS.between(inizio, fine);
		
		if(distanza_anni >=1 && distanza_mesi > 0 && distanza_giorni > 0) throw new DataNonValidaException("Il tirocinio può durare massimo un anno");
		
		
		return fine;
	}
	
	public String validaNomeAzienda(String azienda) throws AziendaNonValidaException, AziendaNonEsistenteException {
		if(azienda == null) throw new AziendaNonValidaException("Il campo azienda non può essere nullo");
		
		if(azienda.length() > Azienda.MAX_LUNGHEZZA_NOME || azienda.length() < Azienda.MIN_LUNGHEZZA_NOME) throw new AziendaNonValidaException();
		
		if(!rep.existsByNome(azienda)) throw new AziendaNonEsistenteException("L'azienda indicata non esiste");
		
		return azienda;
	}
	
	public int validaNumeroCfu(int cfu) throws MassimoNumeroCfuCumulabiliException, NumeroCfuNonValido {
		
		if(cfu == 0) throw new NumeroCfuNonValido();
		
		Studente studente = (Studente)utentiService.getUtenteAutenticato();
		
		int somma = cfu + studente.getCfuTirocinio();
		
		if(somma > DomandaTirocinio.MAX_CFU ) throw new MassimoNumeroCfuCumulabiliException();
		
		return cfu;
	}
}
