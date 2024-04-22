package com.twd.SpringSecurityJWT.repository;


import com.twd.SpringSecurityJWT.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {
    // Add custom query methods if needed
}
