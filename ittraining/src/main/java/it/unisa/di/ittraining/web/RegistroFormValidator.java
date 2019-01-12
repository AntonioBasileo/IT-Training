package it.unisa.di.ittraining.web;

import it.unisa.di.ittraining.registrotirocinio.DataRegistroNonValidaException;
import it.unisa.di.ittraining.registrotirocinio.DataRegistroPrecedenteInizioException;
import it.unisa.di.ittraining.registrotirocinio.DataRegistroSuccessivaFineException;
import it.unisa.di.ittraining.registrotirocinio.MassimoNumeroOreException;
import it.unisa.di.ittraining.registrotirocinio.OrarioFinePrecedenteInizioException;
import it.unisa.di.ittraining.registrotirocinio.OrarioNonValidoException;
import it.unisa.di.ittraining.registrotirocinio.OrePrevisteSuperateException;
import it.unisa.di.ittraining.registrotirocinio.RegistroService;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RegistroFormValidator implements Validator {

  @Autowired
  private RegistroService registriService;
  /**
  * Permette di definire le classi cui poter applicare il validatore.
  */
  
  @Override
  public boolean supports(Class<?> clazz) {
    return RegistroForm.class.isAssignableFrom(clazz);
  }
  
  @Override
  public void validate(Object target, Errors errors) {
    RegistroForm form = (RegistroForm)target;
    
    try {
      if (form.getAnno() == null || form.getMese() == null 
          || form.getGiorno() == null 
          || form.getAnnoInizio() == null || form.getMeseInizio() ==  null 
          || form.getGiornoInizio() == null
          || form.getAnnoFine() == null || form.getMeseFine() == null 
          || form.getGiornoFine() == null) {

        throw new DataRegistroNonValidaException("Il campo di qualche data non "
           + "è stato compilato come dovuto");
      }
      LocalDate data = LocalDate.of(form.getAnno(), form.getMese(), form.getGiorno());
      LocalDate inizio = LocalDate.of(form.getAnnoInizio(), form.getMeseInizio(), 
           form.getGiornoInizio());
      LocalDate fine = LocalDate.of(form.getAnnoFine(), form.getMeseFine(), form.getGiornoFine());

      registriService.validaDataRegistro(data, inizio, fine);
    } catch (DataRegistroSuccessivaFineException e) {
      // TODO Auto-generated catch block
      errors.rejectValue("anno", "formRegistro.data.dopoFine");
    } catch (DataRegistroPrecedenteInizioException e) {
      // TODO Auto-generated catch block
      errors.rejectValue("anno", "formRegistro.data.precedenteInizio");
    } catch (DataRegistroNonValidaException | DateTimeException e) {
      // TODO Auto-generated catch block
      errors.rejectValue("anno", "formRegistro.data.nonValida");
    }
   
    try {
      if (form.getOraInizio() == null || form.getMinutoInizio() == null 
          || form.getOraFine() == null || form.getMinutoFine() == null) {

        throw new OrarioNonValidoException("Alcuni campi degli orari non "
             + "sono stati compilati correttamente");
      }
      LocalTime orarioInizio = LocalTime.of(form.getOraInizio(), form.getMinutoInizio());
      LocalTime orarioFine = LocalTime.of(form.getOraFine(), form.getMinutoFine());
      registriService.validaOrarioInizio(orarioInizio, orarioFine);
    } catch (OrarioNonValidoException e) {
      // TODO Auto-generated catch block
      errors.rejectValue("oraInizio", "formRegistro.orario.inizioNonValido");
    } catch (OrarioFinePrecedenteInizioException e) {
      // TODO Auto-generated catch block
      errors.rejectValue("oraInizio", "formRegistro.orario.inizioSuccessivo");
    }

    try {
      if (form.getOraInizio() == null || form.getMinutoInizio() == null 
           || form.getOraFine() == null || form.getMinutoFine() == null) {

        throw new OrarioNonValidoException("Alcuni campi degli orari non "
             + "sono stati compilati correttamente");
      }

      LocalTime orarioInizio = LocalTime.of(form.getOraInizio(), form.getMinutoInizio());
      LocalTime orarioFine = LocalTime.of(form.getOraFine(), form.getMinutoFine());
      registriService.validaOrarioFine(orarioInizio, orarioFine);
    } catch (OrarioNonValidoException e) {
      // TODO Auto-generated catch block
      errors.rejectValue("oraFine", "formRegistro.orario.fineNonValido");
    } catch (OrarioFinePrecedenteInizioException e) {
      // TODO Auto-generated catch block
      errors.rejectValue("oraFine", "formRegistro.orario.finePrecedente");
    }

    try {
      LocalTime orarioInizio = LocalTime.of(form.getOraInizio(), form.getMinutoInizio());
      LocalTime orarioFine = LocalTime.of(form.getOraFine(), form.getMinutoFine());
      registriService.validaNumeroOre(orarioInizio, orarioFine);
    } catch (MassimoNumeroOreException e) {
      // TODO Auto-generated catch block
      errors.rejectValue("oraFine", "formRegistro.ore.eccessive");
    }
    
    try {
      LocalTime orarioInizio = LocalTime.of(form.getOraInizio(), form.getMinutoInizio());
      LocalTime orarioFine = LocalTime.of(form.getOraFine(), form.getMinutoFine());
      registriService.verificaNumeroOreRegistro(orarioInizio, orarioFine, form.getIdDomanda());
    } catch (OrePrevisteSuperateException e) {
      // TODO Auto-generated catch block
      errors.rejectValue("oraFine", "formRegistro.oretotali.eccessive");
    }
  }
}
