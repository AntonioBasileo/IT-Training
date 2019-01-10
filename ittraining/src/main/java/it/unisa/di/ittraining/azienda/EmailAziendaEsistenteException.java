package it.unisa.di.ittraining.azienda;

public class EmailAziendaEsistenteException extends Exception {

  /**
  *Numero seriale.
  */
  private static final long serialVersionUID = 8399718501018975430L;

  /** Stringa che definisce il messaggio di default utilizzato nell'eccezione. */
  private static final String messaggioDefault = "E-mail già presente";

  /**
  * Costruisce un'eccezione che ha come messaggio {@link #messaggioDefault}.
  */
  public EmailAziendaEsistenteException() {
    super(messaggioDefault);
  }

  /**
  * Costruisce un'eccezione che ha come messaggio la stringa specificata come parametro.
  * 
  * @param messaggio Stringa che rappresenta il messaggio da mostrare
  */
  public EmailAziendaEsistenteException(String messaggio) {
    super(messaggio);
  }

}
