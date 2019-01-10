package it.unisa.di.ittraining.web;

import it.unisa.di.ittraining.studente.MatricolaStudenteEsistenteException;
import it.unisa.di.ittraining.studente.MatricolaStudenteNonValidaException;
import it.unisa.di.ittraining.studente.Studente;
import it.unisa.di.ittraining.tutoraccademico.TutorAccademicoService;
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



@Controller
public class UtenteController {

  @Autowired

  private UtenteService utenteService;

  @Autowired
  private TutorAccademicoService tutorService;

  
  @RequestMapping(value = "/login-form", method = RequestMethod.GET)

  public String showLoginForm(HttpSession session, Model model) {

    if (utenteService.getUtenteAutenticato() != null) {

      return "not-available";
    }
    

    if (!model.containsAttribute("loginForm")) {

      model.addAttribute("loginForm", new LoginForm());
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

      redirectAttributes.addFlashAttribute("testoNotifica", "toast.login.valido");

    } catch (UsernameNonEsistenteException e) {

      result.rejectValue("password", "formLogin.username.nonValido");

      redirectAttributes
          .addFlashAttribute("org.springframework.validation.BindingResult.loginForm", result);

      redirectAttributes.addFlashAttribute("loginForm", loginForm);

      redirectAttributes.addFlashAttribute("testoNotifica", "toast.login.nonValido");

      return "redirect:/login";

    } catch (PasswordErrataException e) {

      result.rejectValue("password", "formLogin.password.nonValida");

      redirectAttributes
              .addFlashAttribute("org.springframework.validation.BindingResult.loginForm", result);

      redirectAttributes.addFlashAttribute("loginForm", loginForm);

      redirectAttributes.addFlashAttribute("testoNotifica", "toast.login.nonValido");

      return "redirect:/login";
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


    if (utenteService.getUtenteAutenticato() == null
        || !(utenteService.getUtenteAutenticato()
        .getClass().getSimpleName().equals("Studente"))) {

      return "not-available";
    }


    if (!model.containsAttribute("studente")) {

      model.addAttribute("studente", ((Studente)utenteService.getUtenteAutenticato()));
    }

    if (!model.containsAttribute("listaTutor")) {

      model.addAttribute("listaTutor", tutorService.elencaTutorAccademici());
    }

    return "/lista-tutor";
  }

  @RequestMapping(value = "/scegli-tutor", method = RequestMethod.GET)
  
  public String scegliTutorAccademico(@RequestParam String op) throws NomeNonValidoException,
      NomeCognomeTroppoLungoException, NomeCognomeTroppoCortoException,
      CognomeNonValidoException,
      EmailNonValidaException, EmailEsistenteException,
      TelefonoNonValidoException, DataDiNascitaNonValidaException,
      PasswordNonValidaException, PasswordNonCorrispondentiException,
      SessoNonValidoException, UsernameNonValidoException,
      UsernameEsistenteException, MatricolaStudenteNonValidaException,
      MatricolaStudenteEsistenteException {


    if (utenteService.getUtenteAutenticato() == null
        || !(utenteService.getUtenteAutenticato()
        .getClass().getSimpleName().equals("Studente"))) {

      return "not-available";
    }

    tutorService.associaTutorAccademico(op);

    return "redirect:/lista-tutor";
  }
}
