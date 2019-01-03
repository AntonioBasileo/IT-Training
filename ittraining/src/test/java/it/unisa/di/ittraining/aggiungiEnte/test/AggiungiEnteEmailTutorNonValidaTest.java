package it.unisa.di.ittraining.aggiungiEnte.test;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.unisa.di.ittraining.azienda.Azienda;
import it.unisa.di.ittraining.azienda.AziendaService;
import it.unisa.di.ittraining.utente.EmailEsistenteException;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class AggiungiEnteEmailTutorNonValidaTest extends TestCase{

private Azienda azienda;
	
	@Autowired
	private AziendaService aziendaService;
	
	@Before
	public void setUp() {
		azienda = new Azienda();
		azienda.setNome("Azienda Inf");
		azienda.setSede("Avellino");
		azienda.setIndirizzo("Via Rossi 3");
		azienda.setTelefono("0825123456");
		azienda.setEmail("informaticaCenter@gmail.com");
	}
	
	@Test
	public void testValidaEmail() throws EmailEsistenteException{
		boolean flag = true;
		try {
			aziendaService.validaEmailAziendale(azienda.getNome(), azienda.getEmail());
		}catch(Exception e) {
			flag = false;
		}
		assertFalse(flag);
	}
	
	@After
	public void tearDown() {
		
	}
}
