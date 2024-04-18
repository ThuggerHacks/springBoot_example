package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(long id) {
        productRepository.deleteById(id);
    }

    public Product update(Product product, long id) {
        Optional<Product> product1 = productRepository.findById(id);
        if (product1.isPresent()) {
            Product newProduct = product1.get();
            newProduct.setName(product.getName());
            newProduct.setPrice(product.getPrice());
            newProduct.setQuantity(product.getQuantity());
            return productRepository.save(product);
        }
        return null;
    }

    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }
}
