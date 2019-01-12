package it.unisa.di.ittraining.utenza.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
public class StudenteRepositoryIntTest {

  @Autowired
  private StudenteRepository studenteRep;

  private Studente studente;

  /**
  * Metodo eseguito prima del test. Permette di istanziare uno studente
  * e salvarlo all'interno del Database.
  */
  @Before
  public void salvaStudente() {
    studente = new Studente();
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

    studenteRep.flush();
  }

  @Test
  public void findStudenteByUsername() {
    Studente studente1 = studenteRep.findByUsername(studente.getUsername());
    assertEquals(studente, studente1);
  }

  @Test
  public void findStudenteByUsernameAndPassword() {
    Studente studente1 = studenteRep.findByUsernameAndPassword(studente.getUsername(), 
        studente.getPassword());
    assertEquals(studente, studente1);
  }

  @Test
  public void existsStudenteByMatricola() {
    boolean flag = studenteRep.existsByMatricola(studente.getMatricola());
    assertTrue(flag);
  }

  @Test
  public void existsStudenteByEmail() {
    boolean flag = studenteRep.existsByEmail(studente.getEmail());
    assertTrue(flag);
  }
}
