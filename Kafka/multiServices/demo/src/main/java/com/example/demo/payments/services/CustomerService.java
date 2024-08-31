package com.example.demo.payments.services;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.example.demo.payments.models.Customer;
import com.example.demo.payments.repositories.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    public Customer addCustomer(Customer entity) {
        Customer customer = this.repository.save(entity);
        
        return customer;
    }

    public Customer updateCustomer(Long id, Customer entity){
        Customer customerdb = repository.findById(id).orElse(null);

        customerdb.setFullName(entity.getFullName());
        customerdb = repository.save(customerdb);

        return customerdb;
    }

    public Customer addOrUpdateCustomer(Customer taxpayer){
        Customer taxPayerdb = repository.findById(taxpayer.getId()).orElse(null);

        if(Objects.isNull(taxPayerdb)){
            return addCustomer(taxpayer);
        }
        else{
            return updateCustomer(taxPayerdb.getId(), taxPayerdb);
        }
    }
}
