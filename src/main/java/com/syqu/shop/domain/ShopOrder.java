package com.syqu.shop.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@Entity
@Table(name = "shop_order")
public class ShopOrder {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "first_name")
    @NotNull
    @NotEmpty
    private String first_name;

    @Column(name = "last_name")
    @NotNull
    @NotEmpty
    private String last_name;

    @Column(name = "card_number")
    @NotNull
    @NotEmpty
    private String card_number;

    @Column(name = "cvv")
    @NotNull
    @NotEmpty
    private String cvv;

    @Column(name = "quantity")
    @NotNull
    @NotEmpty
    private String quantity;

    @Column(name = "address")
    @NotNull
    @NotEmpty
    private String address;

    @Column(name = "email")
    @NotNull
    @NotEmpty
    private String email;

    @Column(name = "company")
    @NotNull
    @NotEmpty
    private String company;

    @Column(name = "country")
    @NotNull
    @NotEmpty
    private String country;
    @Column(name = "total")
    @NotNull
    @NotEmpty
    private String total;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopOrder shopOrder = (ShopOrder) o;
        return id == shopOrder.id && Objects.equals(first_name, shopOrder.first_name) && Objects.equals(last_name, shopOrder.last_name) && Objects.equals(card_number, shopOrder.card_number) && Objects.equals(cvv, shopOrder.cvv) && Objects.equals(quantity, shopOrder.quantity) && Objects.equals(address, shopOrder.address) && Objects.equals(email, shopOrder.email) && Objects.equals(company, shopOrder.company) && Objects.equals(country, shopOrder.country) && Objects.equals(total, shopOrder.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, last_name, card_number, cvv, quantity, address, email, company, country, total);
    }
}
