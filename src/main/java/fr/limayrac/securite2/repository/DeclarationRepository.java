package fr.limayrac.securite2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.limayrac.securite2.model.Declaration;

public interface DeclarationRepository extends JpaRepository<Declaration, Long> {

}

