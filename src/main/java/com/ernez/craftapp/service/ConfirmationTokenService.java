package com.ernez.craftapp.service;

import com.ernez.craftapp.dto.ConfirmationToken;

import java.util.Optional;

public interface ConfirmationTokenService {
    public Optional<ConfirmationToken> getToken(String token);
    public int setConfirmedAt(String token);
}
