package it.unisa.di.ittraining.registrotirocinio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {

  Registro findById(long id);
}
