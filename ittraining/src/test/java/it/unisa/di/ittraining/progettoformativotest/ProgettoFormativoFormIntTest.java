package it.unisa.di.ittraining.progettoformativotest;

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
import it.unisa.di.ittraining.progettoformativo.ProgettoFormativoRepository;
import it.unisa.di.ittraining.progettoformativo.ProgettoFormativoService;
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
public class ProgettoFormativoFormIntTest {

  @Mock
  private Errors errors;

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
  private ProgettoFormativoRepository progettoRep;
    
  @Test
  public void inserisciProgetto() {

    Azienda azienda = new Azienda();
    azienda.setNome("EnteAzienda3");
    azienda.setIndirizzo("via roma 22");
    azienda.setEmail("aziendaente3@gmail.com");
    azienda.setSede("Salerno");
    azienda.setTelefono("3334442122");

    try {
      aziendaService.registraAzienda(azienda);
    } catch (AziendaNonValidaException | AziendaEsistenteException | SedeNonValidaException
       | IndirizzoNonValidoException | EmailNonValidaException | EmailAziendaEsistenteException
       | it.unisa.di.ittraining.azienda.TelefonoNonValidoException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    Studente studente = new Studente();
    studente.setNome("Pasquale");
    studente.setCognome("Rossi");
    studente.setSesso("M");
    studente.setMatricola("0512108878");
    studente.setUsername("Pasquale");
    studente.setPassword("Pasquale");
    studente.setTelefono("3204444416");
    studente.setEmail("pasquale@studenti.unisa.it");
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
    tutorAziendale.setUsername("Giorgia");
    tutorAziendale.setNome("Giorgia");
    tutorAziendale.setCognome("Esposito");
    tutorAziendale.setDataDiNascita(LocalDate.of(1970, Month.DECEMBER, 30));
    tutorAziendale.setTelefono("0987646521");
    tutorAziendale.setEmail(azienda.getEmail());
    tutorAziendale.setPassword("giorgia123");
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
    domanda.setInizioTirocinio(LocalDate.of(2019, Month.FEBRUARY, 17));
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
    progettoFormativo.setDomanda(domanda);
    
    progettoService.inserisciProgetto(progettoFormativo, (long) domanda.getId());
    
    assertEquals(progettoFormativo, progettoRep.findById(progettoFormativo.getId()));
  }
}
