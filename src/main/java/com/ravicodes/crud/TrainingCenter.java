package com.ravicodes.crud;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Entity
@Data
public class TrainingCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String centerName;

    @Column(nullable = false, length = 12, unique = true)
    private String centerCode;

    @Embedded
    private Address address;

    private Integer studentCapacity;

    @ElementCollection
    private List<String> coursesOffered;

    @Column(nullable = false)
    private Long createdOn;

    private String contactEmail;

    @Column(nullable = false)
    private String contactPhone;

    

    // Constructors, getters, and setters
}
