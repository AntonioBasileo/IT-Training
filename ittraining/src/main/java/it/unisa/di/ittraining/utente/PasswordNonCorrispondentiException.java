package it.unisa.di.ittraining.utente;

public class PasswordNonCorrispondentiException extends Exception {

	  /** Stringa che definisce il messaggio di default utilizzato nell'eccezione. */
	  private static final String messaggioDefault = "Le email non corrispondono";
	  
	  /**
	   * Costruisce un'eccezione che ha come messaggio {@link #messaggioDefault}.
	   */
	  public PasswordNonCorrispondentiException() {
	    super(messaggioDefault);
	  }
	  
	  /**
	   * Costruisce un'eccezione che ha come messaggio la stringa specificata come parametro.
	   * 
	   * @param messaggio Stringa che rappresenta il messaggio da mostrare
	   */
	  public PasswordNonCorrispondentiException(String messaggio) {
	    super(messaggio);
	  }
}
