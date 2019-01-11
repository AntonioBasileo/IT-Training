package it.unisa.di.ittraining.domandaTirocinio.test;

import static org.junit.Assert.assertEquals;

import it.unisa.di.ittraining.azienda.Azienda;
import it.unisa.di.ittraining.azienda.AziendaEsistenteException;
import it.unisa.di.ittraining.azienda.AziendaNonEsistenteException;
import it.unisa.di.ittraining.azienda.AziendaNonValidaException;
import it.unisa.di.ittraining.azienda.AziendaService;
import it.unisa.di.ittraining.azienda.EmailAziendaEsistenteException;
import it.unisa.di.ittraining.azienda.EmailNonAssociataException;
import it.unisa.di.ittraining.azienda.IndirizzoNonValidoException;
import it.unisa.di.ittraining.azienda.SedeNonValidaException;
import it.unisa.di.ittraining.azienda.TelefonoNonValidoException;
import it.unisa.di.ittraining.azienda.TutorAziendale;
import it.unisa.di.ittraining.domandatirocinio.DataFinePrecedenteDataInizioException;
import it.unisa.di.ittraining.domandatirocinio.DataNonValidaException;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinioService;
import it.unisa.di.ittraining.domandatirocinio.MassimoNumeroCfuCumulabiliException;
import it.unisa.di.ittraining.domandatirocinio.NumeroCfuNonValidoException;
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
public class DomandaTirocinioServiceTest {
  
  @Autowired
  private AziendaService aziendaService;
  
  @Autowired
  private DomandaTirocinioService domandaTirocinioService;

  @Autowired 
  private UtenteService utenteService;
  
  @Autowired
  private StudentiService studenteService;
  
  @Test
  public void registraDomanda() {
   
    Azienda azienda = new Azienda();
    azienda.setNome("Grafica SRL");
    azienda.setTelefono("3333333333");
    azienda.setSede("Avellino");
    azienda.setIndirizzo("Via Roma 45");
    azienda.setEmail("grafica@gmail.com");
    try {
      aziendaService.registraAzienda(azienda);
    } catch (AziendaNonValidaException | AziendaEsistenteException | SedeNonValidaException
        | IndirizzoNonValidoException | EmailNonValidaException | EmailAziendaEsistenteException
        | TelefonoNonValidoException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    
    TutorAziendale tutorAziendale = new TutorAziendale();
    tutorAziendale.setUsername("giancarlodasantommaso");
    tutorAziendale.setNome("Lina");
    tutorAziendale.setCognome("Neri");
    tutorAziendale.setDataDiNascita(LocalDate.of(1970, Month.DECEMBER, 30));
    tutorAziendale.setTelefono("0987654321");
    tutorAziendale.setEmail(azienda.getEmail());
    tutorAziendale.setPassword("lina123");
    tutorAziendale.setSesso("F");
    
    try {
      aziendaService.registraTutorAziendale(tutorAziendale, azienda.getNome());
    } catch (NomeNonValidoException | NomeCognomeTroppoLungoException 
        | NomeCognomeTroppoCortoException | CognomeNonValidoException 
        | EmailNonValidaException | EmailEsistenteException | EmailNonAssociataException
        | UsernameNonValidoException | UsernameEsistenteException | PasswordNonValidaException
        | PasswordNonCorrispondentiException | DataDiNascitaNonValidaException 
        | AziendaNonValidaException | AziendaEsistenteException | SessoNonValidoException 
        | TelefonoNonValidoException | AziendaNonEsistenteException e2) {
      // TODO Auto-generated catch block
      e2.printStackTrace();
    }
    
    Studente studente = new Studente();
    studente.setNome("Laura");
    studente.setCognome("Oliva");
    studente.setDataDiNascita(LocalDate.of(1997, Month.JUNE, 29));
    studente.setMatricola("0512100000");
    studente.setSesso("F");
    studente.setEmail("laura@studenti.unisa.it");
    studente.setPassword("ab12cd34ef");
    studente.setUsername("laura1997");
    studente.setTelefono("3404050333");
    try {
      studenteService.registraStudente(studente);
    } catch (NomeNonValidoException | NomeCognomeTroppoLungoException 
      | NomeCognomeTroppoCortoException
      | CognomeNonValidoException | DataDiNascitaNonValidaException | UsernameNonValidoException
      | UsernameEsistenteException | EmailNonValidaException | EmailEsistenteException 
      | SessoNonValidoException
      | it.unisa.di.ittraining.utente.TelefonoNonValidoException 
      | MatricolaStudenteNonValidaException
      | MatricolaStudenteEsistenteException | PasswordNonValidaException
      | PasswordNonCorrispondentiException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    
    try {
      utenteService.login(studente.getUsername(), studente.getPassword());
    } catch (UsernameNonEsistenteException | PasswordErrataException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    
    DomandaTirocinio domandaTirocinio = new DomandaTirocinio();
    domandaTirocinio.setId(111L);
    domandaTirocinio.setCfu(6);
    domandaTirocinio.setData(LocalDate.now());
    domandaTirocinio.setInizioTirocinio(LocalDate.of(2019, Month.FEBRUARY, 12));
    domandaTirocinio.setFineTirocinio(LocalDate.of(2019, Month.MARCH, 20));
    domandaTirocinio.setAzienda(azienda);
    domandaTirocinio.setStudente(studente);
    
    DomandaTirocinio domandaSalvata = new DomandaTirocinio();
    
    try {
      domandaSalvata = domandaTirocinioService.registraDomanda(domandaTirocinio, azienda.getNome());
      System.out.println("SONO QUI!");
    } catch (AziendaNonValidaException | AziendaNonEsistenteException 
        | DataDiNascitaNonValidaException | DataNonValidaException 
        | DataFinePrecedenteDataInizioException | MassimoNumeroCfuCumulabiliException
        | NumeroCfuNonValidoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    assertEquals(domandaTirocinio, domandaSalvata);
  }

}
