package it.unisa.di.ittraining.convenzionitest;

import it.unisa.di.ittraining.azienda.Azienda;
import it.unisa.di.ittraining.azienda.AziendaEsistenteException;
import it.unisa.di.ittraining.azienda.AziendaNonValidaException;
import it.unisa.di.ittraining.azienda.AziendaService;
import it.unisa.di.ittraining.azienda.EmailAziendaEsistenteException;
import it.unisa.di.ittraining.azienda.IndirizzoNonValidoException;
import it.unisa.di.ittraining.azienda.SedeNonValidaException;
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
public class AggiungiEnteIntTest {

  @Autowired
  private ImpiegatoSegreteriaService impiegatoService;

  @Autowired
  private AziendaService aziendeService;
  
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
    
    try {
      azienda = aziendeService.registraAzienda(azienda);
    } catch (AziendaNonValidaException | AziendaEsistenteException | SedeNonValidaException
        | IndirizzoNonValidoException | EmailNonValidaException | EmailAziendaEsistenteException
        | it.unisa.di.ittraining.azienda.TelefonoNonValidoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    utentiService.logout();
  }
  
}
