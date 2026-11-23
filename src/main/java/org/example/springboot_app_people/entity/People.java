package org.example.springboot_app_people.entity;

import jakarta.persistence.*;

@Entity
public class People {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column (name = "email", nullable = false, unique = true)
    private String email;
}
