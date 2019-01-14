package it.unisa.di.ittraining.domandaTirocinio.test;

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
import it.unisa.di.ittraining.progettoformativo.ProgettoFormativo;
import it.unisa.di.ittraining.progettoformativo.ProgettoFormativoService;
import it.unisa.di.ittraining.studente.MatricolaStudenteEsistenteException;
import it.unisa.di.ittraining.studente.MatricolaStudenteNonValidaException;
import it.unisa.di.ittraining.studente.Studente;
import it.unisa.di.ittraining.studente.StudentiService;
import it.unisa.di.ittraining.tutoraccademico.TutorAccademico;
import it.unisa.di.ittraining.tutoraccademico.TutorAccademicoService;
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

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

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
public class DomandaTirocinioServiceIntTest {
  
  @Autowired
  private AziendaService aziendaService;
  
  @Autowired
  private DomandaTirocinioService domandaTirocinioService;

  @Autowired 
  private UtenteService utenteService;
  
  @Autowired
  private StudentiService studenteService;
  
  @Autowired
  private ProgettoFormativoService progettoService;
  
  @Autowired
  private TutorAccademicoService tutorAccademicoService;
  
  List<DomandaTirocinio> domandeStudente;
  
  List<DomandaTirocinio> domandeStatus;
  
  List<DomandaTirocinio> domandeAziendali;
  
  private Azienda azienda;
  
  private Studente studente;

