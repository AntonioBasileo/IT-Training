package it.unisa.di.ittraining.utente;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Utente {
	
	@Id
	protected String username = "";
	protected String nome;
	protected String cognome;
	protected LocalDate dataDiNascita;
	private String telefono;
	protected String email = "";
	protected String password = "";
	protected String sesso;

	/** Espressione regolare che definisce il formato del campo username. */
	public static final String USERNAME_PATTERN = "^[a-zA-Z0-9]{6,24}$";
	  
	/** Espressione regolare che definisce il formato del campo password. */
	public static final String PASSWORD_PATTERN = "^[\\S]{6,24}$";

	/** Costante che definisce il formato del campo telefono*/
	public static final String TELEFONO_PATTERN = "^[0-9]{2,4}[0-9]{4,7}$";
	  
	/** Costante che definisce la minima lunghezza dei campi nome e cognome. */
	public static final int MIN_LUNGHEZZA_NOME = 2;
	  
	/** Costante che definisce la massima lunghezza dei campi nome e cognome. */
	public static final int MAX_LUNGHEZZA_NOME = 255;
	
	/** Costante che rappresenta il genere maschile per l'utente. */
	public static final String SESSO_MASCHILE = "M";
	  
	/** Costante che rappresenta il genere femminile per l'utente. */
	public static final String SESSO_FEMMINILE = "F";
	  
	/** Espressione regolare che definisce il formato del campo email. */
	public static final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/"
	                                           + "=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f"
	                                           + "\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x"
	                                           + "0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-"
	                                           + "9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25["
	                                           + "0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2"
	                                           + "[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\"
	                                           + "x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]"
	                                           + "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	  
	
	public Utente() {
		
	}
	  
	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
}
