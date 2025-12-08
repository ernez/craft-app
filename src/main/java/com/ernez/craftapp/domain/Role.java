package com.ernez.craftapp.domain;

import com.ernez.craftapp.domain.enumeration.ERole;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends AbstractEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;


    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private ERole name;

    private Boolean isDefault;

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}
