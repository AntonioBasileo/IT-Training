package it.unisa.di.ittraining.registrotirocinio;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import it.unisa.di.ittraining.azienda.TutorAziendale;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.impiegatosegreteria.ImpiegatoSegreteria;
import it.unisa.di.ittraining.studente.Studente;
import it.unisa.di.ittraining.tutoraccademico.TutorAccademico;

@Entity
public class Registro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Studente studente;
	
	@ManyToOne
	private ImpiegatoSegreteria impiegato;
	
	@ManyToOne
	private TutorAziendale tutorAziendale;
	
	@ManyToOne
	private TutorAccademico tutorAccademico;
	
	@OneToOne
	private DomandaTirocinio domanda;
	
	private LocalDate data;
	private LocalDateTime inizio;
	private LocalDateTime fine;
	private String descrizione;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Studente getStudente() {
		return studente;
	}
	
	public void setStudente(Studente studente) {
		this.studente = studente;
	}
	
	public LocalDate getData() {
		return data;
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public LocalDateTime getInizio() {
		return inizio;
	}
	
	public void setInizio(LocalDateTime inizio) {
		this.inizio = inizio;
	}
	
	public LocalDateTime getFine() {
		return fine;
	}
	
	public void setFine(LocalDateTime fine) {
		this.fine = fine;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public ImpiegatoSegreteria getImpiegato() {
		return impiegato;
	}

	public void setImpiegato(ImpiegatoSegreteria impiegato) {
		this.impiegato = impiegato;
	}

	public TutorAziendale getTutorAziendale() {
		return tutorAziendale;
	}

	public void setTutorAziendale(TutorAziendale tutorAziendale) {
		this.tutorAziendale = tutorAziendale;
	}

	public TutorAccademico getTutorAccademico() {
		return tutorAccademico;
	}

	public void setTutorAccademico(TutorAccademico tutorAccademico) {
		this.tutorAccademico = tutorAccademico;
	}
	
}
