package it.unisa.di.ittraining.web;

import java.time.DateTimeException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.unisa.di.ittraining.azienda.AziendaNonEsistenteException;
import it.unisa.di.ittraining.azienda.AziendaNonValidaException;
import it.unisa.di.ittraining.domandatirocinio.DataFinePrecedenteDataInizioException;
import it.unisa.di.ittraining.domandatirocinio.DataNonValidaException;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinioService;
import it.unisa.di.ittraining.domandatirocinio.MassimoNumeroCfuCumulabiliException;
import it.unisa.di.ittraining.domandatirocinio.NumeroCfuNonValidoException;

@Component
public class DomandaTirocinioFormValidator implements Validator {
	
	@Autowired
	private DomandaTirocinioService domandeService;

	/**
	 * Permette di definire le classi cui il validatore è applicabile.
	 */
	@Override
	public boolean supports(Class<?> clazz) {
	  return DomandaTirocinioForm.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		
		DomandaTirocinioForm form = (DomandaTirocinioForm) target;
		

		
		try {
		    if (form.getAnnoFine() == null
		        || form.getMeseFine() == null
		        || form.getGiornoFine() == null) {
		      throw new DataNonValidaException();
		    }
		      
		    LocalDate fine = LocalDate.of(form.getAnnoFine(),
		                                  form.getMeseFine(), 
		                                  form.getGiornoFine());
		    
		    LocalDate inizio = LocalDate.of(form.getAnnoInizio(),
                    form.getMeseInizio(), 
                    form.getGiornoInizio());
		      
		    try {
		    	
				domandeService.validaDataFine(inizio, fine);
		  } catch (DataFinePrecedenteDataInizioException e) {
			  
			// TODO Auto-generated catch block
			errors.rejectValue("giornoFine", "formDomanda.datafine.precedente");
		  }
		    
		  } catch (DataNonValidaException e) {
			
			errors.rejectValue("giornoFine", "formDomanda.datafine.nonValida");
		    
		  } catch(DateTimeException e) {
			  
			errors.rejectValue("giornoFine", "formDomanda.datafine.nonValida");
		  }
		

		try {
		    if (form.getAnnoInizio() == null
		        || form.getMeseInizio() == null
		        || form.getGiornoInizio() == null) {
		      throw new DataNonValidaException();
		    }
		      
		    LocalDate data_inizio = LocalDate.of(form.getAnnoInizio(),
		                                  form.getMeseInizio(), 
		                                  form.getGiornoInizio());
		      
		      domandeService.validaDataInizio(data_inizio);
		  } catch (DataNonValidaException e) {
			  
		    errors.rejectValue("giornoInizio", "formDomanda.datainizio.nonValida");
		  } catch(DateTimeException e) {
			  
			  errors.rejectValue("giornoInizio", "formDomanda.datainizio.nonValida");
		  }
		
		try {
			
			domandeService.validaNomeAzienda(form.getNomeAzienda());
		} catch (AziendaNonValidaException e) {
			
			// TODO Auto-generated catch block
			errors.rejectValue("nomeAzienda", "formDomanda.azienda.nonValida");
		} catch (AziendaNonEsistenteException e) {
			
			// TODO Auto-generated catch block
			errors.rejectValue("nomeAzienda", "formDomanda.azienda.nonEsistente");
		}
		
		try {
			domandeService.validaNumeroCfu(form.getCfu());
		} catch (MassimoNumeroCfuCumulabiliException e) {
			
			// TODO Auto-generated catch block
			errors.rejectValue("cfu", "formDomanda.cfu.eccesso");
		} catch (NumeroCfuNonValidoException e) {
			
			// TODO Auto-generated catch block
			errors.rejectValue("cfu", "formDomanda.cfu.nonValido");
		}
		
	}
}
