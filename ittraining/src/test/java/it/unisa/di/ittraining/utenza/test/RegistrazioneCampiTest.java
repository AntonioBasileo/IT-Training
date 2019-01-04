package it.unisa.di.ittraining.utenza.test;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import it.unisa.di.ittraining.utente.CognomeNonValidoException;
import it.unisa.di.ittraining.utente.DataDiNascitaNonValidaException;
import it.unisa.di.ittraining.utente.NomeCognomeTroppoCortoException;
import it.unisa.di.ittraining.utente.NomeCognomeTroppoLungoException;
import it.unisa.di.ittraining.utente.NomeNonValidoException;
import it.unisa.di.ittraining.utente.PasswordNonCorrispondentiException;
import it.unisa.di.ittraining.utente.PasswordNonValidaException;
import it.unisa.di.ittraining.utente.SessoNonValidoException;
import it.unisa.di.ittraining.utente.TelefonoNonValidoException;
import it.unisa.di.ittraining.utente.UtenteService;

@RunWith(MockitoJUnitRunner.class)
public class RegistrazioneCampiTest {
	
	@InjectMocks
	private UtenteService utentiService;
	
	@Test(expected =  NomeCognomeTroppoCortoException.class)
	public void validaNome() throws NomeNonValidoException, NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException {
		
		String nome = "an";
		
		utentiService.validaNome(nome);
		
		when(utentiService.validaNome(nome)).thenReturn(nome);
		
		try {
			utentiService.validaNome(nome);
		} catch(NomeNonValidoException | NomeCognomeTroppoLungoException | NomeCognomeTroppoCortoException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test(expected =  TelefonoNonValidoException.class)
	public void validaTelefono() throws TelefonoNonValidoException {
		
		String telefono = "954354389asdad";
		
		utentiService.validaTelefono(telefono);
		
		when(utentiService.validaTelefono(telefono)).thenReturn(telefono);
		
		try {
			utentiService.validaTelefono(telefono);
		} catch(TelefonoNonValidoException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test(expected =  CognomeNonValidoException.class)
	public void validaCognome() throws NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException, CognomeNonValidoException {
		
		String cognome = "bo";
		
		utentiService.validaCognome(cognome);
		
		when(utentiService.validaCognome(cognome)).thenReturn(cognome);
		
		try {
			utentiService.validaCognome(cognome);
		} catch(CognomeNonValidoException | NomeCognomeTroppoLungoException | NomeCognomeTroppoCortoException e) {
			e.printStackTrace();
		}
	}

	
	
	@Test(expected =  DataDiNascitaNonValidaException.class)
	public void validaDataDiNascita() throws DataDiNascitaNonValidaException {
		
		LocalDate data = LocalDate.of(2009, Month.APRIL, 20);
		
		utentiService.validaDataDiNascita(data);
		
		when(utentiService.validaDataDiNascita(data)).thenReturn(data);
		
		try {
			utentiService.validaDataDiNascita(data);
		} catch(DataDiNascitaNonValidaException e) {
			e.printStackTrace();
		}
	}

	
	
	@Test(expected =  SessoNonValidoException.class)
	public void validaSesso() throws SessoNonValidoException {
		
		String sesso = "954354389asdad";
		
		utentiService.validaSesso(sesso);
		
		when(utentiService.validaSesso(sesso)).thenReturn(sesso);
		
		try {
			utentiService.validaSesso(sesso);
		} catch(SessoNonValidoException e) {
			e.printStackTrace();
		}
	}

	
	
	@Test(expected =  PasswordNonValidaException.class)
	public void validaPassword() throws PasswordNonValidaException, PasswordNonCorrispondentiException {
		
		String password = "954";
		
		utentiService.validaPassword(password);
		
		when(utentiService.validaPassword(password)).thenReturn(password);
		
		try {
			utentiService.validaPassword(password);
		} catch(PasswordNonValidaException | PasswordNonCorrispondentiException e) {
			e.printStackTrace();
		}
	}

}
