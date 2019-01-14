package it.unisa.di.ittraining.web;

import it.unisa.di.ittraining.azienda.AziendaNonEsistenteException;
import it.unisa.di.ittraining.azienda.AziendaNonValidaException;
import it.unisa.di.ittraining.azienda.TutorAziendale;
import it.unisa.di.ittraining.domandatirocinio.DataFinePrecedenteDataInizioException;
import it.unisa.di.ittraining.domandatirocinio.DataNonValidaException;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinioService;
import it.unisa.di.ittraining.domandatirocinio.MassimoNumeroCfuCumulabiliException;
import it.unisa.di.ittraining.domandatirocinio.NumeroCfuNonValidoException;
import it.unisa.di.ittraining.tutoraccademico.TutorAccademico;
import it.unisa.di.ittraining.utente.DataDiNascitaNonValidaException;
import it.unisa.di.ittraining.utente.UtenteService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DomandaTirocinioController {

  @Autowired
  private UtenteService utentiService;

  @Autowired
  private DomandaTirocinioService domandeService;

  @Autowired
  private DomandaTirocinioFormValidator validator;

  /**
  * Permette di mostrare il form per la compilazione della domanda di tirocinio.
  */
  @RequestMapping(value = "/home/compila-domanda-form", method = RequestMethod.GET)
  public String showDomandaTirocinioForm(HttpServletRequest request, Model model) {

    if (utentiService.getUtenteAutenticato() == null 
        || !(utentiService.getUtenteAutenticato().getClass().getSimpleName().equals("Studente"))) {
      return "not-available";
    }

    if (!model.containsAttribute("domandaForm")) {
      model.addAttribute("domandaForm", new DomandaTirocinioForm());
    }

    if (request.getParameter("azienda") != null) {
      model.addAttribute("nomeAzienda", request.getParameter("azienda"));
    }
    
    return "compila-domanda";
  }

  /**
  * Permette elaborare i dati inseriti all'interno del form, validarli attraverso il validator
  * e, se non ci sono errori, aggiungere la domanda di tirocinio all'interno del Database.
  */
  @RequestMapping(value = "/home/compila-domanda-form/compila-domanda", 
      method = RequestMethod.POST)
  public String elaboraDomandaTirocinio(@ModelAttribute("domandaForm") 
      DomandaTirocinioForm domandaForm, BindingResult result, Model model, 
      RedirectAttributes redirectAttributes) throws AziendaNonValidaException,
      AziendaNonEsistenteException, DataDiNascitaNonValidaException, 
      DataNonValidaException, DataFinePrecedenteDataInizioException, 
      MassimoNumeroCfuCumulabiliException, NumeroCfuNonValidoException {

    validator.validate(domandaForm, result);

    if (result.hasErrors()) {
      redirectAttributes
          .addFlashAttribute("org.springframework.validation.BindingResult.domandaForm",
          result);
      redirectAttributes.addFlashAttribute("domandaForm", domandaForm);
      redirectAttributes.addFlashAttribute("testoNotifica", "toast.compildomanda.nonValida");
      return "redirect:/home/compila-domanda-form";
    }

    DomandaTirocinio domanda = new DomandaTirocinio();

    // Genera un oggetto LocalDate a partire dagli interi presenti nel form
    LocalDate inizio = LocalDate.of(domandaForm.getAnnoInizio(),
        domandaForm.getMeseInizio(),
        domandaForm.getGiornoInizio());

    // Genera un oggetto LocalDate a partire dagli interi presenti nel form
    LocalDate fine = LocalDate.of(domandaForm.getAnnoFine(),
        domandaForm.getMeseFine(),
        domandaForm.getGiornoFine());
    
    LocalDate oggi = LocalDate.now();
    
    domanda.setData(oggi.plusDays(1));
    domanda.setInizioTirocinio(inizio.plusDays(1));
    domanda.setFineTirocinio(fine.plusDays(1));
    domanda.setCfu(domandaForm.getCfu());
    domanda.setStatus(DomandaTirocinio.IN_ATTESA);

    domandeService.registraDomanda(domanda, domandaForm.getNomeAzienda());

    redirectAttributes.addFlashAttribute("testoNotifica", "toast.compiladomanda.valida");

    return "redirect:/home";
  }

  /**
  * Permette di mostrare la lista delle domande inviate dallo studente.
  */
  @RequestMapping(value = "/home/mostra-domande-studente", method = RequestMethod.GET)
  public String elencaDomandeStudente(HttpSession session, Model model) {

    if (utentiService.getUtenteAutenticato() == null 
        || !(utentiService.getUtenteAutenticato().getClass().getSimpleName().equals("Studente"))) {
      return "not-available";
    }

    if (!model.containsAttribute("listaDomandeStudente")) {
      List<DomandaTirocinio> domande = 
          domandeService.elencaDomandeStudente((String)session.getAttribute("username"));

      Collections.sort(domande);

      model.addAttribute("listaDomandeStudente", domande);

    }

    return "lista-domande-studente";
  }

  /**
  * Permette al tutor aziendale di visualizzare le domande verso l'azienda
  * alla quale appartiene.
  */
  @RequestMapping(value = "/home/mostra-domande-aziendale", method = RequestMethod.GET)
  public String elencaDomandeAziendali(Model model) {

    if (utentiService.getUtenteAutenticato() == null 
          || !(utentiService.getUtenteAutenticato().getClass().getSimpleName()
             .equals("TutorAziendale"))) {
      return "not-available";
    }

    if (!model.containsAttribute("listaDomandeAzienda")) {
      TutorAziendale tutor = (TutorAziendale)utentiService.getUtenteAutenticato();
      List<DomandaTirocinio> domande = domandeService.elencaDomandeAziendali(tutor.getAzienda());

      Collections.sort(domande);

      model.addAttribute("listaDomandeAzienda", domande);
    }

    if (!model.containsAttribute("progettoFormAccetta")) {
      model.addAttribute("progettoFormAccetta", new ProgettoFormativoForm());
    }
    
    if (!model.containsAttribute("progettoFormRifiuta")) {
      model.addAttribute("progettoFormRifiuta", new ProgettoFormativoForm());
    }
    
    return "lista-domande-aziendale";
  }

  /**
  * Permette al tutor aziendale di rifiutare una domanda di tirocinio.
  */
  @RequestMapping(value = "/home/mostra-domande-aziendale/rifiuta-domanda", 
      method = RequestMethod.POST)
  public String rifiutaDomanda(@ModelAttribute("progettoFormRifiuta") ProgettoFormativoForm form, 
        BindingResult result, RedirectAttributes redirectAttributes) {

    domandeService.aggiornaStatoDomanda(form.getIdDomanda(), DomandaTirocinio.RIFIUTATA_AZIENDA);

    redirectAttributes.addFlashAttribute("testoNotifica", "toast.domanda.rifiutata");

    return "redirect:/home/mostra-domande-aziendale";
  }

  /**
  * Permette al tutor accademico di visualizzare le domande inviate dai suoi studenti.
  */
  @RequestMapping(value = "/home/mostra-domande-accademico", method = RequestMethod.GET)
  public String mostraDomandeAccademico(Model model) {

    if (utentiService.getUtenteAutenticato() == null 
        || !(utentiService.getUtenteAutenticato().getClass().getSimpleName()
          .equals("TutorAccademico"))) {
      return "not-available";
    }

    if (!model.containsAttribute("listaDomandeAccademico")) {
      TutorAccademico tutor = (TutorAccademico)utentiService.getUtenteAutenticato();
      Collections.sort(tutor.getAllDomande());

      model.addAttribute("listaDomandeAccademico", tutor.getAllDomande());

    }

    return "lista-domande-accademico";
  }

  /**
  * Permette alla segreteria di visualizzare tutte le domande inviate dagli studenti.
  */
  @RequestMapping(value = "/home/mostra-domande-segreteria", method = RequestMethod.GET)
  public String mostraDomandeSegreteria(Model model) {

    if (utentiService.getUtenteAutenticato() == null 
        || !(utentiService.getUtenteAutenticato().getClass().getSimpleName()
          .equals("ImpiegatoSegreteria"))) {
      return "not-available";
    }

    if (!model.containsAttribute("listaDomandeSegreteria")) {
      model.addAttribute("listaDomandeSegreteria", domandeService.getAll());
    }
    return "lista-domande-segreteria";
  }
}
