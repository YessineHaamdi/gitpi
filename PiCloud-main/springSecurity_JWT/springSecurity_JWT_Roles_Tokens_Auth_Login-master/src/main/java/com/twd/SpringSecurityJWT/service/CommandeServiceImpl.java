package com.twd.SpringSecurityJWT.service;

import com.twd.SpringSecurityJWT.entity.Bien;
import com.twd.SpringSecurityJWT.entity.Cart;
import com.twd.SpringSecurityJWT.entity.Commande;
import com.twd.SpringSecurityJWT.entity.Coupon;
import com.twd.SpringSecurityJWT.repository.CartRepository;
import com.twd.SpringSecurityJWT.repository.CommandeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepository commandeRepository;
    private final CartService cartService;
    private final CartRepository cartRepository;
    private final CouponService couponService;


    @Override
    public void deleteCommande(int commandeId) {
        commandeRepository.deleteById(commandeId);
    }


    public Commande createCommandeFromCart(Integer cartId) {
        // Call the method with only cartId, assuming no coupon is provided
        return createCommandeFromCart(cartId, null);
    }

    public Commande createCommandeFromCart(Integer cartId, String couponCode) {
        if (cartId == null) {
            throw new IllegalArgumentException("Cart ID cannot be null");
        }
        if (couponCode != null) {
            boolean redeemed = couponService.redeemCoupon(couponCode);
            if (!redeemed) {
                throw new IllegalArgumentException("Coupon is invalid or has already been redeemed");
            }
        }
        Cart cart = cartService.getCartById(cartId);

        float totalPrice = calculateTotalPriceForCart(cartId, couponCode);

        Commande commande = new Commande();
        commande.setCart(cart);
        commande.setPrixTotal(totalPrice);
        commande.setDateEnregistrement(LocalDate.now());

        return commandeRepository.save(commande);
    }




    private float calculateTotalPriceForCart(int cartId, String couponCode) {
        float totalPrice = 0.0f;

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        for (Bien bien : cart.getBiens()) {
            totalPrice += bien.getPrix();
        }


            Coupon coupon = couponService.getCouponByCode(couponCode);
        //System.out.println(coupon.getCode());
            if (coupon != null && !coupon.isRedeemed()) {
                float discountAmount = calculateDiscount(totalPrice);
                totalPrice = totalPrice*0.8f;
            }


        return totalPrice;
    }

    private float calculateDiscount(float totalPrice) {
        return totalPrice * 0.20f;
    }

}
