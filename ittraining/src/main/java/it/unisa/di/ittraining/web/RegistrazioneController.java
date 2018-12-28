package it.unisa.di.ittraining.web;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.unisa.di.ittraining.azienda.AziendaService;
import it.unisa.di.ittraining.azienda.TutorAziendale;
import it.unisa.di.ittraining.impiegatosegreteria.ImpiegatoSegreteria;
import it.unisa.di.ittraining.impiegatosegreteria.ImpiegatoSegreteriaService;
import it.unisa.di.ittraining.studente.MatricolaStudenteEsistenteException;
import it.unisa.di.ittraining.studente.MatricolaStudenteNonValidaException;
import it.unisa.di.ittraining.studente.Studente;
import it.unisa.di.ittraining.studente.StudentiService;
import it.unisa.di.ittraining.tutoraccademico.TutorAccademico;
import it.unisa.di.ittraining.tutoraccademico.TutorAccademicoService;
import it.unisa.di.ittraining.utente.CognomeNonValidoException;
import it.unisa.di.ittraining.utente.DataDiNascitaNonValidaException;
import it.unisa.di.ittraining.utente.EmailEsistenteException;
import it.unisa.di.ittraining.utente.EmailNonValidaException;
import it.unisa.di.ittraining.utente.NomeNonValidoException;
import it.unisa.di.ittraining.utente.PasswordNonCorrispondentiException;
import it.unisa.di.ittraining.utente.PasswordNonValidaException;
import it.unisa.di.ittraining.utente.SessoNonValidoException;
import it.unisa.di.ittraining.utente.UsernameEsistenteException;
import it.unisa.di.ittraining.utente.UsernameNonValidoException;
import it.unisa.di.ittraining.utente.UtenteService;

@Controller
public class RegistrazioneController {

	@Autowired
	private StudentiService studentiService;

	@Autowired
	private ImpiegatoSegreteriaService segreteriaService;

	@Autowired
	private TutorAccademicoService tutorAccademicoService;

	@Autowired
	private AziendaService tutorAziendaleService;
	
	@Autowired
	private RegistrazioneStudenteFormValidator validator;
	
	@Autowired
	private RegistrazioneSTTFormValidator validatorSTT;
	
	@Autowired
	private UtenteService utentiService;

	@RequestMapping(value = "/richiesta-registrazione-studente", method = RequestMethod.POST)
	public String elaboraRichiestaIscrizioneStudente(@ModelAttribute("registrazioneStudente") RegistrazioneStudenteForm registrazioneStudente, BindingResult result, 
			RedirectAttributes redirectAttributes) 
			throws UsernameNonValidoException, UsernameEsistenteException, PasswordNonValidaException, 
			PasswordNonCorrispondentiException, EmailEsistenteException, EmailNonValidaException, NomeNonValidoException, 
			CognomeNonValidoException, SessoNonValidoException, MatricolaStudenteNonValidaException, MatricolaStudenteEsistenteException, DataDiNascitaNonValidaException {
		
		validator.validate(registrazioneStudente, result);
		

	    if (result.hasErrors()) {
	      redirectAttributes
	          .addFlashAttribute("org.springframework.validation.BindingResult.registrazioneStudente",
	                             result);
	      redirectAttributes.addFlashAttribute("registrazioneStudente", registrazioneStudente);
	      
	      return "redirect:/home";
	    }
		
	    // Genera un oggetto LocalDate a partire dagli interi presenti nel form
	    LocalDate date = LocalDate.of(registrazioneStudente.getAnnoNascita(),
	    		registrazioneStudente.getMeseNascita(),
	    		registrazioneStudente.getGiornoNascita());
	    
	    if(utentiService.validaPasswords(registrazioneStudente.getPassword(), registrazioneStudente.getConfermaPassword())) {
	    	
	    	Studente studente = new Studente();
		    studente.setNome(registrazioneStudente.getNome());
		    studente.setCognome(registrazioneStudente.getCognome());
		    studente.setEmail(registrazioneStudente.getEmail());
		    studente.setDataDiNascita(date);
		    studente.setMatricola(registrazioneStudente.getMatricola());
		    studente.setUsername(registrazioneStudente.getUsername());
		    studente.setPassword(registrazioneStudente.getPassword());
		    studente.setSesso(registrazioneStudente.getSesso());
			
			studentiService.registraStudente(studente);
	    	
	    }
	    
	    
		return "redirect:/home";	
	}

