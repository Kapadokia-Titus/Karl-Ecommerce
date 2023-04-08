package com.syqu.shop.controller;

import com.syqu.shop.domain.User;
import com.syqu.shop.service.OrderService;
import com.syqu.shop.service.ShoppingCartService;
import com.syqu.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserController {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final OrderService orderService;

    @Autowired
    public UserController(UserService userService, ShoppingCartService shoppingCartService, OrderService orderService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.orderService = orderService;
    }

    @GetMapping("/user")
    public String userPanel(Principal principal, Model model){
        User user = userService.findByUsername(principal.getName());

        if (user != null) {
            model.addAttribute("user", user);
        }else {
            return "error/404";
        }

        return "user";
    }

    @GetMapping("/admin")
    public String userAdmin(Principal principal, Model model){
        User user = userService.findByUsername(principal.getName());

        if (user != null || user.getUsername() == "admin") {
            model.addAttribute("orders", orderService.findAllByOrderByIdAsc());
            model.addAttribute("total", shoppingCartService.productsInCart().size());
            model.addAttribute("admin", user);
        }else {
            return "error/404";
        }

        return "admin";
    }
}
