package it.unisa.di.ittraining.utenzatest;

import static org.junit.Assert.assertTrue;

import it.unisa.di.ittraining.azienda.AziendaEsistenteException;
import it.unisa.di.ittraining.azienda.AziendaNonValidaException;
import it.unisa.di.ittraining.azienda.AziendaService;
import it.unisa.di.ittraining.azienda.EmailAziendaEsistenteException;
import it.unisa.di.ittraining.azienda.IndirizzoNonValidoException;
import it.unisa.di.ittraining.azienda.SedeNonValidaException;
import it.unisa.di.ittraining.impiegatosegreteria.ImpiegatoSegreteriaService;
import it.unisa.di.ittraining.studente.MatricolaStudenteEsistenteException;
import it.unisa.di.ittraining.studente.MatricolaStudenteNonValidaException;
import it.unisa.di.ittraining.studente.StudentiService;
import it.unisa.di.ittraining.tutoraccademico.TutorAccademico;
import it.unisa.di.ittraining.tutoraccademico.TutorAccademicoRepository;
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
public class ValidaCampiTest {

  @Autowired
  private StudentiService studenteService;
  
  @Autowired
  private ImpiegatoSegreteriaService impiegatoService;
  
  @Autowired
  private AziendaService tutorService;

  @Autowired
  private TutorAccademicoService tutorAccademicoService;
  
  @Autowired
  private TutorAccademicoRepository tutorAccademicoRep;
  
  private List<TutorAccademico> tutors;
    
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

  @Test
  public void elencaTutorAccademici() {
   
    TutorAccademico tutor = new TutorAccademico();
    tutor.setNome("Carletto");
    tutor.setCognome("Neri");
    tutor.setDataDiNascita(LocalDate.of(1970, Month.AUGUST, 30));
    tutor.setTelefono("1234567898");
    tutor.setEmail("carletto@unisa.it");
    tutor.setUsername("carletto");
    tutor.setPassword("carlo123");
    tutor.setSesso("M");
    
    
    TutorAccademico tutor1 = new TutorAccademico();
    tutor1.setNome("Luigi");
    tutor1.setCognome("rossi");
    tutor1.setDataDiNascita(LocalDate.of(1960, Month.AUGUST, 20));
    tutor1.setTelefono("7658901234");
    tutor1.setEmail("luigi@unisa.it");
    tutor1.setUsername("luigine");
    tutor1.setPassword("luigi123");
    tutor1.setSesso("M");
    
    
    try {
      tutor = tutorAccademicoService.registraTutorAccademico(tutor);
      tutor1 = tutorAccademicoService.registraTutorAccademico(tutor1);
    } catch (NomeNonValidoException | NomeCognomeTroppoLungoException 
         | NomeCognomeTroppoCortoException | CognomeNonValidoException 
         | EmailNonValidaException | EmailEsistenteException 
         | TelefonoNonValidoException | DataDiNascitaNonValidaException 
         | PasswordNonValidaException | PasswordNonCorrispondentiException 
         | SessoNonValidoException | UsernameNonValidoException | UsernameEsistenteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
       
    
    tutors.add(tutor);
    tutors.add(tutor1);
    
    List<TutorAccademico> tutorSalvati = tutorAccademicoService.elencaTutorAccademici();
    
    for (TutorAccademico a: tutors) {
      assertTrue(tutorSalvati.contains(a));
    }
  }
  
}
