package it.unisa.di.ittraining.azienda;

public class AziendaNonEsistenteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4674945513700504695L;
	
	private static final String messaggiodefault = "L'azienda non esiste";
	
	public AziendaNonEsistenteException() {
		super(messaggiodefault);
	}
	
	public AziendaNonEsistenteException(String messaggio) {
		super(messaggio);
	}
}
