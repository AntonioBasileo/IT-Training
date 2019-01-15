package it.unisa.di.ittraining.webtest;

import it.unisa.di.ittraining.azienda.Azienda;
import it.unisa.di.ittraining.azienda.AziendaEsistenteException;
import it.unisa.di.ittraining.azienda.AziendaNonValidaException;
import it.unisa.di.ittraining.azienda.AziendaService;
import it.unisa.di.ittraining.azienda.EmailAziendaEsistenteException;
import it.unisa.di.ittraining.azienda.IndirizzoNonValidoException;
import it.unisa.di.ittraining.azienda.SedeNonValidaException;
import it.unisa.di.ittraining.azienda.TelefonoNonValidoException;
import it.unisa.di.ittraining.utente.EmailNonValidaException;
import it.unisa.di.ittraining.web.RegistrazioneAziendaleForm;
import it.unisa.di.ittraining.web.RegistrazioneAziendaleFormValidator;
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
public class RegistrazioneTutorAziendaleFormValidatorIntTest {
  @Mock
  private Errors errors;

  @Autowired
  private RegistrazioneAziendaleFormValidator validator;

  @Autowired
  private AziendaService aziendaService;
  
  @Test
  public void registrazioneStudenteFormValidator() {

    Azienda azienda = new Azienda();
    azienda.setNome("AziendaBla");
    azienda.setIndirizzo("via blabla 12");
    azienda.setEmail("aziendagnoma@gmail.com");
    azienda.setSede("Salerno");
    azienda.setTelefono("3331212122");

    try {
      aziendaService.registraAzienda(azienda);
    } catch (AziendaNonValidaException | AziendaEsistenteException | SedeNonValidaException
         | IndirizzoNonValidoException | EmailNonValidaException | EmailAziendaEsistenteException
         | TelefonoNonValidoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    RegistrazioneAziendaleForm tutorForm = new RegistrazioneAziendaleForm();
    tutorForm.setNome("Mario");
    tutorForm.setCognome("Bianchi");
    tutorForm.setSesso("M");
    tutorForm.setGiornoNascita(12);
    tutorForm.setMeseNascita(10);
    tutorForm.setAnnoNascita(1996);
    tutorForm.setUsername("Mario12");
    tutorForm.setPassword("mario12");
    tutorForm.setConfermaPassword("mario12");
    tutorForm.setTelefono("3202244444");
    tutorForm.setEmail(azienda.getEmail());

    validator.validate((Object)tutorForm, (Errors) errors);
  }
}
