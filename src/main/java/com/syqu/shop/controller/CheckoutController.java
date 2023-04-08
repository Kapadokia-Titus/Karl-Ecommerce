package com.syqu.shop.controller;

import com.syqu.shop.domain.ShopOrder;
import com.syqu.shop.service.ProductService;
import com.syqu.shop.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckoutController {

    private static final Logger logger = LoggerFactory.getLogger(CheckoutController.class);
    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;

    public CheckoutController(ShoppingCartService shoppingCartService, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }

    @GetMapping("/checkout")
    public String cart(Model model){
        model.addAttribute("products", shoppingCartService.productsInCart());
        model.addAttribute("total", shoppingCartService.productsInCart().size());
        model.addAttribute("totalPrice", shoppingCartService.totalPrice());
        model.addAttribute("orderForm", new ShopOrder());
        model.addAttribute("method", "new");
        if (shoppingCartService.productsInCart().isEmpty()){
            return "redirect:/home";
        }

        return "checkout";
    }

}
