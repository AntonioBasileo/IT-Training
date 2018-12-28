package it.unisa.di.ittraining.domandatirocinio;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import it.unisa.di.ittraining.azienda.Azienda;
import it.unisa.di.ittraining.progettoformativo.ProgettoFormativo;
import it.unisa.di.ittraining.studente.Studente;
import it.unisa.di.ittraining.tutoraccademico.TutorAccademico;

@Entity
public class DomandaTirocinio implements Comparable<DomandaTirocinio> {
	
	public DomandaTirocinio() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Studente studente;

	@ManyToOne
	private TutorAccademico tutorAccademico;
	
	@ManyToOne
	private Azienda azienda;
	  
	@OneToOne
	private ProgettoFormativo progettoFormativo;
	
	private LocalDate data;
	
	/**
	 * Costante che rappresenta lo stato "in attesa" di una domanda di tirocinio.
	 * Una domanda di tirocinio si trova in questo stato quando è stata inviata dallo studente ma
	 * non è ancora stata esaminata e gestita dall'azienda che offre il progetto formativo ad essa
	 * associato.
	 */
	public static final int IN_ATTESA = 0;
	  
	/**
	 * Costante che rappresenta lo stato "accettato" di una domanda di tirocinio.
	 * Una domanda di tirocinio si trova in questo stato quando è stata gestita ed accettata 
	 * dall'azienda che offre il progetto formativo ad essa associato.
	 */
	public static final int ACCETTATA = 1;
	  
	/**
	 * Costante che rappresenta lo stato "rifiutato" di una domanda di tirocinio.
	 * Una domanda di tirocinio si trova in questo stato quando è stata gestita e rifiutata 
	 * dall'azienda che offre il progetto formativo ad essa associato.
	 */
	public static final int RIFIUTATA = 2;
	  
	/**
	 * Costante che rappresenta lo stato "approvato" di una domanda di tirocinio.
	 * Una domanda di tirocinio si trova in questo stato quando è stata gestita ed approvata
	 * dall'ufficio tirocini. <b>La domanda dev'essere stata precedentemente accettata</b> per potervi
	 * assegnare questo stato.
	 */
	public static final int APPROVATA = 3;
	  
	/**
	 * Costante che rappresenta lo stato "respinto" di una domanda di tirocinio.
	 * Una domanda di tirocinio si trova in questo stato quando, sebbene accettata dall'azienda, la
	 * domanda è stata respinta dall'ufficio tirocini.
	 */
	public static final int RESPINTA = 4;

	/** Costante che definisce il numero minimo di CFU da poter associare ad una domanda. */
	public static final int MIN_CFU = 1;
	  
	/** Costante che definisce il numero massimo di CFU da poter associare ad una domanda. */
	public static final int MAX_CFU = 18;
	  
	  /**
	   * Definisce l'ordine di comparazione tra le domande di tirocinio in base al campo data.
	   */
	  
	  
	  @Override
	public int compareTo(DomandaTirocinio domanda) {
	    
	  if (getData().isBefore(domanda.getData())) {
	    return -1;
	  } else if (getData().isAfter(domanda.getData())) {
	    return 1;
	  } else {
	    return 0;
	  }
	}
	
	public TutorAccademico getTutorAccademico() {
		return tutorAccademico;
	}

	public void setTutorAccademico(TutorAccademico tutorAccademico) {
		this.tutorAccademico = tutorAccademico;
	}

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

	public ProgettoFormativo getProgettoFormativo() {
		return progettoFormativo;
	}

	public void setProgettoFormativo(ProgettoFormativo progettoFormativo) {
		this.progettoFormativo = progettoFormativo;
	}

	public Azienda getAzienda() {
		return azienda;
	}

	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
}
