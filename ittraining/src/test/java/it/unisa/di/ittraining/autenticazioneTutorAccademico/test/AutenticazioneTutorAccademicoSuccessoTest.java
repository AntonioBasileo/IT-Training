package it.unisa.di.ittraining.autenticazioneTutorAccademico.test;

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

import it.unisa.di.ittraining.tutoraccademico.TutorAccademico;
import it.unisa.di.ittraining.tutoraccademico.TutorAccademicoService;
import it.unisa.di.ittraining.utente.UtenteService;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class AutenticazioneTutorAccademicoSuccessoTest extends TestCase{

	private TutorAccademico tutor;
	
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private TutorAccademicoService tutorAccademicoService;
	
	@Before
	public void setUp() {
		tutor = new TutorAccademico();
		tutor.setUsername("Mario");
		tutor.setNome("Mario");
		tutor.setCognome("Rossi");
		tutor.setDataDiNascita(LocalDate.of(1963, Month.JANUARY, 3));
		tutor.setTelefono("3215689741");
		tutor.setEmail("MarioRossi@unisa.it");
		tutor.setPassword("mariorossi");
		tutor.setSesso("M");
		tutorAccademicoService.registraTutorAccademico(tutor);
	}
	
	@Test
	public void testLogin(){
		boolean flag = true;
		try {
			utenteService.login(tutor.getUsername(), tutor.getPassword());
		}catch(Exception e) {
			flag = false;
		}
		assertTrue(flag);
	}
	
	@After
	public void tearDown() throws NullPointerException {
		tutorAccademicoService.cancellaTutorAccademico(tutor);
	}
}
