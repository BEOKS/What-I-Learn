package com.example.testcontainermysql.api;

import java.util.List;

import com.example.testcontainermysql.domain.Customer;
import com.example.testcontainermysql.domain.CustomerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    private final CustomerRepository repo;

    public CustomerController(CustomerRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/api/customers")
    public List<Customer> getAll() {
        return repo.findAll();
    }
}
