package it.unisa.di.ittraining.utenzatest;

import static org.junit.Assert.assertTrue;

import it.unisa.di.ittraining.tutoraccademico.TutorAccademico;
import it.unisa.di.ittraining.tutoraccademico.TutorAccademicoService;
import it.unisa.di.ittraining.utente.CognomeNonValidoException;
import it.unisa.di.ittraining.utente.DataDiNascitaNonValidaException;
import it.unisa.di.ittraining.utente.EmailEsistenteException;
import it.unisa.di.ittraining.utente.EmailNonValidaException;
import it.unisa.di.ittraining.utente.NomeCognomeTroppoCortoException;
import it.unisa.di.ittraining.utente.NomeCognomeTroppoLungoException;
import it.unisa.di.ittraining.utente.NomeNonValidoException;
import it.unisa.di.ittraining.utente.PasswordNonCorrispondentiException;
import it.unisa.di.ittraining.utente.PasswordNonValidaException;
import it.unisa.di.ittraining.utente.SessoNonValidoException;
import it.unisa.di.ittraining.utente.TelefonoNonValidoException;
import it.unisa.di.ittraining.utente.UsernameEsistenteException;
import it.unisa.di.ittraining.utente.UsernameNonValidoException;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
public class TutorAccademicoServiceIntTest {

  @Autowired
  private TutorAccademicoService tutorAccademicoService;

  private List<TutorAccademico> tutors;

  @Before
  public void elencaTutors() {

    tutors = new ArrayList<>();

    TutorAccademico tutor = new TutorAccademico();
    tutor.setNome("Carletto");
    tutor.setCognome("Neri");
    tutor.setDataDiNascita(LocalDate.of(1970, Month.AUGUST, 30));
    tutor.setTelefono("1234567898");
    tutor.setEmail("carletto1@unisa.it");
    tutor.setUsername("carletto");
    tutor.setPassword("carlo123");
    tutor.setSesso("M");

    TutorAccademico tutor1 = new TutorAccademico();
    tutor1.setNome("Luigi");
    tutor1.setCognome("rossi");
    tutor1.setDataDiNascita(LocalDate.of(1960, Month.AUGUST, 20));
    tutor1.setTelefono("7658901234");
    tutor1.setEmail("luigirossi@unisa.it");
    tutor1.setUsername("luigine");
    tutor1.setPassword("luigi123");
    tutor1.setSesso("M");

    try {
      tutor = tutorAccademicoService.registraTutorAccademico(tutor);
      tutor1 = tutorAccademicoService.registraTutorAccademico(tutor1);
    } catch (NomeNonValidoException | NomeCognomeTroppoLungoException 
        | NomeCognomeTroppoCortoException | CognomeNonValidoException 
        | EmailNonValidaException | EmailEsistenteException 
        | TelefonoNonValidoException | DataDiNascitaNonValidaException 
        | PasswordNonValidaException | PasswordNonCorrispondentiException 
        | SessoNonValidoException | UsernameNonValidoException | UsernameEsistenteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    tutors.add(tutor);
    tutors.add(tutor1);
  }
  
  @Test
  public void elencaTutorsAccademici() {
    List<TutorAccademico> tutorSalvati = tutorAccademicoService.elencaTutorAccademici();
    for (TutorAccademico a: tutors) {
      assertTrue(tutorSalvati.contains(a));
    }
  }
  
}
