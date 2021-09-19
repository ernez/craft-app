package com.ernez.craftapp.service;

import com.ernez.craftapp.dto.RegistrationRequest;

public interface RegistrationService {
    String register(RegistrationRequest request);
    String confirmToken(String token);
}
