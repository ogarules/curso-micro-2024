package com.example.demo.invoicing.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.invoicing.models.TaxPayer;

@Repository
public interface TaxPayerRepository extends CrudRepository<TaxPayer, Long>{
    
}
