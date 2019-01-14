package it.unisa.di.ittraining.progettoformativotest;

import static org.junit.Assert.assertEquals;

import it.unisa.di.ittraining.azienda.Azienda;
import it.unisa.di.ittraining.azienda.AziendaRepository;
import it.unisa.di.ittraining.azienda.TutorAziendale;
import it.unisa.di.ittraining.azienda.TutorAziendaleRepository;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinioRepository;
import it.unisa.di.ittraining.progettoformativo.ProgettoFormativo;
import it.unisa.di.ittraining.progettoformativo.ProgettoFormativoRepository;
import it.unisa.di.ittraining.studente.Studente;
import it.unisa.di.ittraining.studente.StudenteRepository;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProgettoFormativoRepositoryIntTest {

  @Autowired
  private ProgettoFormativoRepository progettoFormativoRep;

  @Autowired
  private DomandaTirocinioRepository domandaTirocinioRep;
  
  @Autowired
  private StudenteRepository studenteRep;

  @Autowired
  private AziendaRepository aziendaRep;

  @Autowired
  private TutorAziendaleRepository tutorAziendaleRep;

  private DomandaTirocinio domandaTirocinio;

  /**
  * Metodo eseguito prima del test. Permette di istanziare una domanda e un progetto
  * formativo e salvarli all'interno del Database.
  */
  @Before
  public void salvaProgetto() {

    Studente studente = new Studente();
    studente.setNome("Laura");
    studente.setCognome("Oliva");
    studente.setDataDiNascita(LocalDate.of(1997, Month.JUNE, 29));
    studente.setMatricola("0512100000");
    studente.setSesso("F");
    studente.setEmail("laura@studenti.unisa.it");
    studente.setPassword("ab12cd34ef");
    studente.setUsername("laura1997");
    studente.setTelefono("3404050333");
    studente = studenteRep.save(studente);

    Azienda azienda = new Azienda();
    azienda.setNome("WebDevelopmentSRL");
    azienda.setTelefono("3333333333");
    azienda.setSede("Avellino");
    azienda.setIndirizzo("Via Roma 45");
    azienda.setEmail("grafica@gmail.com");
    azienda = aziendaRep.save(azienda);

    TutorAziendale tutorAziendale = new TutorAziendale();
    tutorAziendale.setUsername("linaWeb");
    tutorAziendale.setNome("Lina");
    tutorAziendale.setCognome("Neri");
    tutorAziendale.setDataDiNascita(LocalDate.of(1970, Month.DECEMBER, 30));
    tutorAziendale.setTelefono("0987654321");
    tutorAziendale.setEmail(azienda.getEmail());
    tutorAziendale.setPassword("lina123");
    tutorAziendale.setSesso("F");
    tutorAziendale.setAzienda(azienda);
    tutorAziendale = tutorAziendaleRep.save(tutorAziendale);

    domandaTirocinio = new DomandaTirocinio();
    domandaTirocinio.setCfu(6);
    domandaTirocinio.setOreTotali(150);
    domandaTirocinio.setData(LocalDate.now());
    domandaTirocinio.setInizioTirocinio(LocalDate.of(2019, Month.FEBRUARY, 12));
    domandaTirocinio.setFineTirocinio(LocalDate.of(2019, Month.MARCH, 20));
    domandaTirocinio.setAzienda(azienda);
    domandaTirocinio.setStudente(studente);
    domandaTirocinio = domandaTirocinioRep.save(domandaTirocinio);

    ProgettoFormativo progetto = new ProgettoFormativo();
    progetto.setDescrizione("Attività di testing");
    domandaTirocinio.setStatus(DomandaTirocinio.ACCETTATA_AZIENDA);
    progetto.setDomanda(domandaTirocinio);
    progetto = progettoFormativoRep.save(progetto);

    studenteRep.flush();
    aziendaRep.flush();
    tutorAziendaleRep.flush();
    domandaTirocinioRep.flush();
    progettoFormativoRep.flush();
  }

  @Test
  public void verificaProgettoFormativo() {
    DomandaTirocinio domanda = domandaTirocinioRep.findById((long)domandaTirocinio.getId());
    assertEquals(domandaTirocinio.getProgettoFormativo(), 
        domanda.getProgettoFormativo());
    assertEquals(domandaTirocinio, domanda);
  }
}
