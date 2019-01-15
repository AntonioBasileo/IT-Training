package it.unisa.di.ittraining.webtest;

import it.unisa.di.ittraining.web.RegistrazioneStudenteForm;
import it.unisa.di.ittraining.web.RegistrazioneStudenteFormValidator;

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
public class RegistrazioneStudenteFormValidatorIntTest {

  @Mock
  private Errors errors;
  
  @Autowired
  private RegistrazioneStudenteFormValidator validator;
  
  @Test
  public void registrazioneStudenteFormValidator() {
    RegistrazioneStudenteForm studenteForm = new RegistrazioneStudenteForm();
    studenteForm.setNome("Marica");
    studenteForm.setCognome("Marea");
    studenteForm.setSesso("F");
    studenteForm.setGiornoNascita(12);
    studenteForm.setMeseNascita(10);
    studenteForm.setAnnoNascita(1996);
    studenteForm.setMatricola("0512105555");
    studenteForm.setUsername("Marica");
    studenteForm.setPassword("marica");
    studenteForm.setConfermaPassword("marica");
    studenteForm.setTelefono("3204444444");
    studenteForm.setEmail("marica@studenti.unisa.it");
  
    validator.validate((Object)studenteForm, (Errors) errors);
  }
  
}
