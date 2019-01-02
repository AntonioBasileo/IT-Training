package it.unisa.di.ittraining.utente;

public class NomeCognomeTroppoLungoException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8103471849233932968L;
	
	private static final String messaggiodefault = "Il campo nome o il campo cognome è troppo lungo";
	
	public NomeCognomeTroppoLungoException() {
		super(messaggiodefault);
	}
	
	public NomeCognomeTroppoLungoException(String messaggio) {
		super(messaggio);
	}
}
