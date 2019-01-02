package it.unisa.di.ittraining.azienda;

public class AziendaEsistenteException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3567732674273319217L;
	
	private static final String messaggiodefault = "L'azienda indicata è già esistente";
	
	public AziendaEsistenteException() {
		super(messaggiodefault);
	}
	
	public AziendaEsistenteException(String messaggio) {
		super(messaggio);
	}

}
