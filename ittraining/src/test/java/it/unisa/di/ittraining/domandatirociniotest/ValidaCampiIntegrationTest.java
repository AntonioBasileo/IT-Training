package it.unisa.di.ittraining.domandatirociniotest;

import it.unisa.di.ittraining.azienda.Azienda;
import it.unisa.di.ittraining.azienda.AziendaEsistenteException;
import it.unisa.di.ittraining.azienda.AziendaNonEsistenteException;
import it.unisa.di.ittraining.azienda.AziendaNonValidaException;
import it.unisa.di.ittraining.azienda.AziendaService;
import it.unisa.di.ittraining.azienda.EmailAziendaEsistenteException;
import it.unisa.di.ittraining.azienda.IndirizzoNonValidoException;
import it.unisa.di.ittraining.azienda.SedeNonValidaException;
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
import it.unisa.di.ittraining.utente.TelefonoNonValidoException;
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
public class ValidaCampiIntegrationTest {

  @Autowired
  private AziendaService tutorService;
  
  @Autowired
  private StudentiService studenteService;
  
  @Autowired
  private DomandaTirocinioService domandeService;
  
  @Autowired
  private UtenteService utentiService;

  //Valida i campi di compilazione della domanda di tirocinio
  
  @Test(expected = DataNonValidaException.class)
  public void validaDataInizioFineNulla() throws DataNonValidaException,
      DataFinePrecedenteDataInizioException {

    LocalDate inizio = null;
    LocalDate fine = LocalDate.of(2019, Month.MAY, 22);

    domandeService.validaDataFine(inizio, fine);
  }
  
  @Test(expected = DataNonValidaException.class)
  public void validaDataInizioNulla() throws DataNonValidaException,
      DataFinePrecedenteDataInizioException {

    LocalDate inizio = null;

    domandeService.validaDataInizio(inizio);
  }
  
  @Test(expected = DataNonValidaException.class)
  public void validaDataFineNulla() throws DataNonValidaException,
      DataFinePrecedenteDataInizioException {

    LocalDate fine = null;
    LocalDate inizio = LocalDate.of(2019, Month.MAY, 22);

    domandeService.validaDataFine(inizio, fine);
  }
  
  @Test(expected = DataNonValidaException.class)
  public void validaPeriodoTirocinioTroppoLungo() throws DataNonValidaException,
      DataFinePrecedenteDataInizioException {

    LocalDate inizio = LocalDate.of(2019, Month.JANUARY, 30);
    LocalDate fine = LocalDate.of(2020, Month.MAY, 22);

    domandeService.validaDataFine(inizio, fine);
  }
  
  @Test(expected = AziendaNonValidaException.class)
  public void validaNomeAziendaDomandaNullo() throws DataNonValidaException,
      DataFinePrecedenteDataInizioException, AziendaNonValidaException,
      AziendaNonEsistenteException {

    String nomeAzienda = null;

    domandeService.validaNomeAzienda(nomeAzienda);
  }
  
  @Test(expected = AziendaNonValidaException.class)
  public void validaNomeAziendaTroppoLungo() throws DataNonValidaException,
      DataFinePrecedenteDataInizioException, AziendaNonValidaException,
      AziendaNonEsistenteException {

    String nomeAzienda = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

    domandeService.validaNomeAzienda(nomeAzienda);
  }
  
  @Test(expected = AziendaNonValidaException.class)
  public void validaNomeAziendaTroppoCorto() throws DataNonValidaException,
      DataFinePrecedenteDataInizioException, AziendaNonValidaException,
      AziendaNonEsistenteException {

    String nomeAzienda = "a";

    domandeService.validaNomeAzienda(nomeAzienda);
  }
  
  @Test(expected = AziendaNonEsistenteException.class)
  public void validaNomeAziendaNonEsistente() throws DataNonValidaException,
      DataFinePrecedenteDataInizioException, AziendaNonValidaException,
      AziendaNonEsistenteException {

    String nomeAzienda = "abcdefghilmnopqrstuvz";

    domandeService.validaNomeAzienda(nomeAzienda);
  }
  
  @Test(expected = NumeroCfuNonValidoException.class)
  public void validaNumeroCfuNonValido() throws DataNonValidaException,
      DataFinePrecedenteDataInizioException, AziendaNonValidaException,
      AziendaNonEsistenteException, MassimoNumeroCfuCumulabiliException,
      NumeroCfuNonValidoException {

    int cfu = 0;

    domandeService.validaNumeroCfu(cfu);
  }
  
