package it.unisa.di.ittraining.webtest;

import it.unisa.di.ittraining.azienda.Azienda;
import it.unisa.di.ittraining.azienda.AziendaEsistenteException;
import it.unisa.di.ittraining.azienda.AziendaNonValidaException;
import it.unisa.di.ittraining.azienda.AziendaService;
import it.unisa.di.ittraining.azienda.EmailAziendaEsistenteException;
import it.unisa.di.ittraining.azienda.IndirizzoNonValidoException;
import it.unisa.di.ittraining.azienda.SedeNonValidaException;
import it.unisa.di.ittraining.azienda.TelefonoNonValidoException;
import it.unisa.di.ittraining.studente.MatricolaStudenteEsistenteException;
import it.unisa.di.ittraining.studente.MatricolaStudenteNonValidaException;
import it.unisa.di.ittraining.studente.Studente;
import it.unisa.di.ittraining.studente.StudentiService;
import it.unisa.di.ittraining.utente.CognomeNonValidoException;
import it.unisa.di.ittraining.utente.DataDiNascitaNonValidaException;
import it.unisa.di.ittraining.utente.EmailEsistenteException;
import it.unisa.di.ittraining.utente.EmailNonValidaException;
import it.unisa.di.ittraining.utente.NomeCognomeTroppoCortoException;
import it.unisa.di.ittraining.utente.NomeCognomeTroppoLungoException;
import it.unisa.di.ittraining.utente.NomeNonValidoException;
import it.unisa.di.ittraining.utente.PasswordErrataException;
import it.unisa.di.ittraining.utente.PasswordNonCorrispondentiException;
import it.unisa.di.ittraining.utente.PasswordNonValidaException;
import it.unisa.di.ittraining.utente.SessoNonValidoException;
import it.unisa.di.ittraining.utente.UsernameEsistenteException;
import it.unisa.di.ittraining.utente.UsernameNonEsistenteException;
import it.unisa.di.ittraining.utente.UsernameNonValidoException;
import it.unisa.di.ittraining.utente.UtenteService;
import it.unisa.di.ittraining.web.DomandaTirocinioForm;
import it.unisa.di.ittraining.web.DomandaTirocinioFormValidator;

import java.time.LocalDate;
import java.time.Month;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.Errors;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
public class DomandaTirocinioFormValidatorIntegrationTest {

  @Mock
   private Errors errors;

  @Autowired
   private DomandaTirocinioFormValidator validator;
  
  @Autowired
  private AziendaService aziendaService;
  
  @Autowired
  private StudentiService studenteService;
  
  @Autowired
  private UtenteService utenteService;
   
  @Test
   public void domandaTirocinioFormValidator() {

    Studente studente = new Studente();
    studente.setNome("Marica");
    studente.setCognome("Marea");
    studente.setSesso("F");
    studente.setMatricola("0512105555");
    studente.setUsername("Marica");
    studente.setPassword("marica");
    studente.setTelefono("3204444444");
    studente.setEmail("marica@studenti.unisa.it");
    studente.setDataDiNascita(LocalDate.of(1996, Month.JULY, 5));

    try {
      studenteService.registraStudente(studente);
    } catch (NomeNonValidoException | NomeCognomeTroppoLungoException 
        | NomeCognomeTroppoCortoException | CognomeNonValidoException 
        | DataDiNascitaNonValidaException | UsernameNonValidoException
        | UsernameEsistenteException | EmailNonValidaException | EmailEsistenteException 
        | SessoNonValidoException | it.unisa.di.ittraining.utente.TelefonoNonValidoException 
        | MatricolaStudenteNonValidaException
        | MatricolaStudenteEsistenteException | PasswordNonValidaException
        | PasswordNonCorrispondentiException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    
    Azienda azienda = new Azienda();
    azienda.setNome("EnteCiao");
    azienda.setIndirizzo("via ciao 12");
    azienda.setEmail("aziendaciao@gmail.com");
    azienda.setSede("Salerno");
    azienda.setTelefono("3338812122");

    try {
      aziendaService.registraAzienda(azienda);
    } catch (AziendaNonValidaException | AziendaEsistenteException 
          | SedeNonValidaException | IndirizzoNonValidoException 
          | EmailNonValidaException | EmailAziendaEsistenteException
          | TelefonoNonValidoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    try {
      utenteService.login(studente.getUsername(), studente.getPassword());
    } catch (UsernameNonEsistenteException | PasswordErrataException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    DomandaTirocinioForm domandaForm = new DomandaTirocinioForm();
    domandaForm.setAnnoInizio(2019);
    domandaForm.setAnnoFine(2019);
    domandaForm.setMeseInizio(2);
    domandaForm.setMeseFine(3);
    domandaForm.setGiornoInizio(2);
    domandaForm.setGiornoFine(10);
    domandaForm.setCfu(6);
    domandaForm.setNomeAzienda(azienda.getNome());
     
    validator.validate((Object) domandaForm, errors);  
    
    utenteService.logout();
  }

}
