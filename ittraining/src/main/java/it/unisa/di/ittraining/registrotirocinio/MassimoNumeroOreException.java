package it.unisa.di.ittraining.registrotirocinio;

public class MassimoNumeroOreException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2132378114841125342L;
	
	private static final String messaggiodefault = "Il massimo numero di ore di tirocinio è 8";
	
	public MassimoNumeroOreException() {
		super(messaggiodefault);
	}
	
	public MassimoNumeroOreException(String messaggio) {
		super(messaggio);
	}
}
