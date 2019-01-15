package it.unisa.di.ittraining.utenzatest;

import it.unisa.di.ittraining.studente.MatricolaStudenteEsistenteException;
import it.unisa.di.ittraining.studente.MatricolaStudenteNonValidaException;
import it.unisa.di.ittraining.studente.StudentiService;
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
public class ValidaCampiTest {

  @Autowired
  private StudentiService studenteService;
  
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

  
}
