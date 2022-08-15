package com.ernez.craftapp.web;

import com.ernez.craftapp.dto.request.LoginRequest;
import com.ernez.craftapp.dto.response.JwtResponse;
import com.ernez.craftapp.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
	public ResponseEntity<?> signIn(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {
		final JwtResponse jwtResponse = appUserService.signIn(loginRequest);
		response.addCookie(createCookie(jwtResponse.getAccessToken()));
		return ResponseEntity.ok(appUserService.signIn(loginRequest));
	}

	private Cookie createCookie(String jwt) {
		Cookie cookie = new Cookie("JWT", jwt);
		cookie.setMaxAge(24 * 60 * 60); // expires in 1 day
		cookie.setSecure(true);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		cookie.setDomain("localhost");
		return cookie;
	}
}
