package it.unisa.di.ittraining.azienda;

public class AziendaNonValidaException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 127626789622253256L;
	
	private static final String messaggiodefault = "Il nome dell'azienda non è valido";
	
	public AziendaNonValidaException() {
		super(messaggiodefault);
	}
	
	public AziendaNonValidaException(String messaggio) {
		super(messaggio);
	}
}
