package it.unisa.di.ittraining.utente;

public class PasswordErrataException extends Exception {

  /**
  * Numero seriale.
  */
  private static final long serialVersionUID = 1L;

  private static final String messaggiodefault = "La password è errata";

  public PasswordErrataException() {
    super(messaggiodefault);
  }

  public PasswordErrataException(String messaggio) {
    super(messaggio);
  }
}
