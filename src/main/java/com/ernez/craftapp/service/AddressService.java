package com.ernez.craftapp.service;

import com.ernez.craftapp.domain.Address;
import com.ernez.craftapp.dto.AddressDto;

public class AddressService {

    public static AddressDto mapToDto(Address address) {
        if (address != null) {
            return new AddressDto(
                    address.getAddress(),
                    address.getCity(),
                    address.getPostcode()
            );
        }
        return null;
    }

}