	@RequestMapping(value = "/richiesta-registrazione-segreteria", method = RequestMethod.POST)
	public String elaboraRichiestaIscrizioneSegreteria(HttpSession session, @ModelAttribute("registrazioneSegreteria") RegistrazioneSTTForm registrazioneSegreteria, BindingResult result, RedirectAttributes redirectAttributes) throws UsernameNonValidoException, 
	UsernameEsistenteException, PasswordNonValidaException, PasswordNonCorrispondentiException, EmailEsistenteException, EmailNonValidaException, NomeNonValidoException, 
	CognomeNonValidoException, SessoNonValidoException, MatricolaStudenteNonValidaException, MatricolaStudenteEsistenteException, DataDiNascitaNonValidaException {
		
		validatorSTT.validate(registrazioneSegreteria, result);
		

	    if (result.hasErrors()) {
	      
	      
	      return "redirect:/home";
	    }
		
		// Genera un oggetto LocalDate a partire dagli interi presenti nel form
	    LocalDate date = LocalDate.of(registrazioneSegreteria.getAnnoNascita(),
	    		registrazioneSegreteria.getMeseNascita(),
	    		registrazioneSegreteria.getGiornoNascita());
	    
		if(utentiService.validaPasswords(registrazioneSegreteria.getPassword(), registrazioneSegreteria.getConfermaPassword())) {
			
			ImpiegatoSegreteria impiegato = new ImpiegatoSegreteria();
			impiegato.setNome(registrazioneSegreteria.getNome());
			impiegato.setCognome(registrazioneSegreteria.getCognome());
			impiegato.setSesso(registrazioneSegreteria.getSesso());
			impiegato.setEmail(registrazioneSegreteria.getEmail());
			impiegato.setUsername(registrazioneSegreteria.getUsername());
			impiegato.setPassword(registrazioneSegreteria.getPassword());
			impiegato.setDataDiNascita(date);
			
			segreteriaService.registraImpiegato(impiegato);
			
		}
		
		
		return "redirect:/home";
	}

	@RequestMapping(value = "/richiesta-registrazione-accademico", method = RequestMethod.POST)
	public String elaboraRichiestaIscrizioneTutorAccademico(HttpSession session, @ModelAttribute("registrazioneAccademico") RegistrazioneSTTForm registrazioneAccademico, BindingResult result, RedirectAttributes redirectAttributes) throws PasswordNonValidaException,
	PasswordNonCorrispondentiException, UsernameNonValidoException, UsernameEsistenteException, EmailEsistenteException, EmailNonValidaException, NomeNonValidoException,
	SessoNonValidoException, CognomeNonValidoException, DataDiNascitaNonValidaException {
		
		validatorSTT.validate(registrazioneAccademico, result);
		

	    if (result.hasErrors()) {
	      
	      
	      return "redirect:/home";
	    }
		
	    // Genera un oggetto LocalDate a partire dagli interi presenti nel form
	    LocalDate date = LocalDate.of(registrazioneAccademico.getAnnoNascita(),
	    		registrazioneAccademico.getMeseNascita(),
	    		registrazioneAccademico.getGiornoNascita());
	    
	    if(utentiService.validaPasswords(registrazioneAccademico.getPassword(), registrazioneAccademico.getConfermaPassword())) {
	    	
	    	TutorAccademico tutor = new TutorAccademico();
	    	tutor.setNome(registrazioneAccademico.getNome());
	    	tutor.setCognome(registrazioneAccademico.getCognome());
	    	tutor.setEmail(registrazioneAccademico.getEmail());
	    	tutor.setDataDiNascita(date);
	    	tutor.setUsername(registrazioneAccademico.getUsername());
	    	tutor.setPassword(registrazioneAccademico.getPassword());
	    	tutor.setSesso(registrazioneAccademico.getSesso());
			
	    	tutorAccademicoService.registraTutorAccademico(tutor);
	    	
	    }
	    
	    return "redirect:/home";
	}
	
	@RequestMapping(value = "/richiesta-registrazione-aziendale", method = RequestMethod.POST)
	public String elaboraRichiestaIscrizioneTutorAziendale(HttpSession session, @ModelAttribute("registrazioneAziendale") RegistrazioneSTTForm registrazioneAziendale, BindingResult result, RedirectAttributes redirectAttributes) throws PasswordNonValidaException,
	PasswordNonCorrispondentiException, UsernameNonValidoException, UsernameEsistenteException, EmailEsistenteException, EmailNonValidaException,
	NomeNonValidoException, CognomeNonValidoException, SessoNonValidoException, DataDiNascitaNonValidaException {
		
		validatorSTT.validate(registrazioneAziendale, result);
		

	    if (result.hasErrors()) {
	      
	      
	      return "redirect:/home";
	    }
		
		// Genera un oggetto LocalDate a partire dagli interi presenti nel form
	    LocalDate date = LocalDate.of(registrazioneAziendale.getAnnoNascita(),
	    		registrazioneAziendale.getMeseNascita(),
	    		registrazioneAziendale.getGiornoNascita());
	    
	    if(utentiService.validaPasswords(registrazioneAziendale.getPassword(), registrazioneAziendale.getConfermaPassword())) {
	    	
	    	TutorAziendale tutor = new TutorAziendale();
	    	tutor.setNome(registrazioneAziendale.getNome());
	    	tutor.setCognome(registrazioneAziendale.getCognome());
	    	tutor.setEmail(registrazioneAziendale.getEmail());
	    	tutor.setDataDiNascita(date);
	    	tutor.setUsername(registrazioneAziendale.getUsername());
	    	tutor.setPassword(registrazioneAziendale.getPassword());
	    	tutor.setSesso(registrazioneAziendale.getSesso());
			
	    	tutorAziendaleService.registraTutorAziendale(tutor);
	    	
	    }
	    
	    return "redirect:/home";
	}
}
