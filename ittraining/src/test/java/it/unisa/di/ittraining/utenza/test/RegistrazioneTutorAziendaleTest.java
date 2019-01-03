package it.unisa.di.ittraining.utenza.test;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import it.unisa.di.ittraining.azienda.Azienda;
import it.unisa.di.ittraining.azienda.AziendaEsistenteException;
import it.unisa.di.ittraining.azienda.AziendaNonValidaException;
import it.unisa.di.ittraining.azienda.AziendaService;
import it.unisa.di.ittraining.azienda.EmailNonAssociataException;
import it.unisa.di.ittraining.azienda.IndirizzoNonValidoException;
import it.unisa.di.ittraining.azienda.SedeNonValidaException;
import it.unisa.di.ittraining.azienda.TutorAziendale;
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
import it.unisa.di.ittraining.utente.TelefonoNonValidoException;
import it.unisa.di.ittraining.utente.UsernameEsistenteException;
import it.unisa.di.ittraining.utente.UsernameNonValidoException;

@RunWith(MockitoJUnitRunner.class)
public class RegistrazioneTutorAziendaleTest {

	@InjectMocks
	private AziendaService tutorAziendaleService;
	
	@Mock
	private it.unisa.di.ittraining.azienda.TutorAziendaleRepository TutorAziendaleRepository;
	
	
	@Test
	public void registratutorAziendaleSuccesso() throws NomeNonValidoException, NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException, CognomeNonValidoException, EmailNonValidaException, EmailEsistenteException, EmailNonAssociataException, UsernameNonValidoException, UsernameEsistenteException, PasswordNonValidaException, PasswordNonCorrispondentiException, DataDiNascitaNonValidaException, AziendaNonValidaException, AziendaEsistenteException, SessoNonValidoException, TelefonoNonValidoException, SedeNonValidaException, IndirizzoNonValidoException, it.unisa.di.ittraining.azienda.TelefonoNonValidoException {
		
		TutorAziendale tutorAziendale = new TutorAziendale();
		tutorAziendale.setNome("Lina");
		tutorAziendale.setCognome("Neri");
		tutorAziendale.setDataDiNascita(LocalDate.of(1970, Month.DECEMBER, 30));
		tutorAziendale.setTelefono("0987654321");
		tutorAziendale.setEmail("lina@gmail.com");
		tutorAziendale.setUsername("LinaN");
		tutorAziendale.setPassword("lina123");
		tutorAziendale.setSesso("F");
		
		Azienda azienda= new Azienda();
		
		azienda.setNome("theorem");
		azienda.setSede("Fisciano");
		azienda.setEmail("lina@gmail.com");
		azienda.setIndirizzo("via Rossi 12");
		azienda.setTelefono("0981234567");
		
	
		when(tutorAziendaleService.registraAzienda(azienda)).thenReturn(azienda);
		when(tutorAziendaleService.registraTutorAziendale(tutorAziendale, azienda.getNome())).thenReturn(tutorAziendale);
		
		
		
			try {
				tutorAziendaleService.registraTutorAziendale(tutorAziendale, azienda.getNome());
			} catch (NomeNonValidoException | NomeCognomeTroppoLungoException | NomeCognomeTroppoCortoException
					| CognomeNonValidoException | EmailNonValidaException | EmailEsistenteException
					| EmailNonAssociataException | UsernameNonValidoException | UsernameEsistenteException
					| PasswordNonValidaException | PasswordNonCorrispondentiException | DataDiNascitaNonValidaException
					| AziendaNonValidaException | AziendaEsistenteException | SessoNonValidoException
					| TelefonoNonValidoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}
	
	
	
	@Test(expected=EmailNonValidaException.class)
	public void registratutorAziendaleEmailNonValida() throws NomeNonValidoException, NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException, CognomeNonValidoException, EmailNonValidaException, EmailEsistenteException, EmailNonAssociataException, UsernameNonValidoException, UsernameEsistenteException, PasswordNonValidaException, PasswordNonCorrispondentiException, DataDiNascitaNonValidaException, AziendaNonValidaException, AziendaEsistenteException, SessoNonValidoException, TelefonoNonValidoException, SedeNonValidaException, IndirizzoNonValidoException, it.unisa.di.ittraining.azienda.TelefonoNonValidoException {
		
		TutorAziendale tutorAziendale = new TutorAziendale();
		tutorAziendale.setNome("Lina");
		tutorAziendale.setCognome("Neri");
		tutorAziendale.setDataDiNascita(LocalDate.of(1970, Month.DECEMBER, 30));
		tutorAziendale.setTelefono("0987654321");
		tutorAziendale.setEmail("linaNeri@gmail.com");
		tutorAziendale.setUsername("LinaN");
		tutorAziendale.setPassword("lina123");
		tutorAziendale.setSesso("F");
		
		Azienda azienda= new Azienda();
		
		azienda.setNome("theorem");
		azienda.setSede("Fisciano");
		azienda.setEmail("lina@gmail.com");
		azienda.setIndirizzo("via Rossi 12");
		azienda.setTelefono("0981234567");
		
		
		when(tutorAziendaleService.registraAzienda(azienda)).thenReturn(azienda);
		when(tutorAziendaleService.registraTutorAziendale(tutorAziendale, azienda.getNome())).thenReturn(tutorAziendale);
		
		
	
			try {
				tutorAziendaleService.registraTutorAziendale(tutorAziendale, azienda.getNome());
			} catch (NomeNonValidoException | NomeCognomeTroppoLungoException | NomeCognomeTroppoCortoException
					| CognomeNonValidoException | EmailNonValidaException | EmailEsistenteException
					| EmailNonAssociataException | UsernameNonValidoException | UsernameEsistenteException
					| PasswordNonValidaException | PasswordNonCorrispondentiException | DataDiNascitaNonValidaException
					| AziendaNonValidaException | AziendaEsistenteException | SessoNonValidoException
					| TelefonoNonValidoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}
	
	
	
	
	
}
