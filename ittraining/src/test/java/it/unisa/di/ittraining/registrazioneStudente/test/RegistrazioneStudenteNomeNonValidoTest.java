package it.unisa.di.ittraining.registrazioneStudente.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.unisa.di.ittraining.studente.Studente;
import it.unisa.di.ittraining.utente.NomeNonValidoException;
import it.unisa.di.ittraining.utente.UtenteService;
import junit.framework.TestCase;

/*
 * Classe di test per {@link RegistrazioneStudenteFormValidator}
 * @author Alessia
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RegistrazioneStudenteNomeNonValidoTest extends TestCase{
	
	private Studente studente;
	@Autowired
	private UtenteService u;
	
	@Before
	public void setUp() {
		//studente di test
		studente = new Studente();
		studente.setNome("Bu");
		studente.setUsername("Anto");
	}
	
	@Test
	public void testValidaNome() throws NomeNonValidoException {
		boolean flag = true;
		try {
			u.validaNome(studente.getNome());
		}catch(Exception e){
			flag = false;
		}
		assertFalse(flag);
	}
	
	@After
	public void tearDown(){
	}
	
}
