package it.unisa.di.ittraining.web;

public class DomandaTirocinioForm {

  public Integer getCfu() {
    return cfu;
  }
  
  public void setCfu(Integer cfu) {
    this.cfu = cfu;
  }
 
  public Integer getGiornoInizio() {
    return giornoInizio;
  }

  public void setGiornoInizio(Integer giornoInizio) {
    this.giornoInizio = giornoInizio;
  }
  
  public Integer getMeseInizio() {
    return meseInizio;
  }

  public void setMeseInizio(Integer meseInizio) {
    this.meseInizio = meseInizio;
  }

  public Integer getAnnoInizio() {
    return annoInizio;
  }
  
  public void setAnnoInizio(Integer annoInizio) {
    this.annoInizio = annoInizio;
  }

  public Integer getGiornoFine() {
    return giornoFine;
  }

  public void setGiornoFine(Integer giornoFine) {
    this.giornoFine = giornoFine;
  }

  public Integer getMeseFine() {
    return meseFine;
  }

  public void setMeseFine(Integer meseFine) {
    this.meseFine = meseFine;
  }

  public Integer getAnnoFine() {
    return annoFine;
  }

  public void setAnnoFine(Integer annoFine) {
    this.annoFine = annoFine;
  }

  public String getNomeAzienda() {
    return nomeAzienda;
  }
  
  public void setNomeAzienda(String nomeAzienda) {
    this.nomeAzienda = nomeAzienda;
  }

  private String nomeAzienda;
  private Integer cfu;
  private Integer giornoInizio;
  private Integer meseInizio;
  private Integer annoInizio;
  private Integer giornoFine;
  private Integer meseFine;
  private Integer annoFine;
}
