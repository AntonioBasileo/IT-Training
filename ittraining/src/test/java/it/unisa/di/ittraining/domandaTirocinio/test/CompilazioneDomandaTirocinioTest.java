package it.unisa.di.ittraining.domandaTirocinio.test;

import static org.mockito.Mockito.when;

import it.unisa.di.ittraining.azienda.Azienda;
import it.unisa.di.ittraining.azienda.AziendaNonEsistenteException;
import it.unisa.di.ittraining.azienda.AziendaNonValidaException;
import it.unisa.di.ittraining.azienda.AziendaRepository;
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

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

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
  public void registraDomanda() throws AziendaNonValidaException, 
      AziendaNonEsistenteException, DataDiNascitaNonValidaException, 
      DataNonValidaException, DataFinePrecedenteDataInizioException,
      MassimoNumeroCfuCumulabiliException, NumeroCfuNonValidoException {

    Azienda azienda = new Azienda();
    azienda.setNome("Grafica SRL");
    azienda.setTelefono("3333333333");
    azienda.setSede("Avellino");
    azienda.setIndirizzo("Via Roma 45");

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
 
    DomandaTirocinio domandaTirocinio = new DomandaTirocinio();
    domandaTirocinio.setId(111L);
    domandaTirocinio.setCfu(6);
    domandaTirocinio.setInizioTirocinio(LocalDate.of(2019, Month.FEBRUARY, 12));
    domandaTirocinio.setFineTirocinio(LocalDate.of(2019, Month.MARCH, 20));
    domandaTirocinio.setAzienda(azienda);

    studente.addDomandaTirocinio(domandaTirocinio);

    when(AutenticazioneHolder.getUtente()).thenReturn(studente.getUsername());
    when(utenteService.getUtenteAutenticato()).thenReturn(studente);
    when(aziendaRepository.existsByNome(azienda.getNome())).thenReturn(true);
    when(aziendaRepository.findByNome(azienda.getNome())).thenReturn(azienda);
    when(domandaTirocinioService.registraDomanda(domandaTirocinio, 
       azienda.getNome())).thenReturn(domandaTirocinio);

    try {
      domandaTirocinioService.registraDomanda(domandaTirocinio, azienda.getNome());
    } catch (AziendaNonValidaException | AziendaNonEsistenteException
      | DataNonValidaException | DataFinePrecedenteDataInizioException 
      | MassimoNumeroCfuCumulabiliException
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
  public void validaDataFine() throws DataNonValidaException, 
      DataFinePrecedenteDataInizioException {
    LocalDate dataInizio = LocalDate.now();
    LocalDate dataFine = LocalDate.of(2019, 1, 1);

    when(domandaTirocinioService.validaDataFine(dataInizio, dataFine)).thenReturn(dataFine);

    try {
      domandaTirocinioService.validaDataFine(dataInizio, dataFine);
    } catch (DataNonValidaException | DataFinePrecedenteDataInizioException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
