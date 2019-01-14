package it.unisa.di.ittraining.utenzatest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import it.unisa.di.ittraining.impiegatosegreteria.ImpiegatoSegreteria;
import it.unisa.di.ittraining.impiegatosegreteria.ImpiegatoSegreteriaRepository;
import java.time.LocalDate;
import java.time.Month;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ImpiegatoSegreteriaRepositoryIntTest {

  @Autowired
  private ImpiegatoSegreteriaRepository impiegatoRep;

  private ImpiegatoSegreteria impiegatoSegreteria;

  /**
  * Metodo eseguito prima del test. Permette di istanziare
  * un impiegato di segreteria e salvarlo all'interno
  * del Database.
  */
  @Before
  public void salvaImpiegato() {
    impiegatoSegreteria = new ImpiegatoSegreteria();
    impiegatoSegreteria.setNome("Franco");
    impiegatoSegreteria.setCognome("Neri");
    impiegatoSegreteria.setEmail("franco@unisa.it");
    impiegatoSegreteria.setDataDiNascita(LocalDate.of(1970, Month.FEBRUARY, 12));
    impiegatoSegreteria.setUsername("Franco");
    impiegatoSegreteria.setPassword("franco");
    impiegatoSegreteria.setSesso("M");
    impiegatoSegreteria.setTelefono("3203333333");
    impiegatoSegreteria = impiegatoRep.save(impiegatoSegreteria);

    impiegatoRep.flush();
  }

  @Test
  public void findImpiegatoByUsernameAndPassword() {
    ImpiegatoSegreteria impiegato = 
        impiegatoRep.findByUsernameAndPassword(impiegatoSegreteria.getUsername(), 
        impiegatoSegreteria.getPassword());
    assertEquals(impiegatoSegreteria, impiegato);
  }

  @Test
  public void findImpiegatoByUsername() {
    ImpiegatoSegreteria impiegato = impiegatoRep.findByUsername(impiegatoSegreteria.getUsername());
    assertEquals(impiegatoSegreteria, impiegato);
  }

  @Test
  public void existsImpiegatoByEmail() {
    boolean flag = impiegatoRep.existsByEmail(impiegatoSegreteria.getEmail());
    assertTrue(flag);
  }
}
