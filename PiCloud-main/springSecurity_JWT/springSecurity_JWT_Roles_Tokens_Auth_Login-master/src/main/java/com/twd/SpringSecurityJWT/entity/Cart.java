package com.twd.SpringSecurityJWT.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCart")
    private int idCart;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cart_bien",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "bien_id")
    )
    private Set<Bien> biens = new HashSet<>();




}



