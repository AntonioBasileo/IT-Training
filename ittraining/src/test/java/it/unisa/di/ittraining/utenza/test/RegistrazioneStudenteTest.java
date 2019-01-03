package it.unisa.di.ittraining.utenza.test;



import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import it.unisa.di.ittraining.azienda.TelefonoNonValidoException;
import it.unisa.di.ittraining.studente.MatricolaStudenteEsistenteException;
import it.unisa.di.ittraining.studente.MatricolaStudenteNonValidaException;
import it.unisa.di.ittraining.studente.Studente;
import it.unisa.di.ittraining.studente.StudenteRepository;
import it.unisa.di.ittraining.studente.StudentiService;
import it.unisa.di.ittraining.utente.CognomeNonValidoException;
import it.unisa.di.ittraining.utente.DataDiNascitaNonValidaException;
import it.unisa.di.ittraining.utente.EmailEsistenteException;
import it.unisa.di.ittraining.utente.EmailNonValidaException;
import it.unisa.di.ittraining.utente.NomeCognomeTroppoCortoException;
import it.unisa.di.ittraining.utente.NomeCognomeTroppoLungoException;
import it.unisa.di.ittraining.utente.NomeNonValidoException;
import it.unisa.di.ittraining.utente.PasswordErrataException;
import it.unisa.di.ittraining.utente.PasswordNonCorrispondentiException;
import it.unisa.di.ittraining.utente.PasswordNonValidaException;
import it.unisa.di.ittraining.utente.SessoNonValidoException;
import it.unisa.di.ittraining.utente.UsernameEsistenteException;
import it.unisa.di.ittraining.utente.UsernameNonValidoException;

@RunWith(MockitoJUnitRunner.class)
public class RegistrazioneStudenteTest {

	@InjectMocks
	private StudentiService studentiService;
	
	@Mock
	private StudenteRepository studenteRepository;
	
