package it.unisa.di.ittraining.progettoFormativo.test;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import it.unisa.di.ittraining.azienda.Azienda;
import it.unisa.di.ittraining.azienda.TutorAziendale;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinioRepository;
import it.unisa.di.ittraining.progettoformativo.ProgettoFormativo;
import it.unisa.di.ittraining.progettoformativo.ProgettoFormativoRepository;
import it.unisa.di.ittraining.progettoformativo.ProgettoFormativoService;
import it.unisa.di.ittraining.studente.MatricolaStudenteEsistenteException;
import it.unisa.di.ittraining.studente.MatricolaStudenteNonValidaException;
import it.unisa.di.ittraining.studente.Studente;
import it.unisa.di.ittraining.studente.StudentiService;
import it.unisa.di.ittraining.utente.AutenticazioneHolder;
import it.unisa.di.ittraining.utente.CognomeNonValidoException;
import it.unisa.di.ittraining.utente.DataDiNascitaNonValidaException;
import it.unisa.di.ittraining.utente.EmailEsistenteException;
import it.unisa.di.ittraining.utente.EmailNonValidaException;
import it.unisa.di.ittraining.utente.NomeCognomeTroppoCortoException;
import it.unisa.di.ittraining.utente.NomeCognomeTroppoLungoException;
import it.unisa.di.ittraining.utente.NomeNonValidoException;
import it.unisa.di.ittraining.utente.PasswordNonCorrispondentiException;
import it.unisa.di.ittraining.utente.PasswordNonValidaException;
import it.unisa.di.ittraining.utente.SessoNonValidoException;
import it.unisa.di.ittraining.utente.TelefonoNonValidoException;
import it.unisa.di.ittraining.utente.UsernameEsistenteException;
import it.unisa.di.ittraining.utente.UsernameNonValidoException;
import it.unisa.di.ittraining.utente.UtenteService;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AutenticazioneHolder.class)
public class CompilaProgettoFormativoTest {

	@InjectMocks
	private ProgettoFormativoService progettiService;
	
	@Mock
	private DomandaTirocinioRepository domandeRep;
	
	@Mock
	private ProgettoFormativoRepository progettiRep;
	
	@Mock
	private UtenteService utenteService;
	
	@Mock
	private StudentiService studenteService;
	
	@org.junit.Before
	public void inizializzaMock() {
	  PowerMockito.mockStatic(AutenticazioneHolder.class);
	}
	
	@Test
	public void inserisciProgettoFormativoSuccesso() throws NomeNonValidoException, NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException, CognomeNonValidoException, DataDiNascitaNonValidaException, UsernameNonValidoException, UsernameEsistenteException, EmailNonValidaException, EmailEsistenteException, SessoNonValidoException, TelefonoNonValidoException, MatricolaStudenteNonValidaException, MatricolaStudenteEsistenteException, PasswordNonValidaException, PasswordNonCorrispondentiException {
		
		long id = 2345;
		
		Azienda azienda = new Azienda();
		azienda.setNome("Grafica SRL");
		azienda.setTelefono("3333333333");
		azienda.setSede("Avellino");
		azienda.setIndirizzo("Via Roma 45");
		azienda.setEmail("marco@gmail.com");
		
		TutorAziendale tutor = new TutorAziendale();
		tutor.setNome("Marco");
		tutor.setCognome("Verdi");
		tutor.setDataDiNascita(LocalDate.of(1980, Month.APRIL, 1));
		tutor.setSesso("M");
		tutor.setEmail(azienda.getEmail());
		azienda.setTutor(tutor);
		
		ProgettoFormativo progetto = new ProgettoFormativo();
		
		Studente studente = new Studente();
		studente.setNome("Laura");
		studente.setCognome("Oliva");
		studente.setDataDiNascita(LocalDate.of(1997, Month.JUNE, 29));
		studente.setMatricola("0512100000");
		studente.setSesso("F");
		studente.setEmail("laura@studenti.unisa.it");
		studente.setPassword("ab12cd34ef");
		studente.setUsername("laura1997");
		studente.setTelefono("3404050333");
		
		DomandaTirocinio domanda = new DomandaTirocinio();
		domanda.setId(id);
		domanda.setCfu(6);
		domanda.setInizioTirocinio(LocalDate.of(2019, Month.FEBRUARY, 12));
		domanda.setFineTirocinio(LocalDate.of(2019, Month.MARCH, 20));
		domanda.setProgettoFormativo(progetto);
		domanda.setAzienda(azienda);
		domanda.setStudente(studente);
		
		when(AutenticazioneHolder.getUtente()).thenReturn(tutor.getUsername());
		when(studenteService.registraStudente(studente)).thenReturn(studente);
		when(domandeRep.save(domanda)).thenReturn(domanda);
		when(utenteService.getUtenteAutenticato()).thenReturn(tutor);
		when(progettiService.inserisciProgetto(progetto, domanda.getId())).thenReturn(progetto);
		
		progettiService.inserisciProgetto(progetto, domanda.getId());
		
	}
	
}
