package it.unisa.di.ittraining.registrotirocinio;

public class OrePrevisteSuperateException extends Exception {

  /**
  * Numero seriale.
  */
  private static final long serialVersionUID = 2132378114841125342L;

  private static final String messaggiodefault = "Ore previste dal "
      + "tirocinio raggiunte oppure superate";

  public OrePrevisteSuperateException() {
    super(messaggiodefault);
  }

  public OrePrevisteSuperateException(String messaggio) {
    super(messaggio);
  }
}
