package it.unisa.di.ittraining.convenzionitest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import it.unisa.di.ittraining.azienda.Azienda;
import it.unisa.di.ittraining.azienda.AziendaRepository;
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
public class AziendaRepositoryIntTest {

  @Autowired
  private AziendaRepository aziendaRep;

  private Azienda azienda;

  private List<Azienda> aziende;

  /**
  * Metodo eseguito prima del test. Permette di istanziare una lista di
  * aziende e salvarle all'interno del Database.
  */
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
    
    aziendaRep.flush();
  }

  @Test
  public void findAziendaByNome() {
    Azienda azienda1 = aziendaRep.findByNome(azienda.getNome());
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
