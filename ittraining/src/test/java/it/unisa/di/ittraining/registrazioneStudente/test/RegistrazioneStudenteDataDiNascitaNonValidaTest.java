package it.unisa.di.ittraining.registrazioneStudente.test;


import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.unisa.di.ittraining.studente.Studente;
import it.unisa.di.ittraining.utente.DataDiNascitaNonValidaException;
import it.unisa.di.ittraining.utente.UtenteService;
import junit.framework.TestCase;

/*
 * Classe di test per {@link elaboraRichiestaIscrizioneStudente in RegistrazioneController}
 * @author Alessia
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RegistrazioneStudenteDataDiNascitaNonValidaTest extends TestCase {

	private Studente studente;
	@Autowired
	private UtenteService u;
	
	@Before
	public void setUp() {
		//studente di test
		studente = new Studente();
		studente.setNome("Laura");
		studente.setCognome("Oliva");
		studente.setDataDiNascita(LocalDate.of(2009,Month.SEPTEMBER,15));
	}
	
	@Test
	public void testValidaDataDiNascita() throws DataDiNascitaNonValidaException {
		boolean f = true;	
		try {
			u.validaDataDiNascita(studente.getDataDiNascita());
		}catch(Exception e) {
			f = false;
		}
		assertFalse(f);
	}
}
