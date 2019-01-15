package it.unisa.di.ittraining.webtest;

import it.unisa.di.ittraining.web.RegistrazioneForm;
import it.unisa.di.ittraining.web.RegistrazioneImpiegatoFormValidator;
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
public class RegistrazioneImpiegatoSegreteriaFormValidatorIntTest {
  
  @Mock
  private Errors errors;
  
  @Autowired
  private RegistrazioneImpiegatoFormValidator validator;
  
  @Test
  public void registrazioneImpiegatoFormValidator() {
    RegistrazioneForm impiegatoForm = new RegistrazioneForm();
    impiegatoForm.setNome("Gabriele");
    impiegatoForm.setCognome("Girolamo");
    impiegatoForm.setAnnoNascita(1980);
    impiegatoForm.setMeseNascita(12);
    impiegatoForm.setGiornoNascita(2);
    impiegatoForm.setEmail("gabriele@unisa.it");
    impiegatoForm.setSesso("M");
    impiegatoForm.setPassword("gabriele");
    impiegatoForm.setConfermaPassword("gabriele");
    impiegatoForm.setTelefono("3381245123");
    impiegatoForm.setUsername("Gabriele");
    
    validator.validate((Object)impiegatoForm, (Errors) errors);
  }
  
}
