package com.syqu.shop;

import com.syqu.shop.domain.Category;
import com.syqu.shop.domain.Product;
import com.syqu.shop.repository.CategoryRepository;
import com.syqu.shop.service.ProductService;
import com.syqu.shop.domain.User;
import com.syqu.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class StartupData implements CommandLineRunner {
    private final UserService userService;
    private final ProductService productService;
    private final CategoryRepository categoryRepository;
    private static final Logger logger = LoggerFactory.getLogger(StartupData.class);

    @Autowired
    public StartupData(UserService userService, ProductService productService, CategoryRepository categoryRepository) {
        this.userService = userService;
        this.productService = productService;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {
        adminAccount();
        userAccount();
        category();
        exampleProducts();
    }

    private void userAccount(){
        User user = new User();

        user.setUsername("user");
        user.setPassword("user");
        user.setPasswordConfirm("user");
        user.setGender("Female");
        user.setEmail("user@example.com");

        userService.save(user);
    }

    private void adminAccount(){
        User admin = new User();

        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setPasswordConfirm("admin");
        admin.setGender("Male");
        admin.setEmail("admin@example.com");

        userService.save(admin);
    }

    private void category(){
        Category category1 = new Category();
        Category category2 = new Category();
        Category category3 = new Category();
        Category category4= new Category();
        Category category5 = new Category();
        category1.setId(1);
        category1.setCategoryName("Woman Wear");
        category2.setId(2);
        category2.setCategoryName("Man Wear");
        category3.setId(3);
        category3.setCategoryName("Children");
        category4.setId(4);
        category4.setCategoryName("Bags & Purses");
        category5.setId(5);
        category5.setCategoryName("Footwear");



        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);
        categoryRepository.save(category4);
        categoryRepository.save(category5);
    }

    private void exampleProducts(){
        final String NAME = "Jean Mindi";
        final String IMAGE_URL = "https://i.pinimg.com/236x/37/2c/6f/372c6f40eb0835eea3abd13a02a64cd0.jpg";
        final String IMAGE_URL2 = "https://i.pinimg.com/564x/af/84/0d/af840d5291bdf623f603aa46c96c1e96.jpg";
        final String IMAGE_URL3 = "https://i.ebayimg.com/images/g/nioAAOSws7didl6~/s-l1600.png";
        final String IMAGE_URL4 = "https://media1.popsugar-assets.com/files/thumbor/V-Az9F47V10EJwKeTZ3QIkI7q2I/fit-in/728xorig/filters:format_auto-!!-:strip_icc-!!-/2021/05/19/747/n/1922564/445a3685c9ca934f_netimgKfks9d/i/best-summer-clothes-for-women-under-50.png";
        final String IMAGE_URL5 = "https://www.etonline.com/sites/default/files/images/2023-01/Screen%20Shot%202023-01-03%20at%208.14.18%20PM.png";
        final String DESCRIPTION = "Jeans midi cocktail dress";
        final BigDecimal PRICE = BigDecimal.valueOf(39.90);

        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();
        Product product4 = new Product();
        Product product5 = new Product();

        product1.setName(NAME);
        product1.setImageUrl(IMAGE_URL);
        product1.setDescription(DESCRIPTION);
        product1.setCategory(categoryRepository.findByCategoryName("Woman Wear"));
        product1.setPrice(PRICE);

        product2.setName(NAME);
        product2.setImageUrl(IMAGE_URL2);
        product2.setDescription(DESCRIPTION);
        product2.setCategory(categoryRepository.findByCategoryName("Woman Wear"));
        product2.setPrice(PRICE);

        product3.setName(NAME);
        product3.setImageUrl(IMAGE_URL3);
        product3.setDescription(DESCRIPTION);
        product3.setCategory(categoryRepository.findByCategoryName("Woman Wear"));
        product3.setPrice(PRICE);

        product4.setName(NAME);
        product4.setImageUrl(IMAGE_URL4);
        product4.setDescription(DESCRIPTION);
        product4.setCategory(categoryRepository.findByCategoryName("Woman Wear"));
        product4.setPrice(PRICE);

        product5.setName(NAME);
        product5.setImageUrl(IMAGE_URL5);
        product5.setDescription(DESCRIPTION);
        product5.setCategory(categoryRepository.findByCategoryName("Woman Wear"));
        product5.setPrice(PRICE);

        productService.save(product1);
        productService.save(product2);
        productService.save(product3);
        productService.save(product4);
        productService.save(product5);
    }
}
