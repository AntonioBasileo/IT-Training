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
import it.unisa.di.ittraining.azienda.AziendaNonValidaException;
import it.unisa.di.ittraining.azienda.AziendaService;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class AggiungiEnteNomeNonValidoTest extends TestCase {

	private Azienda azienda;
	
	@Autowired
	private AziendaService aziendaService;
	
	@Before
	public void setUp() {
		azienda = new Azienda();
		azienda.setNome("I");
	}
	
	@Test
	public void testValidaNome() throws AziendaNonValidaException{
		boolean flag = true;
		try {
			aziendaService.validaNome(azienda.getNome());
		}catch(Exception e) {
			flag = false;
		}
		assertFalse(flag);
	}
	
	@After
	public void tearDown() {
		
	}
	
}
