package it.unisa.di.ittraining.azienda;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisa.di.ittraining.utente.EmailEsistenteException;
import it.unisa.di.ittraining.utente.EmailNonValidaException;

@Service
public class AziendaService {

	@Autowired
	private TutorAziendaleRepository repTutor;
	
	@Autowired
	private AziendaRepository repAzienda;
	
	public boolean existsByNome(String nome) {
		return repAzienda.existsByNome(nome);
	}
	
	public void cancellaAzienda(Azienda azienda) {
		repAzienda.delete(azienda);
	}
	
	public void cancellaTutorAziendale(TutorAziendale tutorAziendale) {
		repTutor.delete(tutorAziendale);
	}
	
	public boolean exixstsByEmail(String email) {
		return repTutor.existsByEmail(email);
	}
	
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
	
	public String validaNome(String nome) throws AziendaNonValidaException, AziendaEsistenteException {
		if(nome == null) throw new AziendaNonValidaException("Il campo azienda non può essere nullo");
		
		if(nome.length() > Azienda.MAX_LUNGHEZZA_NOME || nome.length() < Azienda.MIN_LUNGHEZZA_NOME) throw new AziendaNonValidaException();
		
		if(repAzienda.existsByNome(nome)) throw new AziendaEsistenteException("L'azienda indicata è già esistente");
		
		return nome;
	}
	
	public String validaNomeForTutor(String nome) throws AziendaNonValidaException, AziendaNonEsistenteException {
		if(nome == null) throw new AziendaNonValidaException("Il campo azienda non può essere nullo");
		
		if(nome.length() > Azienda.MAX_LUNGHEZZA_NOME || nome.length() < Azienda.MIN_LUNGHEZZA_NOME) throw new AziendaNonValidaException();
		
		if(!repAzienda.existsByNome(nome)) throw new AziendaNonEsistenteException("L'azienda indicata non è esistente");
		
		return nome;
	}
	
	public String validaEmailAziendale(String nomeAzienda, String email) throws EmailNonValidaException, EmailEsistenteException {
		if(email == null) throw new EmailNonValidaException();
		
		if(!email.matches(Azienda.EMAIL_PATTERN_AZIENDALE)) throw new EmailNonValidaException("Il formato dell'email dell'azienda non rispetta il formato indicato");
		
		if(repTutor.existsByEmail(email)) throw new EmailEsistenteException("Email utilizzata già da un altro ente");
		
		return email;
	}
	
	public String validaEmailTutor(String nomeAzienda, String email) throws EmailNonValidaException, EmailEsistenteException, EmailNonAssociataException {
		if(email == null) throw new EmailNonValidaException();
		
		if(!email.matches(Azienda.EMAIL_PATTERN_AZIENDALE)) throw new EmailNonValidaException("Il formato dell'email dell'azienda non rispetta il formato indicato");
		
		if(repTutor.existsByEmail(email)) throw new EmailEsistenteException("Email utilizzata già da un altro ente");
		
		if(!repAzienda.existsByNomeAndEmail(nomeAzienda, email)) throw new EmailNonAssociataException("Email utilizzata già da un altro ente aziendale");
		
		return email;
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
