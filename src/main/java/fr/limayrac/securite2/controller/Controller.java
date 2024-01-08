package fr.limayrac.securite2.controller;

import fr.limayrac.securite2.model.User;
import fr.limayrac.securite2.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

	private static final Logger logger = LoggerFactory.getLogger(Controller.class);
	@Autowired
	private UserRepository userRepo;

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

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/gestion")
	public String gestion(Model model) {
		return "gestion";
	}
}