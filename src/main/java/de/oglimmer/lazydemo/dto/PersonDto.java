package de.oglimmer.lazydemo.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
public class PersonDto {

    private Long personId;

    private String name;

    @ToString.Exclude
    private List<AddressDto> addresses;

}
