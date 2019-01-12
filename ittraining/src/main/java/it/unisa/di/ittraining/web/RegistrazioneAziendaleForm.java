package it.unisa.di.ittraining.web;

public class RegistrazioneAziendaleForm {

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

  public String getSesso() {
    return sesso;
  }

  public void setSesso(String sesso) {
    this.sesso = sesso;
  }

  public Integer getGiornoNascita() {
    return giornoNascita;
  }

  public void setGiornoNascita(Integer giornoNascita) {
    this.giornoNascita = giornoNascita;
  }

  public Integer getMeseNascita() {
    return meseNascita;
  }

  public void setMeseNascita(Integer meseNascita) {
    this.meseNascita = meseNascita;
  }

  public Integer getAnnoNascita() {
    return annoNascita;
  }

  public void setAnnoNascita(Integer annoNascita) {
    this.annoNascita = annoNascita;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
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

  public String getConfermaPassword() {
    return confermaPassword;
  }

  public void setConfermaPassword(String confermaPassword) {
    this.confermaPassword = confermaPassword;
  }

  public String getNomeAzienda() {
    return nomeAzienda;
  }

  public void setNomeAzienda(String nomeAzienda) {
    this.nomeAzienda = nomeAzienda;
  }

  private String nome;
  private String cognome;
  private String sesso;
  private Integer giornoNascita;
  private Integer meseNascita;
  private Integer annoNascita;
  private String telefono;
  private String username;
  private String email;
  private String password;
  private String confermaPassword;
  private String nomeAzienda;

}
