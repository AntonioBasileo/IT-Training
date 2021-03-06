package it.unisa.di.ittraining.utente;

/**
 * Eccezione lanciata quando il controllo sul campo sesso di un utente fallisce poiché questo non è
 * specificato o non rispetta i valori prestabiliti.
 */
public class SessoNonValidoException extends Exception {

  /**
  * Numero seriale.
  */
  private static final long serialVersionUID = -1293006327981246934L;

  /** Stringa che definisce il messaggio di default utilizzato nell'eccezione. */
  private static final String messaggioDefault = "Sesso non valido";
  
  /**
   * Costruisce un'eccezione che ha come messaggio {@link #messaggioDefault}.
   */
  public SessoNonValidoException() {
    super(messaggioDefault);
  }
  
  /**
   * Costruisce un'eccezione che ha come messaggio la stringa specificata come parametro.
   * 
   * @param messaggio Stringa che rappresenta il messaggio da mostrare
   */
  public SessoNonValidoException(String messaggio) {
    super(messaggio);
  }
  
}
