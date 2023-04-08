package com.syqu.shop.controller;

import com.syqu.shop.domain.ShopOrder;
import com.syqu.shop.service.OrderService;
import com.syqu.shop.service.ShoppingCartService;
import com.syqu.shop.domain.Product;
import com.syqu.shop.service.ProductService;
import com.syqu.shop.validator.OrderValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class CartController {
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);
    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;
    private final OrderService orderService;

    private final OrderValidator orderValidator;

    @Autowired
    public CartController(ShoppingCartService shoppingCartService, ProductService productService, OrderService orderService, OrderValidator orderValidator) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
        this.orderService = orderService;
        this.orderValidator = orderValidator;
    }

    @GetMapping("/cart")
    public String cart(Model model){
        model.addAttribute("products", shoppingCartService.productsInCart());
        model.addAttribute("total", shoppingCartService.productsInCart().size());
        model.addAttribute("totalPrice", shoppingCartService.totalPrice());

        return "cart";
    }

    @GetMapping("/cart/add/{id}")
    public String addProductToCart(@PathVariable("id") long id){
        Product product = productService.findById(id);
        if (product != null){
            shoppingCartService.addProduct(product);
            logger.debug(String.format("Product with id: %s added to shopping cart.", id));
        }
        return "redirect:/home";
    }

    @GetMapping("/cart/remove/{id}")
    public String removeProductFromCart(@PathVariable("id") long id){
        Product product = productService.findById(id);
        if (product != null){
            shoppingCartService.removeProduct(product);
            logger.debug(String.format("Product with id: %s removed from shopping cart.", id));
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart/clear")
    public String clearProductsInCart(){
        shoppingCartService.clearProducts();

        return "redirect:/cart";
    }

    @GetMapping("/cart/checkout")
    public String cartCheckout(Model model){
        shoppingCartService.cartCheckout();
        model.addAttribute("orderForm", new ShopOrder());
        model.addAttribute("method", "new");

        model.addAttribute("products", shoppingCartService.productsInCart());
        if (shoppingCartService.productsInCart().isEmpty()){
            return "redirect:/home";
        }

        return "checkout";
    }
    @PostMapping("/checkout")
    public String newProduct(@ModelAttribute("orderForm") ShopOrder shopOrderForm, BindingResult bindingResult, Model model) {
        orderValidator.validate(shopOrderForm, bindingResult);

        if (bindingResult.hasErrors()) {
            logger.error(String.valueOf(bindingResult.getFieldError()));
            model.addAttribute("method", "new");
            return "checkout";
        }
        BigDecimal totalPrice =  shoppingCartService.totalPrice();
        int count = shoppingCartService.productsInCart().size();
        ShopOrder order = shopOrderForm;
        order.setTotal(String.valueOf(totalPrice));
        order.setQuantity(String.valueOf(count));

        orderService.save(order);
        logger.debug(String.format("Order with id: %s successfully created.", shopOrderForm.getId()));
        shoppingCartService.cartCheckout();
        return "redirect:/home";
    }
}
