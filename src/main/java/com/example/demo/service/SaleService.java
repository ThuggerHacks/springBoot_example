package com.example.demo.service;


import com.example.demo.model.Sale;
import com.example.demo.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {
    private SaleRepository saleRepository;

    @Autowired

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Optional<Sale> getSaleById(long id) {
        return saleRepository.findById(id);
    }

    public Sale addSale(Sale sale) {
        return saleRepository.save(sale);
    }
}
