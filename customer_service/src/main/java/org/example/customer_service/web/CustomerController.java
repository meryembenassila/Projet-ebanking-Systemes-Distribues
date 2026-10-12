package org.example.customer_service.web;


import lombok.AllArgsConstructor;
import org.example.customer_service.entities.Customer;
import org.example.customer_service.service.CustomerService;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerController {
    private CustomerService customerService;


    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers() ;   }



    @GetMapping("/customers/{id}")
    public Customer getAllCustomerByID(@PathVariable Long id ){
        return customerService.getAllCustomerByID(id);

    }

  @PostMapping("/customers")
    public Customer save(@RequestBody Customer customer){
        return customerService.save(customer);

    }
}
