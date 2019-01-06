package it.unisa.di.ittraining.utenza.test;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import it.unisa.di.ittraining.impiegatosegreteria.ImpiegatoSegreteria;
import it.unisa.di.ittraining.impiegatosegreteria.ImpiegatoSegreteriaRepository;
import it.unisa.di.ittraining.impiegatosegreteria.ImpiegatoSegreteriaService;
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
import it.unisa.di.ittraining.utente.UtenteRepository;

@RunWith(MockitoJUnitRunner.class)
public class RegistrazioneImpiegatoSegreteriaTest {

	@InjectMocks
	private ImpiegatoSegreteriaService impiegatiService;
	
	@Mock
	UtenteRepository utenteRep;
	
	@Mock
	private ImpiegatoSegreteriaRepository impiegatoRep;
	
	
	@Test
	public void registrazioneImpiegatoSegreteriaSuccesso() throws NomeNonValidoException, NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException, CognomeNonValidoException,
	DataDiNascitaNonValidaException, UsernameNonValidoException, UsernameEsistenteException, EmailNonValidaException, EmailEsistenteException, SessoNonValidoException, TelefonoNonValidoException,
	PasswordNonValidaException, PasswordNonCorrispondentiException {
		
		ImpiegatoSegreteria impiegato = new ImpiegatoSegreteria();
		impiegato.setNome("Laura");
		impiegato.setCognome("Oliva");
		impiegato.setDataDiNascita(LocalDate.of(1997, Month.JUNE, 29));
		impiegato.setSesso("F");
		impiegato.setEmail("laura@unisa.it");
		impiegato.setPassword("abdfkgnds");
		impiegato.setUsername("lauretta97");
		impiegato.setTelefono("3404050333");
		

		when(impiegatiService.registraImpiegato(impiegato)).thenReturn(impiegato);
		
		try {
			impiegatiService.registraImpiegato(impiegato);
		} catch (NomeNonValidoException | NomeCognomeTroppoLungoException | NomeCognomeTroppoCortoException
				| CognomeNonValidoException | DataDiNascitaNonValidaException | UsernameNonValidoException
				| UsernameEsistenteException | EmailNonValidaException | EmailEsistenteException
				| SessoNonValidoException | TelefonoNonValidoException| PasswordNonValidaException
				| PasswordNonCorrispondentiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test(expected = EmailNonValidaException.class)
	public void registrazioneImpiegatoSegreteriaEmailNonValida() throws NomeNonValidoException, NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException, CognomeNonValidoException,
	DataDiNascitaNonValidaException, UsernameNonValidoException, UsernameEsistenteException, EmailNonValidaException, EmailEsistenteException, SessoNonValidoException, TelefonoNonValidoException,
	PasswordNonValidaException, PasswordNonCorrispondentiException {
		
		ImpiegatoSegreteria impiegato = new ImpiegatoSegreteria();
		impiegato.setNome("Laura");
		impiegato.setCognome("Oliva");
		impiegato.setDataDiNascita(LocalDate.of(1997, Month.JUNE, 29));
		impiegato.setSesso("F");
		impiegato.setEmail("laura@studenti.unisa.it");
		impiegato.setPassword("abdfkgnds");
		impiegato.setUsername("lauretta97");
		impiegato.setTelefono("3404050333");
		

		when(impiegatiService.registraImpiegato(impiegato)).thenReturn(impiegato);
		
		try {
			impiegatiService.registraImpiegato(impiegato);
		} catch (NomeNonValidoException | NomeCognomeTroppoLungoException | NomeCognomeTroppoCortoException
				| CognomeNonValidoException | DataDiNascitaNonValidaException | UsernameNonValidoException
				| UsernameEsistenteException | EmailNonValidaException | EmailEsistenteException
				| SessoNonValidoException | TelefonoNonValidoException| PasswordNonValidaException
				| PasswordNonCorrispondentiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test(expected = EmailEsistenteException.class)
	public void registrazioneImpiegatoSegreteriaEmailEsistente() throws NomeNonValidoException, NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException, CognomeNonValidoException,
	DataDiNascitaNonValidaException, UsernameNonValidoException, UsernameEsistenteException, EmailNonValidaException, EmailEsistenteException, SessoNonValidoException, TelefonoNonValidoException,
	PasswordNonValidaException, PasswordNonCorrispondentiException {
		
		ImpiegatoSegreteria impiegato = new ImpiegatoSegreteria();
		impiegato.setNome("Laura");
		impiegato.setCognome("Oliva");
		impiegato.setDataDiNascita(LocalDate.of(1997, Month.JUNE, 29));
		impiegato.setSesso("F");
		impiegato.setEmail("laura@unisa.it");
		impiegato.setPassword("abdfkgnds");
		impiegato.setUsername("lauretta97");
		impiegato.setTelefono("3404050333");
		
		when(impiegatoRep.existsByEmail(impiegato.getEmail())).thenReturn(true);
		when(impiegatiService.registraImpiegato(impiegato)).thenReturn(impiegato);
		
		try {
			impiegatiService.registraImpiegato(impiegato);
		} catch (NomeNonValidoException | NomeCognomeTroppoLungoException | NomeCognomeTroppoCortoException
				| CognomeNonValidoException | DataDiNascitaNonValidaException | UsernameNonValidoException
				| UsernameEsistenteException | EmailNonValidaException | EmailEsistenteException
				| SessoNonValidoException | TelefonoNonValidoException| PasswordNonValidaException
				| PasswordNonCorrispondentiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
