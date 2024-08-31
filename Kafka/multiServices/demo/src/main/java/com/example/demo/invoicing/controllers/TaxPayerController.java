package com.example.demo.invoicing.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.invoicing.models.TaxPayer;
import com.example.demo.invoicing.services.TaxPayerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("taxpayer")
@RequiredArgsConstructor
public class TaxPayerController {
    
    private final TaxPayerService service;

    @GetMapping()
    public Iterable<TaxPayer> getAll() {
        return service.getAll();
    }
    
    @GetMapping("/{id}")
    public TaxPayer getUser(@PathVariable Long id) {
        return service.getTaxPayer(id);
    }

    @PutMapping("/{id}")
    public TaxPayer putMethodName(@PathVariable Long id, @RequestBody TaxPayer entity) {
        
        return service.updateTaxPayer(id, entity);
    }
}
