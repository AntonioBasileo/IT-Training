package it.unisa.di.ittraining.convenzionitest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import it.unisa.di.ittraining.azienda.Azienda;
import it.unisa.di.ittraining.azienda.AziendaEsistenteException;
import it.unisa.di.ittraining.azienda.AziendaNonEsistenteException;
import it.unisa.di.ittraining.azienda.AziendaNonValidaException;
import it.unisa.di.ittraining.azienda.AziendaRepository;
import it.unisa.di.ittraining.azienda.AziendaService;
import it.unisa.di.ittraining.azienda.EmailAziendaEsistenteException;
import it.unisa.di.ittraining.azienda.EmailNonAssociataException;
import it.unisa.di.ittraining.azienda.IndirizzoNonValidoException;
import it.unisa.di.ittraining.azienda.SedeNonValidaException;
import it.unisa.di.ittraining.azienda.TutorAziendale;
import it.unisa.di.ittraining.azienda.TutorAziendaleRepository;
import it.unisa.di.ittraining.impiegatosegreteria.ImpiegatoSegreteria;
import it.unisa.di.ittraining.impiegatosegreteria.ImpiegatoSegreteriaService;
import it.unisa.di.ittraining.utente.CognomeNonValidoException;
import it.unisa.di.ittraining.utente.DataDiNascitaNonValidaException;
import it.unisa.di.ittraining.utente.EmailEsistenteException;
import it.unisa.di.ittraining.utente.EmailNonValidaException;
import it.unisa.di.ittraining.utente.NomeCognomeTroppoCortoException;
import it.unisa.di.ittraining.utente.NomeCognomeTroppoLungoException;
import it.unisa.di.ittraining.utente.NomeNonValidoException;
import it.unisa.di.ittraining.utente.PasswordErrataException;
import it.unisa.di.ittraining.utente.PasswordNonCorrispondentiException;
import it.unisa.di.ittraining.utente.PasswordNonValidaException;
import it.unisa.di.ittraining.utente.SessoNonValidoException;
import it.unisa.di.ittraining.utente.TelefonoNonValidoException;
import it.unisa.di.ittraining.utente.UsernameEsistenteException;
import it.unisa.di.ittraining.utente.UsernameNonEsistenteException;
import it.unisa.di.ittraining.utente.UsernameNonValidoException;
import it.unisa.di.ittraining.utente.UtenteService;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
public class AziendaServiceIntegrationTest {

  @Autowired
  private ImpiegatoSegreteriaService impiegatoService;

  @Autowired
  private AziendaService aziendeService;
  
  @Autowired
  private AziendaRepository aziendeRep;
  
  @Autowired
  private TutorAziendaleRepository aziendaleRep;
  
  @Autowired
  private UtenteService utentiService;

  @Test
  public void aggiungiConvenzione() {

    ImpiegatoSegreteria impiegato = new ImpiegatoSegreteria();
    impiegato.setNome("nicola");
    impiegato.setCognome("rossi");
    impiegato.setUsername("nicola88");
    impiegato.setDataDiNascita(LocalDate.of(1988, Month.APRIL, 20));
    impiegato.setPassword("nicola88");
    impiegato.setSesso("M");
    impiegato.setEmail("n.rossi@unisa.it");
    impiegato.setTelefono("3392345612");
    
    try {
      impiegato = impiegatoService.registraImpiegato(impiegato);
    } catch (NomeNonValidoException | NomeCognomeTroppoLungoException
        | NomeCognomeTroppoCortoException
        | CognomeNonValidoException | EmailNonValidaException
        | EmailEsistenteException | PasswordNonValidaException
        | PasswordNonCorrispondentiException | DataDiNascitaNonValidaException
        | UsernameNonValidoException
        | UsernameEsistenteException | SessoNonValidoException
        | TelefonoNonValidoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    try {
      utentiService.login(impiegato.getUsername(), impiegato.getPassword());
    } catch (UsernameNonEsistenteException | PasswordErrataException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    Azienda azienda = new Azienda();
    azienda.setNome("3D Graphic");
    azienda.setEmail("3d.srl@gmail.com");
    azienda.setSede("napoli");
    azienda.setIndirizzo("Via toledo 32");
    azienda.setTelefono("3472134567");
    
    Azienda azienda1 = new Azienda();
    azienda1.setNome("Src graphic center");
    azienda1.setEmail("graphic.center@gmail.com");
    azienda1.setSede("avella");
    azienda1.setIndirizzo("Via settimio 11");
    azienda1.setTelefono("3490123451");
    
    Azienda azienda2 = new Azienda();
    azienda2.setNome("easy webdevelop");
    azienda2.setEmail("easy.web@gmail.com");
    azienda2.setSede("sturno");
    azienda2.setIndirizzo("Viale europa");
    azienda2.setTelefono("3409876512");
    
    try {
      azienda = aziendeService.registraAzienda(azienda);
      azienda1 = aziendeService.registraAzienda(azienda1);
      azienda2 = aziendeService.registraAzienda(azienda2);
    } catch (AziendaNonValidaException | AziendaEsistenteException | SedeNonValidaException
        | IndirizzoNonValidoException | EmailNonValidaException | EmailAziendaEsistenteException
        | it.unisa.di.ittraining.azienda.TelefonoNonValidoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    utentiService.logout();
    
    List<Azienda> aziende = new ArrayList<>();
    aziende.add(azienda);
    aziende.add(azienda1);
    aziende.add(azienda2);
    
    TutorAziendale tutoraz = new TutorAziendale();
    tutoraz.setNome("Antonio");
    tutoraz.setCognome("Russo");
    tutoraz.setUsername("antorusso");
    tutoraz.setDataDiNascita(LocalDate.of(1980, Month.MAY, 21));
    tutoraz.setPassword("antorusso80");
    tutoraz.setEmail(azienda.getEmail());
    tutoraz.setSesso("M");
    tutoraz.setTelefono("3498761234");
    
    try {
      tutoraz = aziendeService.registraTutorAziendale(tutoraz, azienda.getNome());
    } catch (NomeNonValidoException | NomeCognomeTroppoLungoException 
        | NomeCognomeTroppoCortoException | CognomeNonValidoException 
        | EmailNonValidaException | EmailEsistenteException | EmailNonAssociataException
        | UsernameNonValidoException | UsernameEsistenteException | PasswordNonValidaException
        | PasswordNonCorrispondentiException | DataDiNascitaNonValidaException 
        | AziendaNonValidaException | AziendaEsistenteException | SessoNonValidoException 
        | AziendaNonEsistenteException 
        | it.unisa.di.ittraining.azienda.TelefonoNonValidoException e2) {
      // TODO Auto-generated catch block
      e2.printStackTrace();
    }
    
    assertEquals(azienda, aziendeRep.findByNome(azienda.getNome()));
    assertEquals(azienda1, aziendeRep.findByNome(azienda1.getNome()));
    assertEquals(azienda2, aziendeRep.findByNome(azienda2.getNome()));
    assertEquals(tutoraz, aziendaleRep.findByUsername(tutoraz.getUsername()));
    
    List<Azienda> aziendeSalvate = aziendeRep.findAll();
    
    for (Azienda a: aziende) {
      assertTrue(aziendeSalvate.contains(a));
    }
  }
  
}
