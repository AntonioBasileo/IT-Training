package it.unisa.di.ittraining.utenzatest;

import static org.junit.Assert.assertEquals;

import it.unisa.di.ittraining.azienda.Azienda;
import it.unisa.di.ittraining.azienda.AziendaEsistenteException;
import it.unisa.di.ittraining.azienda.AziendaNonEsistenteException;
import it.unisa.di.ittraining.azienda.AziendaNonValidaException;
import it.unisa.di.ittraining.azienda.AziendaService;
import it.unisa.di.ittraining.azienda.EmailAziendaEsistenteException;
import it.unisa.di.ittraining.azienda.EmailNonAssociataException;
import it.unisa.di.ittraining.azienda.IndirizzoNonValidoException;
import it.unisa.di.ittraining.azienda.SedeNonValidaException;
import it.unisa.di.ittraining.azienda.TelefonoNonValidoException;
import it.unisa.di.ittraining.azienda.TutorAziendale;
import it.unisa.di.ittraining.azienda.TutorAziendaleRepository;
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
public class RegistrazioneTutorAziendaleIntegrationTest {
  
  @Autowired
  private AziendaService tutorService;
  
  @Autowired
  private TutorAziendaleRepository tutorRep;
  
  @Test
  public void registraTutorAziendale() {

    Azienda azienda = new Azienda();
    azienda.setNome("theorem");
    azienda.setSede("Fisciano");
    azienda.setEmail("gianfili@gmail.com");
    azienda.setIndirizzo("via Rossi 12");
    azienda.setTelefono("0981234567");
    
    try {
      azienda = tutorService.registraAzienda(azienda);
    } catch (AziendaNonValidaException | AziendaEsistenteException | SedeNonValidaException
       | IndirizzoNonValidoException | EmailNonValidaException | EmailAziendaEsistenteException
       | TelefonoNonValidoException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    
    TutorAziendale tutorAziendale = new TutorAziendale();
    tutorAziendale.setUsername("giancarlodasantommaso");
    tutorAziendale.setNome("Lina");
    tutorAziendale.setCognome("Neri");
    tutorAziendale.setDataDiNascita(LocalDate.of(1970, Month.DECEMBER, 30));
    tutorAziendale.setTelefono("0987654321");
    tutorAziendale.setEmail(azienda.getEmail());
    tutorAziendale.setPassword("lina123");
    tutorAziendale.setSesso("F");
    
    try {
      tutorAziendale = tutorService.registraTutorAziendale(tutorAziendale,azienda.getNome());
    } catch (NomeNonValidoException | NomeCognomeTroppoLungoException 
       | NomeCognomeTroppoCortoException | CognomeNonValidoException 
       | EmailNonValidaException | EmailEsistenteException | EmailNonAssociataException
       | UsernameNonValidoException | UsernameEsistenteException | PasswordNonValidaException
       | PasswordNonCorrispondentiException | DataDiNascitaNonValidaException 
       | AziendaNonValidaException | AziendaEsistenteException 
       | SessoNonValidoException | TelefonoNonValidoException
       | AziendaNonEsistenteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    assertEquals(tutorAziendale, tutorRep.findByUsername(tutorAziendale.getUsername()));
  }
}
