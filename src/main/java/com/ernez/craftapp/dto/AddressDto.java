/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ernez.craftapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author n.lamouchi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private String address;
    private String city;
    private String postcode;
}
