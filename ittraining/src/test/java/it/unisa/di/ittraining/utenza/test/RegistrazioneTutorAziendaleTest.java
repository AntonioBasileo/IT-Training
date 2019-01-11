package it.unisa.di.ittraining.utenza.test;

import static org.mockito.Mockito.when;

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
import it.unisa.di.ittraining.utente.UtenteRepository;
import it.unisa.di.ittraining.utente.UtenteService;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;



@RunWith(MockitoJUnitRunner.class)
public class RegistrazioneTutorAziendaleTest {

  @InjectMocks
  private AziendaService aziendeService;

  @Mock
  private UtenteService utentiService;

  @Mock
  private TutorAziendaleRepository tutorRep;

  @Mock
  private AziendaRepository aziendeRep;

  @Mock
  private UtenteRepository utenteRep;


  @Test
  public void registraTutorAziendaleSuccesso() throws NomeNonValidoException, 
      NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException,
      CognomeNonValidoException, EmailNonValidaException, EmailEsistenteException, 
      EmailNonAssociataException, UsernameNonValidoException, UsernameEsistenteException,
      PasswordNonValidaException, PasswordNonCorrispondentiException, 
      DataDiNascitaNonValidaException, AziendaNonValidaException, 
      AziendaEsistenteException, SessoNonValidoException,
      TelefonoNonValidoException, SedeNonValidaException, 
      IndirizzoNonValidoException,
      it.unisa.di.ittraining.azienda.TelefonoNonValidoException, 
      EmailAziendaEsistenteException, AziendaNonEsistenteException {

    TutorAziendale tutorAziendale = new TutorAziendale();
    tutorAziendale.setUsername("giancarlodasantommaso");
    tutorAziendale.setNome("Lina");
    tutorAziendale.setCognome("Neri");
    tutorAziendale.setDataDiNascita(LocalDate.of(1970, Month.DECEMBER, 30));
    tutorAziendale.setTelefono("0987654321");
    tutorAziendale.setEmail("gianfilibertaoliva@gmail.com");
    tutorAziendale.setPassword("lina123");
    tutorAziendale.setSesso("F");

    Azienda azienda = new Azienda();
    azienda.setNome("theorem");
    azienda.setSede("Fisciano");
    azienda.setEmail("gianfilibertaoliva@gmail.com");
    azienda.setIndirizzo("via Rossi 12");
    azienda.setTelefono("0981234567");

    tutorAziendale.setAzienda(azienda);

    when(utenteRep.existsByUsername(tutorAziendale.getUsername())).thenReturn(false);
    when(aziendeRep.existsByNomeAndEmail(azienda.getNome(), azienda.getEmail())).thenReturn(true);
    when(aziendeRep.findByNome(azienda.getNome())).thenReturn(azienda);
    when(aziendeService.registraAzienda(azienda)).thenReturn(azienda);

    try {
      aziendeService.registraAzienda(azienda);
    } catch (AziendaNonValidaException | AziendaEsistenteException
          | SedeNonValidaException | IndirizzoNonValidoException 
          | EmailNonValidaException | EmailAziendaEsistenteException e) {
      System.out.println("sono qui");
      e.printStackTrace();
    }

    when(aziendeService.registraTutorAziendale(tutorAziendale, 
      azienda.getNome())).thenReturn(tutorAziendale);

    try {
      aziendeService.registraTutorAziendale(tutorAziendale, azienda.getNome());
    } catch (NomeNonValidoException | NomeCognomeTroppoLungoException 
      | NomeCognomeTroppoCortoException
      | CognomeNonValidoException | EmailNonValidaException 
      | EmailEsistenteException
      | EmailNonAssociataException | UsernameNonValidoException
      | UsernameEsistenteException
      | PasswordNonValidaException | PasswordNonCorrispondentiException 
      | DataDiNascitaNonValidaException
      | AziendaNonValidaException | AziendaEsistenteException 
      | SessoNonValidoException
      | it.unisa.di.ittraining.azienda.TelefonoNonValidoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (AziendaNonEsistenteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }


  @Test(expected = EmailNonAssociataException.class)
  public void registratutorAziendaleEmailNonValida() 
      throws NomeNonValidoException, NomeCognomeTroppoLungoException, 
       NomeCognomeTroppoCortoException, CognomeNonValidoException,
       EmailNonValidaException, EmailEsistenteException, EmailNonAssociataException,
       UsernameNonValidoException, UsernameEsistenteException, PasswordNonValidaException,
       PasswordNonCorrispondentiException, DataDiNascitaNonValidaException, 
       AziendaNonValidaException, AziendaEsistenteException, SessoNonValidoException, 
       TelefonoNonValidoException,SedeNonValidaException,
       IndirizzoNonValidoException, it.unisa.di.ittraining.azienda.TelefonoNonValidoException,
       EmailAziendaEsistenteException, AziendaNonEsistenteException {

    TutorAziendale tutorAziendale = new TutorAziendale();
    tutorAziendale.setNome("Lina");
    tutorAziendale.setCognome("Neri");
    tutorAziendale.setDataDiNascita(LocalDate.of(1970, Month.DECEMBER, 30));
    tutorAziendale.setTelefono("0987654321");
    tutorAziendale.setEmail("linaNeri@gmail.com");
    tutorAziendale.setUsername("LinaNeri");
    tutorAziendale.setPassword("lina123");
    tutorAziendale.setSesso("F");

    Azienda azienda = new Azienda();

    azienda.setNome("theorem");
    azienda.setSede("Fisciano");
    azienda.setEmail("lina@gmail.com");
    azienda.setIndirizzo("via Rossi 12");
    azienda.setTelefono("0981234567");


    when(aziendeService.registraAzienda(azienda)).thenReturn(azienda);
    when(aziendeService.registraTutorAziendale(tutorAziendale, 
      azienda.getNome())).thenReturn(tutorAziendale);


    try {
      aziendeService.registraTutorAziendale(tutorAziendale, azienda.getNome());
    } catch (NomeNonValidoException | NomeCognomeTroppoLungoException
      | NomeCognomeTroppoCortoException
      | CognomeNonValidoException | EmailNonValidaException | EmailEsistenteException
      | EmailNonAssociataException | UsernameNonValidoException | UsernameEsistenteException
      | PasswordNonValidaException | PasswordNonCorrispondentiException
      | DataDiNascitaNonValidaException
      | AziendaNonValidaException | AziendaEsistenteException | SessoNonValidoException
      | it.unisa.di.ittraining.azienda.TelefonoNonValidoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}
