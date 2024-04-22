package com.twd.SpringSecurityJWT.service;

import com.twd.SpringSecurityJWT.entity.Bien;
import com.twd.SpringSecurityJWT.entity.Cart;
import com.twd.SpringSecurityJWT.entity.Users;
import com.twd.SpringSecurityJWT.repository.BienRepository;
import com.twd.SpringSecurityJWT.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final BienRepository bienRepository;




    @Override
    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart updateCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void removeCartById(int idCart) {
        cartRepository.deleteById(idCart);
    }


    @Override
    public void addBiensToCart(List<Integer> bienIds, Authentication authentication) {
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
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'ajout des biens au panier : " + e.getMessage());
        }
    }
    @Override
    public List<Cart> getAllCart() {
        return null;
    }

    public List<Bien> getBiensInCart(int cartId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null) {
            return null;
        }
        Set<Bien> biens = cart.getBiens();
        List<Integer> bienIds = biens.stream()
                .map(Bien::getId)
                .collect(Collectors.toList());
        return bienRepository.findAllById(bienIds);
    }

    @Override
    public Cart getCartById(int cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found with id: " + cartId));
    }

}



