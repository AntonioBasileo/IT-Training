package it.unisa.di.ittraining.utente;

public class UsernameNonEsistenteException extends Exception {

  /**
  *Numero seriale. 
  */
  private static final long serialVersionUID = 1L;

  private static final String messagiodefault = "Username non esistente";

  public UsernameNonEsistenteException() {
    super(messagiodefault);
  }

  public UsernameNonEsistenteException(String messaggio) {
    super(messaggio);
  }
}
