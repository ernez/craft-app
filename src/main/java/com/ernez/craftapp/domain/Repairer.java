package com.ernez.craftapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "repairers")
public class Repairer extends AbstractEntity {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String surName;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JsonIgnore
    private RepairerType repairerType;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JsonIgnore
    private User user;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true,
            mappedBy = "repairer")
    @JsonIgnore
    private Set<Work> works;
}
