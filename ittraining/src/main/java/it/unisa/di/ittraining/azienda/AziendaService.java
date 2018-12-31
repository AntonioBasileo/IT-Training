package it.unisa.di.ittraining.azienda;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisa.di.ittraining.utente.NomeNonValidoException;

@Service
public class AziendaService {

	@Autowired
	private TutorAziendaleRepository repTutor;
	
	@Autowired
	private AziendaRepository repAzienda;
	
	public List<Azienda> elancaAziende() {
		
		return repAzienda.findAll();
	}
	
	@Transactional(rollbackFor = Exception.class)
	public TutorAziendale registraTutorAziendale(TutorAziendale tutor) {
				
		repTutor.save(tutor);
				
		return tutor;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Azienda registraAzienda(Azienda azienda) {
		
		repAzienda.save(azienda);
		
		return azienda;
	}
	
	public String validaNome(String nome) throws NomeNonValidoException {
		  if (nome == null) {
		    throw new NomeNonValidoException();
		  } else {
		    nome = nome.trim();
		      
		    if (nome.length() < Azienda.MIN_LUNGHEZZA_NOME
		        || nome.length() > Azienda.MAX_LUNGHEZZA_NOME) {
		      throw new NomeNonValidoException();
		    } else {
		      return nome;
		    }
		  }
	}
	
	public String validaTelefono(String telefono) throws TelefonoNonValidoException {
		if(telefono == null) throw new TelefonoNonValidoException("Il campo telefono non può essere nullo.");
		
		if(!telefono.matches(Azienda.TELEFONO_PATTERN)) throw new TelefonoNonValidoException("Il campo telefono non è valido.");
		
		return telefono;
	}
	
	public String validaIndirizzo(String indirizzo) throws IndirizzoNonValidoException {
		if(indirizzo == null) throw new IndirizzoNonValidoException("Il campo indirizzo non può essere nullo");
		
		if(indirizzo.length() < Azienda.MIN_LUNGHEZZA_INDIRIZZO || indirizzo.length() > Azienda.MAX_LUNGHEZZA_INDIRIZZO) throw new IndirizzoNonValidoException("Il campo indirizzo non è valido.");
		
		return indirizzo;
	}
	
	public String validaSede(String sede) throws SedeNonValidaException {
		if(sede == null) throw new SedeNonValidaException("Il campo sede non può essere nullo.");
		
		if(sede.length() < Azienda.MIN_LUNGHEZZA_SEDE || sede.length() > Azienda.MAX_LUNGHEZZA_SEDE) throw new SedeNonValidaException("Il campo sede non è valido.");
		
		return sede;
	}
	
	public Azienda getAziendaByNome(String nome) {
		
		return repAzienda.findByNome(nome);
	}
	
}
