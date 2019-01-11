package it.unisa.di.ittraining.web;

public class ProgettoFormativoForm {

  public long getIdDomanda() {
    return idDomanda;
  }

  public void setIdDomanda(Long idDomanda) {
    this.idDomanda = idDomanda;
  }

  public String getDescrizione() {
    return descrizione;
  }

  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  private long idDomanda;
  private String descrizione;
}
