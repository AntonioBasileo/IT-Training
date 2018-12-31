package it.unisa.di.ittraining.web.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.unisa.di.ittraining.studente.Studente;
import it.unisa.di.ittraining.web.RegistrazioneStudenteForm;
import junit.framework.TestCase;

/*
 * Classe di test per {@link RegistrazioneStudenteForm}
 * @author 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RegistrazioneStudenteForm.class)
public class RegistrazioneStudenteFormTest extends TestCase{
	
	private Studente studente;
	
	@Before
	public void setUp() {
		//studente di test
		studente = new Studente();
		studente.setNome("Bu");
	}
	
	@Test
	public void testGetNome() {
		boolean f = false;
		if(studente.getNome().length()<2) {
			f=false;
		}else {
			f=true;
		}
		assertTrue(f);
	}

	
}
