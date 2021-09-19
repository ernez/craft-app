package com.ernez.craftapp.dto;

import com.ernez.craftapp.domain.AbstractEntity;
import com.ernez.craftapp.domain.AppUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.Instant;

@Data
@NoArgsConstructor
@Entity
public class ConfirmationToken extends AbstractEntity {


    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private Instant expiresAt;

    private Instant confirmedAt;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private AppUser appUser;

    public ConfirmationToken(String token,
                             Instant expiresAt,
                             AppUser appUser) {
        this.token = token;
        this.expiresAt = expiresAt;
        this.appUser = appUser;
    }
}
