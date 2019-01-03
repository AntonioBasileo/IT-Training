package it.unisa.di.ittraining.registrazioneStudente.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({RegistrazioneStudenteNomeNonValidoTest.class, RegistrazioneStudenteDataDiNascitaNonValidaTest.class,
	RegistrazioneStudenteEmailNonValidaTest.class, RegistrazioneStudenteMatricolaNonValidaTest.class, 
	RegistrazioneStudentePasswordNonValidaTest.class, RegistrazioneStudenteSuccessoTest.class})

public class AllTestsRegistrazioneStudente {

}
