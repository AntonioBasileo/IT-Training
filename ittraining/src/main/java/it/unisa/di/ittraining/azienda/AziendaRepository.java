package it.unisa.di.ittraining.azienda;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AziendaRepository extends JpaRepository<Azienda, String> {

  List<Azienda> findAll();

  Azienda findByNome(String nome);

  boolean existsByEmail(String email);

  boolean existsByNomeAndEmail(String nome, String email);

  boolean existsByNome(String nome);

}
