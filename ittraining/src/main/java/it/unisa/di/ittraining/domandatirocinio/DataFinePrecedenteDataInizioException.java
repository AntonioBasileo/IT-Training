package it.unisa.di.ittraining.domandatirocinio;

public class DataFinePrecedenteDataInizioException extends Exception {

  /**
 * Numero seriale.
 */
  private static final long serialVersionUID = -431737826929044037L;

  private static final String messaggiodefault = "La data di fine del tirocinio "
      + "è precedente alla data di inizio";

  public DataFinePrecedenteDataInizioException() {
    super(messaggiodefault);
  }

  public DataFinePrecedenteDataInizioException(String messaggio) {
    super(messaggio);
  }
}
