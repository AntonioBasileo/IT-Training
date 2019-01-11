package it.unisa.di.ittraining.azienda;

public class EmailNonAssociataException extends Exception {
  /**
  * Numero seriale.
  */
  private static final long serialVersionUID = -7428368659223122980L;

  private static final String messaggiodefault = "L'email aziendale indicata"
      + " non è associata ad alcuna azienda";

  public EmailNonAssociataException() {
    super(messaggiodefault);
  }

  public EmailNonAssociataException(String messaggio) {
    super(messaggio);
  }
}
