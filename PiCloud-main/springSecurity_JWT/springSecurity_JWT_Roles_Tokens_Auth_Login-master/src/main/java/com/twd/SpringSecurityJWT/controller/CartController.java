package com.twd.SpringSecurityJWT.controller;


import com.twd.SpringSecurityJWT.entity.Bien;
import com.twd.SpringSecurityJWT.entity.Cart;
import com.twd.SpringSecurityJWT.entity.Users;
import com.twd.SpringSecurityJWT.repository.BienRepository;
import com.twd.SpringSecurityJWT.repository.CartRepository;
import com.twd.SpringSecurityJWT.service.BienService;
import com.twd.SpringSecurityJWT.service.CartService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("CartController")
public class CartController {

    private final CartService cartService;
    private final CartRepository cartRepository;
    private final BienRepository bienRepository;
    private BienService bienService;


    @GetMapping("/Get-All-Cart")
    public List<Cart> getAllCart() {
        return cartService.getAllCart();
    }

    @PostMapping("/add-cart")
    public Cart addCart(@RequestBody Cart cart) {
        return cartService.addCart(cart);
    }

    @PutMapping("/update-Cart")
    public Cart updateCart(@RequestBody Cart cart) {
        return cartService.updateCart(cart);
    }

    @DeleteMapping("/Delete-Cart/{idCart}")
    public void removeCart(@PathVariable("idCart") int idCart) {
        cartService.removeCartById(idCart);
    }

    @PostMapping("/addCard")
    public ResponseEntity<String> addBiensToCart(@RequestBody List<Integer> bienIds, Authentication authentication) {
        try {
            // Retrieve the current user
            Users currentUser = (Users) authentication.getPrincipal();

            // Get the user's cart or create a new one if it doesn't exist
            Cart cart = currentUser.getCart();
            if (cart == null) {
                cart = new Cart();
                cart.setUser(currentUser); // Set the user for the cart
                currentUser.setCart(cart);
            }

            // Add the selected biens to the cart
            for (int bienId : bienIds) {
                Bien bien = bienRepository.findById(bienId)
                        .orElseThrow(() -> new RuntimeException("Bien not found"));
                bien.getCarts().add(cart);
                cart.getBiens().add(bien);
            }

            // Save changes to the cart
            cartRepository.save(cart);

            return ResponseEntity.ok("Biens ajoutés au panier avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout des biens au panier : " + e.getMessage());
        }
    }





    @GetMapping("/cart/{cartId}/biens")
    public ResponseEntity<List<Bien>> getBiensInCart(@PathVariable int cartId) {
        List<Bien> biens = cartService.getBiensInCart(cartId);
        if (biens != null) {
            return ResponseEntity.ok(biens);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/carts/{cartId}")
    public ResponseEntity<Cart> getCartById(@PathVariable int cartId) {
        Cart cart = cartService.getCartById(cartId);
        if (cart != null) {
            return ResponseEntity.ok(cart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}


