package it.unisa.di.ittraining.azienda;

public class SedeNonValidaException extends Exception {

  /**
  *Numero seriale.
  */
  private static final long serialVersionUID = 2709762615869225003L;


  /** Stringa che definisce il messaggio di default utilizzato nell'eccezione. */


  private static final String messaggioDefault = "Sede non valida";


  /**
  * Costruisce un'eccezione che ha come messaggio {@link #messaggioDefault}.
  */

  public SedeNonValidaException() {

    super(messaggioDefault);

  }

  /**
  * Costruisce un'eccezione che ha come messaggio la stringa specificata come parametro.
  * 
  * @param messaggio Stringa che rappresenta il messaggio da mostrare
  */
  public SedeNonValidaException(String messaggio) {
    super(messaggio);
  }

}
