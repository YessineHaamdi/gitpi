package com.twd.SpringSecurityJWT.service;


import com.twd.SpringSecurityJWT.entity.Bien;

import java.util.List;

public interface BienService {

    Bien addBien(Bien bien);
    Bien updateBien(Bien bien);
    void removeBienById(int idBien);
    Bien getBien(int idBien);
    List<Bien> getAllBiens();
    List<Bien> searchBiensByName(String name);



    List<Bien> orderBiensAsc();

    List<Bien> orderBiensDesc();
}
