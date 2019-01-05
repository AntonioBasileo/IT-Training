package it.unisa.di.ittraining.registrotirocinio;

public class DataRegistroSuccessivaFineException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3532538386832947940L;
	
	private static final String messaggiodefault = "La data di compilazione non può essere successiva alla fine del tirocinio";
	
	public DataRegistroSuccessivaFineException() {
		super(messaggiodefault);
	}
	
	public DataRegistroSuccessivaFineException(String messaggio) {
		super(messaggio);
	}
}