	@Test
	public void registraStudenteSuccesso() throws NomeNonValidoException, NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException, CognomeNonValidoException, DataDiNascitaNonValidaException, UsernameNonValidoException, UsernameEsistenteException, EmailNonValidaException, EmailEsistenteException, SessoNonValidoException, it.unisa.di.ittraining.utente.TelefonoNonValidoException, MatricolaStudenteNonValidaException, MatricolaStudenteEsistenteException, PasswordNonValidaException, PasswordNonCorrispondentiException {
		
		Studente studente = new Studente();
		studente.setNome("Laura");
		studente.setCognome("Oliva");
		studente.setDataDiNascita(LocalDate.of(1997, Month.JUNE, 29));
		studente.setMatricola("0512100000");
		studente.setSesso("F");
		studente.setEmail("laura@studenti.unisa.it");
		studente.setPassword("ab12cd34ef");
		studente.setUsername("laura");
		studente.setTelefono("3404050333");
		
		
		
		when(studentiService.registraStudente(studente)).thenReturn(studente);
		
		
		
			try {
				studentiService.registraStudente(studente);
			} catch (NomeNonValidoException | NomeCognomeTroppoLungoException | NomeCognomeTroppoCortoException
					| CognomeNonValidoException | DataDiNascitaNonValidaException | UsernameNonValidoException
					| UsernameEsistenteException | EmailNonValidaException | EmailEsistenteException
					| SessoNonValidoException | it.unisa.di.ittraining.utente.TelefonoNonValidoException
					| MatricolaStudenteNonValidaException | MatricolaStudenteEsistenteException
					| PasswordNonValidaException | PasswordNonCorrispondentiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
		
	}
	
	
	
	
	@Test(expected =  NomeCognomeTroppoCortoException.class)
	public void registraStudenteNomeTroppoCorto() throws NomeNonValidoException, NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException, CognomeNonValidoException, DataDiNascitaNonValidaException, UsernameNonValidoException, UsernameEsistenteException, EmailNonValidaException, EmailEsistenteException, SessoNonValidoException, it.unisa.di.ittraining.utente.TelefonoNonValidoException, MatricolaStudenteNonValidaException, MatricolaStudenteEsistenteException, PasswordNonValidaException, PasswordNonCorrispondentiException {
		
		Studente studente = new Studente();
		studente.setNome("La");
		studente.setCognome("Oliva");
		studente.setDataDiNascita(LocalDate.of(1997, Month.JUNE, 29));
		studente.setMatricola("0512100000");
		studente.setSesso("F");
		studente.setEmail("laura@studenti.unisa.it");
		studente.setPassword("ab12cd34ef");
		studente.setUsername("laura");
		studente.setTelefono("3404050333");
		
		
		
		when(studentiService.registraStudente(studente)).thenReturn(studente);
		
	
	
			try {
				studentiService.registraStudente(studente);
			} catch (NomeNonValidoException | NomeCognomeTroppoLungoException | NomeCognomeTroppoCortoException
					| CognomeNonValidoException | DataDiNascitaNonValidaException | UsernameNonValidoException
					| UsernameEsistenteException | EmailNonValidaException | EmailEsistenteException
					| SessoNonValidoException | it.unisa.di.ittraining.utente.TelefonoNonValidoException
					| MatricolaStudenteNonValidaException | MatricolaStudenteEsistenteException
					| PasswordNonValidaException | PasswordNonCorrispondentiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}
	
	@Test(expected =  DataDiNascitaNonValidaException.class)
	public void registraStudenteDataDiNascitaNonValida() throws NomeNonValidoException, NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException, CognomeNonValidoException, DataDiNascitaNonValidaException, UsernameNonValidoException, UsernameEsistenteException, EmailNonValidaException, EmailEsistenteException, SessoNonValidoException, it.unisa.di.ittraining.utente.TelefonoNonValidoException, MatricolaStudenteNonValidaException, MatricolaStudenteEsistenteException, PasswordNonValidaException, PasswordNonCorrispondentiException {
		
		Studente studente = new Studente();
		studente.setNome("Laura");
		studente.setCognome("Oliva");
		studente.setDataDiNascita(LocalDate.of(2009, Month.JUNE, 29));
		studente.setMatricola("0512100000");
		studente.setSesso("F");
		studente.setEmail("laura@studenti.unisa.it");
		studente.setPassword("ab12cd34ef");
		studente.setUsername("laura");
		studente.setTelefono("3404050333");
		
		
		
		when(studentiService.registraStudente(studente)).thenReturn(studente);
		
		
			try {
				studentiService.registraStudente(studente);
			} catch (NomeNonValidoException | NomeCognomeTroppoLungoException | NomeCognomeTroppoCortoException
					| CognomeNonValidoException | DataDiNascitaNonValidaException | UsernameNonValidoException
					| UsernameEsistenteException | EmailNonValidaException | EmailEsistenteException
					| SessoNonValidoException | it.unisa.di.ittraining.utente.TelefonoNonValidoException
					| MatricolaStudenteNonValidaException | MatricolaStudenteEsistenteException
					| PasswordNonValidaException | PasswordNonCorrispondentiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}
	
	
	@Test(expected =  EmailNonValidaException.class)
	public void registraStudenteEmailNonValida() throws NomeNonValidoException, NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException, CognomeNonValidoException, DataDiNascitaNonValidaException, UsernameNonValidoException, UsernameEsistenteException, EmailNonValidaException, EmailEsistenteException, SessoNonValidoException, it.unisa.di.ittraining.utente.TelefonoNonValidoException, MatricolaStudenteNonValidaException, MatricolaStudenteEsistenteException, PasswordNonValidaException, PasswordNonCorrispondentiException {
		
		Studente studente = new Studente();
		studente.setNome("Laura");
		studente.setCognome("Oliva");
		studente.setDataDiNascita(LocalDate.of(1997, Month.JUNE, 29));
		studente.setMatricola("0512100000");
		studente.setSesso("F");
		studente.setEmail("laura@gmail.com");
		studente.setPassword("ab12cd34ef");
		studente.setUsername("laura");
		studente.setTelefono("3404050333");
		
		
		
		when(studentiService.registraStudente(studente)).thenReturn(studente);
		

			try {
				studentiService.registraStudente(studente);
			} catch (NomeNonValidoException | NomeCognomeTroppoLungoException | NomeCognomeTroppoCortoException
					| CognomeNonValidoException | DataDiNascitaNonValidaException | UsernameNonValidoException
					| UsernameEsistenteException | EmailNonValidaException | EmailEsistenteException
					| SessoNonValidoException | it.unisa.di.ittraining.utente.TelefonoNonValidoException
					| MatricolaStudenteNonValidaException | MatricolaStudenteEsistenteException
					| PasswordNonValidaException | PasswordNonCorrispondentiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	
	
	@Test(expected =  MatricolaStudenteNonValidaException.class)
	public void registraStudenteMatricolaNonValida() throws NomeNonValidoException, NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException, CognomeNonValidoException, DataDiNascitaNonValidaException, UsernameNonValidoException, UsernameEsistenteException, EmailNonValidaException, EmailEsistenteException, SessoNonValidoException, it.unisa.di.ittraining.utente.TelefonoNonValidoException, MatricolaStudenteNonValidaException, MatricolaStudenteEsistenteException, PasswordNonValidaException, PasswordNonCorrispondentiException {
		
		Studente studente = new Studente();
		studente.setNome("Laura");
		studente.setCognome("Oliva");
		studente.setDataDiNascita(LocalDate.of(1997, Month.JUNE, 29));
		studente.setMatricola("05121ababa");
		studente.setSesso("F");
		studente.setEmail("laura@studenti.unisa.it");
		studente.setPassword("ab12cd34ef");
		studente.setUsername("laura");
		
		studente.setTelefono("3404050333");
		
		
		
		when(studentiService.registraStudente(studente)).thenReturn(studente);
		
		
			try {
				studentiService.registraStudente(studente);
			} catch (NomeNonValidoException | NomeCognomeTroppoLungoException | NomeCognomeTroppoCortoException
					| CognomeNonValidoException | DataDiNascitaNonValidaException | UsernameNonValidoException
					| UsernameEsistenteException | EmailNonValidaException | EmailEsistenteException
					| SessoNonValidoException | it.unisa.di.ittraining.utente.TelefonoNonValidoException
					| MatricolaStudenteNonValidaException | MatricolaStudenteEsistenteException
					| PasswordNonValidaException | PasswordNonCorrispondentiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}
	
	
	@Test(expected =  PasswordNonValidaException.class)
	public void registraStudentePasswordNonValida() throws NomeNonValidoException, NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException, CognomeNonValidoException, DataDiNascitaNonValidaException, UsernameNonValidoException, UsernameEsistenteException, EmailNonValidaException, EmailEsistenteException, SessoNonValidoException, it.unisa.di.ittraining.utente.TelefonoNonValidoException, MatricolaStudenteNonValidaException, MatricolaStudenteEsistenteException, PasswordNonValidaException, PasswordNonCorrispondentiException {
		
		Studente studente = new Studente();
		studente.setNome("Laura");
		studente.setCognome("Oliva");
		studente.setDataDiNascita(LocalDate.of(1997, Month.JUNE, 29));
		studente.setMatricola("0512100000");
		studente.setSesso("F");
		studente.setEmail("laura@studenti.unisa.it");
		studente.setPassword("abcd");
		studente.setUsername("laura");
		studente.setTelefono("3404050333");
		
		
		
		when(studentiService.registraStudente(studente)).thenReturn(studente);
		
		
	
			try {
				studentiService.registraStudente(studente);
			} catch (NomeNonValidoException | NomeCognomeTroppoLungoException | NomeCognomeTroppoCortoException
					| CognomeNonValidoException | DataDiNascitaNonValidaException | UsernameNonValidoException
					| UsernameEsistenteException | EmailNonValidaException | EmailEsistenteException
					| SessoNonValidoException | it.unisa.di.ittraining.utente.TelefonoNonValidoException
					| MatricolaStudenteNonValidaException | MatricolaStudenteEsistenteException
					| PasswordNonValidaException | PasswordNonCorrispondentiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	
	
	
}
