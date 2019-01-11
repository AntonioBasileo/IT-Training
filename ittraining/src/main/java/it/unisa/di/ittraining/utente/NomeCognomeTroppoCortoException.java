package it.unisa.di.ittraining.utente;

public class NomeCognomeTroppoCortoException extends Exception {

  /**
  *Numero seriale.
  */
  private static final long serialVersionUID = -5923151163207296581L;

  private static final String messaggiodefault = "Il campo nome o il campo cognome è troppo corto";

  public NomeCognomeTroppoCortoException() {
    super(messaggiodefault);
  }

  public NomeCognomeTroppoCortoException(String messaggio) {
    super(messaggio);
  }

}
