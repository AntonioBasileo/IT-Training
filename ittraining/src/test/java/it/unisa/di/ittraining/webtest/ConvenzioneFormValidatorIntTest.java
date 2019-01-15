package it.unisa.di.ittraining.webtest;

import it.unisa.di.ittraining.web.ConvenzioneForm;
import it.unisa.di.ittraining.web.ConvenzioneFormValidator;
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
public class ConvenzioneFormValidatorIntTest {

  @Mock
  private Errors errors;

  @Autowired
  private ConvenzioneFormValidator validator;

  @Test
  public void convenzioneFormValidator() {

    ConvenzioneForm convenzioneForm = new ConvenzioneForm();
    convenzioneForm.setNome("Ente1");
    convenzioneForm.setIndirizzo("via rossi 3");
    convenzioneForm.setEmail("ente1@gmail.com");
    convenzioneForm.setSede("Avellino");
    convenzioneForm.setTelefono("3661212452");
    
    validator.validate((Object) convenzioneForm, errors);
  }
}
