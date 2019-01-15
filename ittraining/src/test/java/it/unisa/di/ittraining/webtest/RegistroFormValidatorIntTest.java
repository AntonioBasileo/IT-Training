package it.unisa.di.ittraining.webtest;

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
import it.unisa.di.ittraining.web.RegistroForm;
import it.unisa.di.ittraining.web.RegistroFormValidator;

import java.time.LocalDate;
import java.time.Month;

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
public class RegistroFormValidatorIntTest {
  
  @Mock
  private Errors errors;

  @Autowired
  private RegistroFormValidator validator;
  
  @Autowired
  private StudentiService studenteService;
  
  @Autowired
  private AziendaService aziendaService;
  
  @Autowired
  private DomandaTirocinioService domandaService;
  
  @Autowired
  private UtenteService utenteService;
  
  @Autowired
  private ProgettoFormativoService progettoService;
  
  @Autowired
  private TutorAccademicoService tutorAccademicoService;
  
  @Test
  public void registroFormValidator() {
    
    Azienda azienda = new Azienda();
    azienda.setNome("EnteAzienda");
    azienda.setIndirizzo("via roma 12");
    azienda.setEmail("aziendaente@gmail.com");
    azienda.setSede("Salerno");
    azienda.setTelefono("3334112122");
    
    try {
      aziendaService.registraAzienda(azienda);
    } catch (AziendaNonValidaException | AziendaEsistenteException | SedeNonValidaException
       | IndirizzoNonValidoException | EmailNonValidaException | EmailAziendaEsistenteException
       | it.unisa.di.ittraining.azienda.TelefonoNonValidoException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    
    Studente studente = new Studente();
    studente.setNome("Gianmaria");
    studente.setCognome("Neri");
    studente.setSesso("M");
    studente.setMatricola("0512107878");
    studente.setUsername("Gianmaria");
    studente.setPassword("Gianmaria");
    studente.setTelefono("3204444456");
    studente.setEmail("gianmaria@studenti.unisa.it");
    studente.setDataDiNascita(LocalDate.of(1996, Month.JULY, 4));
    
    try {
      studenteService.registraStudente(studente);
    } catch (NomeNonValidoException | NomeCognomeTroppoLungoException 
      | NomeCognomeTroppoCortoException | CognomeNonValidoException 
      | DataDiNascitaNonValidaException | UsernameNonValidoException
      | UsernameEsistenteException | EmailNonValidaException 
      | EmailEsistenteException | SessoNonValidoException 
      | TelefonoNonValidoException | MatricolaStudenteNonValidaException 
      | MatricolaStudenteEsistenteException
      | PasswordNonValidaException | PasswordNonCorrispondentiException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    TutorAziendale tutorAziendale = new TutorAziendale();
    tutorAziendale.setUsername("Bianca");
    tutorAziendale.setNome("Bianca");
    tutorAziendale.setCognome("Rossi");
    tutorAziendale.setDataDiNascita(LocalDate.of(1970, Month.DECEMBER, 30));
    tutorAziendale.setTelefono("0987634521");
    tutorAziendale.setEmail(azienda.getEmail());
    tutorAziendale.setPassword("bianca123");
    tutorAziendale.setSesso("F");
    
    try {
      aziendaService.registraTutorAziendale(tutorAziendale, azienda.getNome());
    } catch (NomeNonValidoException | NomeCognomeTroppoLungoException 
        | NomeCognomeTroppoCortoException | CognomeNonValidoException 
        | EmailNonValidaException | EmailEsistenteException | EmailNonAssociataException
        | UsernameNonValidoException | UsernameEsistenteException 
        | PasswordNonValidaException | PasswordNonCorrispondentiException 
        | DataDiNascitaNonValidaException | AziendaNonValidaException
        | AziendaEsistenteException | SessoNonValidoException
        | it.unisa.di.ittraining.azienda.TelefonoNonValidoException 
        | AziendaNonEsistenteException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    
    try {
      utenteService.login(studente.getUsername(), studente.getPassword());
    } catch (UsernameNonEsistenteException | PasswordErrataException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    
    DomandaTirocinio domanda = new DomandaTirocinio();
    domanda.setData(LocalDate.now());
    domanda.setAzienda(azienda);
    domanda.setCfu(6);
    domanda.setInizioTirocinio(LocalDate.of(2019, Month.FEBRUARY, 16));
    domanda.setFineTirocinio(LocalDate.of(2019, Month.MARCH, 10));
    domanda.setStudente(studente);
    
    try {
      domandaService.registraDomanda(domanda, azienda.getNome());
    } catch (AziendaNonValidaException | AziendaNonEsistenteException 
          | DataDiNascitaNonValidaException
          | DataNonValidaException | DataFinePrecedenteDataInizioException 
          | MassimoNumeroCfuCumulabiliException | NumeroCfuNonValidoException e) {
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
          domanda.getId());
      
    utenteService.logout();
    
    TutorAccademico tutorAccademico = new TutorAccademico();
    tutorAccademico.setUsername("Giovanni");
    tutorAccademico.setNome("Giovanni");
    tutorAccademico.setCognome("Neri");
    tutorAccademico.setDataDiNascita(LocalDate.of(1972, Month.DECEMBER, 30));
    tutorAccademico.setTelefono("0987654311");
    tutorAccademico.setEmail("giovanni@unisa.it");
    tutorAccademico.setPassword("giovanni");
    tutorAccademico.setSesso("M");
    
    try {
      tutorAccademico = tutorAccademicoService.registraTutorAccademico(tutorAccademico);
    } catch (NomeNonValidoException | NomeCognomeTroppoLungoException 
         | NomeCognomeTroppoCortoException | CognomeNonValidoException 
         | EmailNonValidaException | EmailEsistenteException
         | it.unisa.di.ittraining.utente.TelefonoNonValidoException 
         | DataDiNascitaNonValidaException
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
        
    domanda = domandaService.aggiornaStatoDomanda(domanda.getId(), 
            DomandaTirocinio.APPROVATA);
        
    utenteService.logout();
      
    try {
      utenteService.login(studente.getUsername(), studente.getPassword());
    } catch (UsernameNonEsistenteException | PasswordErrataException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    
    RegistroForm registro = new RegistroForm();
    registro.setAnno(2019);
    registro.setMese(2);
    registro.setGiorno(18);
    registro.setAnnoInizio(2019);
    registro.setAnnoFine(2019);
    registro.setMeseInizio(2);
    registro.setMeseFine(3);
    registro.setGiornoInizio(16);
    registro.setGiornoFine(10);
    registro.setDescrizione("test e revisione");
    registro.setOraInizio(10);
    registro.setOraFine(18);
    registro.setMinutoInizio(30);
    registro.setMinutoFine(30);
    registro.setIdDomanda(domanda.getId());
    
    validator.validate((Object) registro, errors);
    
    utenteService.logout();
    
    
  }
}
