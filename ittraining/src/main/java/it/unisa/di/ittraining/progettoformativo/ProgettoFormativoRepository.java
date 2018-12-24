package it.unisa.di.ittraining.progettoformativo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgettoFormativoRepository extends JpaRepository<ProgettoFormativo, String> {

}
