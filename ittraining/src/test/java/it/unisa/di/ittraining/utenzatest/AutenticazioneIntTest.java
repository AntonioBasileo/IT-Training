package it.unisa.di.ittraining.utenzatest;

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
import it.unisa.di.ittraining.impiegatosegreteria.ImpiegatoSegreteria;
import it.unisa.di.ittraining.impiegatosegreteria.ImpiegatoSegreteriaService;
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
public class AutenticazioneIntTest {
  
  @Autowired
  private StudentiService studentiService;
  
  @Autowired
  private TutorAccademicoService accademicoService;
  
  @Autowired
  private AziendaService aziendaService;
  
  @Autowired
  private ImpiegatoSegreteriaService impiegatiService;

  @Autowired
  private UtenteService utentiService;
  
  @SuppressWarnings("static-access")
  @Test
  public void verificaLogin() {

    TutorAccademico tutorac = new TutorAccademico();
    tutorac.setUsername("Francesca");
    tutorac.setNome("Franca");
    tutorac.setCognome("Neri");
    tutorac.setDataDiNascita(LocalDate.of(1970, Month.DECEMBER, 30));
    tutorac.setTelefono("0987654324");
    tutorac.setEmail("franca@unisa.it");
    tutorac.setPassword("franca1");
    tutorac.setSesso("F");
    
    try {
      tutorac = accademicoService.registraTutorAccademico(tutorac);
    } catch (NomeNonValidoException | NomeCognomeTroppoLungoException
        | NomeCognomeTroppoCortoException
        | CognomeNonValidoException | EmailNonValidaException
        | EmailEsistenteException | TelefonoNonValidoException
        | DataDiNascitaNonValidaException | PasswordNonValidaException
        | PasswordNonCorrispondentiException
        | SessoNonValidoException | UsernameNonValidoException | UsernameEsistenteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    try {
      utentiService.login(tutorac.getUsername(), tutorac.getPassword());
    } catch (UsernameNonEsistenteException | PasswordErrataException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    assertEquals(tutorac.getUsername(), utentiService.getUtenteAutenticato().getUsername());
    
    utentiService.logout();
    
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
      studente = studentiService.registraStudente(studente);
    } catch (NomeNonValidoException | NomeCognomeTroppoLungoException 
        | NomeCognomeTroppoCortoException
        | CognomeNonValidoException | DataDiNascitaNonValidaException | UsernameNonValidoException
        | UsernameEsistenteException | EmailNonValidaException 
        | EmailEsistenteException | SessoNonValidoException
        | TelefonoNonValidoException | MatricolaStudenteNonValidaException 
        | MatricolaStudenteEsistenteException
        | PasswordNonValidaException | PasswordNonCorrispondentiException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    try {
      utentiService.login(studente.getUsername(), studente.getPassword());
    } catch (UsernameNonEsistenteException | PasswordErrataException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    assertEquals(studente.getUsername(), utentiService.getUtenteAutenticato().getUsername());
    
    utentiService.logout();
    
    ImpiegatoSegreteria impiegato = new ImpiegatoSegreteria();
    impiegato.setNome("bartolomeo");
    impiegato.setCognome("rossi");
    impiegato.setUsername("bartolomeo88");
    impiegato.setDataDiNascita(LocalDate.of(1988, Month.APRIL, 20));
    impiegato.setPassword("nicola88");
    impiegato.setSesso("M");
    impiegato.setEmail("b.rossi@unisa.it");
    impiegato.setTelefono("3392345612");
    
    try {
      impiegato = impiegatiService.registraImpiegato(impiegato);
    } catch (NomeNonValidoException | NomeCognomeTroppoLungoException
        | NomeCognomeTroppoCortoException
        | CognomeNonValidoException | EmailNonValidaException
        | EmailEsistenteException | PasswordNonValidaException
        | PasswordNonCorrispondentiException | DataDiNascitaNonValidaException
        | UsernameNonValidoException
        | UsernameEsistenteException | SessoNonValidoException
        | TelefonoNonValidoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    try {
      utentiService.login(impiegato.getUsername(), impiegato.getPassword());
    } catch (UsernameNonEsistenteException | PasswordErrataException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    assertEquals(impiegato.getUsername(), utentiService.getUtenteAutenticato().getUsername());
    
    Azienda azienda = new Azienda();
    azienda.setNome("theorem");
    azienda.setSede("Fisciano");
    azienda.setEmail("a.iannaccone@gmail.com");
    azienda.setIndirizzo("via Rossi 12");
    azienda.setTelefono("0981234567");
    
    try {
      azienda = aziendaService.registraAzienda(azienda);
    } catch (AziendaNonValidaException | AziendaEsistenteException | SedeNonValidaException
        | IndirizzoNonValidoException | EmailNonValidaException | EmailAziendaEsistenteException
        | it.unisa.di.ittraining.azienda.TelefonoNonValidoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    utentiService.logout();
    
    
    TutorAziendale tutoraz = new TutorAziendale();
    tutoraz.setNome("Antonio");
    tutoraz.setCognome("Iannaccone");
    tutoraz.setUsername("antoiannacc");
    tutoraz.setDataDiNascita(LocalDate.of(1980, Month.MAY, 21));
    tutoraz.setPassword("antoianna80");
    tutoraz.setEmail(azienda.getEmail());
    tutoraz.setSesso("M");
    tutoraz.setTelefono("3498761234");
    
    try {
      tutoraz = aziendaService.registraTutorAziendale(tutoraz, azienda.getNome());
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
      utentiService.login(tutoraz.getUsername(), tutoraz.getPassword());
    } catch (UsernameNonEsistenteException | PasswordErrataException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    assertEquals(tutoraz.getUsername(), utentiService.getUtenteAutenticato().getUsername());
    
    utentiService.logout();
    
  }
}
