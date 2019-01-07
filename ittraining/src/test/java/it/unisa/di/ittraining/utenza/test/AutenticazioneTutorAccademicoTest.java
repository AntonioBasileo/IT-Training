package it.unisa.di.ittraining.utenza.test;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import it.unisa.di.ittraining.azienda.TutorAziendaleRepository;
import it.unisa.di.ittraining.impiegatosegreteria.ImpiegatoSegreteriaRepository;
import it.unisa.di.ittraining.studente.StudenteRepository;
import it.unisa.di.ittraining.tutoraccademico.TutorAccademico;
import it.unisa.di.ittraining.tutoraccademico.TutorAccademicoRepository;
import it.unisa.di.ittraining.utente.AutenticazioneHolder;
import it.unisa.di.ittraining.utente.PasswordErrataException;
import it.unisa.di.ittraining.utente.UsernameNonEsistenteException;
import it.unisa.di.ittraining.utente.UtenteRepository;
import it.unisa.di.ittraining.utente.UtenteService;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AutenticazioneHolder.class)
public class AutenticazioneTutorAccademicoTest {
	
	@InjectMocks
	private UtenteService utentiService;
	
	@Mock
	private UtenteRepository utentiRep;
	
	@Mock
	private TutorAccademicoRepository accademicoRep;
	
	@Mock
	private StudenteRepository studentiRep;
	
	@Mock
	private TutorAziendaleRepository aziendaleRep;
	
	@Mock
	private ImpiegatoSegreteriaRepository impiegatiRep;
	
	@Mock
	private AutenticazioneHolder autentiazioneHolder;

	@org.junit.Before
	public void inizializzaMock() {
	  PowerMockito.mockStatic(AutenticazioneHolder.class);
	}
	
	@Test(expected = UsernameNonEsistenteException.class)
	public void autenticazioneTutorAccademicoUsernameErrato() throws UsernameNonEsistenteException, PasswordErrataException {
		
		TutorAccademico tutor = new TutorAccademico();
		tutor.setUsername("giancarlo");
		tutor.setPassword("password");
		
		when(utentiRep.existsByUsername(tutor.getUsername())).thenReturn(false);
		
		utentiService.login(tutor.getUsername(), tutor.getPassword());
	}
	
	@Test(expected = PasswordErrataException.class)
	public void autenticazioneTutorAccademicoPasswordErrata() throws UsernameNonEsistenteException, PasswordErrataException {
		
		TutorAccademico tutor = new TutorAccademico();
		tutor.setUsername("giancarlo");
		tutor.setPassword("password");
		
		when(utentiRep.existsByUsername(tutor.getUsername())).thenReturn(true);
		when(studentiRep.findByUsernameAndPassword(tutor.getUsername(), tutor.getPassword())).thenReturn(null);
		when(aziendaleRep.findByUsernameAndPassword(tutor.getUsername(), tutor.getPassword())).thenReturn(null);
		when(impiegatiRep.findByUsernameAndPassword(tutor.getUsername(), tutor.getPassword())).thenReturn(null);
		when(accademicoRep.findByUsernameAndPassword(tutor.getUsername(), tutor.getPassword())).thenReturn(null);
		
		utentiService.login(tutor.getUsername(), tutor.getPassword());
	}
	
	@Test
	public void autenticazioneTutorAccademicoConSuccesso() {
		
		TutorAccademico tutor = new TutorAccademico();
		tutor.setUsername("giancarlo");
		tutor.setPassword("password");
		
		when(utentiRep.existsByUsername(tutor.getUsername())).thenReturn(true);
		when(studentiRep.findByUsernameAndPassword(tutor.getUsername(), tutor.getPassword())).thenReturn(null);
		when(aziendaleRep.findByUsernameAndPassword(tutor.getUsername(), tutor.getPassword())).thenReturn(null);
		when(impiegatiRep.findByUsernameAndPassword(tutor.getUsername(), tutor.getPassword())).thenReturn(null);
		when(accademicoRep.findByUsernameAndPassword(tutor.getUsername(), tutor.getPassword())).thenReturn(tutor);
		
		try {
			utentiService.login(tutor.getUsername(), tutor.getPassword());
		} catch (UsernameNonEsistenteException | PasswordErrataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
