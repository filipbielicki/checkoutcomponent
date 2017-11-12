package com.filipbielicki.checkoutComponent.service.item;

import org.springframework.stereotype.Service;

@Service
public class DiscountCalculator {

    public double calculateDiscountedPriceWhenQuantityEven(double specialPrice, int quantity, int quantityForSpecialPrice){
        return getPriceWhenQtyEven(specialPrice, quantity, quantityForSpecialPrice);
    }

    public double calculateDiscountedPriceWhenQuantityIsUneven(int quantity, int quantityForSpecialPrice,
                                           double price, double specialPrice){
        return getPriceWhenQtyUneven(quantity, quantityForSpecialPrice, price, specialPrice);
    }

    private double getPriceWhenQtyUneven(int qty, int qtyForSpecPrice, double price, double specialPrice) {
        return ((qty % qtyForSpecPrice) * price) + ((qty - qty % qtyForSpecPrice) / qtyForSpecPrice * specialPrice);
    }

    private double getPriceWhenQtyEven(double specialPrice, int quantity, int qtyForSpecialPrice){
        return specialPrice * (quantity / qtyForSpecialPrice);
    }
}
