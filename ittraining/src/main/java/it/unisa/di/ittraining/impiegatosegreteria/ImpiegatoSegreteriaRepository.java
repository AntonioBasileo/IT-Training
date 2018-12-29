package it.unisa.di.ittraining.impiegatosegreteria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpiegatoSegreteriaRepository extends JpaRepository<ImpiegatoSegreteria, String> {
	
	public ImpiegatoSegreteria findByUsernameAndPassword(String username, String password);

	public ImpiegatoSegreteria findByUsername(String username);
}