  @Test
  public void registraDomanda() {
   
    domandeStudente = new ArrayList<>();
    domandeStatus = new ArrayList<>();
    domandeAziendali = new ArrayList<>();
        
    azienda = new Azienda();
    azienda.setNome("GraficaSRL");
    azienda.setTelefono("3333333333");
    azienda.setSede("Avellino");
    azienda.setIndirizzo("Via Roma 45");
    azienda.setEmail("grafica@gmail.com");
    try {
      azienda = aziendaService.registraAzienda(azienda);
    } catch (AziendaNonValidaException | AziendaEsistenteException | SedeNonValidaException
        | IndirizzoNonValidoException | EmailNonValidaException | EmailAziendaEsistenteException
        | TelefonoNonValidoException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    
    TutorAziendale tutorAziendale = new TutorAziendale();
    tutorAziendale.setUsername("linaGrafica");
    tutorAziendale.setNome("Lina");
    tutorAziendale.setCognome("Neri");
    tutorAziendale.setDataDiNascita(LocalDate.of(1970, Month.DECEMBER, 30));
    tutorAziendale.setTelefono("0987654321");
    tutorAziendale.setEmail(azienda.getEmail());
    tutorAziendale.setPassword("lina123");
    tutorAziendale.setSesso("F");
    
    try {
      tutorAziendale = aziendaService.registraTutorAziendale(tutorAziendale, azienda.getNome());
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
    
    studente = new Studente();
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
      studente = studenteService.registraStudente(studente);
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
    domandaTirocinio.setCfu(6);
    domandaTirocinio.setData(LocalDate.now());
    domandaTirocinio.setInizioTirocinio(LocalDate.of(2019, Month.FEBRUARY, 12));
    domandaTirocinio.setFineTirocinio(LocalDate.of(2019, Month.MARCH, 20));
    domandaTirocinio.setAzienda(azienda);
    domandaTirocinio.setStudente(studente);
    
    DomandaTirocinio domandaTirocinio1 = new DomandaTirocinio();
    domandaTirocinio1.setCfu(6);
    domandaTirocinio1.setData(LocalDate.now());
    domandaTirocinio1.setInizioTirocinio(LocalDate.of(2019, Month.MARCH, 21));
    domandaTirocinio1.setFineTirocinio(LocalDate.of(2019, Month.APRIL, 24));
    domandaTirocinio1.setAzienda(azienda);
    domandaTirocinio1.setStudente(studente);
    
    DomandaTirocinio domandaTirocinio2 = new DomandaTirocinio();
    domandaTirocinio2.setCfu(6);
    domandaTirocinio2.setData(LocalDate.now());
    domandaTirocinio2.setInizioTirocinio(LocalDate.of(2019, Month.MAY, 12));
    domandaTirocinio2.setFineTirocinio(LocalDate.of(2019, Month.JUNE, 20));
    domandaTirocinio2.setAzienda(azienda);
    domandaTirocinio2.setStudente(studente);
    
    try {
      domandaTirocinio = domandaTirocinioService.registraDomanda(domandaTirocinio, 
          azienda.getNome());
      domandaTirocinio1 = domandaTirocinioService.registraDomanda(domandaTirocinio1,
          azienda.getNome());
      domandaTirocinio2 = domandaTirocinioService.registraDomanda(domandaTirocinio2,
              azienda.getNome());
    } catch (AziendaNonValidaException | AziendaNonEsistenteException 
        | DataDiNascitaNonValidaException | DataNonValidaException 
        | DataFinePrecedenteDataInizioException | MassimoNumeroCfuCumulabiliException
        | NumeroCfuNonValidoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    domandeStudente.add(domandaTirocinio);
    domandeStudente.add(domandaTirocinio1);
    domandeStudente.add(domandaTirocinio2);
    
    domandeAziendali.add(domandaTirocinio);
    domandeAziendali.add(domandaTirocinio1);
    domandeAziendali.add(domandaTirocinio2);
    
    utenteService.logout();
    
    try {
      utenteService.login(tutorAziendale.getUsername(), tutorAziendale.getPassword());
    } catch (UsernameNonEsistenteException | PasswordErrataException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    ProgettoFormativo progettoFormativo = new ProgettoFormativo();
    progettoFormativo.setDescrizione("Test e revisione");
    
    progettoFormativo = progettoService.inserisciProgetto(progettoFormativo, 
      domandaTirocinio.getId());
    
    ProgettoFormativo progettoFormativo1 = new ProgettoFormativo();
    progettoFormativo1.setDescrizione("Test di integrazione");
    
    progettoFormativo1 = progettoService.inserisciProgetto(progettoFormativo1, 
      domandaTirocinio1.getId());
    
    utenteService.logout();
    
    TutorAccademico tutorAccademico = new TutorAccademico();
    tutorAccademico.setUsername("Francesca");
    tutorAccademico.setNome("Franca");
    tutorAccademico.setCognome("Neri");
    tutorAccademico.setDataDiNascita(LocalDate.of(1970, Month.DECEMBER, 30));
    tutorAccademico.setTelefono("0987654324");
    tutorAccademico.setEmail("franca@unisa.it");
    tutorAccademico.setPassword("franca1");
    tutorAccademico.setSesso("F");
    
    try {
      tutorAccademico = tutorAccademicoService.registraTutorAccademico(tutorAccademico);
    } catch (NomeNonValidoException | NomeCognomeTroppoLungoException 
       | NomeCognomeTroppoCortoException | CognomeNonValidoException 
       | EmailNonValidaException | EmailEsistenteException
       | it.unisa.di.ittraining.utente.TelefonoNonValidoException | DataDiNascitaNonValidaException
       | PasswordNonValidaException | PasswordNonCorrispondentiException | SessoNonValidoException
       | UsernameNonValidoException | UsernameEsistenteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    try {
      utenteService.login(tutorAccademico.getUsername(), tutorAccademico.getPassword());
    } catch (UsernameNonEsistenteException | PasswordErrataException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    domandaTirocinioService.aggiornaStatoDomanda(domandaTirocinio.getId(), 
        DomandaTirocinio.APPROVATA);
  
    domandaTirocinioService.aggiornaStatoDomanda(domandaTirocinio1.getId(), 
            DomandaTirocinio.APPROVATA);
    
    domandeStatus.add(domandaTirocinio);
    domandeStatus.add(domandaTirocinio1);
    
    utenteService.logout();    
  }

  @Test
  public void elencaDomandeAziendali() {
    List<DomandaTirocinio> domandeAziendali1 = domandaTirocinioService
         .elencaDomandeAziendali(azienda);
    for (DomandaTirocinio d: domandeAziendali1) {
      assertTrue(domandeAziendali.contains(d));
    }
  }
  
  @Test
  public void elencaDomandeStudente() {
    List<DomandaTirocinio> domandeStudente1 = domandaTirocinioService
         .elencaDomandeStudente(studente.getUsername());
    for (DomandaTirocinio d: domandeStudente1) {
      assertTrue(domandeStudente.contains(d));
    }
  }
  
  @Test
  public void elencaDomandeStatus() {
    List<DomandaTirocinio> domandeStatus1 = domandaTirocinioService
         .getAllByStatus(DomandaTirocinio.APPROVATA);
    for (DomandaTirocinio d: domandeStatus1) {
      assertTrue(domandeStatus.contains(d));
    }
  }
  
}
