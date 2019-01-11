package it.unisa.di.ittraining.utente;

/**
* Eccezione lanciata quando i valori dei campi Password e Conferma password non corrispondono.
*
*/
public class PasswordNonCorrispondentiException extends Exception {

  /**
  * Numero seriale.
  */
  private static final long serialVersionUID = 6429064271308527098L;

  /** Stringa che definisce il messaggio di default utilizzato nell'eccezione. */
  private static final String messaggioDefault = "Le email non corrispondono";

  /**
  * Costruisce un'eccezione che ha come messaggio {@link #messaggioDefault}.
  */
  public PasswordNonCorrispondentiException() {
    super(messaggioDefault);
  }

  /**
  * Costruisce un'eccezione che ha come messaggio la stringa specificata come parametro.
  * 
  * @param messaggio Stringa che rappresenta il messaggio da mostrare
  */
  public PasswordNonCorrispondentiException(String messaggio) {
    super(messaggio);
  }
}
