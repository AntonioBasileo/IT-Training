package it.unisa.di.ittraining.utente;

/**
 * Eccezione lanciata quando il controllo sul cognome di un utente fallisce perché questo non
 * è valido oppure è nullo.
 */
public class CognomeNonValidoException extends Exception {
  
  /**
  *Numero seriale.
  */
  private static final long serialVersionUID = 1L;

  /** Stringa che definisce il messaggio di default utilizzato nell'eccezione. */
  private static final String messaggioDefault = "Cognome non valido";
  
  /**
   * Costruisce un'eccezione che ha come messaggio {@link #messaggioDefault}.
   */
  public CognomeNonValidoException() {
    super(messaggioDefault);
  }
  
  /**
   * Costruisce un'eccezione che ha come messaggio la stringa specificata come parametro.
   * 
   * @param messaggio Stringa che rappresenta il messaggio da mostrare
   */
  public CognomeNonValidoException(String messaggio) {
    super(messaggio);
  }
  
}
