package it.unisa.di.ittraining.registri.test;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinioRepository;
import it.unisa.di.ittraining.registrotirocinio.DataRegistroNonValidaException;
import it.unisa.di.ittraining.registrotirocinio.DataRegistroPrecedenteInizioException;
import it.unisa.di.ittraining.registrotirocinio.DataRegistroSuccessivaFineException;
import it.unisa.di.ittraining.registrotirocinio.OrarioFinePrecedenteInizioException;
import it.unisa.di.ittraining.registrotirocinio.OrarioNonValidoException;
import it.unisa.di.ittraining.registrotirocinio.Registro;
import it.unisa.di.ittraining.registrotirocinio.RegistroRepository;
import it.unisa.di.ittraining.registrotirocinio.RegistroService;

@RunWith(MockitoJUnitRunner.class)
public class CompilaRegistroTest {
	
	@InjectMocks
	private RegistroService registriService;
	
	@Mock
	private RegistroRepository registriRep;
	
	@Mock
	private DomandaTirocinioRepository domandeRep;
	
	@Test
	public void compilaRegistroSuccesso() throws DataRegistroSuccessivaFineException, DataRegistroPrecedenteInizioException, DataRegistroNonValidaException, OrarioNonValidoException,
	OrarioFinePrecedenteInizioException {
		
		DomandaTirocinio domanda = new DomandaTirocinio();
		domanda.setId(111L);
		domanda.setCfu(6);
		domanda.setInizioTirocinio(LocalDate.of(2019, Month.FEBRUARY, 12));
		domanda.setFineTirocinio(LocalDate.of(2019, Month.MARCH, 20));
		
		LocalTime inizio = LocalTime.of(17, 30);
		LocalTime fine = LocalTime.of(19, 37);
				
		
		Registro registro = new Registro();
		registro.setDescrizione("Sviluppo di un'app in java");
		registro.setData(LocalDate.of(2019, Month.FEBRUARY, 15));
		registro.setInizio(inizio);
		registro.setFine(fine);
		registro.setDomanda(domanda);
		
		when(domandeRep.findById((long)domanda.getId())).thenReturn(domanda);
		when(registriRep.save(registro)).thenReturn(registro);
		when(registriService.registraTirocinio(registro, domanda.getId())).thenReturn(registro);
		
		try {
			registriService.registraTirocinio(registro, domanda.getId());
		} catch (DataRegistroSuccessivaFineException | DataRegistroPrecedenteInizioException
				| DataRegistroNonValidaException | OrarioNonValidoException | OrarioFinePrecedenteInizioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Test(expected = OrarioFinePrecedenteInizioException.class)
	public void compilaRegistroOraFineNonValido() throws DataRegistroSuccessivaFineException, DataRegistroPrecedenteInizioException, DataRegistroNonValidaException, OrarioNonValidoException,
	OrarioFinePrecedenteInizioException {
		
		DomandaTirocinio domanda = new DomandaTirocinio();
		domanda.setId(111L);
		domanda.setCfu(6);
		domanda.setInizioTirocinio(LocalDate.of(2019, Month.FEBRUARY, 12));
		domanda.setFineTirocinio(LocalDate.of(2019, Month.MARCH, 20));
		
		LocalTime inizio = LocalTime.of(17, 30);
		LocalTime fine = LocalTime.of(13, 21);
				
		
		Registro registro = new Registro();
		registro.setDescrizione("Sviluppo di un'app in java");
		registro.setData(LocalDate.of(2019, Month.FEBRUARY, 15));
		registro.setInizio(inizio);
		registro.setFine(fine);
		registro.setDomanda(domanda);
		
		when(domandeRep.findById((long)domanda.getId())).thenReturn(domanda);
		when(registriService.registraTirocinio(registro, domanda.getId())).thenReturn(registro);
		
		try {
			registriService.registraTirocinio(registro, domanda.getId());
		} catch (DataRegistroSuccessivaFineException | DataRegistroPrecedenteInizioException
				| DataRegistroNonValidaException | OrarioNonValidoException | OrarioFinePrecedenteInizioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Test(expected = OrarioFinePrecedenteInizioException.class)
	public void compilaRegistroOraInizioNonValido() throws DataRegistroSuccessivaFineException, DataRegistroPrecedenteInizioException, DataRegistroNonValidaException, OrarioNonValidoException,
	OrarioFinePrecedenteInizioException {
		
		DomandaTirocinio domanda = new DomandaTirocinio();
		domanda.setId(111L);
		domanda.setCfu(6);
		domanda.setInizioTirocinio(LocalDate.of(2019, Month.FEBRUARY, 12));
		domanda.setFineTirocinio(LocalDate.of(2019, Month.MARCH, 20));
		
		LocalTime inizio = LocalTime.of(17, 30);
		LocalTime fine = LocalTime.of(13, 21);
				
		
		Registro registro = new Registro();
		registro.setDescrizione("Sviluppo di un'app in java");
		registro.setData(LocalDate.of(2019, Month.FEBRUARY, 15));
		registro.setInizio(inizio);
		registro.setFine(fine);
		registro.setDomanda(domanda);
		
		when(domandeRep.findById((long)domanda.getId())).thenReturn(domanda);
		when(registriService.registraTirocinio(registro, domanda.getId())).thenReturn(registro);
		
		try {
			registriService.registraTirocinio(registro, domanda.getId());
		} catch (DataRegistroSuccessivaFineException | DataRegistroPrecedenteInizioException
				| DataRegistroNonValidaException | OrarioNonValidoException | OrarioFinePrecedenteInizioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Test(expected = DataRegistroPrecedenteInizioException.class)
	public void compilaRegistroDataRegistroPrecedenteInizio() throws DataRegistroSuccessivaFineException, DataRegistroPrecedenteInizioException, DataRegistroNonValidaException,
	OrarioNonValidoException,
	OrarioFinePrecedenteInizioException {
		
		DomandaTirocinio domanda = new DomandaTirocinio();
		domanda.setId(111L);
		domanda.setCfu(6);
		domanda.setInizioTirocinio(LocalDate.of(2019, Month.FEBRUARY, 12));
		domanda.setFineTirocinio(LocalDate.of(2019, Month.MARCH, 20));
		
		LocalTime inizio = LocalTime.of(17, 30);
		LocalTime fine = LocalTime.of(13, 21);
				
		
		Registro registro = new Registro();
		registro.setDescrizione("Sviluppo di un'app in java");
		registro.setData(LocalDate.of(2019, Month.JANUARY, 15));
		registro.setInizio(inizio);
		registro.setFine(fine);
		registro.setDomanda(domanda);
		
		when(domandeRep.findById((long)domanda.getId())).thenReturn(domanda);
		when(registriService.registraTirocinio(registro, domanda.getId())).thenReturn(registro);
		
		try {
			registriService.registraTirocinio(registro, domanda.getId());
		} catch (DataRegistroSuccessivaFineException | DataRegistroPrecedenteInizioException
				| DataRegistroNonValidaException | OrarioNonValidoException | OrarioFinePrecedenteInizioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Test(expected = DataRegistroSuccessivaFineException.class)
	public void compilaRegistroDataRegistroSuccessivaFine() throws DataRegistroSuccessivaFineException, DataRegistroPrecedenteInizioException, DataRegistroNonValidaException,
	OrarioNonValidoException,
	OrarioFinePrecedenteInizioException {
		
		DomandaTirocinio domanda = new DomandaTirocinio();
		domanda.setId(111L);
		domanda.setCfu(6);
		domanda.setInizioTirocinio(LocalDate.of(2019, Month.FEBRUARY, 12));
		domanda.setFineTirocinio(LocalDate.of(2019, Month.MARCH, 20));
		
		LocalTime inizio = LocalTime.of(17, 30);
		LocalTime fine = LocalTime.of(13, 21);
				
		
		Registro registro = new Registro();
		registro.setDescrizione("Sviluppo di un'app in java");
		registro.setData(LocalDate.of(2019, Month.APRIL, 15));
		registro.setInizio(inizio);
		registro.setFine(fine);
		registro.setDomanda(domanda);
		
		when(domandeRep.findById((long)domanda.getId())).thenReturn(domanda);
		when(registriService.registraTirocinio(registro, domanda.getId())).thenReturn(registro);
		
		try {
			registriService.registraTirocinio(registro, domanda.getId());
		} catch (DataRegistroSuccessivaFineException | DataRegistroPrecedenteInizioException
				| DataRegistroNonValidaException | OrarioNonValidoException | OrarioFinePrecedenteInizioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
