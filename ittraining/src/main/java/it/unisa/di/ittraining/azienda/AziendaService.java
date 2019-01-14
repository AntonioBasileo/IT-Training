package it.unisa.di.ittraining.azienda;

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
import it.unisa.di.ittraining.utente.UsernameEsistenteException;
import it.unisa.di.ittraining.utente.UsernameNonValidoException;
import it.unisa.di.ittraining.utente.Utente;
import it.unisa.di.ittraining.utente.UtenteRepository;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AziendaService {

  @Autowired

  private TutorAziendaleRepository repTutor;

  @Autowired

  private AziendaRepository repAzienda;

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

  public List<Azienda> elancaAziende() {

    return repAzienda.findAll();
  }

  /**
  * Questo metodo permette di validare lato server i campi 
  * della registrazione inseriti dal tutor e di inserirlo
  * nel database.
  */
  @Transactional(rollbackFor = Exception.class)

  public TutorAziendale registraTutorAziendale(TutorAziendale tutor, String nomeAzienda) 
      throws NomeNonValidoException, NomeCognomeTroppoLungoException,
      NomeCognomeTroppoCortoException,
      CognomeNonValidoException, EmailNonValidaException,
      EmailEsistenteException, EmailNonAssociataException,
      UsernameNonValidoException, UsernameEsistenteException,
      PasswordNonValidaException, PasswordNonCorrispondentiException,
      DataDiNascitaNonValidaException, AziendaNonValidaException,
      AziendaEsistenteException, SessoNonValidoException,
      it.unisa.di.ittraining.azienda.TelefonoNonValidoException, AziendaNonEsistenteException {

    tutor.setNome(validaNomeTutor(tutor.getNome()));
    tutor.setCognome(validaCognome(tutor.getCognome()));
    tutor.setEmail(validaEmailTutor(nomeAzienda, tutor.getEmail()));
    tutor.setUsername(validaUsername(tutor.getUsername()));
    tutor.setDataDiNascita(validaDataDiNascita(tutor.getDataDiNascita()));
    tutor.setPassword(validaPassword(tutor.getPassword()));
    tutor.setAzienda(repAzienda.findByNome(nomeAzienda));
    tutor.setSesso(validaSesso(tutor.getSesso()));
    tutor.setTelefono(validaTelefono(tutor.getTelefono()));

    tutor = repTutor.save(tutor);

    return tutor;
  }


  /**
 * Questo metodo permette di validare lato server i campi
 * inseriti dall'impiegato di segreteria
 * nella sezione adibita ad aggiungere una convenzione
 * e ad aggiungerla nel Database.
 */
  @Transactional(rollbackFor = Exception.class)

  public Azienda registraAzienda(Azienda azienda) throws AziendaNonValidaException,
      AziendaEsistenteException,
      SedeNonValidaException, IndirizzoNonValidoException,
      EmailNonValidaException, EmailAziendaEsistenteException,
      it.unisa.di.ittraining.azienda.TelefonoNonValidoException {

    azienda.setNome(validaNome(azienda.getNome()));
    azienda.setSede(validaSede(azienda.getSede()));
    azienda.setIndirizzo(validaIndirizzo(azienda.getIndirizzo()));
    azienda.setEmail(validaEmailAziendale(azienda.getNome(), azienda.getEmail()));
    azienda.setTelefono(validaTelefono(azienda.getTelefono()));

    azienda = repAzienda.save(azienda);

    return azienda;
  }

  /*Gestione azienda*/

  /**
 * Questo metodo permette di validare il nome dell'azienda.
 */
  public String validaNome(String nome) throws AziendaNonValidaException,
      AziendaEsistenteException {
    if (nome == null) {
      throw new AziendaNonValidaException("Il campo azienda non può essere nullo");
    }

    if (nome.length() > Azienda.MAX_LUNGHEZZA_NOME || nome.length() < Azienda.MIN_LUNGHEZZA_NOME) {
      throw new AziendaNonValidaException();
    }

    if (repAzienda.existsByNome(nome)) {
      throw new AziendaEsistenteException("L'azienda indicata è già esistente");
    }

    return nome;
  }

  /**
 * Questo metodo permette di validare il nome dell'azienda e verificare
 * se l'azienda del tutor alla quale dice di appartenere,
 * esiste realmente.
 */
  public String validaNomeForTutor(String nome) throws AziendaNonValidaException,
      AziendaNonEsistenteException {
 
    if (nome == null) {
      throw new AziendaNonValidaException("Il campo azienda non può essere nullo");
    }

    if (nome.length() > Azienda.MAX_LUNGHEZZA_NOME || nome.length() < Azienda.MIN_LUNGHEZZA_NOME) {
      throw new AziendaNonValidaException();
    }

    if (!repAzienda.existsByNome(nome)) {
      throw new AziendaNonEsistenteException("L'azienda indicata non è esistente");
    }

    return nome;
  }

  /**
 * Questo di validare l'email dell'azienda.
 */
  public String validaEmailAziendale(String nomeAzienda, String email) 
      throws EmailNonValidaException, 
      EmailAziendaEsistenteException {

    if (email == null) {
      throw new EmailNonValidaException();
    }

    if (!email.matches(Azienda.EMAIL_PATTERN_AZIENDALE)) {
      throw 
        new 
          EmailNonValidaException("AZIENDA: l'email non rispetta il formato indicato");
    }

    if (repAzienda.existsByEmail(email)) {
      throw new EmailAziendaEsistenteException("Email utilizzata già da un altro ente");
    }

    return email;
  }
  
  /**
  * Questo metodo permette di validare il campo telefono dell'azienda.
  */
  public String validaTelefono(String telefono) 
      throws it.unisa.di.ittraining.azienda.TelefonoNonValidoException {

    if (telefono == null) {
      throw new it.unisa.di.ittraining.azienda.TelefonoNonValidoException();
    }

    if (!telefono.matches(Azienda.TELEFONO_PATTERN)) {
      throw new it.unisa.di.ittraining.azienda.TelefonoNonValidoException();
    }

    return telefono;
  }

  /**
  * Questo metodo permette di validare il campo indirizzo dell'azienda.
  */
  public String validaIndirizzo(String indirizzo) throws IndirizzoNonValidoException {

    if (indirizzo == null) {
      throw new IndirizzoNonValidoException("Il campo indirizzo non può essere nullo");
    }

    if (indirizzo.length() < Azienda.MIN_LUNGHEZZA_INDIRIZZO
        || indirizzo.length() > Azienda.MAX_LUNGHEZZA_INDIRIZZO) {
      throw new IndirizzoNonValidoException("Il campo indirizzo non è valido.");
    }

    return indirizzo;
  }

  /**
  * Questo metodo permette di validare il campo sede dell'azienda.
  */
  public String validaSede(String sede) throws SedeNonValidaException {

    if (sede == null) {
      throw new SedeNonValidaException("Il campo sede non può essere nullo.");
    }

    if (sede.length() < Azienda.MIN_LUNGHEZZA_SEDE || sede.length() > Azienda.MAX_LUNGHEZZA_SEDE) {
      throw new SedeNonValidaException("Il campo sede non è valido.");
    }

    return sede;
  }

  /*Gestione tutor*/

  /**
  * Questo metodo permette di validare il campo nome del
  * tutor aziendale.
  */
  public String validaNomeTutor(String nome) throws NomeNonValidoException,
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
  * Questo metodo permette di validare il campo cognome del
  * tutor aziendale.
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
  * Questo metodo permette di validare il campo sesso del
  * tutor aziendale.
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
  * Questo metodo permette di validare il campo data di nascita del
  * tutor aziendale.
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
  * Questo metodo permette di validare il campo username del
  * tutor aziendale.
  */
  public String validaUsername(String username) 
      throws UsernameNonValidoException,
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
  * Questo metodo permette di validare l'email del tutor aziendale
  * verificando se l'email inserita combacia con l'azienda indicata
  * e se è già associata ad un altro tutor aziendale.
  */
  public boolean validaPasswords(String password, String confirmPassword) 
      throws PasswordNonValidaException,
      PasswordNonCorrispondentiException {

    if (password == null || confirmPassword == null) {
      throw new PasswordNonValidaException("I campi password sono nulli");
    }

    if (!password.matches(Utente.PASSWORD_PATTERN) 
        || !confirmPassword.matches(Utente.PASSWORD_PATTERN)) {

      throw new PasswordNonValidaException("Le passwords non rispettano il formato previsto");
    }

    if (!password.equals(confirmPassword)) {
      throw new PasswordNonCorrispondentiException("Le due password non corrispondono");
    }

    return true;
  }

  /**
  * Questo metodo permette di validare l'email del tutor aziendale
  * verificando se l'email inserita combacia con l'azienda indicata
  * e se è già associata ad un altro tutor aziendale.
  */
  public String validaPassword(String password) throws PasswordNonValidaException,
      PasswordNonCorrispondentiException {

    if (password == null) {
      throw new PasswordNonValidaException("AZIENDA SERVICE - Il campo password non è valido");
    }

    if (!password.matches(Utente.PASSWORD_PATTERN)) {
      throw new PasswordNonValidaException("AZIENDA SERVICE - Non rispecchia il formato");
    }

    return password;
  }

  /**
  * Questo metodo permette di validare l'email del tutor aziendale
  * verificando se l'email inserita combacia con l'azienda indicata
  * e se esiste già un tutor aziendale che ha inserito l'email inserita.
  */
  public String validaEmailTutor(String nomeAzienda, String email) throws EmailNonValidaException,
      EmailEsistenteException, EmailNonAssociataException {

    if (email == null) {
      throw new EmailNonValidaException();
    }

    if (!email.matches(Azienda.EMAIL_PATTERN_AZIENDALE)) {
      throw 
        new 
          EmailNonValidaException("TUTOR-AZ: l'email non rispetta il formato indicato");
    }

    if (repTutor.existsByEmail(email)) {
      throw new EmailEsistenteException("Email utilizzata già da un altro tutor aziendale");
    }

    if (!repAzienda.existsByNomeAndEmail(nomeAzienda, email)) {
      throw new EmailNonAssociataException("L'email non è associata ad alcuna azienda");
    }

    return email;
  }
}
