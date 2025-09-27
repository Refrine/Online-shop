package com.example.online_shop.services;

import com.example.online_shop.models.Product;
import com.example.online_shop.models.User;
import com.example.online_shop.repo.ProductRepository;
import com.example.online_shop.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProductService {
    private final ProductRepository productRepo;

    private final UserRepository userRepo;





    public ProductService(ProductRepository productRepo, UserRepository userRepo) {
        this.productRepo = productRepo;
        this.userRepo = userRepo;
    }

    public List<Product> listProducts() {
        return productRepo.findAll();
    }

    public List<Product> listProducts(String title) {

        if(title != null){
           return productRepo.findByTitle(title);
        }
        return productRepo.findAll();
    }




    public void saveProduct(Principal principal, Product product){
        product.setUser(getUserByPrincipal(principal));
        productRepo.save(product);

    }

    public User getUserByPrincipal(Principal principal) {
        if(principal == null) return new User();
        return userRepo.findByEmail(principal.getName());
    }

    public void deleteProduct(Long id){
       productRepo.deleteById(id);
    }


    public Product getProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }
}
