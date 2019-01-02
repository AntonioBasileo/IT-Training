package it.unisa.di.ittraining.registrazioneStudente.test;

import static org.junit.Assert.assertFalse;

import java.time.LocalDate;
import java.time.Month;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.unisa.di.ittraining.studente.MatricolaStudenteNonValidaException;
import it.unisa.di.ittraining.studente.Studente;
import it.unisa.di.ittraining.studente.StudentiService;

/*
 * Classe di test per {@link elaboraRichiestaIscrizioneStudente in RegistrazioneController}
 * @author Alessia
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RegistrazioneStudenteMatricolaNonValidaTest {
	
	private Studente studente;
	@Autowired
	private StudentiService studenteService;
	
	@Before
	public void setUp() {
		//studente di test
		studente = new Studente();
		studente.setNome("Laura");
		studente.setUsername("Oliva");
		studente.setDataDiNascita(LocalDate.of(1997, Month.JUNE, 29));
		studente.setMatricola("051210abab");
	}
	
	@Test
	public void testValidaMatricolaStudente() throws MatricolaStudenteNonValidaException {
		boolean flag = true;
		try {
			studenteService.validaMatricolaStudente(studente.getMatricola());
		}catch(Exception e){
			flag = false;
		}
		assertFalse(flag);
	}
	
	@After
	public void tearDown(){
	}
	
}
