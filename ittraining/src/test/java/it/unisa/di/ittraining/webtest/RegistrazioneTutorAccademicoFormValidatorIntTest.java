package it.unisa.di.ittraining.webtest;

import it.unisa.di.ittraining.web.RegistrazioneAccademicoFormValidator;
import it.unisa.di.ittraining.web.RegistrazioneForm;

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
public class RegistrazioneTutorAccademicoFormValidatorIntTest {

  @Mock
  private Errors errors;

  @Autowired
  private RegistrazioneAccademicoFormValidator validator;
  
  @Test
  public void registrazioneTutorAccademicoFormValidator() {
    RegistrazioneForm tutorForm = new RegistrazioneForm();
    tutorForm.setNome("Mariagrazia");
    tutorForm.setCognome("Rossi");
    tutorForm.setSesso("F");
    tutorForm.setGiornoNascita(1);
    tutorForm.setMeseNascita(11);
    tutorForm.setAnnoNascita(1970);
    tutorForm.setUsername("Mariagrazia");
    tutorForm.setPassword("mariagrazia");
    tutorForm.setConfermaPassword("mariagrazia");
    tutorForm.setTelefono("3202245555");
    tutorForm.setEmail("mariagrazia@unisa.it");

    validator.validate((Object)tutorForm, (Errors) errors);
  }

}
