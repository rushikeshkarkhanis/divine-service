package com.tk.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String streetName;
    private String city;
    private String state;
    private String pinCode;
}
