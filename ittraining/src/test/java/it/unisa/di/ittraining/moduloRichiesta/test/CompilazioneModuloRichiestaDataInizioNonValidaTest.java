package it.unisa.di.ittraining.moduloRichiesta.test;

import static org.junit.Assert.assertFalse;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.unisa.di.ittraining.domandatirocinio.DataNonValidaException;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinioService;

/*
 * Classe di test per {@link elaboraDomandaTirocinio in DomandaTirocinioController}
 * @author Alessia
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CompilazioneModuloRichiestaDataInizioNonValidaTest {

	private DomandaTirocinio domandaTirocinio;
	@Autowired
	private DomandaTirocinioService domandaTirocinioService;
	
	@Before
	public void setUp() {
		domandaTirocinio = new DomandaTirocinio();
		domandaTirocinio.setInizioTirocinio(LocalDate.of(2018, Month.DECEMBER, 3));
	}
	
	@Test
	public void testValidaDataInizio() throws DataNonValidaException{
		boolean flag = true;
		try {
			domandaTirocinioService.validaDataInizio(domandaTirocinio.getInizioTirocinio());
		}catch(Exception e){
			flag = false;
		}
		assertFalse(flag);
	}
}
