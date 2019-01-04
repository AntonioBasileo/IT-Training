package it.unisa.di.ittraining.registrazioneImpiegatoSegreteria.test;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import it.unisa.di.ittraining.impiegatosegreteria.ImpiegatoSegreteria;
import it.unisa.di.ittraining.impiegatosegreteria.ImpiegatoSegreteriaRepository;
import it.unisa.di.ittraining.impiegatosegreteria.ImpiegatoSegreteriaService;

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


@RunWith(MockitoJUnitRunner.class)
public class RegistrazioneImpiegatoSegreteriaEmailNonValidaTest {
  
  
  @InjectMocks
  private ImpiegatoSegreteriaService impiegatoService;
  
  
  @Mock
  private ImpiegatoSegreteriaRepository rep;
  
  
  @Mock
  private UtenteService utentiService;
  
  
  
  
  @Test
  public void registraImpiegatoSegreteriaSuccesso() throws NomeNonValidoException, NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException, CognomeNonValidoException,
  EmailNonValidaException, EmailEsistenteException, TelefonoNonValidoException, DataDiNascitaNonValidaException, PasswordNonValidaException, PasswordNonCorrispondentiException,
  SessoNonValidoException, UsernameNonValidoException, UsernameEsistenteException {
    
    ImpiegatoSegreteria impiegato = new ImpiegatoSegreteria();
    impiegato.setNome("Giancarlo");
    impiegato.setCognome("Rossi");
    impiegato.setDataDiNascita(LocalDate.of(1960, Month.OCTOBER, 28));
    impiegato.setSesso("M");
    impiegato.setEmail("giancarlo@unisa.it");
    impiegato.setPassword("ab12cd34ef");
    impiegato.setUsername("giancarlo1960");
    impiegato.setTelefono("3407525362");
    
    
    when(impiegatoService.registraImpiegato(impiegato)).thenReturn(impiegato);
    
      try {
        impiegatoService.registraImpiegato(impiegato);
      } catch (NomeNonValidoException | NomeCognomeTroppoLungoException | NomeCognomeTroppoCortoException
          | CognomeNonValidoException | EmailNonValidaException | EmailEsistenteException
          | TelefonoNonValidoException | DataDiNascitaNonValidaException | PasswordNonValidaException
          | PasswordNonCorrispondentiException | SessoNonValidoException | UsernameNonValidoException
          | UsernameEsistenteException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      
  }
  
  
  @Test(expected =  EmailEsistenteException.class)
  public void registraImpiegatoSegreteriaEmailNonValida() throws NomeNonValidoException, NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException, CognomeNonValidoException,
  EmailNonValidaException, EmailEsistenteException, TelefonoNonValidoException, DataDiNascitaNonValidaException, PasswordNonValidaException, PasswordNonCorrispondentiException,
  SessoNonValidoException, UsernameNonValidoException, UsernameEsistenteException {
    
    ImpiegatoSegreteria impiegato = new ImpiegatoSegreteria();
    impiegato.setNome("Giancarlo");
    impiegato.setCognome("Rossi");
    impiegato.setDataDiNascita(LocalDate.of(1960, Month.OCTOBER, 28));
    impiegato.setSesso("M");
    impiegato.setEmail("giancarlo@unisa.it");
    impiegato.setPassword("ab12cd34ef");
    impiegato.setUsername("giancarlo1960");
    impiegato.setTelefono("3407525362");
    
    when(rep.existsByEmail(impiegato.getEmail())).thenReturn(true);
    

      try {
        impiegatoService.registraImpiegato(impiegato);
      } catch (NomeNonValidoException | NomeCognomeTroppoLungoException | NomeCognomeTroppoCortoException
          | CognomeNonValidoException | EmailNonValidaException | EmailEsistenteException
          | TelefonoNonValidoException | DataDiNascitaNonValidaException | PasswordNonValidaException
          | PasswordNonCorrispondentiException | SessoNonValidoException | UsernameNonValidoException
          | UsernameEsistenteException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      
  }
  
  

}