package com.twd.SpringSecurityJWT.repository;


import com.twd.SpringSecurityJWT.entity.Bien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BienRepository extends JpaRepository<Bien,Integer> {

}

