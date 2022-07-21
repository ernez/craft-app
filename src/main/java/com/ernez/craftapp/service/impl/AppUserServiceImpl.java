package com.ernez.craftapp.service.impl;

import com.ernez.craftapp.domain.AppUser;
import com.ernez.craftapp.domain.Role;
import com.ernez.craftapp.dto.ConfirmationToken;
import com.ernez.craftapp.dto.request.LoginRequest;
import com.ernez.craftapp.dto.response.JwtResponse;
import com.ernez.craftapp.repository.AppUserRepository;
import com.ernez.craftapp.repository.RoleRepository;
import com.ernez.craftapp.security.UserPrincipal;
import com.ernez.craftapp.security.jwt.JwtUtils;
import com.ernez.craftapp.service.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
//@AllArgsConstructor
@Slf4j
@Transactional
public class AppUserServiceImpl implements AppUserService {

	private final static String USER_NOT_FOUND_MSG =
			"user with email %s not found";

	private final AppUserRepository appUserRepository;
	private final PasswordEncoder passwordEncoder;
	private final ConfirmationTokenServiceImpl confirmationTokenService;
	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;
	private final RoleRepository roleRepository;

	@Autowired
	public AppUserServiceImpl(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder,
							  ConfirmationTokenServiceImpl confirmationTokenService, AuthenticationManager authenticationManager,
							  JwtUtils jwtUtils, RoleRepository roleRepository) {
		this.appUserRepository = appUserRepository;
		this.passwordEncoder = passwordEncoder;
		this.confirmationTokenService = confirmationTokenService;
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
		this.roleRepository = roleRepository;
	}

	public String signUpUser(AppUser appUser) {
		boolean userExists = appUserRepository
				.findByEmail(appUser.getEmail())
				.isPresent();

		if (userExists) {
			// TODO check of attributes are the same and
			// TODO if email not confirmed send confirmation email.

			throw new IllegalStateException("email already taken");
		}

		String encodedPassword = passwordEncoder
				.encode(appUser.getPassword());

		appUser.setPassword(encodedPassword);
		appUser.setUsername(appUser.getEmail());
		appUser.setRoles(getDefaultRoles());

		appUserRepository.save(appUser);

		String token = UUID.randomUUID().toString();

		ConfirmationToken confirmationToken = new ConfirmationToken(
				token,
				Instant.now().plus(20l, ChronoUnit.MINUTES),
				appUser
		);

		confirmationTokenService.saveConfirmationToken(
				confirmationToken);

		//TODO: SEND EMAIL
		return token;
	}

	private Set<Role> getDefaultRoles() {
		return new HashSet<>(roleRepository.findAllByIsDefault(true));
	}

	@Override
	public JwtResponse signIn(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		List<String> roles = userPrincipal.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return new JwtResponse(jwt,
				userPrincipal.getId(),
				userPrincipal.getUsername(),
				userPrincipal.getEmail(),
				roles);
	}

	@Override
	public void enableAppUser(String email) {
		appUserRepository.enableAppUser(email);
	}

	@Override
	public void disable(Long id) {
		log.debug("Request to delete Customer : {}", id);

		AppUser user = appUserRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("Cannot find Customer with id " + id));

		user.setEnabled(false);
		appUserRepository.save(user);
	}

	@Override
	public void delete(Long id) {
		        log.debug("Request to delete Customer : {}", id);

        AppUser user = this.appUserRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Cannot find Customer with id " + id));

        this.appUserRepository.delete(user);
	}

	@Override
	public List<AppUser> findAllActive() {
		log.debug("Request to get all Customers");
        return this.appUserRepository.findAllByEnabled(true)
                .stream()
                .collect(Collectors.toList());
	}

	@Override
	public AppUser create(AppUser appUser) {
		log.debug("Request to get create User {}", appUser.getEmail());
		return this.appUserRepository.save(appUser);
	}

	@Override
	public void update(AppUser appUser) {
		this.appUserRepository.save(appUser);
	}
}
