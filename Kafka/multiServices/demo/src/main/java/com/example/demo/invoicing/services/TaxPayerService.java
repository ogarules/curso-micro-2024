package com.example.demo.invoicing.services;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.example.demo.invoicing.models.TaxPayer;
import com.example.demo.invoicing.repositories.TaxPayerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaxPayerService {
    private final TaxPayerRepository repository;

    public Iterable<TaxPayer> getAll() {
        return repository.findAll();
    }
    
    public TaxPayer getTaxPayer(Long id) {
        return repository.findById(id).orElse(null);
    }

    public TaxPayer addTaxpayUser(TaxPayer entity) {
        TaxPayer dbTaxPayer = this.repository.save(entity);
        
        return dbTaxPayer;
    }

    public TaxPayer updateTaxPayer(Long id, TaxPayer entity){
        TaxPayer taxPayerdb = repository.findById(id).orElse(null);

        taxPayerdb.setCurp(entity.getCurp());
        taxPayerdb.setRfc(entity.getRfc());
        taxPayerdb = repository.save(taxPayerdb);

        return taxPayerdb;
    }

    public TaxPayer addOrUpdateTaxPayer(TaxPayer taxpayer){
        TaxPayer taxPayerdb = repository.findById(taxpayer.getId()).orElse(null);

        if(Objects.isNull(taxPayerdb)){
            return addTaxpayUser(taxpayer);
        }
        else{
            taxpayer.setCurp(taxPayerdb.getCurp());
            taxpayer.setRfc(taxPayerdb.getRfc());
            return updateTaxPayer(taxPayerdb.getId(), taxPayerdb);
        }
    }
}
