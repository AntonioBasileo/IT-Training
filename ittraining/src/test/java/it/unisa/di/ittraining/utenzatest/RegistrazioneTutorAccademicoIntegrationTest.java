package it.unisa.di.ittraining.utenzatest;

import static org.junit.Assert.assertEquals;

import it.unisa.di.ittraining.tutoraccademico.TutorAccademico;
import it.unisa.di.ittraining.tutoraccademico.TutorAccademicoRepository;
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

import javax.transaction.Transactional;

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
public class RegistrazioneTutorAccademicoIntegrationTest {

  @Autowired
  private TutorAccademicoService tutorService;
  
  @Autowired
  private TutorAccademicoRepository tutorRep;
  
  @Test
  public void registraTutorAccademico() {
    TutorAccademico tutorAccademico = new TutorAccademico();
    tutorAccademico.setNome("Franco");
    tutorAccademico.setCognome("Neri");
    tutorAccademico.setDataDiNascita(LocalDate.of(1960, Month.AUGUST, 30));
    tutorAccademico.setTelefono("1234567898");
    tutorAccademico.setEmail("franco@unisa.it");
    tutorAccademico.setUsername("francoblabla");
    tutorAccademico.setPassword("franco123");
    tutorAccademico.setSesso("M");
 
    try {
      tutorAccademico = tutorService.registraTutorAccademico(tutorAccademico);
    } catch (NomeNonValidoException | NomeCognomeTroppoLungoException 
       | NomeCognomeTroppoCortoException | CognomeNonValidoException 
       | EmailNonValidaException | EmailEsistenteException 
       | TelefonoNonValidoException | DataDiNascitaNonValidaException 
       | PasswordNonValidaException | PasswordNonCorrispondentiException 
       | SessoNonValidoException | UsernameNonValidoException | UsernameEsistenteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    assertEquals(tutorAccademico.getUsername(), tutorRep.findByUsername(tutorAccademico
        .getUsername()).getUsername());
  
  }
}
