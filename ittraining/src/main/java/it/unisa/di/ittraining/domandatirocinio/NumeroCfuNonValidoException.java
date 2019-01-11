package it.unisa.di.ittraining.domandatirocinio;

public class NumeroCfuNonValidoException extends Exception {

  /**
 * 
 */
  private static final long serialVersionUID = -681139644337189285L;

  private static final String messagiodefault = "Il numero di CFU non è valido";

  public NumeroCfuNonValidoException() {
    super(messagiodefault);
  }

  public NumeroCfuNonValidoException(String messaggio) {
    super(messaggio);
  }
}
