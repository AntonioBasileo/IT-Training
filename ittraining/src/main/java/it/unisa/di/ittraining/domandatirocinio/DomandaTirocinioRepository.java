package it.unisa.di.ittraining.domandatirocinio;

import it.unisa.di.ittraining.azienda.Azienda;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomandaTirocinioRepository extends JpaRepository<DomandaTirocinio, Long> {

  List<DomandaTirocinio> findAllByStudenteUsername(String username);

  List<DomandaTirocinio> findAllByStudenteUsernameAndStatus(String username, int status);

  List<DomandaTirocinio> findAllByAzienda(Azienda azienda);
  
  List<DomandaTirocinio> findAllByAziendaAndStatus(Azienda azienda, int status);

  DomandaTirocinio findById(long id);
  
  List<DomandaTirocinio> findAllByStatus(int status);

  List<DomandaTirocinio> findAll();

  void delete(DomandaTirocinio domanda);
}
