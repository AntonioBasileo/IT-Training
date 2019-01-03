package it.unisa.di.ittraining.registrazioneStudente.test;

import java.time.LocalDate;
import java.time.Month;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.unisa.di.ittraining.studente.Studente;
import it.unisa.di.ittraining.studente.StudentiService;
import junit.framework.TestCase;

/*
 * Classe di test per {@link elaboraRichiestaIscrizioneStudente in RegistrazioneController}
 * @author Alessia
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class RegistrazioneStudenteSuccessoTest extends TestCase{

	private Studente studente;
	@Autowired
	private StudentiService studenteService;
	
	@Before
	public void setUp() {
		//studente di test
		studente = new Studente();
		studente.setNome("Laura");
		studente.setCognome("Oliva");
		studente.setDataDiNascita(LocalDate.of(1997, Month.JUNE, 29));
		studente.setMatricola("0512100000");
		studente.setSesso("F");
		studente.setEmail("laura@studenti.unisa.it");
		studente.setPassword("ab12cd34ef");
		studente.setUsername("laura");
		studente.setMediaPonderata(28);
		studente.setTelefono("3404050333");
				
		studenteService.registraStudente(studente);
	}
	
	@Test
	public void testRegistraStudente() {
		boolean flag = studenteService.existsByMatricola(studente.getMatricola());
		assertTrue(flag);
	}
	
	@After
	public void tearDown()throws NullPointerException{
		studenteService.cancellaStudente(studente);
	}
}
