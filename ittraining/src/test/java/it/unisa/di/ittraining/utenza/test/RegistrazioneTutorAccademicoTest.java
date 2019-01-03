package it.unisa.di.ittraining.utenza.test;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import it.unisa.di.ittraining.tutoraccademico.TutorAccademico;
import it.unisa.di.ittraining.tutoraccademico.TutorAccademicoRepository;
import it.unisa.di.ittraining.tutoraccademico.TutorAccademicoService;
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
public class RegistrazioneTutorAccademicoTest {

	
	@InjectMocks
	private TutorAccademicoService tutorAccademicoService;
	
	@Mock
	private TutorAccademicoRepository tutorAccademicoRepository;
	
	
	@Test
	public void registraTutorAccademicoSuccesso() throws NomeNonValidoException, NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException, CognomeNonValidoException, EmailNonValidaException, EmailEsistenteException, TelefonoNonValidoException, DataDiNascitaNonValidaException, PasswordNonValidaException, PasswordNonCorrispondentiException, SessoNonValidoException, UsernameNonValidoException, UsernameEsistenteException {
		
		TutorAccademico tutorAccademico = new TutorAccademico();
		tutorAccademico.setNome("Franco");
		tutorAccademico.setCognome("Rossi");
		tutorAccademico.setDataDiNascita(LocalDate.of(1960, Month.AUGUST, 30));
		tutorAccademico.setTelefono("1234567890");
		tutorAccademico.setEmail("franco@unisa.it");
		tutorAccademico.setUsername("francoR");
		tutorAccademico.setPassword("franco123");
		tutorAccademico.setSesso("M");
		
		
		
		when(tutorAccademicoService.registraTutorAccademico(tutorAccademico)).thenReturn(tutorAccademico);
		
		
		
			try {
				tutorAccademicoService.registraTutorAccademico(tutorAccademico);
			} catch (NomeNonValidoException | NomeCognomeTroppoLungoException | NomeCognomeTroppoCortoException
					| CognomeNonValidoException | EmailNonValidaException | EmailEsistenteException
					| TelefonoNonValidoException | DataDiNascitaNonValidaException | PasswordNonValidaException
					| PasswordNonCorrispondentiException | SessoNonValidoException | UsernameNonValidoException
					| UsernameEsistenteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	
	
	@Test(expected=EmailNonValidaException.class)
	public void registraTutorAccademicoEmailNonValida() throws NomeNonValidoException, NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException, CognomeNonValidoException, EmailNonValidaException, EmailEsistenteException, TelefonoNonValidoException, DataDiNascitaNonValidaException, PasswordNonValidaException, PasswordNonCorrispondentiException, SessoNonValidoException, UsernameNonValidoException, UsernameEsistenteException {
		
		TutorAccademico tutorAccademico = new TutorAccademico();
		tutorAccademico.setNome("Franco");
		tutorAccademico.setCognome("Rossi");
		tutorAccademico.setDataDiNascita(LocalDate.of(1960, Month.AUGUST, 30));
		tutorAccademico.setTelefono("1234567890");
		tutorAccademico.setEmail("franco@outlook.it");
		tutorAccademico.setUsername("francoR");
		tutorAccademico.setPassword("franco123");
		tutorAccademico.setSesso("M");
		
		
		
		when(tutorAccademicoService.registraTutorAccademico(tutorAccademico)).thenReturn(tutorAccademico);
		
		
		
			try {
				tutorAccademicoService.registraTutorAccademico(tutorAccademico);
			} catch (NomeNonValidoException | NomeCognomeTroppoLungoException | NomeCognomeTroppoCortoException
					| CognomeNonValidoException | EmailNonValidaException | EmailEsistenteException
					| TelefonoNonValidoException | DataDiNascitaNonValidaException | PasswordNonValidaException
					| PasswordNonCorrispondentiException | SessoNonValidoException | UsernameNonValidoException
					| UsernameEsistenteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	
	

	
}
