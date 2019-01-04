package it.unisa.di.ittraining.domandaTirocinio.test;


import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import it.unisa.di.ittraining.azienda.Azienda;
import it.unisa.di.ittraining.azienda.AziendaEsistenteException;
import it.unisa.di.ittraining.azienda.AziendaNonValidaException;
import it.unisa.di.ittraining.azienda.AziendaRepository;
import it.unisa.di.ittraining.azienda.AziendaService;
import it.unisa.di.ittraining.azienda.EmailAziendaEsistenteException;
import it.unisa.di.ittraining.azienda.IndirizzoNonValidoException;
import it.unisa.di.ittraining.azienda.SedeNonValidaException;
import it.unisa.di.ittraining.azienda.TelefonoNonValidoException;
import it.unisa.di.ittraining.utente.EmailEsistenteException;
import it.unisa.di.ittraining.utente.EmailNonValidaException;

@RunWith(MockitoJUnitRunner.class)
public class AggiungiEnteTest {
	
	@InjectMocks
	private AziendaService aziendaService;
	
	@Mock
	private AziendaRepository aziendaRepository;
	
	
	@Test
	public void registraAzienda() throws AziendaNonValidaException, AziendaEsistenteException, SedeNonValidaException, IndirizzoNonValidoException, EmailNonValidaException, EmailAziendaEsistenteException, TelefonoNonValidoException {
		
		
		
		Azienda azienda = new Azienda();
		
		azienda.setNome("theorem");
		azienda.setSede("Fisciano");
		azienda.setEmail("gianfilibertaoliva@gmail.com");
		azienda.setIndirizzo("via Rossi 12");
		azienda.setTelefono("0981234567");
		
		
		
		
		when(aziendaService.registraAzienda(azienda)).thenReturn(azienda);
		
		
		
		try {
			aziendaService.registraAzienda(azienda);
		} catch (AziendaNonValidaException | AziendaEsistenteException | SedeNonValidaException
				| IndirizzoNonValidoException | EmailNonValidaException | EmailAziendaEsistenteException
				| TelefonoNonValidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	@Test(expected = AziendaNonValidaException.class)
	public void registraAziendaNomeTroppoCorto() throws AziendaNonValidaException, AziendaEsistenteException, SedeNonValidaException, IndirizzoNonValidoException, EmailNonValidaException, EmailAziendaEsistenteException, TelefonoNonValidoException {
		
		
		
		Azienda azienda = new Azienda();
		
		azienda.setNome("t");
		azienda.setSede("Fisciano");
		azienda.setEmail("gianfilibertaoliva@gmail.com");
		azienda.setIndirizzo("via Rossi 12");
		azienda.setTelefono("0981234567");
		
		
		
		
		when(aziendaService.registraAzienda(azienda)).thenReturn(azienda);
		
		
		
		try {
			aziendaService.registraAzienda(azienda);
		} catch (AziendaNonValidaException | AziendaEsistenteException | SedeNonValidaException
				| IndirizzoNonValidoException | EmailNonValidaException | EmailAziendaEsistenteException
				| TelefonoNonValidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test(expected = SedeNonValidaException.class)
	public void registraAziendaSedeNonValida() throws AziendaNonValidaException, AziendaEsistenteException, SedeNonValidaException, IndirizzoNonValidoException, EmailNonValidaException, EmailAziendaEsistenteException, TelefonoNonValidoException {
		
		
		
		Azienda azienda = new Azienda();
		
		azienda.setNome("theorem");
		azienda.setSede("F");
		azienda.setEmail("gianfilibertaoliva@gmail.com");
		azienda.setIndirizzo("via Rossi 12");
		azienda.setTelefono("0981234567");
		
		
		
		
		when(aziendaService.registraAzienda(azienda)).thenReturn(azienda);
		
		
		
		try {
			aziendaService.registraAzienda(azienda);
		} catch (AziendaNonValidaException | AziendaEsistenteException | SedeNonValidaException
				| IndirizzoNonValidoException | EmailNonValidaException | EmailAziendaEsistenteException
				| TelefonoNonValidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Test(expected=IndirizzoNonValidoException.class)
	public void registraAziendaIndirizzoNonValido() throws AziendaNonValidaException, AziendaEsistenteException, SedeNonValidaException, IndirizzoNonValidoException, EmailNonValidaException, EmailAziendaEsistenteException, TelefonoNonValidoException {
		
		
		
		Azienda azienda = new Azienda();
		
		azienda.setNome("theorem");
		azienda.setSede("Fisciano");
		azienda.setEmail("gianfilibertaoliva@gmail.com");
		azienda.setIndirizzo("v");
		azienda.setTelefono("0981234567");
		
		
		
		
		when(aziendaService.registraAzienda(azienda)).thenReturn(azienda);
		
		
		
		try {
			aziendaService.registraAzienda(azienda);
		} catch (AziendaNonValidaException | AziendaEsistenteException | SedeNonValidaException
				| IndirizzoNonValidoException | EmailNonValidaException | EmailAziendaEsistenteException
				| TelefonoNonValidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	@Test(expected = EmailAziendaEsistenteException.class)
	public void aggiungiEnteEmailAziendaEsistente() {
		
		Azienda azienda = new Azienda();
		
		azienda.setNome("Grafic IT");
		azienda.setSede("Avellino");
		azienda.setIndirizzo("Via Roma 41");
		azienda.setEmail("graficaIT@gmail.com");
		azienda.setTelefono("0811234567");
		
		when(aziendaRepository.existsByEmail(azienda.getEmail())).thenReturn(true);
		
		
		try {
			aziendaService.registraAzienda(azienda);
		} catch (AziendaNonValidaException | AziendaEsistenteException | SedeNonValidaException
				| IndirizzoNonValidoException | EmailNonValidaException | TelefonoNonValidoException
				| EmailAziendaEsistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
