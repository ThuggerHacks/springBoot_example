package com.example.demo.controller;


import com.example.demo.model.Product;
import com.example.demo.model.Sale;
import com.example.demo.model.User;
import com.example.demo.repository.SaleRepository;
import com.example.demo.service.ProductService;
import com.example.demo.service.SaleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SaleController extends BaseController {
    private final SaleService saleService;
    private final ProductService productService;
    private final UserService userService;
    private final SaleRepository saleRepository;

    @Autowired

    public SaleController(SaleService saleService, ProductService productService, UserService userService, SaleRepository saleRepository) {
        this.saleService = saleService;
        this.productService = productService;
        this.userService = userService;
        this.saleRepository = saleRepository;
    }

    @GetMapping("/sales")
    public List<Sale> getSales() {
        return saleService.getAllSales();
    }

    @PostMapping("/sales/{userId}/{productId}")
    public Sale addSale(@PathVariable Long userId, @PathVariable Long productId, @RequestBody Sale sale) {
        Optional<User> user = userService.findById(userId);
        Optional<Product> product = productService.findById(productId);
        if (user.isPresent() && product.isPresent()) {
            sale.setUser(user.get());
            sale.setProduct(product.get());
        }
        return saleRepository.save(sale);
    }
}
