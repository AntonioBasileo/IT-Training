package it.unisa.di.ittraining.domandatirociniotest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import it.unisa.di.ittraining.azienda.Azienda;
import it.unisa.di.ittraining.azienda.AziendaRepository;
import it.unisa.di.ittraining.azienda.TutorAziendale;
import it.unisa.di.ittraining.azienda.TutorAziendaleRepository;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinioRepository;
import it.unisa.di.ittraining.studente.Studente;
import it.unisa.di.ittraining.studente.StudenteRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

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
public class DomandaTirocinioRepositoryIntegrationTest {

  @Autowired
  private StudenteRepository studenteRep;

  @Autowired
  private AziendaRepository aziendaRep;

  @Autowired
  private DomandaTirocinioRepository domandaRep;

  @Autowired
  private TutorAziendaleRepository tutorAziendaleRep;

  private DomandaTirocinio domandaTirocinio;
    
  private List<DomandaTirocinio> domande;

  /**
  * Metodo eseguito prima del test. Permette di istanziare uno studente
  * e salvarlo all'interno del Database.
  */
  @Before
  public void salvaDomanda() {

    domande = new ArrayList<>();

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
    domandaTirocinio.setStatus(0);
    domandaTirocinio = domandaRep.save(domandaTirocinio);

    DomandaTirocinio domandaTirocinio1 = new DomandaTirocinio();
    domandaTirocinio1.setCfu(6);
    domandaTirocinio1.setOreTotali(150);
    domandaTirocinio1.setData(LocalDate.now());
    domandaTirocinio1.setInizioTirocinio(LocalDate.of(2019, Month.MAY, 2));
    domandaTirocinio1.setFineTirocinio(LocalDate.of(2019, Month.JUNE, 20));
    domandaTirocinio1.setAzienda(azienda);
    domandaTirocinio1.setStudente(studente);
    domandaTirocinio1.setStatus(0);
    domandaTirocinio1 = domandaRep.save(domandaTirocinio1);
    
    domande.add(domandaTirocinio);
    domande.add(domandaTirocinio1);
    
    studenteRep.flush();
    aziendaRep.flush();
    tutorAziendaleRep.flush();
    domandaRep.flush();

  }

  @Test
  public void findById() {
    DomandaTirocinio domanda = domandaRep.findById((long) domandaTirocinio.getId());
    assertEquals(domandaTirocinio, domanda);
  }
  
  @Test
  public void findAllByStudenteUsername() {
    List<DomandaTirocinio> domandeSalvate = 
        domandaRep.findAllByStudenteUsername(domandaTirocinio.getStudente().getUsername());
    for (DomandaTirocinio d: domande) {
      assertTrue(domandeSalvate.contains(d));
    }
  }
  
  @Test
  public void findAllByStudenteUsernameAndStatus() {
    List<DomandaTirocinio> domandeSalvate = 
        domandaRep.findAllByStudenteUsernameAndStatus(domandaTirocinio.getStudente().getUsername(), 
            domandaTirocinio.getStatus());
    for (DomandaTirocinio d: domande) {
      assertTrue(domandeSalvate.contains(d));
    }
  }
  
  @Test
  public void findAllByAzienda() {
    List<DomandaTirocinio> domandeSalvate = 
        domandaRep.findAllByAzienda(domandaTirocinio.getAzienda());
    for (DomandaTirocinio d: domande) {
      assertTrue(domandeSalvate.contains(d));
    }
  }
  
  @Test
  public void findAllByStatus() {
    List<DomandaTirocinio> domandeSalvate = 
         domandaRep.findAllByStatus(domandaTirocinio.getStatus());
    for (DomandaTirocinio d: domande) {
      assertTrue(domandeSalvate.contains(d));
    }
  }
  
  @Test
  public void findAll() {
    List<DomandaTirocinio> domandeSalvate = domandaRep.findAll();
    for (DomandaTirocinio d: domande) {
      assertTrue(domandeSalvate.contains(d));
    }
  }
}
