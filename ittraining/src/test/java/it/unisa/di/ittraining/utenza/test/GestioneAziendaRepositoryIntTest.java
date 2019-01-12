package it.unisa.di.ittraining.utenza.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import it.unisa.di.ittraining.azienda.Azienda;
import it.unisa.di.ittraining.azienda.AziendaRepository;
import it.unisa.di.ittraining.azienda.TutorAziendale;
import it.unisa.di.ittraining.azienda.TutorAziendaleRepository;

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
public class GestioneAziendaRepositoryIntTest {

  @Autowired
  private TutorAziendaleRepository tutorAziendaleRep;

  @Autowired
  private AziendaRepository aziendaRep;

  private TutorAziendale tutorAziendale;

  private Azienda azienda;

  private List<Azienda> aziende;

  @Before
  public void salva() {

    aziende = new ArrayList<>();
    Azienda azienda2 = new Azienda();
    azienda2.setNome("Informatica Center");
    azienda2.setTelefono("3663333333");
    azienda2.setSede("Avellino");
    azienda2.setIndirizzo("Via Mazzini 45");
    azienda2.setEmail("informatica@gmail.com");
    azienda2 = aziendaRep.save(azienda2);
    
    aziende.add(azienda2);
    
    Azienda azienda3 = new Azienda();
    azienda3.setNome("Azienda3");
    azienda3.setTelefono("3383333333");
    azienda3.setSede("Salerno");
    azienda3.setIndirizzo("Via Piave 21");
    azienda3.setEmail("azienda3@gmail.com");
    azienda3 = aziendaRep.save(azienda3);

    aziende.add(azienda3);
    
    azienda = new Azienda();
    azienda.setNome("Grafica SRL");
    azienda.setTelefono("3333333333");
    azienda.setSede("Avellino");
    azienda.setIndirizzo("Via Roma 45");
    azienda.setEmail("grafica@gmail.com");
    azienda = aziendaRep.save(azienda);

    aziende.add(azienda);
    
    tutorAziendale = new TutorAziendale();
    tutorAziendale.setUsername("giancarlodasantommaso");
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

  @Test
  public void findAziendaByNome() {
    Azienda azienda1 = aziendaRep.findByNome(tutorAziendale.getAzienda().getNome());
    assertEquals(azienda, azienda1);
  }

  @Test
  public void findAllAziende() {
    List<Azienda> aziende2 = aziendaRep.findAll();
    for (Azienda a: aziende) {
      assertTrue(aziende2.contains(a));
    }
  }

  @Test
  public void existsAziendaByEmail() {
    boolean flag = aziendaRep.existsByEmail(azienda.getEmail());
    assertTrue(flag);
  }

  @Test
  public void existsAziendaByNomeAndEmail() {
    boolean flag = aziendaRep.existsByNomeAndEmail(azienda.getNome(), azienda.getEmail());
    assertTrue(flag);
  }

  @Test
  public void existsAziendaByNome() {
    boolean flag = aziendaRep.existsByNome(azienda.getNome());
    assertTrue(flag);
  }
}
