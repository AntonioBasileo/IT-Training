package it.unisa.di.ittraining.registrazioneStudente.test;

import java.time.LocalDate;
import java.time.Month;

import javax.transaction.Transactional;

import org.aspectj.lang.annotation.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
import it.unisa.di.ittraining.utente.PasswordNonCorrispondentiException;
import it.unisa.di.ittraining.utente.PasswordNonValidaException;
import it.unisa.di.ittraining.utente.SessoNonValidoException;
import it.unisa.di.ittraining.utente.TelefonoNonValidoException;
import it.unisa.di.ittraining.utente.UsernameEsistenteException;
import it.unisa.di.ittraining.utente.UsernameNonValidoException;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class RegistrazioneStudenteSuccessoTest extends TestCase{

	private Studente studente;
	@Autowired
	private StudentiService studenteService;
	
	@Autowired
	private StudenteRepository studenteRepository;
	
	@org.junit.Before
	public void setUp() throws NomeNonValidoException, NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException, CognomeNonValidoException, DataDiNascitaNonValidaException, UsernameNonValidoException, UsernameEsistenteException, EmailNonValidaException, EmailEsistenteException, SessoNonValidoException, TelefonoNonValidoException, MatricolaStudenteNonValidaException, MatricolaStudenteEsistenteException, PasswordNonValidaException, PasswordNonCorrispondentiException {
		//studente di test
		studente = new Studente();
		studente.setNome("Laura");
		studente.setCognome("Oliva");
		studente.setDataDiNascita(LocalDate.of(1997, Month.APRIL, 29));
		studente.setMatricola("0512100000");
		studente.setSesso("F");
		studente.setEmail("giancarlo@studenti.unisa.it");
		studente.setPassword("ab12cd34ef");
		studente.setUsername("Giancarlo");
		studente.setTelefono("3404050333");
				
		studenteService.registraStudente(studente);
	}
	
	@Test
	public void testRegistraStudente() {
		boolean flag = studenteRepository.existsByMatricola(studente.getMatricola());
		assertTrue(flag);
	}
	
	@After
	public void tearDown()throws NullPointerException{
		studenteRepository.delete(studente);
	}
}

