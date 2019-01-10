package it.unisa.di.ittraining.azienda;

public class IndirizzoNonValidoException extends Exception {

  /**
  * Numero seriale.
  */
  private static final long serialVersionUID = -8627543952113616402L;

  /** Stringa che definisce il messaggio di default utilizzato nell'eccezione. */
  private static final String messaggioDefault = "Indirizzo non valido";

  /**
  * Costruisce un'eccezione che ha come messaggio {@link #messaggioDefault}.
  */
  public IndirizzoNonValidoException() {
    super(messaggioDefault);
  }

  /**
  * Costruisce un'eccezione che ha come messaggio la stringa specificata come parametro.
  * 
  * @param messaggio Stringa che rappresenta il messaggio da mostrare
  */
  public IndirizzoNonValidoException(String messaggio) {
    super(messaggio);
  }

}
