package it.unisa.di.ittraining.registritest;

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
import it.unisa.di.ittraining.azienda.TutorAziendale;
import it.unisa.di.ittraining.domandatirocinio.DataFinePrecedenteDataInizioException;
import it.unisa.di.ittraining.domandatirocinio.DataNonValidaException;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinioService;
import it.unisa.di.ittraining.domandatirocinio.MassimoNumeroCfuCumulabiliException;
import it.unisa.di.ittraining.domandatirocinio.NumeroCfuNonValidoException;
import it.unisa.di.ittraining.progettoformativo.ProgettoFormativo;
import it.unisa.di.ittraining.progettoformativo.ProgettoFormativoService;
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
import it.unisa.di.ittraining.utente.TelefonoNonValidoException;
import it.unisa.di.ittraining.utente.UsernameEsistenteException;
import it.unisa.di.ittraining.utente.UsernameNonEsistenteException;
import it.unisa.di.ittraining.utente.UsernameNonValidoException;
import it.unisa.di.ittraining.utente.UtenteService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

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
public class RegistroServiceIntegrationTest {

  @Autowired
  private StudentiService studenteService;
  
  @Autowired
  private AziendaService aziendaService;
  
  @Autowired
  private UtenteService utenteService;
  
  @Autowired
  private DomandaTirocinioService domandaTirocinioService;
  
  @Autowired
  private ProgettoFormativoService progettoService;
  
  @Autowired
  private TutorAccademicoService tutorAccademicoService;
  
  @Autowired
  private RegistroService registroService;
  
  @Autowired
  private RegistroRepository registroRep;
  
  @Test
  public void registraTirocinio() {

    Studente studente = new Studente();
    studente.setNome("Chiara");
    studente.setCognome("Rossi");
    studente.setDataDiNascita(LocalDate.of(1995, Month.APRIL, 29));
    studente.setMatricola("0512100002");
    studente.setSesso("F");
    studente.setEmail("chiara@studenti.unisa.it");
    studente.setPassword("chiara");
    studente.setUsername("chiara");
    studente.setTelefono("3404050111");
    
    try {
      studente = studenteService.registraStudente(studente);
    } catch (NomeNonValidoException | NomeCognomeTroppoLungoException 
       | NomeCognomeTroppoCortoException | CognomeNonValidoException 
       | DataDiNascitaNonValidaException | UsernameNonValidoException
       | UsernameEsistenteException | EmailNonValidaException | EmailEsistenteException 
       | SessoNonValidoException | TelefonoNonValidoException 
       | MatricolaStudenteNonValidaException | MatricolaStudenteEsistenteException
       | PasswordNonValidaException | PasswordNonCorrispondentiException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    Azienda azienda = new Azienda();
    azienda.setNome("GraficaSRL");
    azienda.setTelefono("3333333333");
    azienda.setSede("Avellino");
    azienda.setIndirizzo("Via Roma 45");
    azienda.setEmail("grafica@gmail.com");

    try {
      azienda = aziendaService.registraAzienda(azienda);
    } catch (AziendaNonValidaException | AziendaEsistenteException | SedeNonValidaException
        | IndirizzoNonValidoException | EmailNonValidaException | EmailAziendaEsistenteException
        | it.unisa.di.ittraining.azienda.TelefonoNonValidoException e1) {
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
        | AziendaNonEsistenteException 
        | it.unisa.di.ittraining.azienda.TelefonoNonValidoException e2) {
      // TODO Auto-generated catch block
      e2.printStackTrace();
    }
    
    try {
      utenteService.login(studente.getUsername(), studente.getPassword());
    } catch (UsernameNonEsistenteException | PasswordErrataException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
      
    DomandaTirocinio domandaTirocinio = new DomandaTirocinio();
    domandaTirocinio.setCfu(6);
    domandaTirocinio.setStatus(DomandaTirocinio.IN_ATTESA);
    domandaTirocinio.setData(LocalDate.now());
    domandaTirocinio.setInizioTirocinio(LocalDate.of(2019, Month.FEBRUARY, 12));
    domandaTirocinio.setFineTirocinio(LocalDate.of(2019, Month.MARCH, 20));
    
    try {
      domandaTirocinio = domandaTirocinioService.registraDomanda(domandaTirocinio, 
         azienda.getNome());
    } catch (AziendaNonValidaException | AziendaNonEsistenteException 
        | DataDiNascitaNonValidaException | DataNonValidaException 
        | DataFinePrecedenteDataInizioException | MassimoNumeroCfuCumulabiliException
        | NumeroCfuNonValidoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
      
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
      utenteService.login(studente.getUsername(), studente.getPassword());
    } catch (UsernameNonEsistenteException | PasswordErrataException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    
    tutorAccademico = tutorAccademicoService.associaTutorAccademico(tutorAccademico.getUsername());
    
    utenteService.logout();
    
    try {
      utenteService.login(tutorAccademico.getUsername(), tutorAccademico.getPassword());
    } catch (UsernameNonEsistenteException | PasswordErrataException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
      
    domandaTirocinio = domandaTirocinioService.aggiornaStatoDomanda(domandaTirocinio.getId(), 
          DomandaTirocinio.APPROVATA);
      
    utenteService.logout();
    
    try {
      utenteService.login(studente.getUsername(), studente.getPassword());
    } catch (UsernameNonEsistenteException | PasswordErrataException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    
    Registro registro = new Registro();
    registro.setData(LocalDate.of(2019, Month.FEBRUARY, 16));
    registro.setDescrizione("Test e revisione");
    registro.setInizio(LocalTime.of(10, 30));
    registro.setFine(LocalTime.of(18, 30));
    registro.setNumero_minuti(((ChronoUnit.MILLIS.between(registro.getInizio(), 
        registro.getFine())) / 1000) / 60);
    
    try {
      registro = registroService.registraTirocinio(registro, domandaTirocinio.getId());
    } catch (DataRegistroSuccessivaFineException | DataRegistroPrecedenteInizioException
         | DataRegistroNonValidaException | OrarioNonValidoException 
         | OrarioFinePrecedenteInizioException
         | MassimoNumeroOreException | OrePrevisteSuperateException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    assertEquals(registro, registroRep.findById((long)registro.getId()));
    
    registroService.cancellaTirocinio(registro.getId());
    
    assertEquals(null, registroRep.findById((long)registro.getId()));
    
    utenteService.logout();
  }
  
}
