package com.ravicodes.crud;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Address {

    private String detailedAddress;
    private String city;
    private String state;
    private String pincode;

    // Constructors, getters, and setters
}