package it.unisa.di.ittraining.utenza.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import it.unisa.di.ittraining.azienda.Azienda;
import it.unisa.di.ittraining.azienda.AziendaRepository;
import it.unisa.di.ittraining.azienda.TutorAziendale;
import it.unisa.di.ittraining.azienda.TutorAziendaleRepository;

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
public class TutorAziendaleRepositoryIntTest {

  @Autowired
  private TutorAziendaleRepository tutorAziendaleRep;
  
  @Autowired
  private AziendaRepository aziendaRep;
  
  private TutorAziendale tutorAziendale;

  /**
  * Metodo eseguito prima del test. Permette di istanziare un'azienda
  * e associarle un tutor aziendale, salvando entrambi all'interno del
  * Database.
  */
  @Before
  public void salva() {

    Azienda azienda = new Azienda();
    azienda.setNome("Grafica SRL");
    azienda.setTelefono("3333333333");
    azienda.setSede("Avellino");
    azienda.setIndirizzo("Via Roma 45");
    azienda.setEmail("grafica@gmail.com");
    azienda = aziendaRep.save(azienda);

    tutorAziendale = new TutorAziendale();
    tutorAziendale.setUsername("linaGrafica");
    tutorAziendale.setNome("Lina");
    tutorAziendale.setCognome("Neri");
    tutorAziendale.setDataDiNascita(LocalDate.of(1970, Month.DECEMBER, 30));
    tutorAziendale.setTelefono("0987654321");
    tutorAziendale.setEmail(azienda.getEmail());
    tutorAziendale.setPassword("lina123");
    tutorAziendale.setSesso("F");
    tutorAziendale.setAzienda(azienda);
    tutorAziendale = tutorAziendaleRep.save(tutorAziendale);
    
    aziendaRep.flush();
    tutorAziendaleRep.flush();
  }


  @Test
  public void findTutorAziendaleByUsernameAndPassword() {
    TutorAziendale tutor1 = 
        tutorAziendaleRep.findByUsernameAndPassword(tutorAziendale.getUsername(), 
        tutorAziendale.getPassword());
    assertEquals(tutorAziendale, tutor1);
  }

  @Test
  public void findTutorAziendaleByUsername() {
    TutorAziendale tutor1 = tutorAziendaleRep.findByUsername(tutorAziendale.getUsername());
    assertEquals(tutorAziendale, tutor1);
  }

  @Test
  public void existsTutorAziendaleByEmail() {
    boolean flag = tutorAziendaleRep.existsByEmail(tutorAziendale.getEmail());
    assertTrue(flag);
  }
}
