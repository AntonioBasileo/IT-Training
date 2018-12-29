package it.unisa.di.ittraining.tutoraccademico;

import org.springframework.data.jpa.repository.JpaRepository;

import it.unisa.di.ittraining.azienda.TutorAziendale;

public interface TutorAccademicoRepository extends JpaRepository<TutorAccademico, String> {

	public TutorAziendale findByUsernameAndPassword(String username, String password);
}
