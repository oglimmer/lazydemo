package de.oglimmer.lazydemo.dto;

import lombok.Data;

@Data
public class AddressDto {

    private Long addressId;

    private String street;
    private String city;

}
