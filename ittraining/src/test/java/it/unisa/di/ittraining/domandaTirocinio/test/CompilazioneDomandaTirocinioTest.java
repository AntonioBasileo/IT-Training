package it.unisa.di.ittraining.domandaTirocinio.test;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.annotation.Rollback;

import it.unisa.di.ittraining.azienda.Azienda;
import it.unisa.di.ittraining.azienda.AziendaNonEsistenteException;
import it.unisa.di.ittraining.azienda.AziendaNonValidaException;
import it.unisa.di.ittraining.azienda.AziendaRepository;
import it.unisa.di.ittraining.azienda.AziendaService;
import it.unisa.di.ittraining.domandatirocinio.DataFinePrecedenteDataInizioException;
import it.unisa.di.ittraining.domandatirocinio.DataNonValidaException;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinioRepository;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinioService;
import it.unisa.di.ittraining.domandatirocinio.MassimoNumeroCfuCumulabiliException;
import it.unisa.di.ittraining.domandatirocinio.NumeroCfuNonValidoException;
import it.unisa.di.ittraining.studente.Studente;
import it.unisa.di.ittraining.studente.StudenteRepository;
import it.unisa.di.ittraining.utente.DataDiNascitaNonValidaException;
import it.unisa.di.ittraining.utente.UtenteService;


@Transactional
@Rollback
@RunWith(MockitoJUnitRunner.class)
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
	
	@Test
	public void registraDomanda() throws AziendaNonValidaException, AziendaNonEsistenteException, DataDiNascitaNonValidaException, DataNonValidaException, DataFinePrecedenteDataInizioException, MassimoNumeroCfuCumulabiliException, NumeroCfuNonValidoException {
		
		Azienda azienda= new Azienda();
		azienda.setNome("theorem");
		azienda.setSede("Fisciano");
		azienda.setEmail("gianfilibertaoliva@gmail.com");
		azienda.setIndirizzo("via Rossi 12");
		azienda.setTelefono("0981234567");
		
		aziendaRepository.save(azienda);
		
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
		
		studenteRepository.save(studente);
		
		DomandaTirocinio domandaTirocinio= new DomandaTirocinio();
		domandaTirocinio.setAzienda(azienda);
		domandaTirocinio.setCfu(6);
		domandaTirocinio.setInizioTirocinio(LocalDate.of(2019, Month.FEBRUARY, 12));
		domandaTirocinio.setFineTirocinio(LocalDate.of(2019, Month.MARCH, 20));
		domandaTirocinio.setStudente(studente);
		
		
		when(aziendaRepository.save(azienda)).thenReturn(azienda);
		when(studenteRepository.save(studente)).thenReturn(studente);
		when(domandaTirocinioService.registraDomanda(domandaTirocinio, azienda.getNome())).thenReturn(domandaTirocinio);
		
		
		
		
		try {
			domandaTirocinioService.registraDomanda(domandaTirocinio, azienda.getNome());
		} catch (AziendaNonValidaException | AziendaNonEsistenteException | DataDiNascitaNonValidaException
				| DataNonValidaException | DataFinePrecedenteDataInizioException | MassimoNumeroCfuCumulabiliException
				| NumeroCfuNonValidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
