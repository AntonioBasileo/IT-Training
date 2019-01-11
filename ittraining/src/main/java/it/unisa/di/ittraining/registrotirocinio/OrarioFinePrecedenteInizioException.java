package it.unisa.di.ittraining.registrotirocinio;

public class OrarioFinePrecedenteInizioException extends Exception {

  /**
  * Numero seriale.
  */
  private static final long serialVersionUID = 6022080967875916354L;

  private static final String messaggiodefault =
      "L'orario di fine non può precedere quello di inizio";

  public OrarioFinePrecedenteInizioException() {
    super(messaggiodefault);
  }

  public OrarioFinePrecedenteInizioException(String messaggio) {
    super(messaggio);
  }
}
