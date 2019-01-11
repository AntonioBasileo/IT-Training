package it.unisa.di.ittraining.utente;

/**
 * Eccezione lanciata quando il controllo sull'username di un utente fallisce perché già
 * presente nel sistema.
 */
public class UsernameEsistenteException extends Exception {
  
  /**
  *Numero seriale. 
  */
  private static final long serialVersionUID = -2352224561228810672L;

  /** Stringa che definisce il messaggio di default utilizzato nell'eccezione. */
  private static final String messaggioDefault = "Username già presente";
  
  /**
   * Costruisce un'eccezione che ha come messaggio {@link #messaggioDefault}.
   */
  public UsernameEsistenteException() {
    super(messaggioDefault);
  }
  
  /**
   * Costruisce un'eccezione che ha come messaggio la stringa specificata come parametro.
   * 
   * @param messaggio Stringa che rappresenta il messaggio da mostrare
   */
  public UsernameEsistenteException(String messaggio) {
    super(messaggio);
  }
  
}
