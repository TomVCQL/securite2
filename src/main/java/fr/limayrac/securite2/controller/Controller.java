package fr.limayrac.securite2.controller;

import fr.limayrac.securite2.model.User;
import fr.limayrac.securite2.repository.UserRepository;
import fr.limayrac.securite2.service.CustomUserDetails;
import fr.limayrac.securite2.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Random;

@org.springframework.stereotype.Controller
public class Controller {

	private static final Logger logger = LoggerFactory.getLogger(Controller.class);
	@Autowired
	private UserRepository userRepo;

	@GetMapping("")
	public String viewHomePage() {

		// CustomUserDetails customUserDetails = (CustomUserDetails)
		// authentication.getPrincipal();

		// User user = customUserDetails.getUser();

		// model.addAttribute("user", user);

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
	public class numDossier {
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
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/gestion")
	public String gestion(Model model) {
		return "gestion";
	}
}