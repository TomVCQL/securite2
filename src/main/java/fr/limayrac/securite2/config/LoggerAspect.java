package fr.limayrac.securite2.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import fr.limayrac.securite2.model.Declaration;
import fr.limayrac.securite2.model.User;

@Aspect
@Component
public class LoggerAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    @AfterReturning(pointcut = "execution(* fr.limayrac.securite2.controller.Controller.NumDossier.saveDecla(..))", returning = "declaration")
    public void SaveDeclaration(Object declaration) {
    
        Declaration declaration2 = (Declaration) declaration;
        logger.info("Les frais de transport sont de : {} euros",declaration2.getPrixTransport());
        logger.info("Les frais de restauration sont de : {} euros",declaration2.getPrixRestauration());

        int somme = Integer.parseInt(declaration2.getPrixHebergement()) + Integer.parseInt(declaration2.getPrixRestauration()) + Integer.parseInt(declaration2.getPrixTransport());
        logger.info("Les frais de hébergement sont de : {} euros pour un total de frais de {} euros",declaration2.getPrixHebergement(),somme);
    }

    @AfterReturning(pointcut = "execution(* fr.limayrac.securite2.controller.Controller.accepter(..))", returning = "result")
    public void logAccepterDetails(JoinPoint joinPoint, ModelAndView result) {
    
        Object objetDeclaration = result.getModelMap().get("declaration");
        Object objetUser = result.getModelMap().get("user");
    
        if (objetDeclaration != null && objetUser != null) {
            Declaration declaration = (Declaration) objetDeclaration;
            User user = (User) objetUser;
    
            logger.info("Vous êtes passé du statut 'En attente' au statut {} par {} {} pour le dossier numéro {}", declaration.getStatut(), user.getFirstName(), user.getLastName(), declaration.getNumDossier());
        } else {
            logger.warn("Objet Declaration ou User est null dans le modèle.");
        }
    }
    

    @AfterReturning(pointcut = "execution(* fr.limayrac.securite2.controller.Controller.refuser(..))", returning = "result")
    public void logRefuserDetails(JoinPoint joinPoint, ModelAndView result) {
        Object objetDeclaration = result.getModelMap().get("declaration");
        Declaration declaration = (Declaration) objetDeclaration;
        Object objetUser = result.getModelMap().get("user");
        User user = (User) objetUser;

        logger.info("Vous etes passer du statut 'En attente' au statut {} par {} {} pour le dossier numéro {}", declaration.getStatut(), user.getFirstName(),user.getLastName(),declaration.getNumDossier());;
    }

}
