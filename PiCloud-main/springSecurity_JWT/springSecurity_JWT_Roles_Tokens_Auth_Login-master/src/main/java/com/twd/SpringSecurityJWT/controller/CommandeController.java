// CommandeController.java
package com.twd.SpringSecurityJWT.controller;


import com.twd.SpringSecurityJWT.entity.Commande;
import com.twd.SpringSecurityJWT.service.CommandeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("commandes")
public class CommandeController {

    CommandeService commandeService;



    @PostMapping("/create-commande/{cartId}")
    public ResponseEntity<Commande> createCommandeFromCart(@PathVariable("cartId") Integer cartId, @RequestParam(name = "code", required = false) String couponCode) {
        try {
            Commande commande;
            if (couponCode != null) {
                commande = commandeService.createCommandeFromCart(cartId, couponCode);
            } else {
                commande = commandeService.createCommandeFromCart(cartId);
            }
            return ResponseEntity.ok(commande);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }





}
