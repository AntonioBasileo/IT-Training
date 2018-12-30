package it.unisa.di.ittraining.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.unisa.di.ittraining.utente.PasswordErrataException;
import it.unisa.di.ittraining.utente.UsernameNonEsistenteException;
import it.unisa.di.ittraining.utente.UtenteService;

@Controller
public class UtenteController {
	
	@Autowired
	private UtenteService utenteService;
	
	@RequestMapping(value = "/login-form")
	public String showLoginForm(HttpSession session, Model model) {
		
		if(!model.containsAttribute("loginForm"))
			model.addAttribute("loginForm", new LoginForm());
		
		if(utenteService.getUtenteAutenticato() != null) {
			if(!model.containsAttribute("nomeUtente"))
				model.addAttribute("nomeUtente", utenteService.getUtenteAutenticato().getNome() + " " + utenteService.getUtenteAutenticato().getCognome());
			
			return "already-logged";
		}
		
		
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
}
