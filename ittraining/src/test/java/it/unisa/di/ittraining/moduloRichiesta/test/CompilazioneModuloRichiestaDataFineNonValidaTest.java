package it.unisa.di.ittraining.moduloRichiesta.test;

import java.time.LocalDate;
import java.time.Month;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.unisa.di.ittraining.domandatirocinio.DataNonValidaException;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinioService;
import junit.framework.TestCase;

/*
 * Classe di test per {@link elaboraDomandaTirocinio in DomandaTirocinioController}
 * @author Alessia
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class CompilazioneModuloRichiestaDataFineNonValidaTest extends TestCase{

	private DomandaTirocinio domandaTirocinio;
	@Autowired
	private DomandaTirocinioService domandaTirocinioService;
	
	@Before
	public void setUp() {
		domandaTirocinio = new DomandaTirocinio();
		domandaTirocinio.setInizioTirocinio(LocalDate.of(2019, Month.FEBRUARY, 2));
		domandaTirocinio.setFineTirocinio(LocalDate.of(2019, Month.FEBRUARY, 1));
	}
	
	@Test
	public void testValidaDataFine() throws DataNonValidaException{
		boolean flag = true;
		try {
			domandaTirocinioService.validaDataFine(domandaTirocinio.getInizioTirocinio(),domandaTirocinio.getFineTirocinio());
		}catch(Exception e){
			flag = false;
		}
		assertFalse(flag);
	}
}
