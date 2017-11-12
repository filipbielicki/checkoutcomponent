package com.filipbielicki.checkoutComponent.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
@Getter
@Setter
@ToString
@Entity(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double price;

    @Column(name = "quantity_for_special_price")
    private int quantityForSpecialPrice;

    @Column(name = "special_price")
    private double specialPrice;

    public Item() {
    }

    public Item(double price, int quantityForSpecialPrice, double specialPrice) {
        this.price = price;
        this.quantityForSpecialPrice = quantityForSpecialPrice;
        this.specialPrice = specialPrice;
    }
}
