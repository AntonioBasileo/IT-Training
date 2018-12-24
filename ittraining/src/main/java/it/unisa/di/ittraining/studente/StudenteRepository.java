package it.unisa.di.ittraining.studente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudenteRepository extends JpaRepository<Studente, String>  {
	
	public Studente findByUsername(String username);
	
	public Studente findByUsernameAndNome(String username, String cognome);
	
	boolean existsByMatricola(String matricola);
}
