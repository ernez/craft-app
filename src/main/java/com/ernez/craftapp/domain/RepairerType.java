package com.ernez.craftapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "repairer_types")
public class RepairerType extends AbstractEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    //Ovo treba provjeriti da li je dovoljno samo unidirectional annotation on Repairer Entity
    @OneToOne(mappedBy = "repairerType", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Repairer repairer;
}
