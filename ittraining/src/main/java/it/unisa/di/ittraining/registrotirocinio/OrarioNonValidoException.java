package it.unisa.di.ittraining.registrotirocinio;

public class OrarioNonValidoException extends Exception {

  /**
  * Numero seriale.
  */
  private static final long serialVersionUID = -3492089797155005071L;

  private static final String messaggiodefault = "L'orario di fine o di inizio non è valido";

  public OrarioNonValidoException() {
    super(messaggiodefault);
  }

  public OrarioNonValidoException(String messaggio) {
    super(messaggio);
  }
}