  @Test(expected = MassimoNumeroCfuCumulabiliException.class)
  public void validaNumeroCfuRaggiunti() throws DataNonValidaException,
      DataFinePrecedenteDataInizioException, AziendaNonValidaException,
      AziendaNonEsistenteException, MassimoNumeroCfuCumulabiliException,
      NumeroCfuNonValidoException, NomeNonValidoException, NomeCognomeTroppoLungoException,
      NomeCognomeTroppoCortoException, CognomeNonValidoException, DataDiNascitaNonValidaException,
      UsernameNonValidoException, UsernameEsistenteException, EmailNonValidaException,
      EmailEsistenteException, SessoNonValidoException, TelefonoNonValidoException,
      MatricolaStudenteNonValidaException, MatricolaStudenteEsistenteException,
      PasswordNonValidaException, PasswordNonCorrispondentiException,
      UsernameNonEsistenteException, PasswordErrataException, AziendaEsistenteException,
      SedeNonValidaException, IndirizzoNonValidoException, EmailAziendaEsistenteException,
      it.unisa.di.ittraining.azienda.TelefonoNonValidoException {
  
    Azienda azienda = new Azienda();
    azienda.setNome("Informatics Next Gen");
    azienda.setSede("Napoli");
    azienda.setIndirizzo("Corso Amedeo di Savoia 214");
    azienda.setEmail("next-gen@gmail.com");
    azienda.setTelefono("3498785630");
  
    tutorService.registraAzienda(azienda);

    Studente studente = new Studente();
    studente.setNome("paolo");
    studente.setCognome("crivellin");
    studente.setDataDiNascita(LocalDate.of(1994, Month.SEPTEMBER, 3));
    studente.setMatricola("0512107000");
    studente.setEmail("p.crivellin@studenti.unisa.it");
    studente.setTelefono("3396520147");
    studente.setPassword("paolocrivellin");
    studente.setUsername("paolocrive94");
    studente.setSesso("M");
    
    studenteService.registraStudente(studente);
    
    utentiService.login(studente.getUsername(), studente.getPassword());
    
    DomandaTirocinio domandaTirocinio = new DomandaTirocinio();
    domandaTirocinio.setCfu(6);
    domandaTirocinio.setStatus(DomandaTirocinio.IN_ATTESA);
    domandaTirocinio.setData(LocalDate.now());
    domandaTirocinio.setInizioTirocinio(LocalDate.of(2019, Month.FEBRUARY, 12));
    domandaTirocinio.setFineTirocinio(LocalDate.of(2019, Month.MARCH, 20));
    
    DomandaTirocinio domandaTirocinio1 = new DomandaTirocinio();
    domandaTirocinio1.setCfu(12);
    domandaTirocinio1.setStatus(DomandaTirocinio.IN_ATTESA);
    domandaTirocinio1.setData(LocalDate.now());
    domandaTirocinio1.setInizioTirocinio(LocalDate.of(2019, Month.MARCH, 21));
    domandaTirocinio1.setFineTirocinio(LocalDate.of(2019, Month.APRIL, 24));
    
    domandeService.registraDomanda(domandaTirocinio, azienda.getNome());
    domandeService.registraDomanda(domandaTirocinio1, azienda.getNome());
    
    domandeService.validaNumeroCfu(6);
    
    utentiService.logout();
  }
  
  @Test
  public void validaRegistraDomandaDiciottoCfu() throws AziendaNonValidaException,
      AziendaEsistenteException,
      SedeNonValidaException, IndirizzoNonValidoException, EmailNonValidaException,
      EmailAziendaEsistenteException,
      it.unisa.di.ittraining.azienda.TelefonoNonValidoException, NomeNonValidoException,
      NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException,
      CognomeNonValidoException, DataDiNascitaNonValidaException,
      UsernameNonValidoException, UsernameEsistenteException,
      EmailEsistenteException, SessoNonValidoException,
      TelefonoNonValidoException, MatricolaStudenteNonValidaException,
      MatricolaStudenteEsistenteException, PasswordNonValidaException,
      PasswordNonCorrispondentiException, UsernameNonEsistenteException,
      PasswordErrataException, AziendaNonEsistenteException, DataNonValidaException,
      DataFinePrecedenteDataInizioException, MassimoNumeroCfuCumulabiliException,
      NumeroCfuNonValidoException {
  
    Azienda azienda = new Azienda();
    azienda.setNome("Informatics Next Gen Series");
    azienda.setSede("Napoli");
    azienda.setIndirizzo("Corso Amedeo di Savoia 214");
    azienda.setEmail("next-gen-series@gmail.com");
    azienda.setTelefono("3498785630");
  
    tutorService.registraAzienda(azienda);

    Studente studente = new Studente();
    studente.setNome("angela");
    studente.setCognome("caloisi");
    studente.setDataDiNascita(LocalDate.of(1994, Month.SEPTEMBER, 3));
    studente.setMatricola("0512109000");
    studente.setEmail("a.caloisi@studenti.unisa.it");
    studente.setTelefono("3396520147");
    studente.setPassword("paolocrivellin");
    studente.setUsername("angelacalo94");
    studente.setSesso("F");
    
    studenteService.registraStudente(studente);
    
    utentiService.login(studente.getUsername(), studente.getPassword());
    
    DomandaTirocinio domandaTirocinio = new DomandaTirocinio();
    domandaTirocinio.setCfu(18);
    domandaTirocinio.setStatus(DomandaTirocinio.IN_ATTESA);
    domandaTirocinio.setData(LocalDate.now());
    domandaTirocinio.setInizioTirocinio(LocalDate.of(2019, Month.FEBRUARY, 12));
    domandaTirocinio.setFineTirocinio(LocalDate.of(2019, Month.MARCH, 20));
    
    domandeService.registraDomanda(domandaTirocinio, azienda.getNome());
    
    utentiService.logout();
  }
}
