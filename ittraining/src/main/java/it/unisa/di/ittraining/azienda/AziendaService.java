package it.unisa.di.ittraining.azienda;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisa.di.ittraining.utente.CognomeNonValidoException;
import it.unisa.di.ittraining.utente.DataDiNascitaNonValidaException;
import it.unisa.di.ittraining.utente.EmailEsistenteException;
import it.unisa.di.ittraining.utente.EmailNonValidaException;
import it.unisa.di.ittraining.utente.NomeCognomeTroppoCortoException;
import it.unisa.di.ittraining.utente.NomeCognomeTroppoLungoException;
import it.unisa.di.ittraining.utente.NomeNonValidoException;
import it.unisa.di.ittraining.utente.PasswordNonCorrispondentiException;
import it.unisa.di.ittraining.utente.PasswordNonValidaException;
import it.unisa.di.ittraining.utente.SessoNonValidoException;
import it.unisa.di.ittraining.utente.UsernameEsistenteException;
import it.unisa.di.ittraining.utente.UsernameNonValidoException;
import it.unisa.di.ittraining.utente.UtenteService;

@Service
public class AziendaService {

	@Autowired
	private TutorAziendaleRepository repTutor;
	
	@Autowired
	private AziendaRepository repAzienda;
	
	@Autowired
	private UtenteService utentiService;
	
	public List<Azienda> elancaAziende() {
		
		return repAzienda.findAll();
	}
	
	@Transactional(rollbackFor = Exception.class)
	public TutorAziendale registraTutorAziendale(TutorAziendale tutor, String nomeAzienda) throws NomeNonValidoException, NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException,
	CognomeNonValidoException, EmailNonValidaException, EmailEsistenteException, EmailNonAssociataException, UsernameNonValidoException, UsernameEsistenteException,
	PasswordNonValidaException, PasswordNonCorrispondentiException, DataDiNascitaNonValidaException, AziendaNonValidaException,
	AziendaEsistenteException, SessoNonValidoException, it.unisa.di.ittraining.utente.TelefonoNonValidoException {
		
		tutor.setNome(utentiService.validaNome(tutor.getNome()));
		tutor.setCognome(utentiService.validaCognome(tutor.getCognome()));
		tutor.setEmail(validaEmailTutor(nomeAzienda, tutor.getEmail()));
		tutor.setUsername(utentiService.validaUsername(tutor.getUsername()));
		tutor.setDataDiNascita(utentiService.validaDataDiNascita(tutor.getDataDiNascita()));
		tutor.setPassword(utentiService.validaPassword(tutor.getPassword()));
		tutor.setAzienda(repAzienda.findByNome(nomeAzienda));
		tutor.setSesso(utentiService.validaSesso(tutor.getSesso()));
		tutor.setTelefono(utentiService.validaTelefono(tutor.getTelefono()));
		
		Azienda azienda = repAzienda.findByNome(nomeAzienda);
		azienda.setTutor(tutor);
				
		repAzienda.save(azienda);
		repTutor.save(tutor);
				
		return tutor;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Azienda registraAzienda(Azienda azienda) throws AziendaNonValidaException, AziendaEsistenteException, SedeNonValidaException, IndirizzoNonValidoException,
	EmailNonValidaException, EmailEsistenteException, TelefonoNonValidoException {
		
		azienda.setNome(validaNome(azienda.getNome()));
		azienda.setSede(validaSede(azienda.getSede()));
		azienda.setIndirizzo(validaIndirizzo(azienda.getIndirizzo()));
		azienda.setEmail(validaEmailAziendale(azienda.getNome(), azienda.getEmail()));
		azienda.setTelefono(validaTelefono(azienda.getTelefono()));
		
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
	
}
