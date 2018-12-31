package it.unisa.di.ittraining.domandatirocinio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomandaTirocinioRepository extends JpaRepository<DomandaTirocinio, Long> {
	
	List<DomandaTirocinio> findAllByStudenteUsername(String username);
	
	List<DomandaTirocinio> findAllByStudenteUsernameAndStatus(String username, int status);
	
	List<DomandaTirocinio> findAll();
}
