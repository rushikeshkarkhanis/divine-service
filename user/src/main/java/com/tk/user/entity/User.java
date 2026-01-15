package com.tk.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fName;
    private String lName;
    private Integer age;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id") // Creates a foreign key in the Address table
    private List<Address> addresses;
}
