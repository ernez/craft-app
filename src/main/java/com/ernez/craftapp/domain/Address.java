package com.ernez.craftapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
//@NoArgsConstructor
@Embeddable
public class Address implements Serializable {
    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Size(max = 10)
    @Column(name = "postcode")
    private String postcode;
}
