package it.unisa.di.ittraining.web;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.unisa.di.ittraining.azienda.AziendaService;
import it.unisa.di.ittraining.azienda.TutorAziendale;
import it.unisa.di.ittraining.impiegatosegreteria.ImpiegatoSegreteria;
import it.unisa.di.ittraining.impiegatosegreteria.ImpiegatoSegreteriaService;
import it.unisa.di.ittraining.studente.Studente;
import it.unisa.di.ittraining.studente.StudentiService;
import it.unisa.di.ittraining.tutoraccademico.TutorAccademico;
import it.unisa.di.ittraining.tutoraccademico.TutorAccademicoService;
import it.unisa.di.ittraining.utente.PasswordNonCorrispondentiException;
import it.unisa.di.ittraining.utente.PasswordNonValidaException;
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
	private AziendaService aziendeService;
	
	@Autowired
	private RegistrazioneStudenteFormValidator validatorStudente;
	
	@Autowired
	private RegistrazioneImpiegatoFormValidator validatorImpiegato;
	
	@Autowired
	private RegistrazioneAccademicoFormValidator validatorAccademico;
	
	@Autowired
	private RegistrazioneAziendaleFormValidator validatorAziendale;
	
	@Autowired
	private UtenteService utentiService;
	
	
	@RequestMapping(value = "/registrazione-studente-form", method = RequestMethod.GET)
	public String showRegistrazioneStudenteForm(Model model) {
		
		if(utentiService.getUtenteAutenticato() != null)
			return "not-available";
		
		if (!model.containsAttribute("registrazioneStudente")) {
			model.addAttribute("registrazioneStudente", new RegistrazioneStudenteForm());
			
		}
		
		return "registrazione-studente";
	}

	@RequestMapping(value = "/registrazione-segreteria-form", method = RequestMethod.GET)
	public String showRegistrazioneSegreteriaForm(Model model) {
		
		if(utentiService.getUtenteAutenticato() != null)
			return "not-available";

		if (!model.containsAttribute("registrazioneSegreteria")) {
			model.addAttribute("registrazioneSegreteria", new RegistrazioneForm());
		}
		
		return "registrazione-segreteria";
	}

	@RequestMapping(value = "/registrazione-accademico-form", method = RequestMethod.GET)
	public String showRegistrazioneTutorAccademicoForm(Model model) {
		
		if(utentiService.getUtenteAutenticato() != null)
			return "not-available";

		if (!model.containsAttribute("registrazioneAccademico")) {
			model.addAttribute("registrazioneAccademico", new RegistrazioneForm());
		}
		
		return "registrazione-tutor-accademico";
	}

	@RequestMapping(value = "/registrazione-aziendale-form", method = RequestMethod.GET)
	public String showRegistrazioneTutorAziendaleForm(Model model) {
		
		if(utentiService.getUtenteAutenticato() != null)
			return "not-available";

		if (!model.containsAttribute("registrazioneAziendale")) {
			model.addAttribute("registrazioneAziendale", new RegistrazioneAziendaleForm());
		}
		
		return "registrazione-tutor-aziendale";
	}
	
	
	

	@RequestMapping(value = "/richiesta-registrazione-studente", method = RequestMethod.POST)
	public String elaboraRichiestaIscrizioneStudente(@ModelAttribute("registrazioneStudente") RegistrazioneStudenteForm registrazioneStudente, Model model, BindingResult result, 
			RedirectAttributes redirectAttributes) throws PasswordNonValidaException, PasswordNonCorrispondentiException {
		
		validatorStudente.validate(registrazioneStudente, result);
		

	    if (result.hasErrors()) {
	      redirectAttributes
	          .addFlashAttribute("org.springframework.validation.BindingResult.registrazioneStudente",
	                             result);
	      redirectAttributes.addFlashAttribute("registrazioneStudente", registrazioneStudente);
	      
	      return "registrazione-studente";
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
		    studente.setTelefono(registrazioneStudente.getTelefono());
			
			studentiService.registraStudente(studente);
	    	
	    }
	    
		return "redirect:/home";	
	}

	@RequestMapping(value = "/richiesta-registrazione-segreteria", method = RequestMethod.POST)
	public String elaboraRichiestaIscrizioneSegreteria(HttpSession session, @ModelAttribute("registrazioneSegreteria") RegistrazioneForm registrazioneSegreteria, Model model,
			BindingResult result, RedirectAttributes redirectAttributes) throws PasswordNonValidaException, PasswordNonCorrispondentiException {
		
		validatorImpiegato.validate(registrazioneSegreteria, result);


	    if (result.hasErrors()) {
	      redirectAttributes
	          .addFlashAttribute("org.springframework.validation.BindingResult.registrazioneSegreteria",
	                             result);
	      redirectAttributes.addFlashAttribute("registrazioneSegreteria", registrazioneSegreteria);
	      
	      return "registrazione-segreteria";
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
			impiegato.setTelefono(registrazioneSegreteria.getTelefono());
			
			segreteriaService.registraImpiegato(impiegato);
			
		}
		
		return "redirect:/home";
	}

	@RequestMapping(value = "/richiesta-registrazione-accademico", method = RequestMethod.POST)
	public String elaboraRichiestaIscrizioneTutorAccademico(HttpSession session, @ModelAttribute("registrazioneAccademico") RegistrazioneForm registrazioneAccademico, Model model, 
			BindingResult result, RedirectAttributes redirectAttributes) throws PasswordNonValidaException, PasswordNonCorrispondentiException {
		
		validatorAccademico.validate(registrazioneAccademico, result);
		

		if (result.hasErrors()) {
		      redirectAttributes
		          .addFlashAttribute("org.springframework.validation.BindingResult.registrazioneAccademico",
		                             result);
		      redirectAttributes.addFlashAttribute("registrazioneAccademico", registrazioneAccademico);
		      
		      return "registrazione-tutor-accademico";
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
	    	tutor.setTelefono(registrazioneAccademico.getTelefono());
			
	    	tutorAccademicoService.registraTutorAccademico(tutor);
	    	
	    }
	    
	    return "redirect:/home";
	}
	
	@RequestMapping(value = "/richiesta-registrazione-aziendale", method = RequestMethod.POST)
	public String elaboraRichiestaIscrizioneTutorAziendale(HttpSession session, @ModelAttribute("registrazioneAziendale") RegistrazioneAziendaleForm registrazioneAziendale, Model model,
			BindingResult result, RedirectAttributes redirectAttributes) throws PasswordNonValidaException, PasswordNonCorrispondentiException {
		
		validatorAziendale.validate(registrazioneAziendale, result);
		

		if (result.hasErrors()) {
		      redirectAttributes
		          .addFlashAttribute("org.springframework.validation.BindingResult.registrazioneAziendale",
		                             result);
		      redirectAttributes.addFlashAttribute("registrazioneAziendale", registrazioneAziendale);
		      
		      return "registrazione-tutor-aziendale";
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
	    	tutor.setTelefono(registrazioneAziendale.getTelefono());
	    	tutor.setAzienda(aziendeService.getAziendaByNome(registrazioneAziendale.getNomeAzienda()));
	    	
	    	aziendeService.getAziendaByNome(registrazioneAziendale.getNomeAzienda()).setTutorAziendale(tutor);
	    	
	    	aziendeService.registraAzienda(aziendeService.getAziendaByNome(registrazioneAziendale.getNomeAzienda()));
			
	    	aziendeService.registraTutorAziendale(tutor);
	    	
	    }
	    
	    return "redirect:/home";
	}
}
