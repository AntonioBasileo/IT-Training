package it.unisa.di.ittraining.impiegatosegreteria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpiegatoSegreteriaRepository extends JpaRepository<ImpiegatoSegreteria, String> {
	
	ImpiegatoSegreteria findByUsernameAndPassword(String username, String password);

	ImpiegatoSegreteria findByUsername(String username);
	
	boolean existsByEmail(String email);
}
