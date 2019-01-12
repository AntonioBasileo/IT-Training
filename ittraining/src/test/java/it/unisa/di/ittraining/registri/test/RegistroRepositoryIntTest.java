package it.unisa.di.ittraining.registri.test;

import static org.junit.Assert.assertTrue;

import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinioRepository;
import it.unisa.di.ittraining.registrotirocinio.Registro;
import it.unisa.di.ittraining.registrotirocinio.RegistroRepository;

import java.time.LocalDate;
import java.time.LocalTime;
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
public class RegistroRepositoryIntTest {

  @Autowired
  private RegistroRepository registroRep;

  @Autowired
  private DomandaTirocinioRepository domandaRep;

  private DomandaTirocinio domandaTirocinio;

  private Registro registro1;
  private Registro registro2;
  private Registro registro3;

  @Before
  public void salvaRegistro() {

    domandaTirocinio = new DomandaTirocinio();
    domandaTirocinio.setId(222L);
    domandaTirocinio.setCfu(6);
    domandaTirocinio.setInizioTirocinio(LocalDate.of(2019, Month.FEBRUARY, 12));
    domandaTirocinio.setFineTirocinio(LocalDate.of(2019, Month.MARCH, 20));

    registro1 = new Registro();
    registro1.setId(111L);
    registro1.setData(LocalDate.of(2019, Month.MARCH, 2));
    registro1.setInizio(LocalTime.of(9, 30));
    registro1.setFine(LocalTime.of(16, 30));

    registro2 = new Registro();
    registro2.setId(112L);
    registro2.setData(LocalDate.of(2019, Month.MARCH, 1));
    registro2.setInizio(LocalTime.of(9, 30));
    registro2.setFine(LocalTime.of(16, 30));

    registro3 = new Registro();
    registro3.setId(113L);
    registro3.setData(LocalDate.of(2019, Month.MARCH, 5));
    registro3.setInizio(LocalTime.of(9, 30));
    registro3.setFine(LocalTime.of(16, 30));

    domandaTirocinio.addRegistro(registro1);
    domandaTirocinio.addRegistro(registro2);
    domandaTirocinio.addRegistro(registro3);

    domandaTirocinio = domandaRep.save(domandaTirocinio);
    registro1 = registroRep.save(registro1);
    registro2 = registroRep.save(registro2);
    registro3 = registroRep.save(registro3);

    domandaRep.flush();
    registroRep.flush();
  }

  @Test
  public void verificaRegistro() {
    DomandaTirocinio domanda = domandaRep.findById((long) domandaTirocinio.getId());
    assertTrue(domanda.getRegistri().contains(registro1));
    assertTrue(domanda.getRegistri().contains(registro2));
    assertTrue(domanda.getRegistri().contains(registro3));
  }
}
