package it.unisa.di.ittraining.domandatirocinio;

public class DataNonValidaException extends Exception {

  /**
* 
*/
  private static final long serialVersionUID = 8733121574959917081L;

  private static final String messaggiodefault = "La data di inizio "
      + "o di fine del tirocinio non è valida";

  public DataNonValidaException() {
    super(messaggiodefault);
  }

  public DataNonValidaException(String messaggio) {
    super(messaggio);
  }

}
