package it.unisa.di.ittraining.domandatirocinio;

public class MassimoNumeroCfuCumulabiliException extends Exception {

  /**
 * Numero seriale.
 */
  private static final long serialVersionUID = -8136209252974035746L;

  private static final String messagiodefault = "Hai raggiunto il massimo "
      + "numero di cfu cumulabili. Non è possibile eseguire la richiesta";

  public MassimoNumeroCfuCumulabiliException() {
    super(messagiodefault);
  }

  public MassimoNumeroCfuCumulabiliException(String messaggio) {
    super(messaggio);
  }

}
