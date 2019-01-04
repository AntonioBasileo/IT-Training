package it.unisa.di.ittraining.domandaTirocinio.test;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import it.unisa.di.ittraining.azienda.Azienda;
import it.unisa.di.ittraining.azienda.AziendaNonEsistenteException;
import it.unisa.di.ittraining.azienda.AziendaNonValidaException;
import it.unisa.di.ittraining.azienda.AziendaRepository;
import it.unisa.di.ittraining.azienda.TutorAziendale;
import it.unisa.di.ittraining.domandatirocinio.DataFinePrecedenteDataInizioException;
import it.unisa.di.ittraining.domandatirocinio.DataNonValidaException;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinioRepository;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinioService;
import it.unisa.di.ittraining.domandatirocinio.MassimoNumeroCfuCumulabiliException;
import it.unisa.di.ittraining.domandatirocinio.NumeroCfuNonValidoException;
import it.unisa.di.ittraining.studente.Studente;
import it.unisa.di.ittraining.studente.StudenteRepository;
import it.unisa.di.ittraining.utente.AutenticazioneHolder;
import it.unisa.di.ittraining.utente.DataDiNascitaNonValidaException;
import it.unisa.di.ittraining.utente.UtenteService;



@RunWith(PowerMockRunner.class)
@PrepareForTest(AutenticazioneHolder.class)
public class CompilazioneDomandaTirocinioTest {

	@InjectMocks
	private DomandaTirocinioService domandaTirocinioService;
	
	@Mock
	private DomandaTirocinioRepository domandaTirocinioRrepository;
	
	@Mock
	private UtenteService utenteService;
	
	@Mock
	private AziendaRepository aziendaRepository;
	
	@Mock
	private StudenteRepository studenteRepository;
	
	@org.junit.Before
	public void inizializzaMock() {
	  PowerMockito.mockStatic(AutenticazioneHolder.class);
	}
	
	@Test
	public void registraDomanda() throws AziendaNonValidaException, AziendaNonEsistenteException, DataDiNascitaNonValidaException, DataNonValidaException, DataFinePrecedenteDataInizioException, MassimoNumeroCfuCumulabiliException, NumeroCfuNonValidoException {
		
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
		
		Studente studente = new Studente();
		
		
		DomandaTirocinio domandaTirocinio= new DomandaTirocinio();
		domandaTirocinio.setCfu(6);
		domandaTirocinio.setInizioTirocinio(LocalDate.of(2019, Month.FEBRUARY, 12));
		domandaTirocinio.setFineTirocinio(LocalDate.of(2019, Month.MARCH, 20));
		domandaTirocinio.setAzienda(azienda);
		
		studente.getDomandeTirocinio().add(domandaTirocinio);
		domandaTirocinio.setStudente(studente);
		
		when(AutenticazioneHolder.getUtente()).thenReturn(studente.getUsername());
		when(utenteService.getUtenteAutenticato()).thenReturn(studente);
		when(aziendaRepository.existsByNome(azienda.getNome())).thenReturn(true);
		when(aziendaRepository.findByNome(domandaTirocinioService.validaNomeAzienda(azienda.getNome()))).thenReturn(azienda);
		when(domandaTirocinioService.registraDomanda(domandaTirocinio, azienda.getNome())).thenReturn(domandaTirocinio);
		
		
		
		
		try {
			domandaTirocinioService.registraDomanda(domandaTirocinio, azienda.getNome());
		} catch (AziendaNonValidaException | AziendaNonEsistenteException
				| DataNonValidaException | DataFinePrecedenteDataInizioException | MassimoNumeroCfuCumulabiliException
				| NumeroCfuNonValidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(expected = DataNonValidaException.class)
	public void validaDataPrecedenteAdOggi() throws DataNonValidaException {
		LocalDate data = LocalDate.of(2018, Month.APRIL, 23);
		
		when(domandaTirocinioService.validaDataInizio(data)).thenReturn(data);
		
		try {
			domandaTirocinioService.validaDataInizio(data);
		} catch (DataNonValidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(expected = DataFinePrecedenteDataInizioException.class)
	public void validaDataFine() throws DataNonValidaException, DataFinePrecedenteDataInizioException {
		LocalDate data_inizio = LocalDate.now();
		LocalDate data_fine = LocalDate.of(2019, 1, 1);
		
		when(domandaTirocinioService.validaDataFine(data_inizio, data_fine)).thenReturn(data_fine);
		
		try {
			domandaTirocinioService.validaDataFine(data_inizio, data_fine);
		} catch (DataNonValidaException | DataFinePrecedenteDataInizioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
