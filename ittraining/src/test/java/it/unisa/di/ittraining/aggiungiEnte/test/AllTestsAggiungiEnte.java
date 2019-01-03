package it.unisa.di.ittraining.aggiungiEnte.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({AggiungiEnteCittaNonValidaTest.class, AggiungiEnteEmailTutorNonValidaTest.class,
	AggiungiEnteIndirizzoNonValidoTest.class, AggiungiEnteNomeNonValidoTest.class, AggiungiEnteSuccessoTest.class})
public class AllTestsAggiungiEnte {

}
