package it.unisa.di.ittraining.registrotirocinio;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;

@Entity
public class Registro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	private DomandaTirocinio domanda;
	
	private LocalDate data;
	private LocalTime inizio;
	private LocalTime fine;
	private float numero_minuti;
	private String descrizione;
	private int status;
	
	public static final int IN_ATTESA = 0;
	
	public static final int IN_COMPILAZIONE = 1;
	
	public static final int APPROVATO = 2;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public LocalDate getData() {
		return data;
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public LocalTime getInizio() {
		return inizio;
	}
	
	public void setInizio(LocalTime inizio) {
		this.inizio = inizio;
	}
	
	public LocalTime getFine() {
		return fine;
	}
	
	public void setFine(LocalTime fine) {
		this.fine = fine;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public DomandaTirocinio getDomanda() {
		return domanda;
	}

	public void setDomanda(DomandaTirocinio domanda) {
		if(this.domanda != domanda) {
			this.domanda = domanda;
			domanda.addRegistro(this);
		}
	}

	public float getNumero_minuti() {
		return numero_minuti;
	}

	public void setNumero_minuti(long numero_minuti) {
		this.numero_minuti = numero_minuti;
	}

	public float getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
