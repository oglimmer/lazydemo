package de.oglimmer.lazydemo.dto;

import lombok.Data;

import java.util.List;

@Data
public class PersonDto {

    private Long personId;

    private String name;

    private List<AddressDto> addresses;

}
