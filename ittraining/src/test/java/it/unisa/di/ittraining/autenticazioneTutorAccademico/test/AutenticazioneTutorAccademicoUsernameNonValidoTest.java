package it.unisa.di.ittraining.autenticazioneTutorAccademico.test;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.unisa.di.ittraining.tutoraccademico.TutorAccademico;
import it.unisa.di.ittraining.utente.UtenteService;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class AutenticazioneTutorAccademicoUsernameNonValidoTest extends TestCase {

	private TutorAccademico tutor;
	
	@Autowired
	private UtenteService utenteService;
	
	@Before
	public void setUp() {
		tutor = new TutorAccademico();
		tutor.setUsername("Bla");
	}
	
	@Test
	public void testLogin(){
		boolean flag = true;
		try {
			utenteService.login(tutor.getUsername(), tutor.getPassword());
		}catch(Exception e) {
			flag = false;
		}
		assertFalse(flag);
	}
	
	@After
	public void tearDown() {
		
	}
	
	
}
