package it.unisa.di.ittraining.registritest;

import static org.junit.Assert.assertEquals;

import it.unisa.di.ittraining.azienda.Azienda;
import it.unisa.di.ittraining.azienda.AziendaEsistenteException;
import it.unisa.di.ittraining.azienda.AziendaNonEsistenteException;
import it.unisa.di.ittraining.azienda.AziendaNonValidaException;
import it.unisa.di.ittraining.azienda.AziendaService;
import it.unisa.di.ittraining.azienda.EmailAziendaEsistenteException;
import it.unisa.di.ittraining.azienda.IndirizzoNonValidoException;
import it.unisa.di.ittraining.azienda.SedeNonValidaException;
import it.unisa.di.ittraining.azienda.TelefonoNonValidoException;
import it.unisa.di.ittraining.domandatirocinio.DataFinePrecedenteDataInizioException;
import it.unisa.di.ittraining.domandatirocinio.DataNonValidaException;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinioService;
import it.unisa.di.ittraining.domandatirocinio.MassimoNumeroCfuCumulabiliException;
import it.unisa.di.ittraining.domandatirocinio.NumeroCfuNonValidoException;
import it.unisa.di.ittraining.registrotirocinio.DataRegistroNonValidaException;
import it.unisa.di.ittraining.registrotirocinio.DataRegistroPrecedenteInizioException;
import it.unisa.di.ittraining.registrotirocinio.DataRegistroSuccessivaFineException;
import it.unisa.di.ittraining.registrotirocinio.MassimoNumeroOreException;
import it.unisa.di.ittraining.registrotirocinio.OrarioFinePrecedenteInizioException;
import it.unisa.di.ittraining.registrotirocinio.OrarioNonValidoException;
import it.unisa.di.ittraining.registrotirocinio.OrePrevisteSuperateException;
import it.unisa.di.ittraining.registrotirocinio.Registro;
import it.unisa.di.ittraining.registrotirocinio.RegistroRepository;
import it.unisa.di.ittraining.registrotirocinio.RegistroService;
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
import java.time.LocalTime;
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
  private RegistroService registriService;

  @Autowired
  private DomandaTirocinioService domandeService;
  
  @Autowired
  private AziendaService aziendeService;
  
  @Autowired
  private StudentiService studenteService;
  
  @Autowired
  private UtenteService utentiService;
  
  @Autowired
  private RegistroRepository registriRep;
  
  @Test(expected = OrarioNonValidoException.class)
  public void validaOrarioInizioNullo() throws OrarioNonValidoException,
      OrarioFinePrecedenteInizioException {

    LocalTime inizio = null;
    LocalTime fine = LocalTime.of(18, 30);
  
    registriService.validaOrarioInizio(inizio, fine);
  }
  
  @Test(expected = OrarioNonValidoException.class)
  public void validaOrarioFineNullo() throws OrarioNonValidoException,
      OrarioFinePrecedenteInizioException {

    LocalTime inizio = LocalTime.of(9, 30);
    LocalTime fine = null;
  
    registriService.validaOrarioFine(inizio, fine);
  }
  
  @Test(expected = OrarioFinePrecedenteInizioException.class)
  public void validaOrarioFinePrecedenteInizio() throws OrarioNonValidoException,
      OrarioFinePrecedenteInizioException {

    LocalTime fine = LocalTime.of(15, 30);
    LocalTime inizio = LocalTime.of(18, 30);
  
    registriService.validaOrarioInizio(inizio, fine);
  }
  
  @Test(expected = OrarioFinePrecedenteInizioException.class)
  public void validaOrarioFinePrecedenteInizio2() throws OrarioNonValidoException,
      OrarioFinePrecedenteInizioException {

    LocalTime fine = LocalTime.of(15, 30);
    LocalTime inizio = LocalTime.of(18, 30);
  
    registriService.validaOrarioFine(inizio, fine);
  }
  
  @Test(expected = MassimoNumeroOreException.class)
  public void validaNumeroOreTirocinioEccessivo() throws OrarioNonValidoException,
      OrarioFinePrecedenteInizioException, MassimoNumeroOreException {

    LocalTime inizio = LocalTime.of(8, 30);
    LocalTime fine = LocalTime.of(20, 30);
    
  
    registriService.validaNumeroOreTirocinio(inizio, fine);
  }
  
  @Test(expected = DataRegistroNonValidaException.class)
  public void validaDataRegistroNulla() throws OrarioNonValidoException,
      OrarioFinePrecedenteInizioException, MassimoNumeroOreException,
      DataRegistroSuccessivaFineException, DataRegistroPrecedenteInizioException,
      DataRegistroNonValidaException {

    LocalDate data = null;
    LocalDate inizio = LocalDate.of(2019, Month.MARCH, 15);
    LocalDate fine = LocalDate.of(2019, Month.MAY, 30);
    
  
    registriService.validaDataRegistro(data, inizio, fine);
  }
  
  @Test(expected = DataRegistroNonValidaException.class)
  public void validaDataRegistroInizioNulla() throws OrarioNonValidoException,
      OrarioFinePrecedenteInizioException, MassimoNumeroOreException,
      DataRegistroSuccessivaFineException, DataRegistroPrecedenteInizioException,
      DataRegistroNonValidaException {

    LocalDate data = LocalDate.now();
    LocalDate inizio = null;
    LocalDate fine = LocalDate.of(2019, Month.MAY, 30);
    
  
    registriService.validaDataRegistro(data, inizio, fine);
  }
  
  @Test(expected = DataRegistroNonValidaException.class)
  public void validaDataRegistroFineNulla() throws OrarioNonValidoException,
      OrarioFinePrecedenteInizioException, MassimoNumeroOreException,
      DataRegistroSuccessivaFineException, DataRegistroPrecedenteInizioException,
      DataRegistroNonValidaException {

    LocalDate data = LocalDate.now();
    LocalDate inizio = LocalDate.of(2019, Month.MARCH, 15);
    LocalDate fine = null;
    
  
    registriService.validaDataRegistro(data, inizio, fine);
  }
  
  @Test(expected = OrePrevisteSuperateException.class)
  public void validaOreDomandaEccessive6() throws OrarioNonValidoException,
      OrarioFinePrecedenteInizioException, MassimoNumeroOreException,
      DataRegistroSuccessivaFineException, DataRegistroPrecedenteInizioException,
      DataRegistroNonValidaException, AziendaNonValidaException,
      AziendaEsistenteException, SedeNonValidaException,
      IndirizzoNonValidoException, EmailNonValidaException,
      EmailAziendaEsistenteException, TelefonoNonValidoException,
      AziendaNonEsistenteException, DataDiNascitaNonValidaException,
      DataNonValidaException, DataFinePrecedenteDataInizioException,
      MassimoNumeroCfuCumulabiliException, NumeroCfuNonValidoException,
      OrePrevisteSuperateException, NomeNonValidoException,
      NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException,
      CognomeNonValidoException, UsernameNonValidoException, UsernameEsistenteException,
      EmailEsistenteException, SessoNonValidoException,
      it.unisa.di.ittraining.utente.TelefonoNonValidoException,
      MatricolaStudenteNonValidaException, MatricolaStudenteEsistenteException,
      PasswordNonValidaException, PasswordNonCorrispondentiException,
      UsernameNonEsistenteException, PasswordErrataException {
  
    Studente studente = new Studente();
    studente.setNome("Laura");
    studente.setCognome("Oliva");
    studente.setDataDiNascita(LocalDate.of(1997, Month.JUNE, 29));
    studente.setMatricola("0512101111");
    studente.setSesso("F");
    studente.setEmail("laura.oliva29@studenti.unisa.it");
    studente.setPassword("ab12cd34ef");
    studente.setUsername("laura2961997");
    studente.setTelefono("3404050333");
    
    studenteService.registraStudente(studente);
  
    Azienda azienda = new Azienda();
    azienda.setNome("EtihadCENTER");
    azienda.setSede("Torino");
    azienda.setIndirizzo("Via della Mole 31");
    azienda.setEmail("etihad.center@gmail.com");
    azienda.setTelefono("0912345612");
    
    aziendeService.registraAzienda(azienda);
    
    utentiService.login(studente.getUsername(), studente.getPassword());

    DomandaTirocinio domandaTirocinio = new DomandaTirocinio();
    domandaTirocinio.setCfu(6);
    domandaTirocinio.setStatus(DomandaTirocinio.IN_ATTESA);
    domandaTirocinio.setData(LocalDate.now());
    domandaTirocinio.setInizioTirocinio(LocalDate.of(2019, Month.FEBRUARY, 12));
    domandaTirocinio.setFineTirocinio(LocalDate.of(2019, Month.MAY, 20));
    
    domandaTirocinio = domandeService.registraDomanda(domandaTirocinio, azienda.getNome());
    
    Registro registro = new Registro();
    registro.setData(LocalDate.of(2019, Month.MARCH, 1));
    registro.setInizio(LocalTime.of(8, 30));
    registro.setFine(LocalTime.of(16, 30));
    
    registro = registriService.registraTirocinio(registro, domandaTirocinio.getId());
    
    assertEquals(registro, registriRep.findById((long)registro.getId()));
    
    Registro registro1 = new Registro();
    registro1.setData(LocalDate.of(2019, Month.MARCH, 2));
    registro1.setInizio(LocalTime.of(8, 30));
    registro1.setFine(LocalTime.of(16, 30));
    
    registro1 = registriService.registraTirocinio(registro1, domandaTirocinio.getId());
    
    assertEquals(registro1, registriRep.findById((long)registro1.getId()));
    
    Registro registro2 = new Registro();
    registro2.setData(LocalDate.of(2019, Month.MARCH, 3));
    registro2.setInizio(LocalTime.of(8, 30));
    registro2.setFine(LocalTime.of(16, 30));
    
    registro2 = registriService.registraTirocinio(registro2, domandaTirocinio.getId());
    
    assertEquals(registro2, registriRep.findById((long)registro2.getId()));
    
    Registro registro3 = new Registro();
    registro3.setData(LocalDate.of(2019, Month.MARCH, 4));
    registro3.setInizio(LocalTime.of(8, 30));
    registro3.setFine(LocalTime.of(16, 30));
    
    registro3 = registriService.registraTirocinio(registro3, domandaTirocinio.getId());
    
    assertEquals(registro3, registriRep.findById((long)registro3.getId()));
    
    Registro registro4 = new Registro();
    registro4.setData(LocalDate.of(2019, Month.MARCH, 5));
    registro4.setInizio(LocalTime.of(8, 30));
    registro4.setFine(LocalTime.of(16, 30));
    
    registro4 = registriService.registraTirocinio(registro4, domandaTirocinio.getId());
    
    assertEquals(registro4, registriRep.findById((long)registro4.getId()));
    
    Registro registro5 = new Registro();
    registro5.setData(LocalDate.of(2019, Month.MARCH, 5));
    registro5.setInizio(LocalTime.of(8, 30));
    registro5.setFine(LocalTime.of(16, 30));
    
    registro5 = registriService.registraTirocinio(registro5, domandaTirocinio.getId());
    
    assertEquals(registro5, registriRep.findById((long)registro5.getId()));
    
    Registro registro6 = new Registro();
    registro6.setData(LocalDate.of(2019, Month.MARCH, 6));
    registro6.setInizio(LocalTime.of(8, 30));
    registro6.setFine(LocalTime.of(16, 30));
    
    registro6 = registriService.registraTirocinio(registro6, domandaTirocinio.getId());
    
    assertEquals(registro6, registriRep.findById((long)registro6.getId()));
    
    Registro registro7 = new Registro();
    registro7.setData(LocalDate.of(2019, Month.MARCH, 7));
    registro7.setInizio(LocalTime.of(8, 30));
    registro7.setFine(LocalTime.of(16, 30));
    
    registro7 = registriService.registraTirocinio(registro7, domandaTirocinio.getId());
    
    assertEquals(registro7, registriRep.findById((long)registro7.getId()));
    
    Registro registro8 = new Registro();
    registro8.setData(LocalDate.of(2019, Month.MARCH, 8));
    registro8.setInizio(LocalTime.of(8, 30));
    registro8.setFine(LocalTime.of(16, 30));
    
    registro8 = registriService.registraTirocinio(registro8, domandaTirocinio.getId());
    
    assertEquals(registro8, registriRep.findById((long)registro8.getId()));
    
    Registro registro9 = new Registro();
    registro9.setData(LocalDate.of(2019, Month.MARCH, 9));
    registro9.setInizio(LocalTime.of(8, 30));
    registro9.setFine(LocalTime.of(16, 30));
    
    registro9 = registriService.registraTirocinio(registro9, domandaTirocinio.getId());
    
    assertEquals(registro9, registriRep.findById((long)registro9.getId()));
    
    Registro registro10 = new Registro();
    registro10.setData(LocalDate.of(2019, Month.MARCH, 10));
    registro10.setInizio(LocalTime.of(8, 30));
    registro10.setFine(LocalTime.of(16, 30));
    
    registro10 = registriService.registraTirocinio(registro10, domandaTirocinio.getId());
    
    assertEquals(registro10, registriRep.findById((long)registro10.getId()));
    
    Registro registro11 = new Registro();
    registro11.setData(LocalDate.of(2019, Month.MARCH, 11));
    registro11.setInizio(LocalTime.of(8, 30));
    registro11.setFine(LocalTime.of(16, 30));
    
    registro11 = registriService.registraTirocinio(registro11, domandaTirocinio.getId());
    
    assertEquals(registro11, registriRep.findById((long)registro11.getId()));
    
    Registro registro12 = new Registro();
    registro12.setData(LocalDate.of(2019, Month.MARCH, 12));
    registro12.setInizio(LocalTime.of(8, 30));
    registro12.setFine(LocalTime.of(16, 30));
    
    registro12 = registriService.registraTirocinio(registro12, domandaTirocinio.getId());
    
    assertEquals(registro12, registriRep.findById((long)registro12.getId()));
    
    Registro registro13 = new Registro();
    registro13.setData(LocalDate.of(2019, Month.MARCH, 13));
    registro13.setInizio(LocalTime.of(8, 30));
    registro13.setFine(LocalTime.of(16, 30));
    
    registro13 = registriService.registraTirocinio(registro13, domandaTirocinio.getId());
    
    assertEquals(registro13, registriRep.findById((long)registro13.getId()));
    
    Registro registro14 = new Registro();
    registro14.setData(LocalDate.of(2019, Month.MARCH, 14));
    registro14.setInizio(LocalTime.of(8, 30));
    registro14.setFine(LocalTime.of(16, 30));
    
    registro14 = registriService.registraTirocinio(registro14, domandaTirocinio.getId());
    
    assertEquals(registro14, registriRep.findById((long)registro14.getId()));
    
    Registro registro15 = new Registro();
    registro15.setData(LocalDate.of(2019, Month.MARCH, 15));
    registro15.setInizio(LocalTime.of(8, 30));
    registro15.setFine(LocalTime.of(16, 30));
    
    registro15 = registriService.registraTirocinio(registro15, domandaTirocinio.getId());
    
    assertEquals(registro15, registriRep.findById((long)registro15.getId()));
    
    Registro registro16 = new Registro();
    registro16.setData(LocalDate.of(2019, Month.MARCH, 16));
    registro16.setInizio(LocalTime.of(8, 30));
    registro16.setFine(LocalTime.of(16, 30));
    
    registro16 = registriService.registraTirocinio(registro16, domandaTirocinio.getId());
    
    assertEquals(registro16, registriRep.findById((long)registro16.getId()));
    
    Registro registro17 = new Registro();
    registro17.setData(LocalDate.of(2019, Month.MARCH, 17));
    registro17.setInizio(LocalTime.of(8, 30));
    registro17.setFine(LocalTime.of(16, 30));
    
    registro17 = registriService.registraTirocinio(registro17, domandaTirocinio.getId());
    
    assertEquals(registro17, registriRep.findById((long)registro17.getId()));
    
    registriRep.flush();
    
    registriService
        .verificaNumeroOreRegistro(LocalTime.of(8, 30),
            LocalTime.of(16, 30), domandaTirocinio.getId());
  }
  
  @Test(expected = OrePrevisteSuperateException.class)
  public void validaOreDomandaEccessive12() throws OrarioNonValidoException,
      OrarioFinePrecedenteInizioException, MassimoNumeroOreException,
      DataRegistroSuccessivaFineException, DataRegistroPrecedenteInizioException,
      DataRegistroNonValidaException, AziendaNonValidaException,
      AziendaEsistenteException, SedeNonValidaException,
      IndirizzoNonValidoException, EmailNonValidaException,
      EmailAziendaEsistenteException, TelefonoNonValidoException,
      AziendaNonEsistenteException, DataDiNascitaNonValidaException,
      DataNonValidaException, DataFinePrecedenteDataInizioException,
      MassimoNumeroCfuCumulabiliException, NumeroCfuNonValidoException,
      OrePrevisteSuperateException, NomeNonValidoException,
      NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException,
      CognomeNonValidoException, UsernameNonValidoException, UsernameEsistenteException,
      EmailEsistenteException, SessoNonValidoException,
      it.unisa.di.ittraining.utente.TelefonoNonValidoException,
      MatricolaStudenteNonValidaException, MatricolaStudenteEsistenteException,
      PasswordNonValidaException, PasswordNonCorrispondentiException,
      UsernameNonEsistenteException, PasswordErrataException {
  
    Studente studente = new Studente();
    studente.setNome("Laura");
    studente.setCognome("Oliva");
    studente.setDataDiNascita(LocalDate.of(1997, Month.JUNE, 29));
    studente.setMatricola("0512101212");
    studente.setSesso("F");
    studente.setEmail("laura.oliva2997@studenti.unisa.it");
    studente.setPassword("ab12cd34ef");
    studente.setUsername("laura2961997bella");
    studente.setTelefono("3404050333");
    
    studenteService.registraStudente(studente);
  
    Azienda azienda = new Azienda();
    azienda.setNome("EtihadinformaticsCENTER");
    azienda.setSede("Torino");
    azienda.setIndirizzo("Via della Mole 31");
    azienda.setEmail("etihad.informatics.center@gmail.com");
    azienda.setTelefono("0912345612");
    
    aziendeService.registraAzienda(azienda);
    
    utentiService.login(studente.getUsername(), studente.getPassword());

    DomandaTirocinio domandaTirocinio = new DomandaTirocinio();
    domandaTirocinio.setCfu(12);
    domandaTirocinio.setStatus(DomandaTirocinio.IN_ATTESA);
    domandaTirocinio.setData(LocalDate.now());
    domandaTirocinio.setInizioTirocinio(LocalDate.of(2019, Month.FEBRUARY, 12));
    domandaTirocinio.setFineTirocinio(LocalDate.of(2019, Month.MAY, 20));
    
    domandaTirocinio = domandeService.registraDomanda(domandaTirocinio, azienda.getNome());
    
    Registro registro = new Registro();
    registro.setData(LocalDate.of(2019, Month.MARCH, 1));
    registro.setInizio(LocalTime.of(8, 30));
    registro.setFine(LocalTime.of(16, 30));
    
    registro = registriService.registraTirocinio(registro, domandaTirocinio.getId());
    
    assertEquals(registro, registriRep.findById((long)registro.getId()));
    
    Registro registro1 = new Registro();
    registro1.setData(LocalDate.of(2019, Month.MARCH, 2));
    registro1.setInizio(LocalTime.of(8, 30));
    registro1.setFine(LocalTime.of(16, 30));
    
    registro1 = registriService.registraTirocinio(registro1, domandaTirocinio.getId());
    
    assertEquals(registro1, registriRep.findById((long)registro1.getId()));
    
    Registro registro2 = new Registro();
    registro2.setData(LocalDate.of(2019, Month.MARCH, 3));
    registro2.setInizio(LocalTime.of(8, 30));
    registro2.setFine(LocalTime.of(16, 30));
    
    registro2 = registriService.registraTirocinio(registro2, domandaTirocinio.getId());
    
    assertEquals(registro2, registriRep.findById((long)registro2.getId()));
    
    Registro registro3 = new Registro();
    registro3.setData(LocalDate.of(2019, Month.MARCH, 4));
    registro3.setInizio(LocalTime.of(8, 30));
    registro3.setFine(LocalTime.of(16, 30));
    
    registro3 = registriService.registraTirocinio(registro3, domandaTirocinio.getId());
    
    assertEquals(registro3, registriRep.findById((long)registro3.getId()));
    
    Registro registro4 = new Registro();
    registro4.setData(LocalDate.of(2019, Month.MARCH, 5));
    registro4.setInizio(LocalTime.of(8, 30));
    registro4.setFine(LocalTime.of(16, 30));
    
    registro4 = registriService.registraTirocinio(registro4, domandaTirocinio.getId());
    
    assertEquals(registro4, registriRep.findById((long)registro4.getId()));
    
    Registro registro5 = new Registro();
    registro5.setData(LocalDate.of(2019, Month.MARCH, 5));
    registro5.setInizio(LocalTime.of(8, 30));
    registro5.setFine(LocalTime.of(16, 30));
    
    registro5 = registriService.registraTirocinio(registro5, domandaTirocinio.getId());
    
    assertEquals(registro5, registriRep.findById((long)registro5.getId()));
    
    Registro registro6 = new Registro();
    registro6.setData(LocalDate.of(2019, Month.MARCH, 6));
    registro6.setInizio(LocalTime.of(8, 30));
    registro6.setFine(LocalTime.of(16, 30));
    
    registro6 = registriService.registraTirocinio(registro6, domandaTirocinio.getId());
    
    assertEquals(registro6, registriRep.findById((long)registro6.getId()));
    
    Registro registro7 = new Registro();
    registro7.setData(LocalDate.of(2019, Month.MARCH, 7));
    registro7.setInizio(LocalTime.of(8, 30));
    registro7.setFine(LocalTime.of(16, 30));
    
    registro7 = registriService.registraTirocinio(registro7, domandaTirocinio.getId());
    
    assertEquals(registro7, registriRep.findById((long)registro7.getId()));
    
    Registro registro8 = new Registro();
    registro8.setData(LocalDate.of(2019, Month.MARCH, 8));
    registro8.setInizio(LocalTime.of(8, 30));
    registro8.setFine(LocalTime.of(16, 30));
    
    registro8 = registriService.registraTirocinio(registro8, domandaTirocinio.getId());
    
    assertEquals(registro8, registriRep.findById((long)registro8.getId()));
    
    Registro registro9 = new Registro();
    registro9.setData(LocalDate.of(2019, Month.MARCH, 9));
    registro9.setInizio(LocalTime.of(8, 30));
    registro9.setFine(LocalTime.of(16, 30));
    
    registro9 = registriService.registraTirocinio(registro9, domandaTirocinio.getId());
    
    assertEquals(registro9, registriRep.findById((long)registro9.getId()));
    
    Registro registro10 = new Registro();
    registro10.setData(LocalDate.of(2019, Month.MARCH, 10));
    registro10.setInizio(LocalTime.of(8, 30));
    registro10.setFine(LocalTime.of(16, 30));
    
    registro10 = registriService.registraTirocinio(registro10, domandaTirocinio.getId());
    
    assertEquals(registro10, registriRep.findById((long)registro10.getId()));
    
    Registro registro11 = new Registro();
    registro11.setData(LocalDate.of(2019, Month.MARCH, 11));
    registro11.setInizio(LocalTime.of(8, 30));
    registro11.setFine(LocalTime.of(16, 30));
    
    registro11 = registriService.registraTirocinio(registro11, domandaTirocinio.getId());
    
    assertEquals(registro11, registriRep.findById((long)registro11.getId()));
    
    Registro registro12 = new Registro();
    registro12.setData(LocalDate.of(2019, Month.MARCH, 12));
    registro12.setInizio(LocalTime.of(8, 30));
    registro12.setFine(LocalTime.of(16, 30));
    
    registro12 = registriService.registraTirocinio(registro12, domandaTirocinio.getId());
    
    assertEquals(registro12, registriRep.findById((long)registro12.getId()));
    
    Registro registro13 = new Registro();
    registro13.setData(LocalDate.of(2019, Month.MARCH, 13));
    registro13.setInizio(LocalTime.of(8, 30));
    registro13.setFine(LocalTime.of(16, 30));
    
    registro13 = registriService.registraTirocinio(registro13, domandaTirocinio.getId());
    
    assertEquals(registro13, registriRep.findById((long)registro13.getId()));
    
    Registro registro14 = new Registro();
    registro14.setData(LocalDate.of(2019, Month.MARCH, 14));
    registro14.setInizio(LocalTime.of(8, 30));
    registro14.setFine(LocalTime.of(16, 30));
    
    registro14 = registriService.registraTirocinio(registro14, domandaTirocinio.getId());
    
    assertEquals(registro14, registriRep.findById((long)registro14.getId()));
    
    Registro registro15 = new Registro();
    registro15.setData(LocalDate.of(2019, Month.MARCH, 15));
    registro15.setInizio(LocalTime.of(8, 30));
    registro15.setFine(LocalTime.of(16, 30));
    
    registro15 = registriService.registraTirocinio(registro15, domandaTirocinio.getId());
    
    assertEquals(registro15, registriRep.findById((long)registro15.getId()));
    
    Registro registro16 = new Registro();
    registro16.setData(LocalDate.of(2019, Month.MARCH, 16));
    registro16.setInizio(LocalTime.of(8, 30));
    registro16.setFine(LocalTime.of(16, 30));
    
    registro16 = registriService.registraTirocinio(registro16, domandaTirocinio.getId());
    
    assertEquals(registro16, registriRep.findById((long)registro16.getId()));
    
    Registro registro17 = new Registro();
    registro17.setData(LocalDate.of(2019, Month.MARCH, 17));
    registro17.setInizio(LocalTime.of(8, 30));
    registro17.setFine(LocalTime.of(16, 30));
    
    registro17 = registriService.registraTirocinio(registro17, domandaTirocinio.getId());
    
    assertEquals(registro17, registriRep.findById((long)registro17.getId()));
    
    Registro registro18 = new Registro();
    registro18.setData(LocalDate.of(2019, Month.MARCH, 18));
    registro18.setInizio(LocalTime.of(8, 30));
    registro18.setFine(LocalTime.of(16, 30));
    
    registro18 = registriService.registraTirocinio(registro18, domandaTirocinio.getId());
    
    assertEquals(registro18, registriRep.findById((long)registro18.getId()));
    
    Registro registro19 = new Registro();
    registro19.setData(LocalDate.of(2019, Month.MARCH, 19));
    registro19.setInizio(LocalTime.of(8, 30));
    registro19.setFine(LocalTime.of(16, 30));
    
    registro19 = registriService.registraTirocinio(registro19, domandaTirocinio.getId());
    
    assertEquals(registro19, registriRep.findById((long)registro19.getId()));
    
    Registro registro20 = new Registro();
    registro20.setData(LocalDate.of(2019, Month.MARCH, 20));
    registro20.setInizio(LocalTime.of(8, 30));
    registro20.setFine(LocalTime.of(16, 30));
    
    registro20 = registriService.registraTirocinio(registro20, domandaTirocinio.getId());
    
    assertEquals(registro20, registriRep.findById((long)registro20.getId()));
    
    Registro registro21 = new Registro();
    registro21.setData(LocalDate.of(2019, Month.MARCH, 21));
    registro21.setInizio(LocalTime.of(8, 30));
    registro21.setFine(LocalTime.of(16, 30));
    
    registro21 = registriService.registraTirocinio(registro21, domandaTirocinio.getId());
    
    assertEquals(registro21, registriRep.findById((long)registro21.getId()));
    
    Registro registro22 = new Registro();
    registro22.setData(LocalDate.of(2019, Month.MARCH, 22));
    registro22.setInizio(LocalTime.of(8, 30));
    registro22.setFine(LocalTime.of(16, 30));
    
    registro22 = registriService.registraTirocinio(registro22, domandaTirocinio.getId());
    
    assertEquals(registro22, registriRep.findById((long)registro22.getId()));
    
    Registro registro23 = new Registro();
    registro23.setData(LocalDate.of(2019, Month.MARCH, 23));
    registro23.setInizio(LocalTime.of(8, 30));
    registro23.setFine(LocalTime.of(16, 30));
    
    registro23 = registriService.registraTirocinio(registro23, domandaTirocinio.getId());
    
    assertEquals(registro23, registriRep.findById((long)registro23.getId()));
    
    Registro registro24 = new Registro();
    registro24.setData(LocalDate.of(2019, Month.MARCH, 24));
    registro24.setInizio(LocalTime.of(8, 30));
    registro24.setFine(LocalTime.of(16, 30));
    
    registro24 = registriService.registraTirocinio(registro24, domandaTirocinio.getId());
    
    assertEquals(registro24, registriRep.findById((long)registro24.getId()));
    
    Registro registro25 = new Registro();
    registro25.setData(LocalDate.of(2019, Month.MARCH, 25));
    registro25.setInizio(LocalTime.of(8, 30));
    registro25.setFine(LocalTime.of(16, 30));
    
    registro25 = registriService.registraTirocinio(registro25, domandaTirocinio.getId());
    
    assertEquals(registro25, registriRep.findById((long)registro25.getId()));
    
    Registro registro26 = new Registro();
    registro26.setData(LocalDate.of(2019, Month.MARCH, 26));
    registro26.setInizio(LocalTime.of(8, 30));
    registro26.setFine(LocalTime.of(16, 30));
    
    registro26 = registriService.registraTirocinio(registro26, domandaTirocinio.getId());
    
    assertEquals(registro26, registriRep.findById((long)registro26.getId()));
    
    Registro registro27 = new Registro();
    registro27.setData(LocalDate.of(2019, Month.MARCH, 27));
    registro27.setInizio(LocalTime.of(8, 30));
    registro27.setFine(LocalTime.of(16, 30));
    
    registro27 = registriService.registraTirocinio(registro27, domandaTirocinio.getId());
    
    assertEquals(registro27, registriRep.findById((long)registro27.getId()));
    
    Registro registro28 = new Registro();
    registro28.setData(LocalDate.of(2019, Month.MARCH, 28));
    registro28.setInizio(LocalTime.of(8, 30));
    registro28.setFine(LocalTime.of(16, 30));
    
    registro28 = registriService.registraTirocinio(registro28, domandaTirocinio.getId());
    
    assertEquals(registro28, registriRep.findById((long)registro28.getId()));
    
    Registro registro29 = new Registro();
    registro29.setData(LocalDate.of(2019, Month.MARCH, 29));
    registro29.setInizio(LocalTime.of(8, 30));
    registro29.setFine(LocalTime.of(16, 30));
    
    registro29 = registriService.registraTirocinio(registro29, domandaTirocinio.getId());
    
    assertEquals(registro29, registriRep.findById((long)registro29.getId()));
    
    Registro registro30 = new Registro();
    registro30.setData(LocalDate.of(2019, Month.MARCH, 30));
    registro30.setInizio(LocalTime.of(8, 30));
    registro30.setFine(LocalTime.of(16, 30));
    
    registro30 = registriService.registraTirocinio(registro30, domandaTirocinio.getId());
    
    assertEquals(registro30, registriRep.findById((long)registro30.getId()));
    
    Registro registro31 = new Registro();
    registro31.setData(LocalDate.of(2019, Month.MARCH, 31));
    registro31.setInizio(LocalTime.of(8, 30));
    registro31.setFine(LocalTime.of(16, 30));
    
    registro31 = registriService.registraTirocinio(registro31, domandaTirocinio.getId());
    
    assertEquals(registro31, registriRep.findById((long)registro31.getId()));
    
    Registro registro32 = new Registro();
    registro32.setData(LocalDate.of(2019, Month.APRIL, 1));
    registro32.setInizio(LocalTime.of(8, 30));
    registro32.setFine(LocalTime.of(16, 30));
    
    registro32 = registriService.registraTirocinio(registro32, domandaTirocinio.getId());
    
    assertEquals(registro32, registriRep.findById((long)registro32.getId()));
    
    Registro registro33 = new Registro();
    registro33.setData(LocalDate.of(2019, Month.APRIL, 2));
    registro33.setInizio(LocalTime.of(8, 30));
    registro33.setFine(LocalTime.of(16, 30));
    
    registro33 = registriService.registraTirocinio(registro33, domandaTirocinio.getId());
    
    assertEquals(registro33, registriRep.findById((long)registro33.getId()));
    
    Registro registro34 = new Registro();
    registro34.setData(LocalDate.of(2019, Month.APRIL, 3));
    registro34.setInizio(LocalTime.of(8, 30));
    registro34.setFine(LocalTime.of(16, 30));
    
    registro34 = registriService.registraTirocinio(registro34, domandaTirocinio.getId());
    
    assertEquals(registro34, registriRep.findById((long)registro34.getId()));
    
    Registro registro35 = new Registro();
    registro35.setData(LocalDate.of(2019, Month.APRIL, 4));
    registro35.setInizio(LocalTime.of(8, 30));
    registro35.setFine(LocalTime.of(16, 30));
    
    registro35 = registriService.registraTirocinio(registro35, domandaTirocinio.getId());
    
    assertEquals(registro35, registriRep.findById((long)registro35.getId()));
    
    Registro registro36 = new Registro();
    registro36.setData(LocalDate.of(2019, Month.APRIL, 5));
    registro36.setInizio(LocalTime.of(8, 30));
    registro36.setFine(LocalTime.of(16, 30));
    
    registro36 = registriService.registraTirocinio(registro36, domandaTirocinio.getId());
    
    assertEquals(registro36, registriRep.findById((long)registro36.getId()));
    
    registriRep.flush();
    
    registriService
        .verificaNumeroOreRegistro(LocalTime.of(8, 30),
            LocalTime.of(16, 30), domandaTirocinio.getId());
    
  }
  
  @Test(expected = OrePrevisteSuperateException.class)
  public void validaOreDomandaEccessive18() throws OrarioNonValidoException,
      OrarioFinePrecedenteInizioException, MassimoNumeroOreException,
      DataRegistroSuccessivaFineException, DataRegistroPrecedenteInizioException,
      DataRegistroNonValidaException, AziendaNonValidaException,
      AziendaEsistenteException, SedeNonValidaException,
      IndirizzoNonValidoException, EmailNonValidaException,
      EmailAziendaEsistenteException, TelefonoNonValidoException,
      AziendaNonEsistenteException, DataDiNascitaNonValidaException,
      DataNonValidaException, DataFinePrecedenteDataInizioException,
      MassimoNumeroCfuCumulabiliException, NumeroCfuNonValidoException,
      OrePrevisteSuperateException, NomeNonValidoException,
      NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException,
      CognomeNonValidoException, UsernameNonValidoException, UsernameEsistenteException,
      EmailEsistenteException, SessoNonValidoException,
      it.unisa.di.ittraining.utente.TelefonoNonValidoException,
      MatricolaStudenteNonValidaException, MatricolaStudenteEsistenteException,
      PasswordNonValidaException, PasswordNonCorrispondentiException,
      UsernameNonEsistenteException, PasswordErrataException {
  
    Studente studente = new Studente();
    studente.setNome("Laura");
    studente.setCognome("Oliva");
    studente.setDataDiNascita(LocalDate.of(1997, Month.JUNE, 29));
    studente.setMatricola("0512103131");
    studente.setSesso("F");
    studente.setEmail("laura.oliva29973131@studenti.unisa.it");
    studente.setPassword("ab12cd34ef");
    studente.setUsername("laura2961997bella3131");
    studente.setTelefono("3404050333");
    
    studenteService.registraStudente(studente);
  
    Azienda azienda = new Azienda();
    azienda.setNome("EtihadinformaticsFutureCENTER");
    azienda.setSede("Torino");
    azienda.setIndirizzo("Via della Mole 31");
    azienda.setEmail("etihad.informatics.future.center@gmail.com");
    azienda.setTelefono("0912345612");
    
    aziendeService.registraAzienda(azienda);
    
    utentiService.login(studente.getUsername(), studente.getPassword());

    DomandaTirocinio domandaTirocinio = new DomandaTirocinio();
    domandaTirocinio.setCfu(18);
    domandaTirocinio.setStatus(DomandaTirocinio.IN_ATTESA);
    domandaTirocinio.setData(LocalDate.now());
    domandaTirocinio.setInizioTirocinio(LocalDate.of(2019, Month.FEBRUARY, 12));
    domandaTirocinio.setFineTirocinio(LocalDate.of(2019, Month.MAY, 20));
    
    domandaTirocinio = domandeService.registraDomanda(domandaTirocinio, azienda.getNome());
    
    Registro registro = new Registro();
    registro.setData(LocalDate.of(2019, Month.MARCH, 1));
    registro.setInizio(LocalTime.of(8, 30));
    registro.setFine(LocalTime.of(16, 30));
    
    registro = registriService.registraTirocinio(registro, domandaTirocinio.getId());
    
    assertEquals(registro, registriRep.findById((long)registro.getId()));
    
    Registro registro1 = new Registro();
    registro1.setData(LocalDate.of(2019, Month.MARCH, 2));
    registro1.setInizio(LocalTime.of(8, 30));
    registro1.setFine(LocalTime.of(16, 30));
    
    registro1 = registriService.registraTirocinio(registro1, domandaTirocinio.getId());
    
    assertEquals(registro1, registriRep.findById((long)registro1.getId()));
    
    Registro registro2 = new Registro();
    registro2.setData(LocalDate.of(2019, Month.MARCH, 3));
    registro2.setInizio(LocalTime.of(8, 30));
    registro2.setFine(LocalTime.of(16, 30));
    
    registro2 = registriService.registraTirocinio(registro2, domandaTirocinio.getId());
    
    assertEquals(registro2, registriRep.findById((long)registro2.getId()));
    
    Registro registro3 = new Registro();
    registro3.setData(LocalDate.of(2019, Month.MARCH, 4));
    registro3.setInizio(LocalTime.of(8, 30));
    registro3.setFine(LocalTime.of(16, 30));
    
    registro3 = registriService.registraTirocinio(registro3, domandaTirocinio.getId());
    
    assertEquals(registro3, registriRep.findById((long)registro3.getId()));
    
    Registro registro4 = new Registro();
    registro4.setData(LocalDate.of(2019, Month.MARCH, 5));
    registro4.setInizio(LocalTime.of(8, 30));
    registro4.setFine(LocalTime.of(16, 30));
    
    registro4 = registriService.registraTirocinio(registro4, domandaTirocinio.getId());
    
    assertEquals(registro4, registriRep.findById((long)registro4.getId()));
    
    Registro registro5 = new Registro();
    registro5.setData(LocalDate.of(2019, Month.MARCH, 5));
    registro5.setInizio(LocalTime.of(8, 30));
    registro5.setFine(LocalTime.of(16, 30));
    
    registro5 = registriService.registraTirocinio(registro5, domandaTirocinio.getId());
    
    assertEquals(registro5, registriRep.findById((long)registro5.getId()));
    
    Registro registro6 = new Registro();
    registro6.setData(LocalDate.of(2019, Month.MARCH, 6));
    registro6.setInizio(LocalTime.of(8, 30));
    registro6.setFine(LocalTime.of(16, 30));
    
    registro6 = registriService.registraTirocinio(registro6, domandaTirocinio.getId());
    
    assertEquals(registro6, registriRep.findById((long)registro6.getId()));
    
    Registro registro7 = new Registro();
    registro7.setData(LocalDate.of(2019, Month.MARCH, 7));
    registro7.setInizio(LocalTime.of(8, 30));
    registro7.setFine(LocalTime.of(16, 30));
    
    registro7 = registriService.registraTirocinio(registro7, domandaTirocinio.getId());
    
    assertEquals(registro7, registriRep.findById((long)registro7.getId()));
    
    Registro registro8 = new Registro();
    registro8.setData(LocalDate.of(2019, Month.MARCH, 8));
    registro8.setInizio(LocalTime.of(8, 30));
    registro8.setFine(LocalTime.of(16, 30));
    
    registro8 = registriService.registraTirocinio(registro8, domandaTirocinio.getId());
    
    assertEquals(registro8, registriRep.findById((long)registro8.getId()));
    
    Registro registro9 = new Registro();
    registro9.setData(LocalDate.of(2019, Month.MARCH, 9));
    registro9.setInizio(LocalTime.of(8, 30));
    registro9.setFine(LocalTime.of(16, 30));
    
    registro9 = registriService.registraTirocinio(registro9, domandaTirocinio.getId());
    
    assertEquals(registro9, registriRep.findById((long)registro9.getId()));
    
    Registro registro10 = new Registro();
    registro10.setData(LocalDate.of(2019, Month.MARCH, 10));
    registro10.setInizio(LocalTime.of(8, 30));
    registro10.setFine(LocalTime.of(16, 30));
    
    registro10 = registriService.registraTirocinio(registro10, domandaTirocinio.getId());
    
    assertEquals(registro10, registriRep.findById((long)registro10.getId()));
    
    Registro registro11 = new Registro();
    registro11.setData(LocalDate.of(2019, Month.MARCH, 11));
    registro11.setInizio(LocalTime.of(8, 30));
    registro11.setFine(LocalTime.of(16, 30));
    
    registro11 = registriService.registraTirocinio(registro11, domandaTirocinio.getId());
    
    assertEquals(registro11, registriRep.findById((long)registro11.getId()));
    
    Registro registro12 = new Registro();
    registro12.setData(LocalDate.of(2019, Month.MARCH, 12));
    registro12.setInizio(LocalTime.of(8, 30));
    registro12.setFine(LocalTime.of(16, 30));
    
    registro12 = registriService.registraTirocinio(registro12, domandaTirocinio.getId());
    
    assertEquals(registro12, registriRep.findById((long)registro12.getId()));
    
    Registro registro13 = new Registro();
    registro13.setData(LocalDate.of(2019, Month.MARCH, 13));
    registro13.setInizio(LocalTime.of(8, 30));
    registro13.setFine(LocalTime.of(16, 30));
    
    registro13 = registriService.registraTirocinio(registro13, domandaTirocinio.getId());
    
    assertEquals(registro13, registriRep.findById((long)registro13.getId()));
    
    Registro registro14 = new Registro();
    registro14.setData(LocalDate.of(2019, Month.MARCH, 14));
    registro14.setInizio(LocalTime.of(8, 30));
    registro14.setFine(LocalTime.of(16, 30));
    
    registro14 = registriService.registraTirocinio(registro14, domandaTirocinio.getId());
    
    assertEquals(registro14, registriRep.findById((long)registro14.getId()));
    
    Registro registro15 = new Registro();
    registro15.setData(LocalDate.of(2019, Month.MARCH, 15));
    registro15.setInizio(LocalTime.of(8, 30));
    registro15.setFine(LocalTime.of(16, 30));
    
    registro15 = registriService.registraTirocinio(registro15, domandaTirocinio.getId());
    
    assertEquals(registro15, registriRep.findById((long)registro15.getId()));
    
    Registro registro16 = new Registro();
    registro16.setData(LocalDate.of(2019, Month.MARCH, 16));
    registro16.setInizio(LocalTime.of(8, 30));
    registro16.setFine(LocalTime.of(16, 30));
    
    registro16 = registriService.registraTirocinio(registro16, domandaTirocinio.getId());
    
    assertEquals(registro16, registriRep.findById((long)registro16.getId()));
    
    Registro registro17 = new Registro();
    registro17.setData(LocalDate.of(2019, Month.MARCH, 17));
    registro17.setInizio(LocalTime.of(8, 30));
    registro17.setFine(LocalTime.of(16, 30));
    
    registro17 = registriService.registraTirocinio(registro17, domandaTirocinio.getId());
    
    assertEquals(registro17, registriRep.findById((long)registro17.getId()));
    
    Registro registro18 = new Registro();
    registro18.setData(LocalDate.of(2019, Month.MARCH, 18));
    registro18.setInizio(LocalTime.of(8, 30));
    registro18.setFine(LocalTime.of(16, 30));
    
    registro18 = registriService.registraTirocinio(registro18, domandaTirocinio.getId());
    
    assertEquals(registro18, registriRep.findById((long)registro18.getId()));
    
    Registro registro19 = new Registro();
    registro19.setData(LocalDate.of(2019, Month.MARCH, 19));
    registro19.setInizio(LocalTime.of(8, 30));
    registro19.setFine(LocalTime.of(16, 30));
    
    registro19 = registriService.registraTirocinio(registro19, domandaTirocinio.getId());
    
    assertEquals(registro19, registriRep.findById((long)registro19.getId()));
    
    Registro registro20 = new Registro();
    registro20.setData(LocalDate.of(2019, Month.MARCH, 20));
    registro20.setInizio(LocalTime.of(8, 30));
    registro20.setFine(LocalTime.of(16, 30));
    
    registro20 = registriService.registraTirocinio(registro20, domandaTirocinio.getId());
    
    assertEquals(registro20, registriRep.findById((long)registro20.getId()));
    
    Registro registro21 = new Registro();
    registro21.setData(LocalDate.of(2019, Month.MARCH, 21));
    registro21.setInizio(LocalTime.of(8, 30));
    registro21.setFine(LocalTime.of(16, 30));
    
    registro21 = registriService.registraTirocinio(registro21, domandaTirocinio.getId());
    
    assertEquals(registro21, registriRep.findById((long)registro21.getId()));
    
    Registro registro22 = new Registro();
    registro22.setData(LocalDate.of(2019, Month.MARCH, 22));
    registro22.setInizio(LocalTime.of(8, 30));
    registro22.setFine(LocalTime.of(16, 30));
    
    registro22 = registriService.registraTirocinio(registro22, domandaTirocinio.getId());
    
    assertEquals(registro22, registriRep.findById((long)registro22.getId()));
    
    Registro registro23 = new Registro();
    registro23.setData(LocalDate.of(2019, Month.MARCH, 23));
    registro23.setInizio(LocalTime.of(8, 30));
    registro23.setFine(LocalTime.of(16, 30));
    
    registro23 = registriService.registraTirocinio(registro23, domandaTirocinio.getId());
    
    assertEquals(registro23, registriRep.findById((long)registro23.getId()));
    
    Registro registro24 = new Registro();
    registro24.setData(LocalDate.of(2019, Month.MARCH, 24));
    registro24.setInizio(LocalTime.of(8, 30));
    registro24.setFine(LocalTime.of(16, 30));
    
    registro24 = registriService.registraTirocinio(registro24, domandaTirocinio.getId());
    
    assertEquals(registro24, registriRep.findById((long)registro24.getId()));
    
    Registro registro25 = new Registro();
    registro25.setData(LocalDate.of(2019, Month.MARCH, 25));
    registro25.setInizio(LocalTime.of(8, 30));
    registro25.setFine(LocalTime.of(16, 30));
    
    registro25 = registriService.registraTirocinio(registro25, domandaTirocinio.getId());
    
    assertEquals(registro25, registriRep.findById((long)registro25.getId()));
    
    Registro registro26 = new Registro();
    registro26.setData(LocalDate.of(2019, Month.MARCH, 26));
    registro26.setInizio(LocalTime.of(8, 30));
    registro26.setFine(LocalTime.of(16, 30));
    
    registro26 = registriService.registraTirocinio(registro26, domandaTirocinio.getId());
    
    assertEquals(registro26, registriRep.findById((long)registro26.getId()));
    
    Registro registro27 = new Registro();
    registro27.setData(LocalDate.of(2019, Month.MARCH, 27));
    registro27.setInizio(LocalTime.of(8, 30));
    registro27.setFine(LocalTime.of(16, 30));
    
    registro27 = registriService.registraTirocinio(registro27, domandaTirocinio.getId());
    
    assertEquals(registro27, registriRep.findById((long)registro27.getId()));
    
    Registro registro28 = new Registro();
    registro28.setData(LocalDate.of(2019, Month.MARCH, 28));
    registro28.setInizio(LocalTime.of(8, 30));
    registro28.setFine(LocalTime.of(16, 30));
    
    registro28 = registriService.registraTirocinio(registro28, domandaTirocinio.getId());
    
    assertEquals(registro28, registriRep.findById((long)registro28.getId()));
    
    Registro registro29 = new Registro();
    registro29.setData(LocalDate.of(2019, Month.MARCH, 29));
    registro29.setInizio(LocalTime.of(8, 30));
    registro29.setFine(LocalTime.of(16, 30));
    
    registro29 = registriService.registraTirocinio(registro29, domandaTirocinio.getId());
    
    assertEquals(registro29, registriRep.findById((long)registro29.getId()));
    
    Registro registro30 = new Registro();
    registro30.setData(LocalDate.of(2019, Month.MARCH, 30));
    registro30.setInizio(LocalTime.of(8, 30));
    registro30.setFine(LocalTime.of(16, 30));
    
    registro30 = registriService.registraTirocinio(registro30, domandaTirocinio.getId());
    
    assertEquals(registro30, registriRep.findById((long)registro30.getId()));
    
    Registro registro31 = new Registro();
    registro31.setData(LocalDate.of(2019, Month.MARCH, 31));
    registro31.setInizio(LocalTime.of(8, 30));
    registro31.setFine(LocalTime.of(16, 30));
    
    registro31 = registriService.registraTirocinio(registro31, domandaTirocinio.getId());
    
    assertEquals(registro31, registriRep.findById((long)registro31.getId()));
    
    Registro registro32 = new Registro();
    registro32.setData(LocalDate.of(2019, Month.APRIL, 1));
    registro32.setInizio(LocalTime.of(8, 30));
    registro32.setFine(LocalTime.of(16, 30));
    
    registro32 = registriService.registraTirocinio(registro32, domandaTirocinio.getId());
    
    assertEquals(registro32, registriRep.findById((long)registro32.getId()));
    
    Registro registro33 = new Registro();
    registro33.setData(LocalDate.of(2019, Month.APRIL, 2));
    registro33.setInizio(LocalTime.of(8, 30));
    registro33.setFine(LocalTime.of(16, 30));
    
    registro33 = registriService.registraTirocinio(registro33, domandaTirocinio.getId());
    
    assertEquals(registro33, registriRep.findById((long)registro33.getId()));
    
    Registro registro34 = new Registro();
    registro34.setData(LocalDate.of(2019, Month.APRIL, 3));
    registro34.setInizio(LocalTime.of(8, 30));
    registro34.setFine(LocalTime.of(16, 30));
    
    registro34 = registriService.registraTirocinio(registro34, domandaTirocinio.getId());
    
    assertEquals(registro34, registriRep.findById((long)registro34.getId()));
    
    Registro registro35 = new Registro();
    registro35.setData(LocalDate.of(2019, Month.APRIL, 4));
    registro35.setInizio(LocalTime.of(8, 30));
    registro35.setFine(LocalTime.of(16, 30));
    
    registro35 = registriService.registraTirocinio(registro35, domandaTirocinio.getId());
    
    assertEquals(registro35, registriRep.findById((long)registro35.getId()));
    
    Registro registro36 = new Registro();
    registro36.setData(LocalDate.of(2019, Month.APRIL, 5));
    registro36.setInizio(LocalTime.of(8, 30));
    registro36.setFine(LocalTime.of(16, 30));
    
    registro36 = registriService.registraTirocinio(registro36, domandaTirocinio.getId());
    
    assertEquals(registro36, registriRep.findById((long)registro36.getId()));
    
    Registro registro37 = new Registro();
    registro37.setData(LocalDate.of(2019, Month.APRIL, 6));
    registro37.setInizio(LocalTime.of(8, 30));
    registro37.setFine(LocalTime.of(16, 30));
    
    registro37 = registriService.registraTirocinio(registro37, domandaTirocinio.getId());
    
    assertEquals(registro37, registriRep.findById((long)registro37.getId()));
    
    Registro registro38 = new Registro();
    registro38.setData(LocalDate.of(2019, Month.APRIL, 7));
    registro38.setInizio(LocalTime.of(8, 30));
    registro38.setFine(LocalTime.of(16, 30));
    
    registro38 = registriService.registraTirocinio(registro38, domandaTirocinio.getId());
    
    assertEquals(registro38, registriRep.findById((long)registro38.getId()));
    
    Registro registro39 = new Registro();
    registro39.setData(LocalDate.of(2019, Month.APRIL, 8));
    registro39.setInizio(LocalTime.of(8, 30));
    registro39.setFine(LocalTime.of(16, 30));
    
    registro39 = registriService.registraTirocinio(registro39, domandaTirocinio.getId());
    
    assertEquals(registro39, registriRep.findById((long)registro39.getId()));
    
    Registro registro40 = new Registro();
    registro40.setData(LocalDate.of(2019, Month.APRIL, 9));
    registro40.setInizio(LocalTime.of(8, 30));
    registro40.setFine(LocalTime.of(16, 30));
    
    registro40 = registriService.registraTirocinio(registro40, domandaTirocinio.getId());
    
    assertEquals(registro40, registriRep.findById((long)registro40.getId()));
    
    Registro registro41 = new Registro();
    registro41.setData(LocalDate.of(2019, Month.APRIL, 10));
    registro41.setInizio(LocalTime.of(8, 30));
    registro41.setFine(LocalTime.of(16, 30));
    
    registro41 = registriService.registraTirocinio(registro41, domandaTirocinio.getId());
    
    assertEquals(registro41, registriRep.findById((long)registro41.getId()));
    
    Registro registro42 = new Registro();
    registro42.setData(LocalDate.of(2019, Month.APRIL, 11));
    registro42.setInizio(LocalTime.of(8, 30));
    registro42.setFine(LocalTime.of(16, 30));
    
    registro42 = registriService.registraTirocinio(registro42, domandaTirocinio.getId());
    
    assertEquals(registro42, registriRep.findById((long)registro42.getId()));
    
    Registro registro43 = new Registro();
    registro43.setData(LocalDate.of(2019, Month.APRIL, 12));
    registro43.setInizio(LocalTime.of(8, 30));
    registro43.setFine(LocalTime.of(16, 30));
    
    registro43 = registriService.registraTirocinio(registro43, domandaTirocinio.getId());
    
    assertEquals(registro43, registriRep.findById((long)registro43.getId()));
    
    Registro registro44 = new Registro();
    registro44.setData(LocalDate.of(2019, Month.APRIL, 13));
    registro44.setInizio(LocalTime.of(8, 30));
    registro44.setFine(LocalTime.of(16, 30));
    
    registro44 = registriService.registraTirocinio(registro44, domandaTirocinio.getId());
    
    assertEquals(registro44, registriRep.findById((long)registro44.getId()));
    
    Registro registro45 = new Registro();
    registro45.setData(LocalDate.of(2019, Month.APRIL, 14));
    registro45.setInizio(LocalTime.of(8, 30));
    registro45.setFine(LocalTime.of(16, 30));
    
    registro45 = registriService.registraTirocinio(registro45, domandaTirocinio.getId());
    
    assertEquals(registro45, registriRep.findById((long)registro45.getId()));
    
    Registro registro46 = new Registro();
    registro46.setData(LocalDate.of(2019, Month.APRIL, 15));
    registro46.setInizio(LocalTime.of(8, 30));
    registro46.setFine(LocalTime.of(16, 30));
    
    registro46 = registriService.registraTirocinio(registro46, domandaTirocinio.getId());
    
    assertEquals(registro46, registriRep.findById((long)registro46.getId()));
    
    Registro registro47 = new Registro();
    registro47.setData(LocalDate.of(2019, Month.APRIL, 16));
    registro47.setInizio(LocalTime.of(8, 30));
    registro47.setFine(LocalTime.of(16, 30));
    
    registro47 = registriService.registraTirocinio(registro47, domandaTirocinio.getId());
    
    assertEquals(registro47, registriRep.findById((long)registro47.getId()));
    
    Registro registro48 = new Registro();
    registro48.setData(LocalDate.of(2019, Month.APRIL, 17));
    registro48.setInizio(LocalTime.of(8, 30));
    registro48.setFine(LocalTime.of(16, 30));
    
    registro48 = registriService.registraTirocinio(registro48, domandaTirocinio.getId());
    
    assertEquals(registro48, registriRep.findById((long)registro48.getId()));
    
    Registro registro49 = new Registro();
    registro49.setData(LocalDate.of(2019, Month.APRIL, 18));
    registro49.setInizio(LocalTime.of(8, 30));
    registro49.setFine(LocalTime.of(16, 30));
    
    registro49 = registriService.registraTirocinio(registro49, domandaTirocinio.getId());
    
    assertEquals(registro49, registriRep.findById((long)registro49.getId()));
    
    Registro registro50 = new Registro();
    registro50.setData(LocalDate.of(2019, Month.APRIL, 19));
    registro50.setInizio(LocalTime.of(8, 30));
    registro50.setFine(LocalTime.of(16, 30));
    
    registro50 = registriService.registraTirocinio(registro50, domandaTirocinio.getId());
    
    assertEquals(registro50, registriRep.findById((long)registro50.getId()));
    
    Registro registro51 = new Registro();
    registro51.setData(LocalDate.of(2019, Month.APRIL, 20));
    registro51.setInizio(LocalTime.of(8, 30));
    registro51.setFine(LocalTime.of(16, 30));
    
    registro51 = registriService.registraTirocinio(registro51, domandaTirocinio.getId());
    
    assertEquals(registro51, registriRep.findById((long)registro51.getId()));
    
    Registro registro52 = new Registro();
    registro52.setData(LocalDate.of(2019, Month.APRIL, 21));
    registro52.setInizio(LocalTime.of(8, 30));
    registro52.setFine(LocalTime.of(16, 30));
    
    registro52 = registriService.registraTirocinio(registro52, domandaTirocinio.getId());
    
    assertEquals(registro52, registriRep.findById((long)registro52.getId()));
    
    Registro registro53 = new Registro();
    registro53.setData(LocalDate.of(2019, Month.MARCH, 22));
    registro53.setInizio(LocalTime.of(8, 30));
    registro53.setFine(LocalTime.of(16, 30));
    
    registro53 = registriService.registraTirocinio(registro53, domandaTirocinio.getId());
    
    assertEquals(registro53, registriRep.findById((long)registro53.getId()));
    
    Registro registro54 = new Registro();
    registro54.setData(LocalDate.of(2019, Month.APRIL, 23));
    registro54.setInizio(LocalTime.of(8, 30));
    registro54.setFine(LocalTime.of(16, 30));
    
    registro54 = registriService.registraTirocinio(registro54, domandaTirocinio.getId());
    
    assertEquals(registro54, registriRep.findById((long)registro54.getId()));
    
    Registro registro55 = new Registro();
    registro55.setData(LocalDate.of(2019, Month.APRIL, 24));
    registro55.setInizio(LocalTime.of(8, 30));
    registro55.setFine(LocalTime.of(16, 30));
    
    registro55 = registriService.registraTirocinio(registro55, domandaTirocinio.getId());
    
    assertEquals(registro55, registriRep.findById((long)registro55.getId()));
    
    Registro registro56 = new Registro();
    registro56.setData(LocalDate.of(2019, Month.APRIL, 25));
    registro56.setInizio(LocalTime.of(8, 30));
    registro56.setFine(LocalTime.of(16, 30));
    
    registro56 = registriService.registraTirocinio(registro56, domandaTirocinio.getId());
    
    assertEquals(registro56, registriRep.findById((long)registro56.getId()));
    
    registriRep.flush();
    
    registriService
        .verificaNumeroOreRegistro(LocalTime.of(8, 30),
            LocalTime.of(16, 30), domandaTirocinio.getId());
    
  }
  
}
