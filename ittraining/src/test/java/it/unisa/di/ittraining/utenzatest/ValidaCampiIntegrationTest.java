package it.unisa.di.ittraining.utenzatest;

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
import it.unisa.di.ittraining.tutoraccademico.TutorAccademicoService;
import it.unisa.di.ittraining.utente.CognomeNonValidoException;
import it.unisa.di.ittraining.utente.DataDiNascitaNonValidaException;
import it.unisa.di.ittraining.utente.EmailEsistenteException;
import it.unisa.di.ittraining.utente.EmailNonValidaException;
import it.unisa.di.ittraining.utente.NomeCognomeTroppoCortoException;
import it.unisa.di.ittraining.utente.NomeCognomeTroppoLungoException;
import it.unisa.di.ittraining.utente.NomeNonValidoException;
import it.unisa.di.ittraining.utente.PasswordNonCorrispondentiException;
import it.unisa.di.ittraining.utente.PasswordNonValidaException;
import it.unisa.di.ittraining.utente.SessoNonValidoException;
import it.unisa.di.ittraining.utente.TelefonoNonValidoException;
import it.unisa.di.ittraining.utente.UsernameEsistenteException;
import it.unisa.di.ittraining.utente.UsernameNonValidoException;

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
  private StudentiService studenteService;
  
  @Autowired
  private ImpiegatoSegreteriaService impiegatoService;
  
  @Autowired
  private AziendaService tutorService;

  @Autowired
  private TutorAccademicoService tutorAccademicoService;
  
  
  //Gestione azienda
  @Test(expected = AziendaEsistenteException.class)
  public void validaNomeAziendaNullo() throws AziendaNonValidaException,
      AziendaEsistenteException, SedeNonValidaException, IndirizzoNonValidoException,
      EmailNonValidaException, EmailAziendaEsistenteException,
      it.unisa.di.ittraining.azienda.TelefonoNonValidoException {

    Azienda azienda = new Azienda();
    azienda.setNome("InovaTech");
    azienda.setIndirizzo("Via Ubaldo Leprino 35");
    azienda.setSede("Caserta");
    azienda.setTelefono("3490876123");
    azienda.setEmail("inovatech@gmail.com");
    
    tutorService.registraAzienda(azienda);
    
    String nomeAzienda = "InovaTech";
    
    tutorService.validaNome(nomeAzienda);
  }
  
  @Test(expected = AziendaNonValidaException.class)
  public void validaNomeAziendaForTutorNullo() throws AziendaNonValidaException,
      AziendaNonEsistenteException {

    String nomeAzienda = null;

    tutorService.validaNomeForTutor(nomeAzienda);
  }
  
  @Test(expected = AziendaNonValidaException.class)
  public void validaNomeAziendaForTutorTroppoLungo() throws AziendaNonValidaException,
      AziendaNonEsistenteException {

    String nomeAzienda = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

    tutorService.validaNomeForTutor(nomeAzienda);
  }
  
  @Test(expected = AziendaNonValidaException.class)
  public void validaNomeAziendaForTutorTroppoCorto() throws AziendaNonValidaException,
      AziendaNonEsistenteException {

    String nomeAzienda = "a";

    tutorService.validaNomeForTutor(nomeAzienda);
  }
  
  @Test(expected = AziendaNonEsistenteException.class)
  public void validaNomeAziendaForTutorNonEsistente() throws AziendaNonValidaException,
      AziendaNonEsistenteException {

    String nomeAzienda = "abocacenter";

    tutorService.validaNomeForTutor(nomeAzienda);
  }
  
  @Test
  public void validaNomeAziendaForTutorSuccesso() throws AziendaNonValidaException,
      AziendaNonEsistenteException, AziendaEsistenteException,
      SedeNonValidaException, IndirizzoNonValidoException, EmailNonValidaException,
      EmailAziendaEsistenteException,
      it.unisa.di.ittraining.azienda.TelefonoNonValidoException {

    Azienda azienda = new Azienda();
    azienda.setNome("Abudabi Transation Center");
    azienda.setIndirizzo("Via Ubaldo Leprino 35");
    azienda.setSede("Caserta");
    azienda.setTelefono("3490876123");
    azienda.setEmail("inovatech-abudabi@gmail.com");
    
    tutorService.registraAzienda(azienda);

    tutorService.validaNomeForTutor("Abudabi Transation Center");
  }
  
  @Test(expected = EmailNonValidaException.class)
  public void validaEmailAziendaleNulla() throws EmailNonValidaException,
      EmailAziendaEsistenteException {

    String email = null;
  
    tutorService.validaEmailAziendale("Abudabi Transation Center", email);

  }
  
  @Test(expected = it.unisa.di.ittraining.azienda.TelefonoNonValidoException.class)
  public void validaTelefonoAziendaleNullo() throws EmailNonValidaException,
      EmailAziendaEsistenteException, it.unisa.di.ittraining.azienda.TelefonoNonValidoException {

    String telefono = null;
  
    tutorService.validaTelefono(telefono);

  }
    
  @Test
  public void validaMatricolaStudente() {
    String matricola = "0512103333";
    
    try {
      studenteService.validaMatricolaStudente(matricola);
    } catch (MatricolaStudenteNonValidaException | MatricolaStudenteEsistenteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test (expected = MatricolaStudenteNonValidaException.class)
  public void validaMatricolaStudenteNonValida() throws MatricolaStudenteNonValidaException, 
      MatricolaStudenteEsistenteException {
    String matricola = "0512103ab";
    studenteService.validaMatricolaStudente(matricola);
  }
  
  @Test (expected = MatricolaStudenteNonValidaException.class)
  public void validaMatricolaStudenteNulla() throws MatricolaStudenteNonValidaException, 
      MatricolaStudenteEsistenteException {
    String matricola = null;
    studenteService.validaMatricolaStudente(matricola);
  }
  
  @Test (expected = MatricolaStudenteEsistenteException.class)
  public void validaMatricolaStudenteEsistente() throws MatricolaStudenteNonValidaException, 
      MatricolaStudenteEsistenteException {

    Studente studente = new Studente();
    studente.setUsername("anninodag88");
    studente.setNome("annino");
    studente.setCognome("d'agosto");
    studente.setSesso("M");
    studente.setMatricola("0512103023");
    studente.setDataDiNascita(LocalDate.of(1993, Month.APRIL, 22));
    studente.setEmail("annino.dagosto@studenti.unisa.it");
    studente.setPassword("123456567799");
    studente.setTelefono("0912345612");
    
    try {
      studente = studenteService.registraStudente(studente);
    } catch (NomeNonValidoException | NomeCognomeTroppoLungoException 
        | NomeCognomeTroppoCortoException
        | CognomeNonValidoException | DataDiNascitaNonValidaException 
        | UsernameNonValidoException
        | UsernameEsistenteException | EmailNonValidaException 
        | EmailEsistenteException | SessoNonValidoException
        | TelefonoNonValidoException | PasswordNonValidaException 
        | PasswordNonCorrispondentiException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    String matricola = "0512103023";
    studenteService.validaMatricolaStudente(matricola);
  }
  
  @Test
  public void validaNomeStudente() {
    String nome = "Elisabetta";
    
    try {
      studenteService.validaNome(nome);
    } catch (NomeNonValidoException | NomeCognomeTroppoLungoException 
         | NomeCognomeTroppoCortoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test (expected = NomeNonValidoException.class)
  public void validaNomeStudenteNullo() throws NomeNonValidoException, 
      NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException {
    String nome = null;
    studenteService.validaNome(nome);
  }
  
  @Test (expected = NomeNonValidoException.class)
  public void validaNomeStudenteNoMatch() throws NomeNonValidoException, 
      NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException {
    String nome = "09893489435398534";
    studenteService.validaNome(nome);
  }
  
  @Test (expected = NomeCognomeTroppoCortoException.class)
  public void validaNomeStudenteTroppoCorto() throws NomeNonValidoException, 
      NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException {
    String nome = "El";
    studenteService.validaNome(nome);
  }
  
  @Test (expected = NomeCognomeTroppoLungoException.class)
  public void validaNomeStudenteTroppoLungo() throws NomeNonValidoException, 
       NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException {
    String nome = "GianframarcangeloMarcantonGiulioFrancescoUgoErmeneg"
        + "ildoasandsajnfosajnfjdnfjdosnfsoajnfaojfsdfsf"
        + "sdfjbskjdfnksjdfdskfsdnfsdfnsldkfndslknfldsfnsldkfnds"
        + "slkdndslkngsknglskngflknglfsknglskgnndao";
    studenteService.validaNome(nome);
  }
  
  @Test
  public void validaCognomeStudente() {
    String cognome = "Basileo";
    
    try {
      studenteService.validaCognome(cognome);
    } catch (CognomeNonValidoException | NomeCognomeTroppoLungoException 
        | NomeCognomeTroppoCortoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test (expected = CognomeNonValidoException.class)
  public void validaCognomeStudenteNullo() throws CognomeNonValidoException {
    String cognome = null;
    
    try {
      studenteService.validaCognome(cognome);
    } catch (NomeCognomeTroppoLungoException 
        | NomeCognomeTroppoCortoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test (expected = NomeCognomeTroppoCortoException.class)
  public void validaCognomeStudenteTroppoCorto() throws CognomeNonValidoException, 
      NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException {
    String cognome = "El";
    studenteService.validaCognome(cognome);
  }
  
  @Test (expected = NomeCognomeTroppoLungoException.class)
  public void validaCognomeStudenteTroppoLungo() throws CognomeNonValidoException, 
       NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException {
    String cognome = "GianframarcangeloMarcantonGiulioFrancescoUgoErmenegildod"
          + "sdkfnsdkfsdpngspkgnkfngòskdnfgskldnfgsdknf"
          + "sgkndsfòkgnsdkfgnsdlfkngldsfngldsfkng"
          + "lkfgnsldkfngsldfnglsdfngldfang";
    studenteService.validaCognome(cognome);
  }
  
  @Test (expected = CognomeNonValidoException.class)
  public void validaCognomeStudenteNoMatch() throws CognomeNonValidoException, 
       NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException {
    String cognome = "ddddddddddddddddddddddddd'apice";
    studenteService.validaCognome(cognome);
  }
  
  @Test
  public void validaEmailStudente() {
    String email = "basileo@studenti.unisa.it";
    
    try {
      studenteService.validaEmailStudente(email);
    } catch (EmailNonValidaException | EmailEsistenteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test (expected = EmailNonValidaException.class)
  public void validaEmailStudenteNonValida() throws EmailNonValidaException, 
      EmailEsistenteException {
    String email = "basileo@unisa.it";
    studenteService.validaEmailStudente(email);
  }
  
  @Test (expected = EmailNonValidaException.class)
  public void validaEmailStudenteNulla() throws EmailNonValidaException, 
      EmailEsistenteException {
    String email = null;
    studenteService.validaEmailStudente(email);
  }

  @Test
  public void validaSessoStudente() {
    String sesso = "F";
    try {
      studenteService.validaSesso(sesso);
    } catch (SessoNonValidoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test (expected = SessoNonValidoException.class)
  public void validaSessoStudenteNonValido() throws SessoNonValidoException {
    String sesso = "Fiore";
    studenteService.validaSesso(sesso);
  }
  
  @Test (expected = SessoNonValidoException.class)
  public void validaSessoStudenteNullo() throws SessoNonValidoException {
    String sesso = null;
    studenteService.validaSesso(sesso);
  }
  
  @Test
  public void validaDataDiNascitaStudente() {
    LocalDate data = LocalDate.of(1996, Month.JANUARY, 2);
    try {
      studenteService.validaDataDiNascita(data);
    } catch (DataDiNascitaNonValidaException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Test (expected = DataDiNascitaNonValidaException.class)
  public void validaDataDiNascitaStudenteNonValidaMin() throws DataDiNascitaNonValidaException {
    LocalDate data = LocalDate.of(2005, Month.JANUARY, 2);
    studenteService.validaDataDiNascita(data);
  }

  @Test (expected = DataDiNascitaNonValidaException.class)
  public void validaDataDiNascitaStudenteNulla() throws DataDiNascitaNonValidaException {
    LocalDate data = null;
    studenteService.validaDataDiNascita(data);
  }
  
  @Test (expected = DataDiNascitaNonValidaException.class)
  public void validaDataDiNascitaStudenteNonValidaMax() throws DataDiNascitaNonValidaException {
    LocalDate data = LocalDate.of(1800, Month.JANUARY, 2);
    studenteService.validaDataDiNascita(data);
  }

  @Test
  public void validaTelefonoStudente() {
    String telefono = "3313131331";
    try {
      studenteService.validaTelefono(telefono);
    } catch (TelefonoNonValidoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test (expected = TelefonoNonValidoException.class)
  public void validaTelefonoStudenteNonValido() throws TelefonoNonValidoException {
    String telefono = "abcabc";
    studenteService.validaTelefono(telefono);
  }
  
  @Test (expected = TelefonoNonValidoException.class)
  public void validaTelefonoStudenteNullo() throws TelefonoNonValidoException {
    String telefono = null;
    studenteService.validaTelefono(telefono);
  }
  
  @Test
  public void validaUsernameStudente() {
    String username = "Christian";
    try {
      studenteService.validaUsername(username);
    } catch (UsernameNonValidoException | UsernameEsistenteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test (expected = UsernameNonValidoException.class)
  public void validaUsernameStudenteNonValido() throws UsernameNonValidoException, 
      UsernameEsistenteException {
    String username = "Christian_12";
    studenteService.validaUsername(username);
  }
  
  @Test (expected = UsernameNonValidoException.class)
  public void validaUsernameStudenteNullo() throws UsernameNonValidoException, 
      UsernameEsistenteException {
    String username = null;
    studenteService.validaUsername(username);
  }
  
  @Test
  public void validaPasswordAndConfirmPasswordStudente() {
    String password = "password";
    String confermaPass = "password";
    try {
      studenteService.validaPasswords(password, confermaPass);
    } catch (PasswordNonValidaException | PasswordNonCorrispondentiException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test (expected = PasswordNonCorrispondentiException.class)
  public void validaPasswordStudenteNonCorrispondente() throws PasswordNonValidaException, 
      PasswordNonCorrispondentiException {
    String password = "password";
    String confermaPass = "passwoooord";
    studenteService.validaPasswords(password, confermaPass);
  }
  
  @Test (expected = PasswordNonValidaException.class)
  public void validaPasswordStudenteNoMatchPassword() throws PasswordNonValidaException, 
      PasswordNonCorrispondentiException {
    String password = "p";
    String confermaPass = "passwoooord";
    studenteService.validaPasswords(password, confermaPass);
  }
  
  @Test (expected = PasswordNonValidaException.class)
  public void validaPasswordStudenteNoMatchConfirm() throws PasswordNonValidaException, 
      PasswordNonCorrispondentiException {
    String password = "passwoooord";
    String confermaPass = "p";
    studenteService.validaPasswords(password, confermaPass);
  }
  
  @Test (expected = PasswordNonValidaException.class)
  public void validaPasswordStudentePasswordNulla() throws PasswordNonValidaException, 
      PasswordNonCorrispondentiException {
    String password = null;
    String confermaPass = "passwoooord";
    studenteService.validaPasswords(password, confermaPass);
  }
  
  @Test (expected = PasswordNonValidaException.class)
  public void validaPasswordStudenteConfirmPasswordNulla() throws PasswordNonValidaException, 
      PasswordNonCorrispondentiException {
    String password = "passwoooord";
    String confermaPass = null;
    studenteService.validaPasswords(password, confermaPass);
  }
  
  @Test
  public void validaPasswordStudente() {
    String password = "password";
    try {
      studenteService.validaPassword(password);
    } catch (PasswordNonValidaException | PasswordNonCorrispondentiException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test (expected = PasswordNonValidaException.class)
  public void validaPasswordStudenteNonValida() throws PasswordNonValidaException, 
      PasswordNonCorrispondentiException {
    String password = "12";
    studenteService.validaPassword(password);
  }
  
  @Test (expected = PasswordNonValidaException.class)
  public void validaPasswordStudenteNulla() throws PasswordNonValidaException, 
      PasswordNonCorrispondentiException {
    String password = null;
    studenteService.validaPassword(password);
  }

  //Test per i campi di tutor aziendale

  @Test
  public void validaNomeTutorAziendale() {
    String nome = "Lucia";
    
    try {
      tutorService.validaNomeTutor(nome);
    } catch (NomeNonValidoException | NomeCognomeTroppoLungoException 
         | NomeCognomeTroppoCortoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test (expected = NomeCognomeTroppoCortoException.class)
  public void validaNomeTutorAziendaleTroppoCorto() throws NomeNonValidoException, 
      NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException {
    String nome = "lu";
    tutorService.validaNomeTutor(nome);
  }
  
  @Test (expected = NomeNonValidoException.class)
  public void validaNomeTutorAziendaleNullo() throws NomeNonValidoException, 
      NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException {
    String nome = null;
    tutorService.validaNomeTutor(nome);
  }
  
  @Test (expected = NomeNonValidoException.class)
  public void validaNomeTutorAziendaleNoMatch() throws NomeNonValidoException, 
      NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException {
    String nome = "09989324394230";
    tutorService.validaNomeTutor(nome);
  }
  
  @Test (expected = NomeCognomeTroppoLungoException.class)
  public void validaNomeTutorAziendaleTroppoLungo() throws NomeNonValidoException, 
       NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException {
    String nome = "GianframarcangeloMarcantonGiulioFrancescoUgoErmeneg"
        + "ildoasandsajnfosajnfjdnfjdosnfsoajnfaojfsdfsf"
        + "sdfjbskjdfnksjdfdskfsdnfsdfnsldkfndslknfldsfnsldkfnds"
        + "slkdndslkngsknglskngflknglfsknglskgnndao";
    tutorService.validaNomeTutor(nome);
  }
  
  @Test
  public void validaCognomeTutorAziendale() {
    String cognome = "Del piero";
    
    try {
      tutorService.validaCognome(cognome);
    } catch (CognomeNonValidoException | NomeCognomeTroppoLungoException 
        | NomeCognomeTroppoCortoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test (expected = CognomeNonValidoException.class)
  public void validaomeTutorAziendaleTroppoLungo() throws NomeNonValidoException, 
       NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException,
       CognomeNonValidoException {

    String cognome = null;
    tutorService.validaCognome(cognome);
  }
  
  @Test (expected = CognomeNonValidoException.class)
  public void validaomeTutorAziendaleNoMatch() throws NomeNonValidoException, 
       NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException,
       CognomeNonValidoException {

    String cognome = "ddddddddd'''''''''''''''arge";
    tutorService.validaCognome(cognome);
  }
  
  @Test (expected = NomeCognomeTroppoCortoException.class)
  public void validaCognomeTutorAziendaleTroppoCorto() throws CognomeNonValidoException, 
      NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException {
    String cognome = "ro";
    tutorService.validaCognome(cognome);
  }
  
  @Test (expected = NomeCognomeTroppoLungoException.class)
  public void validaCognomeTutorAziendaleTroppoLungo() throws CognomeNonValidoException, 
       NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException {
    String cognome = "GianframarcangeloMarcantonGiulioFrancescoUgoErmenegildod"
          + "sdkfnsdkfsdpngspkgnkfngòskdnfgskldnfgsdknf"
          + "sgkndsfòkgnsdkfgnsdlfkngldsfngldsfkng"
          + "lkfgnsldkfngsldfnglsdfngldfang";
    tutorService.validaCognome(cognome);
  }
  
 
  @Test
  public void validaSessoTutorAziendale() {
    String sesso = "F";
    try {
      tutorService.validaSesso(sesso);
    } catch (SessoNonValidoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test (expected = SessoNonValidoException.class)
  public void validaSessoTutorAziendaleNonValido() throws SessoNonValidoException {
    String sesso = "Fiore";
    tutorService.validaSesso(sesso);
  }
  
  @Test (expected = SessoNonValidoException.class)
  public void validaSessoTutorAziendaleNullo() throws SessoNonValidoException {
    String sesso = null;
    tutorService.validaSesso(sesso);
  }
  
  @Test
  public void validaDataDiNascitaTutorAziendale() {
    LocalDate data = LocalDate.of(1980, Month.JANUARY, 2);
    try {
      tutorService.validaDataDiNascita(data);
    } catch (DataDiNascitaNonValidaException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Test (expected = DataDiNascitaNonValidaException.class)
  public void validaDataDiNascitaTutorAziendaleNonValidaMin() 
       throws DataDiNascitaNonValidaException {
    LocalDate data = LocalDate.of(2005, Month.JANUARY, 2);
    tutorService.validaDataDiNascita(data);
  }

  @Test (expected = DataDiNascitaNonValidaException.class)
  public void validaDataDiNascitaTutorAziendaleNulla() 
       throws DataDiNascitaNonValidaException {
    LocalDate data = null;
    tutorService.validaDataDiNascita(data);
  }
  
  @Test (expected = DataDiNascitaNonValidaException.class)
  public void validaDataDiNascitaTutorAziendaleNonValidaMax() 
       throws DataDiNascitaNonValidaException {
    LocalDate data = LocalDate.of(1800, Month.JANUARY, 2);
    tutorService.validaDataDiNascita(data);
  }

  @Test
  public void validaTelefonoTutorAziendale() {
    String telefono = "3313131331";
    try {
      tutorService.validaTelefono(telefono);
    } catch (it.unisa.di.ittraining.azienda.TelefonoNonValidoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test (expected = it.unisa.di.ittraining.azienda.TelefonoNonValidoException.class)
  public void validaTelefonoTutorAziendaleNonValido() 
      throws it.unisa.di.ittraining.azienda.TelefonoNonValidoException {
    String telefono = "abcabc";
    tutorService.validaTelefono(telefono);
  }
  
  @Test
  public void validaUsernameTutorAziendale() {
    String username = "Carmine";
    try {
      tutorService.validaUsername(username);
    } catch (UsernameNonValidoException | UsernameEsistenteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test (expected = UsernameNonValidoException.class)
  public void validaUsernameTutorAziendaleNonValido() throws UsernameNonValidoException, 
      UsernameEsistenteException {
    String username = "Carmine_12";
    tutorService.validaUsername(username);
  }
  
  @Test (expected = UsernameEsistenteException.class)
  public void validaUsernameTutorAziendaleEsistente() throws UsernameNonValidoException, 
      UsernameEsistenteException, AziendaNonValidaException,
      AziendaEsistenteException, SedeNonValidaException, IndirizzoNonValidoException,
      EmailNonValidaException, EmailAziendaEsistenteException,
      it.unisa.di.ittraining.azienda.TelefonoNonValidoException,
      NomeNonValidoException, NomeCognomeTroppoLungoException,
      NomeCognomeTroppoCortoException, CognomeNonValidoException,
      EmailEsistenteException, EmailNonAssociataException, PasswordNonValidaException,
      PasswordNonCorrispondentiException, DataDiNascitaNonValidaException,
      SessoNonValidoException, AziendaNonEsistenteException {
  
    Azienda azienda = new Azienda();
    azienda.setNome("Ipernovatechnology");
    azienda.setIndirizzo("Via dei Fratelli 82");
    azienda.setSede("Milano");
    azienda.setTelefono("3278765123");
    azienda.setEmail("ipernova.technology@gmail.com");
    
    tutorService.registraAzienda(azienda);
    
    TutorAziendale tutor = new TutorAziendale();
    tutor.setNome("elide");
    tutor.setCognome("pugliese");
    tutor.setEmail(azienda.getEmail());
    tutor.setUsername("ironman");
    tutor.setPassword("ironman");
    tutor.setTelefono("3512695340");
    tutor.setSesso("F");
    tutor.setDataDiNascita(LocalDate.of(1975, Month.JANUARY, 13));
    
    tutorService.registraTutorAziendale(tutor, azienda.getNome());
    
    String username = "ironman";
    tutorService.validaUsername(username);
  }
  
  @Test (expected = UsernameNonValidoException.class)
  public void validaUsernameTutorAziendaleNullo() throws UsernameNonValidoException, 
      UsernameEsistenteException {
    String username = null;
    tutorService.validaUsername(username);
  }
  
  @Test
  public void validaPasswordAndConfirmPasswordTutorAziendale() {
    String password = "carmine";
    String confermaPass = "carmine";
    try {
      tutorService.validaPasswords(password, confermaPass);
    } catch (PasswordNonValidaException | PasswordNonCorrispondentiException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test (expected = PasswordNonCorrispondentiException.class)
  public void validaPasswordTutorAziendaleNonCorrispondente() 
        throws PasswordNonValidaException, 
      PasswordNonCorrispondentiException {
    String password = "carmine";
    String confermaPass = "filippooooo";
    tutorService.validaPasswords(password, confermaPass);
  }
  
  @Test (expected = PasswordNonValidaException.class)
  public void validaPasswordsTutorAziendaleNulla() 
        throws PasswordNonValidaException, 
      PasswordNonCorrispondentiException {
    String password = null;
    String confermaPass = "filippooooo";
    tutorService.validaPasswords(password, confermaPass);
  }
  
  @Test (expected = PasswordNonValidaException.class)
  public void validaConfirmPasswordTutorAziendaleNulla() 
        throws PasswordNonValidaException, 
      PasswordNonCorrispondentiException {
    String password = "carmine";
    String confermaPass = null;
    tutorService.validaPasswords(password, confermaPass);
  }
  
  @Test (expected = PasswordNonValidaException.class)
  public void validaConfirmPasswordTutorAziendaleNoMatch() 
        throws PasswordNonValidaException, 
      PasswordNonCorrispondentiException {
    String password = "carmine";
    String confermaPass = "c";
    tutorService.validaPasswords(password, confermaPass);
  }
  
  @Test (expected = PasswordNonValidaException.class)
  public void validaPasswordTutorAziendaleNoMatch() 
        throws PasswordNonValidaException, 
      PasswordNonCorrispondentiException {
    String password = "c";
    String confermaPass = "carmine";
    tutorService.validaPasswords(password, confermaPass);
  }
  
  @Test
  public void validaPasswordTutorAziendale() {
    String password = "carmine";
    try {
      tutorService.validaPassword(password);
    } catch (PasswordNonValidaException | PasswordNonCorrispondentiException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test (expected = PasswordNonValidaException.class)
  public void validaPasswordTutorAziendaleNonValida() throws PasswordNonValidaException, 
      PasswordNonCorrispondentiException {
    String password = "ci";
    tutorService.validaPassword(password);
  }
  
  @Test (expected = PasswordNonValidaException.class)
  public void validaPasswordTutorAziendaleNulla() throws PasswordNonValidaException, 
      PasswordNonCorrispondentiException {
    String password = null;
    tutorService.validaPassword(password);
  }
  
  @Test (expected = EmailNonValidaException.class)
  public void validaEmailTutorAziendaleNulla() throws EmailNonValidaException,
      EmailEsistenteException, EmailNonAssociataException {
    
    String email = null;
    tutorService.validaEmailTutor("Assistenza informatica", email);
  }
  
  @Test (expected = EmailNonValidaException.class)
  public void validaEmailTutorAziendaleNoMatch() throws EmailNonValidaException,
      EmailEsistenteException, EmailNonAssociataException {
    
    String email = "ababab@@@@@@@@@gmail....com";
    tutorService.validaEmailTutor("Assistenza informatica", email);
  }
  
  @Test (expected = EmailEsistenteException.class)
  public void validaEmailTutorAziendaleEsistente() throws UsernameNonValidoException, 
      UsernameEsistenteException, AziendaNonValidaException,
      AziendaEsistenteException, SedeNonValidaException, IndirizzoNonValidoException,
      EmailNonValidaException, EmailAziendaEsistenteException,
      it.unisa.di.ittraining.azienda.TelefonoNonValidoException,
      NomeNonValidoException, NomeCognomeTroppoLungoException,
      NomeCognomeTroppoCortoException, CognomeNonValidoException,
      EmailEsistenteException, EmailNonAssociataException, PasswordNonValidaException,
      PasswordNonCorrispondentiException, DataDiNascitaNonValidaException,
      SessoNonValidoException, AziendaNonEsistenteException {
  
    Azienda azienda = new Azienda();
    azienda.setNome("Borderlinetechnology");
    azienda.setIndirizzo("Via dei Fratelli 82");
    azienda.setSede("Milano");
    azienda.setTelefono("3278765123");
    azienda.setEmail("borderline.technology@gmail.com");
    
    tutorService.registraAzienda(azienda);
    
    TutorAziendale tutor = new TutorAziendale();
    tutor.setNome("martina");
    tutor.setCognome("di micco");
    tutor.setEmail(azienda.getEmail());
    tutor.setUsername("irondimicco");
    tutor.setPassword("irondimicco");
    tutor.setTelefono("3512695340");
    tutor.setSesso("F");
    tutor.setDataDiNascita(LocalDate.of(1980, Month.MARCH, 7));
    
    tutorService.registraTutorAziendale(tutor, azienda.getNome());
    
    String email = "borderline.technology@gmail.com";
    tutorService.validaEmailTutor(azienda.getNome(), email);
  }
  
  //Test per i campi segreteria
  
  @Test 
  public void validaEmailImpiegatoSegreteria()  {
    String email = "franco@unisa.it";
    try {
      impiegatoService.validaEmailImpiegato(email);
    } catch (EmailNonValidaException | EmailEsistenteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test
  public void validaNomeImpiegatoSegreteria() {
    String nome = "Giulia";
    
    try {
      impiegatoService.validaNome(nome);
    } catch (NomeNonValidoException | NomeCognomeTroppoLungoException 
         | NomeCognomeTroppoCortoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test (expected = NomeNonValidoException.class)
  public void validaNomeImpiegatoSegreteriaNullo() throws NomeNonValidoException, 
      NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException {
    String nome = null;
    impiegatoService.validaNome(nome);
  }
  
  @Test (expected = NomeNonValidoException.class)
  public void validaNomeImpiegatoSegreteriaNoMatch() throws NomeNonValidoException, 
      NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException {
    String nome = "dddddddddddddddddd'''urge";
    impiegatoService.validaNome(nome);
  }
  
  @Test (expected = NomeCognomeTroppoCortoException.class)
  public void validaNomeImpiegatoSegreteriaTroppoCorto() throws NomeNonValidoException, 
      NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException {
    String nome = "gi";
    impiegatoService.validaNome(nome);
  }
  
  @Test (expected = NomeCognomeTroppoLungoException.class)
  public void validaNomeImpiegatoSegreteriaTroppoLungo() throws NomeNonValidoException, 
       NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException {
    String nome = "GianframarcangeloMarcantonGiulioFrancescoUgoErmeneg"
        + "ildoasandsajnfosajnfjdnfjdosnfsoajnfaojfsdfsf"
        + "sdfjbskjdfnksjdfdskfsdnfsdfnsldkfndslknfldsfnsldkfnds"
        + "slkdndslkngsknglskngflknglfsknglskgnndao";
    impiegatoService.validaNome(nome);
  }
  
  @Test
  public void validaCognomeImpiegatoSegreteria() {
    String cognome = "Rossettini";
    
    try {
      impiegatoService.validaCognome(cognome);
    } catch (CognomeNonValidoException | NomeCognomeTroppoLungoException 
        | NomeCognomeTroppoCortoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test (expected = CognomeNonValidoException.class)
  public void validaCognomeImpiegatoSegreteriaNullo() throws CognomeNonValidoException, 
      NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException {
    String cognome = null;
    impiegatoService.validaCognome(cognome);
  }
  
  @Test (expected = CognomeNonValidoException.class)
  public void validaCognomeImpiegatoSegreteriaNoMatch() throws CognomeNonValidoException, 
      NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException {
    String cognome = "dddddddd'''''''arge";
    impiegatoService.validaCognome(cognome);
  }
  
  @Test (expected = NomeCognomeTroppoCortoException.class)
  public void validaCognomeImpiegatoSegreteriaTroppoCorto() throws CognomeNonValidoException, 
      NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException {
    String cognome = "ro";
    impiegatoService.validaCognome(cognome);
  }
  
  @Test (expected = NomeCognomeTroppoLungoException.class)
  public void validaCognomeImpiegatoSegreteriaTroppoLungo() throws CognomeNonValidoException, 
       NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException {
    String cognome = "GianframarcangeloMarcantonGiulioFrancescoUgoErmenegildod"
          + "sdkfnsdkfsdpngspkgnkfngòskdnfgskldnfgsdknf"
          + "sgkndsfòkgnsdkfgnsdlfkngldsfngldsfkng"
          + "lkfgnsldkfngsldfnglsdfngldfang";
    impiegatoService.validaCognome(cognome);
  }
  
 
  @Test
  public void validaSessoImpiegatoSegreteria() {
    String sesso = "F";
    try {
      impiegatoService.validaSesso(sesso);
    } catch (SessoNonValidoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test (expected = SessoNonValidoException.class)
  public void validaSessoImpiegatoSegreteriaNonValido() throws SessoNonValidoException {
    String sesso = "Fiore";
    impiegatoService.validaSesso(sesso);
  }
  
  @Test (expected = SessoNonValidoException.class)
  public void validaSessoImpiegatoSegreteriaNullo() throws SessoNonValidoException {
    String sesso = null;
    impiegatoService.validaSesso(sesso);
  }
  
  @Test
  public void validaDataDiNascitaImpiegatoSegreteria() {
    LocalDate data = LocalDate.of(1980, Month.JANUARY, 2);
    try {
      impiegatoService.validaDataDiNascita(data);
    } catch (DataDiNascitaNonValidaException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Test (expected = DataDiNascitaNonValidaException.class)
  public void validaDataDiNascitaImpiegatoSegreteriaNonValidaMin() 
       throws DataDiNascitaNonValidaException {
    LocalDate data = LocalDate.of(2005, Month.JANUARY, 2);
    impiegatoService.validaDataDiNascita(data);
  }

  @Test (expected = DataDiNascitaNonValidaException.class)
  public void validaDataDiNascitaImpiegatoSegreteriaNulla() 
       throws DataDiNascitaNonValidaException {
    LocalDate data = null;
    impiegatoService.validaDataDiNascita(data);
  }
  
  @Test (expected = DataDiNascitaNonValidaException.class)
  public void validaDataDiNascitaImpiegatoSegreteriaNonValidaMax() 
       throws DataDiNascitaNonValidaException {
    LocalDate data = LocalDate.of(1800, Month.JANUARY, 2);
    impiegatoService.validaDataDiNascita(data);
  }

  @Test
  public void validaTelefonoImpiegatoSegreteria() {
    String telefono = "3313131331";
    try {
      impiegatoService.validaTelefono(telefono);
    } catch (TelefonoNonValidoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test (expected = TelefonoNonValidoException.class)
  public void validaTelefonoImpiegatoSegreteriaNonValido() throws TelefonoNonValidoException {
    String telefono = "abcabc";
    impiegatoService.validaTelefono(telefono);
  }
  
  @Test (expected = TelefonoNonValidoException.class)
  public void validaTelefonoImpiegatoSegreteriaNullo() throws TelefonoNonValidoException {
    String telefono = null;
    impiegatoService.validaTelefono(telefono);
  }
  
  @Test
  public void validaUsernameImpiegatoSegreteria() {
    String username = "Filippo";
    try {
      impiegatoService.validaUsername(username);
    } catch (UsernameNonValidoException | UsernameEsistenteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test (expected = UsernameNonValidoException.class)
  public void validaUsernameImpiegatoSegreteriaNonValido() throws UsernameNonValidoException, 
      UsernameEsistenteException {
    String username = "Filippo_12";
    impiegatoService.validaUsername(username);
  }
  
  @Test (expected = UsernameNonValidoException.class)
  public void validaUsernameImpiegatoSegreteriaNullo() throws UsernameNonValidoException, 
      UsernameEsistenteException {
    String username = null;
    impiegatoService.validaUsername(username);
  }
  
  @Test (expected = UsernameEsistenteException.class)
  public void validaUsernameImpiegatoSegreteriaEsistente() throws UsernameNonValidoException, 
      UsernameEsistenteException {
    ImpiegatoSegreteria impiegato = new ImpiegatoSegreteria();
    impiegato.setNome("nicola");
    impiegato.setCognome("assuntino");
    impiegato.setUsername("nicolaassuntino93");
    impiegato.setPassword("nicolassuntino88");
    impiegato.setEmail("n.assuntino@unisa.it");
    impiegato.setTelefono("3292113456");
    impiegato.setSesso("M");
    impiegato.setDataDiNascita(LocalDate.of(1988, Month.APRIL, 11));
    
    try {
      impiegatoService.registraImpiegato(impiegato);
    } catch (NomeNonValidoException | NomeCognomeTroppoLungoException 
        | NomeCognomeTroppoCortoException
        | CognomeNonValidoException | EmailNonValidaException 
        | EmailEsistenteException | PasswordNonValidaException
        | PasswordNonCorrispondentiException | DataDiNascitaNonValidaException 
        | SessoNonValidoException
        | TelefonoNonValidoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    String username = "nicolaassuntino93";
    impiegatoService.validaUsername(username);
  }
  
  @Test
  public void validaPasswordAndConfirmPasswordImpiegatoSegreteria() {
    String password = "filippo";
    String confermaPass = "filippo";
    try {
      impiegatoService.validaPasswords(password, confermaPass);
    } catch (PasswordNonValidaException | PasswordNonCorrispondentiException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test (expected = PasswordNonCorrispondentiException.class)
  public void validaPasswordImpiegatoSegreteriaNonCorrispondente() 
        throws PasswordNonValidaException, 
      PasswordNonCorrispondentiException {
    String password = "filippo";
    String confermaPass = "filippooooo";
    impiegatoService.validaPasswords(password, confermaPass);
  }
  
  @Test (expected = PasswordNonValidaException.class)
  public void validaPasswordImpiegatoSegreteriaNulla() 
        throws PasswordNonValidaException, 
      PasswordNonCorrispondentiException {
    String password = null;
    String confermaPass = "filippooooo";
    impiegatoService.validaPasswords(password, confermaPass);
  }
  
  @Test (expected = PasswordNonValidaException.class)
  public void validaConfirmImpiegatoSegreteriaNulla() 
        throws PasswordNonValidaException, 
      PasswordNonCorrispondentiException {
    String password = "filippooooo";
    String confermaPass = null;
    impiegatoService.validaPasswords(password, confermaPass);
  }
  
  @Test (expected = PasswordNonValidaException.class)
  public void validaConfirmImpiegatoSegreteriaNoMatch() 
        throws PasswordNonValidaException, 
      PasswordNonCorrispondentiException {
    String password = "filippooooo";
    String confermaPass = "p";
    impiegatoService.validaPasswords(password, confermaPass);
  }
  
  @Test (expected = PasswordNonValidaException.class)
  public void validaPasswordImpiegatoSegreteriaNoMatch() 
        throws PasswordNonValidaException, 
      PasswordNonCorrispondentiException {
    String password = "f";
    String confermaPass = "filippooooo";
    impiegatoService.validaPasswords(password, confermaPass);
  }
  
  @Test (expected = PasswordNonValidaException.class)
  public void validaPasswordImpiegatoSegreteriaSingleNulla() 
        throws PasswordNonValidaException, 
      PasswordNonCorrispondentiException {
    String password = null;
    impiegatoService.validaPassword(password);
  }
  
  @Test
  public void validaPasswordImpiegatoSegreteria() {
    String password = "filippo";
    try {
      impiegatoService.validaPassword(password);
    } catch (PasswordNonValidaException | PasswordNonCorrispondentiException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test (expected = PasswordNonValidaException.class)
  public void validaPasswordImpiegatoSegreteriaNonValida() throws PasswordNonValidaException, 
      PasswordNonCorrispondentiException {
    String password = "ci";
    impiegatoService.validaPassword(password);
  }

  
  @Test (expected = EmailNonValidaException.class)
  public void validaEmailImpiegatoSegreteriaNull() throws EmailNonValidaException, 
      EmailEsistenteException {
    String email = null;
    impiegatoService.validaEmailImpiegato(email);
  }
  
  @Test (expected = EmailNonValidaException.class)
  public void validaEmailImpiegatoSegreteriaNonValida() throws EmailNonValidaException, 
      EmailEsistenteException {
    String email = "filippo@gmail.com";
    impiegatoService.validaEmailImpiegato(email);
  }

  //test per i campi di azienda
  
  @Test (expected = EmailNonValidaException.class)
  public void validaEmailAziendaNonValida() 
      throws EmailNonValidaException, EmailAziendaEsistenteException {
    String email = "filippocom";
    String nomeAzienda = "macsrl";
    tutorService.validaEmailAziendale(nomeAzienda, email);
  }
  
  @Test
  public void validaIndirizzoAzienda() {
    String indirizzo = "via rossi 23";
    try {
      tutorService.validaIndirizzo(indirizzo);
    } catch (IndirizzoNonValidoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test(expected = IndirizzoNonValidoException.class)
  public void validaIndirizzoAziendaNull() throws IndirizzoNonValidoException {
    String indirizzo = null;
    tutorService.validaIndirizzo(indirizzo);
  }
  
  @Test(expected = IndirizzoNonValidoException.class)
  public void validaIndirizzoAziendaNonValidoMin() throws IndirizzoNonValidoException {
    String indirizzo = "v";
    tutorService.validaIndirizzo(indirizzo);
  }
  
  @Test(expected = IndirizzoNonValidoException.class)
  public void validaIndirizzoAziendaNonValidoMax() throws IndirizzoNonValidoException {
    String indirizzo = "vshxbsdckjvfekjvekjevckjwercjkercjhecjerhcvjhecbjherbcwjher"
        + "ahhajhjhsbcjhfvcjhfvcjhfvcjhdbcjhrvejhevfbjhefvbjefbvjhfv" 
        + "hdcbjhfvbekjfvbkjrvbjrvbjhrevbjhrevbjhrfvbhwdbjdwcvwjekfvjk"
        + "ahhajhjhsbcjhfvcjhfvcjhfvcjhdbcjhrvejhevfbjhefvbjefbvjhfv" 
        + "hdcbjhfvbekjfvbkjrvbjrvbjhrevbjhrevbjhrfvbhwdbjdwcvwjekfvjk"
        + "ahhajhjhsbcjhfvcjhfvcjhfvcjhdbcjhrvejhevfbjhefvbjefbvjhfv" 
        + "hdcbjhfvbekjfvbkjrvbjrvbjhrevbjhrevbjhrfvbhwdbjdwcvwjekfvjk"
        + "ahhajhjhsbcjhfvcjhfvcjhfvcjhdbcjhrvejhevfbjhefvbjefbvjhfv" 
        + "hdcbjhfvbekjfvbkjrvbjrvbjhrevbjhrevbjhrfvbhwdbjdwcvwjekfvjk";
    tutorService.validaIndirizzo(indirizzo);
  }
  
  @Test
  public void validaSedeAzienda() {
    String sede = "napoli";
    try {
      tutorService.validaSede(sede);
    } catch (SedeNonValidaException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test(expected = SedeNonValidaException.class)
  public void validaSedeAziendaNull() throws SedeNonValidaException {
    String sede = null;
    tutorService.validaSede(sede);
  }
  
  @Test(expected = SedeNonValidaException.class)
  public void validaSedeAziendaNonValidaMin() throws SedeNonValidaException {
    String sede = "n";
    tutorService.validaSede(sede);
  }
  
  @Test(expected = SedeNonValidaException.class)
  public void validaSedeAziendaNonValidaMax() throws SedeNonValidaException {
    String sede = "vshxbsdckjvfekjvekjevckjwercjkercjhecjerhcvjhecbjherbcwjher"
            + "ahhajhjhsbcjhfvcjhfvcjhfvcjhdbcjhrvejhevfbjhefvbjefbvjhfv" 
            + "hdcbjhfvbekjfvbkjrvbjrvbjhrevbjhrevbjhrfvbhwdbjdwcvwjekfvjk"
            + "ahhajhjhsbcjhfvcjhfvcjhfvcjhdbcjhrvejhevfbjhefvbjefbvjhfv" 
            + "hdcbjhfvbekjfvbkjrvbjrvbjhrevbjhrevbjhrfvbhwdbjdwcvwjekfvjk"
            + "ahhajhjhsbcjhfvcjhfvcjhfvcjhdbcjhrvejhevfbjhefvbjefbvjhfv" 
            + "hdcbjhfvbekjfvbkjrvbjrvbjhrevbjhrevbjhrfvbhwdbjdwcvwjekfvjk"
            + "ahhajhjhsbcjhfvcjhfvcjhfvcjhdbcjhrvejhevfbjhefvbjefbvjhfv" 
            + "hdcbjhfvbekjfvbkjrvbjrvbjhrevbjhrevbjhrfvbhwdbjdwcvwjekfvjk";
    tutorService.validaSede(sede);
  }
  
  @Test(expected = AziendaNonValidaException.class)
  public void validaNomeAziendaNull() throws AziendaNonValidaException, AziendaEsistenteException {
    String nome = null;
    tutorService.validaNome(nome);
  }
  
  @Test
  public void validaNomeAzienda() {
    String nome = "macsrl";
    try {
      tutorService.validaNome(nome);
    } catch (AziendaNonValidaException | AziendaEsistenteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test(expected = AziendaNonValidaException.class)
  public void validaNomeAziendaNonValidoMin()
      throws AziendaNonValidaException, AziendaEsistenteException {
    String nome = "n";
    tutorService.validaNome(nome);
  }
  
  @Test(expected = AziendaNonValidaException.class)
  public void validaNomeAziendaNonValidoMax()
      throws AziendaNonValidaException, AziendaEsistenteException {
    String nome = "vshxbsdckjvfekjvekjevckjwercjkercjhecjerhcvjhecbjherbcwjher"
            + "ahhajhjhsbcjhfvcjhfvcjhfvcjhdbcjhrvejhevfbjhefvbjefbvjhfv" 
            + "hdcbjhfvbekjfvbkjrvbjrvbjhrevbjhrevbjhrfvbhwdbjdwcvwjekfvjk"
            + "ahhajhjhsbcjhfvcjhfvcjhfvcjhdbcjhrvejhevfbjhefvbjefbvjhfv" 
            + "hdcbjhfvbekjfvbkjrvbjrvbjhrevbjhrevbjhrfvbhwdbjdwcvwjekfvjk"
            + "ahhajhjhsbcjhfvcjhfvcjhfvcjhdbcjhrvejhevfbjhefvbjefbvjhfv" 
            + "hdcbjhfvbekjfvbkjrvbjrvbjhrevbjhrevbjhrfvbhwdbjdwcvwjekfvjk"
            + "ahhajhjhsbcjhfvcjhfvcjhfvcjhdbcjhrvejhevfbjhefvbjefbvjhfv" 
            + "hdcbjhfvbekjfvbkjrvbjrvbjhrevbjhrevbjhrfvbhwdbjdwcvwjekfvjk";
    tutorService.validaNome(nome);
  }
  
  
  //Test i campi tutor accademico
  
  @Test
  public void validaNomeTutorAccademico() {
    String nome = "Federico";
    
    try {
      tutorAccademicoService.validaNome(nome);
    } catch (NomeNonValidoException | NomeCognomeTroppoLungoException 
         | NomeCognomeTroppoCortoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test(expected = NomeNonValidoException.class)
  public void validaNomeTutorAccademicoNull() throws NomeNonValidoException,
      NomeCognomeTroppoLungoException,NomeCognomeTroppoCortoException {
    String nome = null;
    tutorAccademicoService.validaNome(nome);
    
  }
  
  @Test(expected = NomeCognomeTroppoCortoException.class)
  public void validaNomeTutorAccademicoNonValidoMin() throws NomeNonValidoException,
      NomeCognomeTroppoLungoException,NomeCognomeTroppoCortoException {
    String nome = "fe";
    tutorAccademicoService.validaNome(nome);
    
  }
  
  @Test(expected = NomeCognomeTroppoLungoException.class)
  public void validaNomeTutorAccademicoNonValidoMax() throws NomeNonValidoException,
      NomeCognomeTroppoLungoException,NomeCognomeTroppoCortoException {
    String nome = "feeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
         + "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
         + "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
         + "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
         + "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
         + "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee";
    tutorAccademicoService.validaNome(nome);
    
  }
  
  @Test(expected = NomeNonValidoException.class)
  public void validaNomeTutorAccademicoNonValido() throws NomeNonValidoException,
      NomeCognomeTroppoLungoException,NomeCognomeTroppoCortoException {
    String nome = "112";
    tutorAccademicoService.validaNome(nome);
    
  }
  
  @Test
  public void validaCognomeTutorAccademico() {
    String cognome = "Federico";
    
    try {
      tutorAccademicoService.validaCognome(cognome);
    } catch (CognomeNonValidoException | NomeCognomeTroppoLungoException 
         | NomeCognomeTroppoCortoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test(expected = CognomeNonValidoException.class)
  public void validaCognomeTutorAccademicoNull() throws CognomeNonValidoException,
      NomeCognomeTroppoLungoException,NomeCognomeTroppoCortoException {
    String cognome = null;
    tutorAccademicoService.validaCognome(cognome);
    
  }
  
  @Test(expected = NomeCognomeTroppoCortoException.class)
  public void validaCognomeTutorAccademicoNonValidoMin() throws CognomeNonValidoException,
      NomeCognomeTroppoLungoException,NomeCognomeTroppoCortoException {
    String cognome = "fe";
    tutorAccademicoService.validaCognome(cognome);
    
  }
  
  @Test(expected = NomeCognomeTroppoLungoException.class)
  public void validaCognomeTutorAccademicoNonValidoMax() throws CognomeNonValidoException,
      NomeCognomeTroppoLungoException,NomeCognomeTroppoCortoException {
    String cognome = "feeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
         + "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
         + "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
         + "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
         + "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
         + "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee";
    tutorAccademicoService.validaCognome(cognome);
    
  }
  
  @Test(expected = CognomeNonValidoException.class)
  public void validaCognomeTutorAccademicoNonValido() throws CognomeNonValidoException,
      NomeCognomeTroppoLungoException,NomeCognomeTroppoCortoException {
    String cognome = "112";
    tutorAccademicoService.validaCognome(cognome);
    
  }
  
  @Test
  public void validaSessoTutorAccademico() {
    String sesso = "F";
    try {
      tutorAccademicoService.validaSesso(sesso);
    } catch (SessoNonValidoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test (expected = SessoNonValidoException.class)
  public void validaSessoTutorAccademicoNonValido() throws SessoNonValidoException {
    String sesso = "Fiore";
    tutorAccademicoService.validaSesso(sesso);
  }
  
  @Test
  public void validaDataDiNascitaTutorAccademico() {
    LocalDate data = LocalDate.of(1960, Month.JANUARY, 2);
    try {
      tutorAccademicoService.validaDataDiNascita(data);
    } catch (DataDiNascitaNonValidaException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Test (expected = DataDiNascitaNonValidaException.class)
  public void validaDataDiNascitaTutorAccademicoNonValidaMin() 
       throws DataDiNascitaNonValidaException {
    LocalDate data = LocalDate.of(2005, Month.JANUARY, 2);
    tutorAccademicoService.validaDataDiNascita(data);
  }
  
  @Test (expected = DataDiNascitaNonValidaException.class)
  public void validaDataDiNascitaTutorAccademicoNonValidaMax() 
       throws DataDiNascitaNonValidaException {
    LocalDate data = LocalDate.of(1800, Month.JANUARY, 2);
    tutorAccademicoService.validaDataDiNascita(data);
  }

  @Test
  public void validaTelefonoTutorAccademico() {
    String telefono = "3313131331";
    try {
      tutorAccademicoService.validaTelefono(telefono);
    } catch (TelefonoNonValidoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test (expected = TelefonoNonValidoException.class)
  public void validaTelefonoTutorAccademicoNonValido() throws TelefonoNonValidoException {
    String telefono = "abcabc";
    tutorAccademicoService.validaTelefono(telefono);
  }
  
  @Test
  public void validaUsernameTutorAccademico() {
    String username = "Paolane";
    try {
      tutorAccademicoService.validaUsername(username);
    } catch (UsernameNonValidoException | UsernameEsistenteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test (expected = UsernameNonValidoException.class)
  public void validaUsernameTutorAccademicoNonValido() throws UsernameNonValidoException, 
      UsernameEsistenteException {
    String username = "paola34!!";
    tutorAccademicoService.validaUsername(username);
  }
  
  @Test
  public void validaPasswordAndConfirmPasswordTutorAccademico() {
    String password = "paola123";
    String confermaPass = "paola123";
    try {
      tutorAccademicoService.validaPasswords(password, confermaPass);
    } catch (PasswordNonValidaException | PasswordNonCorrispondentiException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test (expected = PasswordNonCorrispondentiException.class)
  public void validaPasswordTutorAccademicoNonCorrispondente() 
        throws PasswordNonValidaException, 
      PasswordNonCorrispondentiException {
    String password = "paola123";
    String confermaPass = "paolaaaooo";
    tutorAccademicoService.validaPasswords(password, confermaPass);
  }
  
  @Test
  public void validaPasswordTutorAccademico() {
    String password = "paola123";
    try {
      tutorAccademicoService.validaPassword(password);
    } catch (PasswordNonValidaException | PasswordNonCorrispondentiException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @Test (expected = PasswordNonValidaException.class)
  public void validaPasswordTutorAccademicoNonValida() throws PasswordNonValidaException, 
      PasswordNonCorrispondentiException {
    String password = "ci";
    tutorAccademicoService.validaPassword(password);
  }
  
}
