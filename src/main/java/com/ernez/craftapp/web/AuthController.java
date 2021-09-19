package com.ernez.craftapp.web;

import com.ernez.craftapp.dto.request.LoginRequest;
import com.ernez.craftapp.repository.AppUserRepository;
import com.ernez.craftapp.repository.RoleRepository;
import com.ernez.craftapp.security.jwt.JwtUtils;
import com.ernez.craftapp.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	private final AppUserService appUserService;

	@Autowired
	public AuthController(AppUserService appUserService) {
		this.appUserService = appUserService;
	}

	@PostMapping("/signin")
	public ResponseEntity<?> signIn(@Valid @RequestBody LoginRequest loginRequest) {
		return ResponseEntity.ok(appUserService.signIn(loginRequest));
	}
}
