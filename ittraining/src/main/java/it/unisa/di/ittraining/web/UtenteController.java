package it.unisa.di.ittraining.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.unisa.di.ittraining.studente.Studente;
import it.unisa.di.ittraining.studente.StudentiService;
import it.unisa.di.ittraining.tutoraccademico.TutorAccademico;
import it.unisa.di.ittraining.tutoraccademico.TutorAccademicoService;
import it.unisa.di.ittraining.utente.PasswordErrataException;
import it.unisa.di.ittraining.utente.UsernameNonEsistenteException;
import it.unisa.di.ittraining.utente.UtenteService;

@Controller
public class UtenteController {
	
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private TutorAccademicoService tutorService;
	
	@Autowired
	private StudentiService studentiService;
	
	
	@RequestMapping(value = "/login-form")
	public String showLoginForm(HttpSession session, Model model) {
		
		if(!model.containsAttribute("loginForm"))
			model.addAttribute("loginForm", new LoginForm());
		
		
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	 public String login(HttpSession session,
             @ModelAttribute("loginForm") LoginForm loginForm,
             BindingResult result, Model model,
             RedirectAttributes redirectAttributes) {
		
		
		try {
			
			utenteService.login(loginForm.getUsername(), loginForm.getPassword());
			
			session.setAttribute("username", loginForm.getUsername());
		} catch (UsernameNonEsistenteException e) {
			// TODO Auto-generated catch block
			result.rejectValue("password", "formLogin.username.nonValido");
			
			redirectAttributes
	          .addFlashAttribute("org.springframework.validation.BindingResult.loginForm",
	                             result);
			
			redirectAttributes.addFlashAttribute("loginForm", loginForm);
			
			return "login";
			
		} catch (PasswordErrataException e) {
			// TODO Auto-generated catch block
			result.rejectValue("password", "formLogin.password.nonValida");
			
			redirectAttributes
	          .addFlashAttribute("org.springframework.validation.BindingResult.loginForm",
	                             result);
			
			redirectAttributes.addFlashAttribute("loginForm", loginForm);
			
			return "login";
		}
		
		
		return "redirect:/home";
	}
	
	 @RequestMapping(value = "/logout", method = RequestMethod.GET)
	 public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
		 
	   if (utenteService.getUtenteAutenticato() != null) {
		   
	     session.setAttribute("username", null);
	     
	     utenteService.logout();
	   }
	    
	   return "redirect:/home";
	 }
	 
	 @RequestMapping(value = "/lista-tutor", method = RequestMethod.GET)
	 public String mostraElencoTutorAccademici(Model model) {
		 
		 if(!model.containsAttribute("tutor"))
			 model.addAttribute("tutor", ((Studente)utenteService.getUtenteAutenticato()).getTutor());
		 
		 if(!model.containsAttribute("listaTutor"))
			 model.addAttribute("listaTutor", tutorService.elencaTutorAccademici());
		 
		 return "/lista-tutor";
	 }
	 
	 @RequestMapping(value = "/scegli-tutor", method = RequestMethod.GET)
	 public String scegliTutorAccademico(@RequestParam String op) {
		 
		 TutorAccademico tutor = tutorService.findByUsername(op);
		 Studente studente = (Studente)utenteService.getUtenteAutenticato();
		 
		 tutor.getStudenti().add(studente);
		 studente.setTutor(tutor);
		 
		 studentiService.registraStudente(studente);
		 tutorService.registraTutorAccademico(tutor);
		 
		 return "/lista-tutor";
	 }
}
