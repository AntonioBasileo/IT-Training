package it.unisa.di.ittraining.web;

import it.unisa.di.ittraining.utente.UtenteService;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

  @Autowired
  private UtenteService utentiService;


  /**
  * Permette agli utenti di visualizzare la pagina principale.
  */
  @RequestMapping(value = "/home", method = RequestMethod.GET)
  public String getHome(HttpSession session, Model model) {

    if (utentiService.getUtenteAutenticato() != null) {

      if (!model.containsAttribute("utente")) {

        model.addAttribute("utente", utentiService.getUtenteAutenticato());
      }
    }

    return "home";
  }
}
