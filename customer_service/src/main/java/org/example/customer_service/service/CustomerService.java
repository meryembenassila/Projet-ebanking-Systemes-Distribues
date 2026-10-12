package org.example.customer_service.service;


import lombok.AllArgsConstructor;
import org.example.customer_service.entities.Customer;
import org.example.customer_service.repositories.CustomerRepository;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerRepository customerRepository;

    @McpTool(description = "get all bank Customers")
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll() ;   }

    @McpTool(description = "get  bank Customer By id ")
    public Customer getAllCustomerByID(@McpToolParam(description = "the Customer id ") Long id ){
        return customerRepository.findById(id).orElseThrow(()->new RuntimeException("Customer Not Found"));

    }

    @McpTool(description = "save   Customer")
    public Customer save(@McpToolParam(description = "the bankAccount to save (nom , email)")Customer customer){
            return customerRepository.save(customer);

        }
    }
