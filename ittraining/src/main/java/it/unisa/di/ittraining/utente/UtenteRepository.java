package it.unisa.di.ittraining.utente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, String> {

	boolean existsByUsername(String username);
	
	boolean existsByEmail(String emanil);
}
