package it.unisa.di.ittraining.tutoraccademico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorAccademicoRepository extends JpaRepository<TutorAccademico, String> {
	
	TutorAccademico findByUsername(String username);

	TutorAccademico findByUsernameAndPassword(String username, String password);
}
