package it.unisa.di.ittraining.progettoFormativo.test;

import static org.junit.Assert.assertEquals;

import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinioRepository;
import it.unisa.di.ittraining.progettoformativo.ProgettoFormativo;
import it.unisa.di.ittraining.progettoformativo.ProgettoFormativoRepository;

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
public class ProgettoFormativoRepositoryIntTest {

  @Autowired
  private ProgettoFormativoRepository progettoFormativoRep;

  @Autowired
  private DomandaTirocinioRepository domandaTirocinioRep;

  private DomandaTirocinio domandaTirocinio;

  /**
  * Metodo eseguito prima del test. Permette di istanziare una domanda e un progetto
  * formativo e salvarli all'interno del Database.
  */
  @Before
  public void salvaProgetto() {

    domandaTirocinio = new DomandaTirocinio();
    domandaTirocinio.setId(111L);
    domandaTirocinio.setCfu(6);
    domandaTirocinio.setOreTotali(150);
    domandaTirocinio.setData(LocalDate.now());
    domandaTirocinio.setInizioTirocinio(LocalDate.of(2019, Month.FEBRUARY, 12));
    domandaTirocinio.setFineTirocinio(LocalDate.of(2019, Month.MARCH, 20));
    domandaTirocinio = domandaTirocinioRep.save(domandaTirocinio);

    ProgettoFormativo progetto = new ProgettoFormativo();
    progetto.setDescrizione("Attività di testing");
    progetto.setId(111L);
    progetto.setDomanda(domandaTirocinio);
    progetto = progettoFormativoRep.save(progetto);

    domandaTirocinioRep.flush();
    progettoFormativoRep.flush();
  }

  @Test
  public void verificaProgettoFormativo() {
    DomandaTirocinio domanda = domandaTirocinioRep.findById((long)domandaTirocinio.getId());
    assertEquals(domandaTirocinio.getProgettoFormativo(), 
        domanda.getProgettoFormativo());
    assertEquals(domandaTirocinio, domanda);
  }
}
