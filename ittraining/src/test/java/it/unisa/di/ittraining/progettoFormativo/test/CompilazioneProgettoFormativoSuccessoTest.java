package it.unisa.di.ittraining.progettoFormativo.test;

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
import it.unisa.di.ittraining.azienda.AziendaService;
import it.unisa.di.ittraining.azienda.TutorAziendale;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinioService;
import it.unisa.di.ittraining.progettoformativo.ProgettoFormativo;
import it.unisa.di.ittraining.progettoformativo.ProgettoFormativoService;
import it.unisa.di.ittraining.studente.Studente;
import it.unisa.di.ittraining.studente.StudentiService;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class CompilazioneProgettoFormativoSuccessoTest extends TestCase {

	private ProgettoFormativo progettoFormativo;
	private Azienda azienda;
	private DomandaTirocinio domandaTirocinio;
	private Studente studente;
	private TutorAziendale tutorAziendale;
	
	@Autowired
	private ProgettoFormativoService progettoFormativoService;
	
	@Autowired
	private AziendaService aziendaService;
	
	@Autowired
	private StudentiService studenteService;
	
	@Autowired
	private DomandaTirocinioService domandaTirocinioService;
	
	@Autowired
	private AziendaService tutorAziendaleService;
	
	@Before
	public void setUp() {
		
		progettoFormativo = new ProgettoFormativo();
		progettoFormativo.setDescrizione("Lo studente dovrà effettuare attività di testing e di revisione");
	
		azienda = new Azienda();
		azienda.setNome("Azienda1");
		azienda.setEmail("azienda1@gmail.com");
		azienda.setIndirizzo("via Rossi 21");
		azienda.setSede("Avellino");
		azienda.setTelefono("3331245789");
		aziendaService.registraAzienda(azienda);
		
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
		
		domandaTirocinio = new DomandaTirocinio();
		domandaTirocinio.setInizioTirocinio(LocalDate.of(2019, Month.FEBRUARY, 2));
		domandaTirocinio.setFineTirocinio(LocalDate.of(2019, Month.MARCH, 20));
		domandaTirocinio.setCfu(6);
		domandaTirocinio.setAzienda(azienda);
		domandaTirocinio.setData(LocalDate.now());
		domandaTirocinio.setOreTotali(150);
		domandaTirocinio.setStudente(studente);
		domandaTirocinioService.registraDomanda(domandaTirocinio);
		
		tutorAziendale = new TutorAziendale();
		tutorAziendale.setNome("Alessia");
		tutorAziendale.setCognome("D'Agosto");
		tutorAziendale.setDataDiNascita(LocalDate.of(1990, Month.JULY, 12));
		tutorAziendale.setEmail("azienda1@gmail.com");
		tutorAziendale.setSesso("F");
		tutorAziendale.setTelefono("0974851111");
		tutorAziendale.setAzienda(azienda);
		tutorAziendaleService.registraTutorAziendale(tutorAziendale);

		progettoFormativo.setAzienda(azienda);
		progettoFormativo.setDomanda(domandaTirocinio);
		
		progettoFormativoService.inserisciProgetto(progettoFormativo);
	}
	
	@Test
	public void testInserisciProgetto() {
		boolean flag = progettoFormativoService.existsById(progettoFormativo.getId());
		assertTrue(flag);
	}
	
	@After
	public void tearDown() throws NullPointerException {
		progettoFormativoService.cancellaProgettoFormativo(progettoFormativo);
		aziendaService.cancellaAzienda(azienda);
		studenteService.cancellaStudente(studente);
		domandaTirocinioService.cancellaDomanda(domandaTirocinio);
		tutorAziendaleService.cancellaTutorAziendale(tutorAziendale);
	}
}
