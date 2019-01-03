package it.unisa.di.ittraining.domandatirocinio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unisa.di.ittraining.azienda.Azienda;

@Repository
public interface DomandaTirocinioRepository extends JpaRepository<DomandaTirocinio, Long> {
	
	List<DomandaTirocinio> findAllByStudenteUsername(String username);
	
	List<DomandaTirocinio> findAllByStudenteUsernameAndStatus(String username, int status);
	
	List<DomandaTirocinio> findAllByAzienda(Azienda azienda);
	
	DomandaTirocinio findById(long id);
	
	List<DomandaTirocinio> findAll();
	
	void delete(DomandaTirocinio domanda);
}
