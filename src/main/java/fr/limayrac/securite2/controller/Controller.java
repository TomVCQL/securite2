package fr.limayrac.securite2.controller;

import fr.limayrac.securite2.model.Declaration;
import fr.limayrac.securite2.model.User;
import fr.limayrac.securite2.repository.DeclarationRepository;
import fr.limayrac.securite2.repository.UserRepository;
import fr.limayrac.securite2.service.CustomUserDetails;
import fr.limayrac.securite2.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.webflow.execution.RequestContext;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

@org.springframework.stereotype.Controller
public class Controller {

	private static final Logger logger = LoggerFactory.getLogger(Controller.class);
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private DeclarationRepository declaRepository;

	@GetMapping("")
	public String viewHomePage() {

		return "index";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());

		return "signup_form";
	}

	@PostMapping("/process_register")
	public String processRegister(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		logger.info(user.toString());
		userRepo.save(user);

		return "register_success";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = userRepo.findAll();
		model.addAttribute("listUsers", listUsers);
		return "users";
	}

	@GetMapping("/declaration")
	public String declaration(Model model) {

		return "declaration";
	}

	@Component("numDossier")
	public class NumDossier {
		public String genNumDossier() {
			int length = 8;
			String prefix = "D";
			String suffix = "";
			String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			Random random = new Random();
			StringBuilder randomPartBuilder = new StringBuilder();
			for (int i = 0; i < length; i++) {
				int index = random.nextInt(characters.length());
				randomPartBuilder.append(characters.charAt(index));
			}
			String numeroDossier = prefix + randomPartBuilder.toString() + suffix;

			return numeroDossier;
		}

		public void saveDecla(RequestContext context) {
			Declaration declaration = new Declaration();

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			User user = userDetails.getUser();

			// GET
			String dateTransport = (String) context.getFlowScope().get("dateTransport");
			String numeroDossier = (String) context.getFlowScope().get("numeroDossier");
			String dateFormation = (String) context.getFlowScope().get("date_formation");
			String lieu_formation = (String) context.getFlowScope().get("lieu_formation");
			String intitule = (String) context.getFlowScope().get("intitule");
			String moyenTransport = (String) context.getFlowScope().get("moyenTransport");
			String pointDepart = (String) context.getFlowScope().get("pointDepart");
			String destination = (String) context.getFlowScope().get("destination");
			String prixTransport = (String) context.getFlowScope().get("prixTransport");
			String typeHebergement = (String) context.getFlowScope().get("typeHebergement");
			String lieuHebergement = (String) context.getFlowScope().get("lieuHebergement");
			String prixHebergement = (String) context.getFlowScope().get("prixHebergement");
			String justificatifHebergement = (String) context.getFlowScope().get("justificatifHebergement");
			String typeRestauration = (String) context.getFlowScope().get("typeRestauration");
			String justificatifRestauration = (String) context.getFlowScope().get("justificatifRestauration");
			String prixRestauration = (String) context.getFlowScope().get("prixRestauration");
			String iban = (String) context.getFlowScope().get("iban");
			String statut = "En attente";
			// SET
			declaration.setStatut(statut);
			declaration.setNumDossier(numeroDossier);
			declaration.setDateTransport(dateTransport);
			declaration.setDate_formation(dateFormation);
			declaration.setLieu_formation(lieu_formation);
			declaration.setIntitule(intitule);
			declaration.setMoyenTransport(moyenTransport);
			declaration.setPointDepart(pointDepart);
			declaration.setDestination(destination);
			declaration.setPrixTransport(prixTransport);
			declaration.setTypeHebergement(typeHebergement);
			declaration.setLieuHebergement(lieuHebergement);
			declaration.setPrixHebergement(prixHebergement);
			declaration.setJustificatifHebergement(justificatifHebergement);
			declaration.setTypeRestauration(typeRestauration);
			declaration.setJustificatifRestauration(justificatifRestauration);
			declaration.setPrixRestauration(prixRestauration);
			declaration.setIban(iban);
			declaration.setIdUser(user);

			logger.info(declaration.toString());
			declaRepository.save(declaration);
		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/gestion")
	public String gestion(Model model) {
		List<Declaration> listDeclaration = declaRepository.findByStatut("En attente");
		model.addAttribute("listDeclaration", listDeclaration);
		return "gestion";
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/liste_declaration")
	public String liste_declaration(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		User user = userDetails.getUser();

		List<Declaration> listDeclaration = declaRepository.findByidUser(user);
		model.addAttribute("listDeclaration", listDeclaration);
		return "liste_declaration";
	}

	@GetMapping("/accepter")
	public String accepter(@RequestParam Long idDeclaration, Model model) {

		Declaration declaration = declaRepository.findById(idDeclaration).orElse(null);

		if (declaration != null) {
			// Mettez à jour le statut de la déclaration ici
			declaration.setStatut("ACCEPTE");
			declaRepository.save(declaration);
		}
		return "gestion";
	}
	
	@GetMapping("/refuser")
	public String refuser(@RequestParam Long idDeclaration, Model model) {

		Declaration declaration = declaRepository.findById(idDeclaration).orElse(null);

		if (declaration != null) {
			// Mettez à jour le statut de la déclaration ici
			declaration.setStatut("REFUSER");
			declaRepository.save(declaration);
		}
		return "gestion";
	}
}