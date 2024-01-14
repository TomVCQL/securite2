package fr.limayrac.securite2.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Aspect
@Component
public class LoggerAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerAspect.class);

    @After("execution(* fr.limayrac.securite2.controller.Controller.accepter(..)) && args(idDeclaration, model)")
    public void logAfterAccepter(Long idDeclaration, Model model) {
        // Logique de journalisation après l'acceptation
        LOGGER.info("Declaration acceptée avec l'ID : {}", idDeclaration);
    }

}
