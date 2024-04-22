package com.twd.SpringSecurityJWT.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "biens")
@AllArgsConstructor
@NoArgsConstructor

public class Bien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private String description;
    private Float prix;
    private LocalDate dateAjout;
    private String image;
    @JsonIgnore
    @ManyToMany(mappedBy = "biens")
    private Set<Cart> carts = new HashSet<>();




}

