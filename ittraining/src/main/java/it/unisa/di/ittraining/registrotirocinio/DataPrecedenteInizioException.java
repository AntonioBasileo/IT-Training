package it.unisa.di.ittraining.registrotirocinio;

public class DataPrecedenteInizioException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5860530217139190838L;
	
	private static final String messaggiodefault = "Il campo data è precedente all'inizio del tirocinio";
	
	public DataPrecedenteInizioException() {
		super(messaggiodefault);
	}
	
	public DataPrecedenteInizioException(String messaggio) {
		super(messaggio);
	}
}
