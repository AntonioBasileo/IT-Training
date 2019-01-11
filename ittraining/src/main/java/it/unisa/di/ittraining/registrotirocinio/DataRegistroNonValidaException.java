package it.unisa.di.ittraining.registrotirocinio;

public class DataRegistroNonValidaException extends Exception {

  /**
   *Numero seriale.
  */
  private static final long serialVersionUID = -4540488759365969340L;

  private static final String messaggiodefault = "La data di compilazione non è valida";

  public DataRegistroNonValidaException() {
    super(messaggiodefault);
  }

  public DataRegistroNonValidaException(String messaggio) {
    super(messaggio);
  }
}
