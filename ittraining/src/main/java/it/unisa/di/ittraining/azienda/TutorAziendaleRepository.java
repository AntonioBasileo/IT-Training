package it.unisa.di.ittraining.azienda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorAziendaleRepository extends JpaRepository<TutorAziendale, String> {

	public TutorAziendale findByUsername(String username);
}
