package it.unisa.di.ittraining.registrotirocinio;

public class DataRegistroPrecedenteInizioException extends Exception {

 /**
  * 
  */
  private static final long serialVersionUID = -8145070150204724178L;

  private static final String messaggiodefault = 
      "La data di compilazione non può essere precedente alla data di inizio";

  public DataRegistroPrecedenteInizioException() {
    super(messaggiodefault);
  }

  public DataRegistroPrecedenteInizioException(String messaggio) {
    super(messaggio);
  }
}
