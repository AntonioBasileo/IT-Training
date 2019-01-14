package it.unisa.di.ittraining.utenzatest;

import static org.junit.Assert.assertEquals;

import it.unisa.di.ittraining.impiegatosegreteria.ImpiegatoSegreteria;
import it.unisa.di.ittraining.impiegatosegreteria.ImpiegatoSegreteriaRepository;
import it.unisa.di.ittraining.impiegatosegreteria.ImpiegatoSegreteriaService;
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
public class RegistrazioneImpiegatoSegreteriaIntegrationTest {
  
  @Autowired
  private ImpiegatoSegreteriaService impiegatoService;
  
  @Autowired
  private ImpiegatoSegreteriaRepository impiegatoRep;
    
  @Test
  public void registraImpiegatoSegreteria() {

    ImpiegatoSegreteria impiegato = new ImpiegatoSegreteria();
    impiegato.setNome("Alessia");
    impiegato.setCognome("D'Agosto");
    impiegato.setDataDiNascita(LocalDate.of(1990, Month.JULY, 12));
    impiegato.setSesso("F");
    impiegato.setEmail("alessia@unisa.it");
    impiegato.setPassword("abdfkgnds");
    impiegato.setUsername("alessia");
    impiegato.setTelefono("3404050000");
    
    try {
      impiegato = impiegatoService.registraImpiegato(impiegato);
    } catch (NomeNonValidoException | NomeCognomeTroppoLungoException 
          | NomeCognomeTroppoCortoException
          | CognomeNonValidoException | EmailNonValidaException 
          | EmailEsistenteException | PasswordNonValidaException
          | PasswordNonCorrispondentiException | DataDiNascitaNonValidaException 
          | UsernameNonValidoException | UsernameEsistenteException 
          | SessoNonValidoException | TelefonoNonValidoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    assertEquals(impiegato, impiegatoRep.findByUsername(impiegato.getUsername()));
  }
}
