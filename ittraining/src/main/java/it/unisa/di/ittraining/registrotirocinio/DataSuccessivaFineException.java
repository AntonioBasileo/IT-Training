package it.unisa.di.ittraining.registrotirocinio;

public class DataSuccessivaFineException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -555389991623349823L;
	
	private static final String messaggiodefault = "La data è successiva alla data di fine del tirocinio";
	
	public DataSuccessivaFineException() {
		super(messaggiodefault);
	}
	
	public DataSuccessivaFineException(String messaggio) {
		super(messaggio);
	}

}
