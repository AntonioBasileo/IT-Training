package it.unisa.di.ittraining.moduloRichiesta.test;

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

import it.unisa.di.ittraining.azienda.Azienda;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinioService;
import it.unisa.di.ittraining.studente.Studente;
import it.unisa.di.ittraining.studente.StudentiService;
import junit.framework.TestCase;

/*
 * Classe di test per {@link elaboraDomandaTirocinio in DomandaTirocinioController}
 * @author Alessia
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class CompilazioneModuloRichiestaSuccessoTest extends TestCase {

	private DomandaTirocinio domandaTirocinio;
	private Azienda azienda;
	private Studente studente;
	@Autowired
	private DomandaTirocinioService domandaTirocinioService;
	@Autowired
	private StudentiService studenteService;
	
	@Before
	public void setUp() {
		
		domandaTirocinio = new DomandaTirocinio();
		azienda = new Azienda();
		azienda.setNome("Informatica Center");
		studente = new Studente();
		studente.setNome("Laura");
		studente.setCognome("Oliva");
		studente.setDataDiNascita(LocalDate.of(1997, Month.JUNE, 29));
		studente.setMatricola("0512100000");
		studente.setSesso("F");
		studente.setEmail("laura@studenti.unisa.it");
		studente.setPassword("ab12cd34ef");
		studente.setUsername("laura");
		studente.setMediaPonderata(28);
		studente.setTelefono("3404050333");
		studenteService.registraStudente(studente);
		domandaTirocinio.setInizioTirocinio(LocalDate.of(2019, Month.FEBRUARY, 2));
		domandaTirocinio.setFineTirocinio(LocalDate.of(2019, Month.MARCH, 20));
		domandaTirocinio.setCfu(6);
		domandaTirocinio.setAzienda(azienda);
		domandaTirocinio.setStudente(studente);
		domandaTirocinio.setData(LocalDate.now());
		domandaTirocinio.setOreTotali(150);
		domandaTirocinioService.registraDomanda(domandaTirocinio);
	}
	
	@Test
	public void testRegistraDomanda(){
		System.out.println(domandaTirocinio.getId());
		boolean flag = domandaTirocinioService.existsById(domandaTirocinio.getId());
		assertTrue(flag);
	}
	
	@After
	public void tearDown() {
		domandaTirocinioService.cancellaDomanda(domandaTirocinio);
		studenteService.cancellaStudente(studente);
	}
}
