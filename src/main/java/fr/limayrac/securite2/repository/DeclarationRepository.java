package fr.limayrac.securite2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.limayrac.securite2.model.Declaration;
import fr.limayrac.securite2.model.User;

public interface DeclarationRepository extends JpaRepository<Declaration, Long> {

    List<Declaration> findByidUser(User idUser);
    List<Declaration> findByStatut(String statut);
    
}

