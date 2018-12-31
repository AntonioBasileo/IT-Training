package it.unisa.di.ittraining.domandatirocinio;

public class NumeroCfuNonValido extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -681139644337189285L;
	
	private static final String messagiodefault = "Il numero di CFU non è valido";
	
	public NumeroCfuNonValido() {
		super(messagiodefault);
	}
	
	public NumeroCfuNonValido(String messaggio) {
		super(messaggio);
	}
}
