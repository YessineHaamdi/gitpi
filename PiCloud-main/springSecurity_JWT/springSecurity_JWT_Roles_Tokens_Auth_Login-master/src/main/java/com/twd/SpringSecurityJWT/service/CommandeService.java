package com.twd.SpringSecurityJWT.service;


import com.twd.SpringSecurityJWT.entity.Commande;

public interface CommandeService {
    Commande createCommandeFromCart(Integer cartId);
    Commande createCommandeFromCart(Integer cartId, String couponCode);


        void deleteCommande(int commandeId);
}
