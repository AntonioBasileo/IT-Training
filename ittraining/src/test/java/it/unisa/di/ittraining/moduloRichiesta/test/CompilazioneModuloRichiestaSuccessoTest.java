package it.unisa.di.ittraining.moduloRichiesta.test;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.unisa.di.ittraining.azienda.Azienda;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinioService;
import it.unisa.di.ittraining.studente.Studente;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CompilazioneModuloRichiestaSuccessoTest {

	private DomandaTirocinio domandaTirocinio;
	private Azienda azienda;
	private Studente studente;
	@Autowired
	private DomandaTirocinioService domandaTirocinioService;
	
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
		domandaTirocinio.setInizioTirocinio(LocalDate.of(2019, Month.FEBRUARY, 2));
		domandaTirocinio.setFineTirocinio(LocalDate.of(2019, Month.MARCH, 20));
		domandaTirocinio.setCfu(6);
		domandaTirocinio.setAzienda(azienda);
		domandaTirocinio.setStudente(studente);
		domandaTirocinioService.registraDomanda(domandaTirocinio);
	}
	
	@Test
	public void testRegistraDomanda() {
		boolean flag = false;
		if(domandaTirocinioService.existsByStudenteAndAzienda(studente, azienda)) {
			flag = true;
		}
		assertTrue(flag);
	}
	
	@After
	public void tearDown() {
		if(domandaTirocinioService.existsByStudenteAndAzienda(studente, azienda)) {
			domandaTirocinioService.cancellaDomanda(domandaTirocinio);
		}
	}
}
