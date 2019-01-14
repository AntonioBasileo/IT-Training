package it.unisa.di.ittraining.utenzatest;

import static org.junit.Assert.assertEquals;

import it.unisa.di.ittraining.studente.MatricolaStudenteEsistenteException;
import it.unisa.di.ittraining.studente.MatricolaStudenteNonValidaException;
import it.unisa.di.ittraining.studente.Studente;
import it.unisa.di.ittraining.studente.StudenteRepository;
import it.unisa.di.ittraining.studente.StudentiService;
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
public class RegistrazioneStudenteIntegrationTest {
  
  @Autowired
  private StudentiService studenteService;
  
  @Autowired
  private StudenteRepository studenteRep;
  
  @Test
  public void registraStudente() {
    
    Studente studente = new Studente();
    studente.setNome("Carlo");
    studente.setCognome("Rossi");
    studente.setDataDiNascita(LocalDate.of(1995, Month.JUNE, 10));
    studente.setMatricola("0512100001");
    studente.setSesso("M");
    studente.setEmail("carlo@studenti.unisa.it");
    studente.setPassword("carlo1995");
    studente.setUsername("carlo1995");
    studente.setTelefono("3404050000");
    
    try {
      studente = studenteService.registraStudente(studente);
    } catch (NomeNonValidoException | NomeCognomeTroppoLungoException 
          | NomeCognomeTroppoCortoException | CognomeNonValidoException 
          | DataDiNascitaNonValidaException | UsernameNonValidoException
          | UsernameEsistenteException | EmailNonValidaException 
          | EmailEsistenteException | SessoNonValidoException
          | TelefonoNonValidoException | MatricolaStudenteNonValidaException 
          | MatricolaStudenteEsistenteException
          | PasswordNonValidaException | PasswordNonCorrispondentiException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    assertEquals(studente, studenteRep.findByUsername(studente.getUsername()));
  }
}
