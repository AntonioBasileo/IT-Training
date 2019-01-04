package it.unisa.di.ittraining.progettoFormativo.test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinio;
import it.unisa.di.ittraining.domandatirocinio.DomandaTirocinioRepository;
import it.unisa.di.ittraining.progettoformativo.ProgettoFormativoRepository;
import it.unisa.di.ittraining.progettoformativo.ProgettoFormativoService;

@RunWith(MockitoJUnitRunner.class)
public class CompilaProgettoFormativoTest {

	@InjectMocks
	private ProgettoFormativoService progettiService;
	
	@Mock
	private DomandaTirocinioRepository domandeRep;
	
	@Mock
	private ProgettoFormativoRepository progettiRep;
	
	
	
}
