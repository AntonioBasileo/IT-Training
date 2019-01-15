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

  /** Espressione regolare che definisce il formato del campo nome. */
  public static final String NOME_PATTERN = "^[a-zA-Z]{3,}(\\s*[a-zA-Z]{2,}?)*$";

  /** Espressione regolare che definisce il formato del campo cognome. */
  public static final String COGNOME_PATTERN = "^(([a-zA-Z]{1,})\\'?"
      + "\\s?)?[a-zA-Z]{2,}(\\s*[a-zA-Z]{2,}?)*$";

  /** Costante che definisce la minima lunghezza del campo nome. */
  public static final int MIN_LUNGHEZZA_NOME = 3;

  /** Costante che definisce la massima lunghezza del campo nome. */
  public static final int MAX_LUNGHEZZA_NOME = 100;

  /** Espressione regolare che definisce il formato del campo username. */
  public static final String USERNAME_PATTERN = "^[a-zA-Z0-9]{6,24}$";

  /** Espressione regolare che definisce il formato del campo password. */
  public static final String PASSWORD_PATTERN = "^[\\S]{6,24}$";

  /* Formato del campo telefono*/
  public static final String TELEFONO_PATTERN = "^[0-9]{2,4}[0-9]{4,7}$";

  /** Costante che rappresenta il genere maschile per l'utente. */
  public static final String SESSO_MASCHILE = "M";

  /** Costante che rappresenta il genere femminile per l'utente. */
  public static final String SESSO_FEMMINILE = "F";

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
