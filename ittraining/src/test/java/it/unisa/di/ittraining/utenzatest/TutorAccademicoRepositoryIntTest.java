package it.unisa.di.ittraining.utenzatest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import it.unisa.di.ittraining.tutoraccademico.TutorAccademico;
import it.unisa.di.ittraining.tutoraccademico.TutorAccademicoRepository;

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
public class TutorAccademicoRepositoryIntTest {

  @Autowired
  private TutorAccademicoRepository tutorAccademicoRep;

  private TutorAccademico tutorAccademico;

  private List<TutorAccademico> tutorsAccademici;

  /**
  * Metodo eseguito prima del test. Permette di istanziare un tutor accademico
  * e di salvarlo all'interno del Database.
  */
  @Before
  public void salvaTutorAccademico() {

    tutorsAccademici = new ArrayList<>();

    TutorAccademico tutorAccademico1 = new TutorAccademico();
    tutorAccademico1.setUsername("Rosetta");
    tutorAccademico1.setNome("Rosa");
    tutorAccademico1.setCognome("Bianchi");
    tutorAccademico1.setDataDiNascita(LocalDate.of(1970, Month.DECEMBER, 30));
    tutorAccademico1.setTelefono("0987654374");
    tutorAccademico1.setEmail("rosetta@unisa.it");
    tutorAccademico1.setPassword("rosetta");
    tutorAccademico1.setSesso("F");
    tutorAccademico1 = tutorAccademicoRep.save(tutorAccademico1);

    tutorsAccademici.add(tutorAccademico1);

    TutorAccademico tutorAccademico2 = new TutorAccademico();
    tutorAccademico2.setUsername("Gianni");
    tutorAccademico2.setNome("Gianni");
    tutorAccademico2.setCognome("Rossi");
    tutorAccademico2.setDataDiNascita(LocalDate.of(1970, Month.DECEMBER, 30));
    tutorAccademico2.setTelefono("0977654374");
    tutorAccademico2.setEmail("gianni@unisa.it");
    tutorAccademico2.setPassword("gianni");
    tutorAccademico2.setSesso("M");
    tutorAccademico2 = tutorAccademicoRep.save(tutorAccademico2);

    tutorsAccademici.add(tutorAccademico2);

    tutorAccademico = new TutorAccademico();
    tutorAccademico.setUsername("Francesca");
    tutorAccademico.setNome("Franca");
    tutorAccademico.setCognome("Neri");
    tutorAccademico.setDataDiNascita(LocalDate.of(1970, Month.DECEMBER, 30));
    tutorAccademico.setTelefono("0987654324");
    tutorAccademico.setEmail("franca@unisa.it");
    tutorAccademico.setPassword("franca1");
    tutorAccademico.setSesso("F");
    tutorAccademico = tutorAccademicoRep.save(tutorAccademico);

    tutorsAccademici.add(tutorAccademico);

    tutorAccademicoRep.flush();
  }

  @Test
  public void findTutorAccademicoByUsername() {
    TutorAccademico tutor1 = tutorAccademicoRep.findByUsername(tutorAccademico.getUsername());
    assertEquals(tutorAccademico, tutor1);
  }

  @Test
  public void findTutorAccademicoByUsernameAndPassword() {
    TutorAccademico tutor1 = 
        tutorAccademicoRep.findByUsernameAndPassword(tutorAccademico.getUsername(), 
        tutorAccademico.getPassword());
    assertEquals(tutorAccademico, tutor1);
  }

  @Test
  public void findAllTutorsAccademici() {
    List<TutorAccademico> tutors = tutorAccademicoRep.findAll();
    for (TutorAccademico t: tutorsAccademici) {
      assertTrue(tutors.contains(t));
    }
  }

  @Test
  public void existsTutorAccademicoByEmail() {
    boolean flag = tutorAccademicoRep.existsByEmail(tutorAccademico.getEmail());
    assertTrue(flag);
  }

}
