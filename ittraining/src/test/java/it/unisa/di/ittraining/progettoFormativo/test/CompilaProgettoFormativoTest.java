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
import it.unisa.di.ittraining.utente.AutenticazioneHolder;
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
	
	@org.junit.Before
	public void inizializzaMock() {
	  PowerMockito.mockStatic(AutenticazioneHolder.class);
	}
	
	@Test
	public void inserisciProgettoFormativoSuccesso() {
		
		long id = 2345;
		
		TutorAziendale tutor = new TutorAziendale();
		tutor.setNome("marco");
		tutor.setCognome("verdi");
		tutor.setDataDiNascita(LocalDate.of(1980, Month.APRIL, 1));
		tutor.setSesso("M");
		tutor.setEmail("marco@gmail.com");
		
		Azienda azienda = new Azienda();
		azienda.setNome("Grafica SRL");
		azienda.setTelefono("3333333333");
		azienda.setSede("Avellino");
		azienda.setIndirizzo("Via Roma 45");
		azienda.setEmail(tutor.getEmail());
		azienda.setTutor(tutor);
		
		ProgettoFormativo progetto = new ProgettoFormativo();
		
		DomandaTirocinio domanda = new DomandaTirocinio();
		domanda.setId(id);
		domanda.setCfu(6);
		domanda.setInizioTirocinio(LocalDate.of(2019, Month.FEBRUARY, 12));
		domanda.setFineTirocinio(LocalDate.of(2019, Month.MARCH, 20));
		domanda.setProgettoFormativo(progetto);
		
		when(AutenticazioneHolder.getUtente()).thenReturn(tutor.getUsername());
		when(utenteService.getUtenteAutenticato()).thenReturn(tutor);
		when(domandeRep.save(domanda)).thenReturn(domanda);
		when(progettiService.inserisciProgetto(progetto, domanda.getId())).thenReturn(progetto);
		
		progettiService.inserisciProgetto(progetto, domanda.getId());
	}
	
}
