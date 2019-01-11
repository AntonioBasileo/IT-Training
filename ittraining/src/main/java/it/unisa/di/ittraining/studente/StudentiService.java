package it.unisa.di.ittraining.studente;

import it.unisa.di.ittraining.studente.MatricolaStudenteEsistenteException;
import it.unisa.di.ittraining.studente.MatricolaStudenteNonValidaException;
import it.unisa.di.ittraining.studente.Studente;
import it.unisa.di.ittraining.studente.StudenteRepository;
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
import it.unisa.di.ittraining.utente.Utente;
import it.unisa.di.ittraining.utente.UtenteRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class StudentiService {

  @Autowired
  private StudenteRepository studenteRepository;
  
  @Autowired
  private UtenteRepository utenteRep;

  /** 
  * Costante che rappresenta la minima distanza in anni dalla data corrente 
  * per la data di nascita.
  */
  public static final int MIN_DISTANZA_ANNO_NASCITA = 17;

  /** 
  * Costante che rappresenta la massima distanza in anni dalla data corrente 
  * per la data di nascita.
  */
  public static final int MAX_DISTANZA_ANNO_NASCITA = 130;

  /**
* Permette di validare i campi della registrazione dello studente lato server e
* di inserire lo stesso all'interno del Database.
*/
  @Transactional(rollbackFor = Exception.class)
  public Studente registraStudente(Studente studente) throws NomeNonValidoException,
      NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException, CognomeNonValidoException,
      DataDiNascitaNonValidaException, UsernameNonValidoException, 
      UsernameEsistenteException, EmailNonValidaException, 
      EmailEsistenteException, SessoNonValidoException, TelefonoNonValidoException,
      MatricolaStudenteNonValidaException, MatricolaStudenteEsistenteException,
      PasswordNonValidaException, PasswordNonCorrispondentiException {

    studente.setNome(validaNome(studente.getNome()));
    studente.setCognome(validaCognome(studente.getCognome()));
    studente.setDataDiNascita(validaDataDiNascita(studente.getDataDiNascita()));
    studente.setUsername(validaUsername(studente.getUsername()));
    studente.setEmail(validaEmailStudente(studente.getEmail()));
    studente.setSesso(validaSesso(studente.getSesso()));
    studente.setTelefono(validaTelefono(studente.getTelefono()));
    studente.setMatricola(validaMatricolaStudente(studente.getMatricola()));
    studente.setPassword(validaPassword(studente.getPassword()));

    studente = studenteRepository.save(studente);
    
    return studente;

  }


  /**
* Permette di validare la matricola dello studente.
*/
  public String validaMatricolaStudente(String matricolaStudente)
      throws MatricolaStudenteNonValidaException, MatricolaStudenteEsistenteException {
    if (matricolaStudente == null) {

      throw new MatricolaStudenteNonValidaException();

    } else {

      matricolaStudente = matricolaStudente.trim();

      if (!matricolaStudente.matches(Studente.MATRICOLA_PATTERN)) {
        throw new MatricolaStudenteNonValidaException();

      } else if (studenteRepository.existsByMatricola(matricolaStudente)) {
        throw new MatricolaStudenteEsistenteException();

      } else {

        return matricolaStudente;
      }
    }
  }

  /**
* Permette di validare l'email dello studente.
*/
  public String validaEmailStudente(String email) throws EmailNonValidaException,
      EmailEsistenteException {
    if (email == null) {
      throw new EmailNonValidaException("Il campo email non può essere nullo");
    }

    if (!email.matches(Studente.EMAIL_PATTERN_STUDENTE)) {
      throw new EmailNonValidaException();
    }

    if (studenteRepository.existsByEmail(email)) {
      throw new EmailEsistenteException("L'email è già utilizzata da un altro studente");
    }

    return email;
  }


  /**
* Permette validare il nome dello studente.
*/
  public String validaNome(String nome) throws NomeNonValidoException, 
      NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException {
    if (nome == null) {
      throw new NomeNonValidoException();
    }

    if (nome.length() > Utente.MAX_LUNGHEZZA_NOME) {
      throw new NomeCognomeTroppoLungoException();
    }

    if (nome.length() < Utente.MIN_LUNGHEZZA_NOME) {
      throw new NomeCognomeTroppoCortoException();
    }

    if (!nome.matches(Utente.NOME_PATTERN)) {
      throw new NomeNonValidoException("Il campo nome non rispetta il formato indicato");
    }

    return nome;
  }


  /**
* Permette di validare il cognome dello studente.
*/
  public String validaCognome(String cognome) throws CognomeNonValidoException, 
      NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException {
    if (cognome == null) {
      throw new CognomeNonValidoException();
    }

    if (cognome.length() > Utente.MAX_LUNGHEZZA_NOME) {
      throw new NomeCognomeTroppoLungoException();
    }

    if (cognome.length() < Utente.MIN_LUNGHEZZA_NOME) {
      throw new NomeCognomeTroppoCortoException();
    }

    if (!cognome.matches(Utente.COGNOME_PATTERN)) {
      throw new CognomeNonValidoException("Il cognome non rispetta il formato indicato");
    }

    return cognome;
  }


  /**
* Permette di validare il sesso dello studente.
*/
  public String validaSesso(String sesso) throws SessoNonValidoException {
    if (sesso == null) {
      throw new SessoNonValidoException();

    } else {
      sesso = sesso.trim();

      if (!sesso.equals(Utente.SESSO_MASCHILE)
          && !sesso.equals(Utente.SESSO_FEMMINILE)) {
        throw new SessoNonValidoException();
      } else {
        return sesso;
      }
    }
  }

  /**
* Permette di validare la data di nascita dello studente.
*/
  public LocalDate validaDataDiNascita(LocalDate dataDiNascita) 
      throws DataDiNascitaNonValidaException {

    if (dataDiNascita == null) {
      throw new DataDiNascitaNonValidaException();

    } else {

      LocalDate oggi = LocalDate.now();
      long distanza = ChronoUnit.YEARS.between(dataDiNascita, oggi);

      if (distanza < MIN_DISTANZA_ANNO_NASCITA || distanza > MAX_DISTANZA_ANNO_NASCITA) {
        throw new DataDiNascitaNonValidaException();
      } else {
        return dataDiNascita;
      } 
    }
  }

  /**
* Permette di validare il telefono dello studente.
*/
  public String validaTelefono(String telefono) throws TelefonoNonValidoException {
    if (telefono == null) {
      throw new TelefonoNonValidoException("Il campo telefono non può essere nullo");
    }

    if (!telefono.matches(Utente.TELEFONO_PATTERN)) {
      throw new TelefonoNonValidoException("Il campo telefono non è valido");
    }

    return telefono;
  }

  /**
* Permette di validare lo username dello studente.
*/
  public String validaUsername(String username) throws UsernameNonValidoException,
      UsernameEsistenteException {
    if (username == null) {
      throw new UsernameNonValidoException("Il campo username è nullo");
    } else {
      username = username.trim();

      if (!username.matches(Utente.USERNAME_PATTERN)) {
        throw new UsernameNonValidoException("Lo username non rispetta il formato.");
      }

    }
    if (utenteRep.existsByUsername(username)) {
      throw new UsernameEsistenteException("Username già utilizzato.");
    }

    return username;

  }

  /**
* Permette di verificare se il campo password e conferma password
* hanno lo stesso valore.
*/
  public boolean validaPasswords(String password, String confirmPassword)
      throws PasswordNonValidaException,PasswordNonCorrispondentiException {
    if (password == null || confirmPassword == null) {
      throw new PasswordNonValidaException(
     "Il campo password oppure il campo conferma password sono nulli");
    }

    if (!password.matches(Utente.PASSWORD_PATTERN) 
        || !confirmPassword.matches(Utente.PASSWORD_PATTERN)) {
      throw new PasswordNonValidaException(
      "Il campo password oppure il campo conferma password non rispettano il formato previsto");
    }

    if (!password.equals(confirmPassword)) {
      throw new PasswordNonCorrispondentiException("Le due password non corrispondono");
    }

    return true;
  }

  /**
* Permette di validare la password dello studente.
*/
  public String validaPassword(String password) 
      throws PasswordNonValidaException, PasswordNonCorrispondentiException {
    if (password == null) {
      throw new PasswordNonValidaException("STUDENTE SERVICE - Il campo password non è valido");
    }

    if (!password.matches(Utente.PASSWORD_PATTERN)) {
      throw new PasswordNonValidaException("STUDENTE SERVICE - Non rispecchia il formato");
    }

    return password;
  }

}
