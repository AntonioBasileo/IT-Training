package it.unisa.di.ittraining.studente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudenteRepository extends JpaRepository<Studente, String>  {
	
	public Studente findByUsernameAndPassword(String username, String password);
	
	public Studente findByUsername(String username);
	
	boolean existsByMatricola(String matricola);
	
	boolean existsByEmail(String email);
}
