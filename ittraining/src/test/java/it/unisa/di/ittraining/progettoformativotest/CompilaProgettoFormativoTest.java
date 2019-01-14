package it.unisa.di.ittraining.progettoformativotest;

import static org.mockito.Mockito.when;

import it.unisa.di.ittraining.azienda.Azienda;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinioRepository;
import it.unisa.di.ittraining.progettoformativo.ProgettoFormativo;
import it.unisa.di.ittraining.progettoformativo.ProgettoFormativoRepository;
import it.unisa.di.ittraining.progettoformativo.ProgettoFormativoService;
import it.unisa.di.ittraining.utente.AutenticazioneHolder;

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
public class CompilaProgettoFormativoTest {

  @InjectMocks
  private ProgettoFormativoService progettiService;

  @Mock
  private DomandaTirocinioRepository domandeRep;

  @Mock
  private ProgettoFormativoRepository progettiRep;

  @org.junit.Before
  public void inizializzaMock() {
    PowerMockito.mockStatic(AutenticazioneHolder.class);
  }
  
  @Test
  public void inserisciProgettoFormativoSuccesso()  {
    
    Azienda azienda = new Azienda();
    azienda.setNome("Grafica SRL");
    azienda.setTelefono("3333333333");
    azienda.setSede("Avellino");
    azienda.setIndirizzo("Via Roma 45");

    DomandaTirocinio domandaTirocinio = new DomandaTirocinio();
    domandaTirocinio.setId(111L);
    domandaTirocinio.setCfu(6);
    domandaTirocinio.setInizioTirocinio(LocalDate.of(2019, Month.FEBRUARY, 12));
    domandaTirocinio.setFineTirocinio(LocalDate.of(2019, Month.MARCH, 20));
    domandaTirocinio.setAzienda(azienda);

    ProgettoFormativo progetto = new ProgettoFormativo();
    progetto.setDescrizione("Sviluppo di un'applicazione Web");
    progetto.setDomanda(domandaTirocinio);

    when(domandeRep.findById((long)domandaTirocinio.getId())).thenReturn(domandaTirocinio);
    when(progettiService.inserisciProgetto(progetto, 
      domandaTirocinio.getId())).thenReturn(progetto);
    progettiService.inserisciProgetto(progetto, domandaTirocinio.getId());
  }
}
