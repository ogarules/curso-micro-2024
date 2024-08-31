package com.example.demo.payments.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.payments.models.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    
}
