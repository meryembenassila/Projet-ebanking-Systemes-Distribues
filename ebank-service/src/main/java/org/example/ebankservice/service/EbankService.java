package org.example.ebankservice.service;

import lombok.AllArgsConstructor;
import org.example.ebankservice.entities.BankAccount;
import org.example.ebankservice.feign.CustomerRestClient;
import org.example.ebankservice.model.Customer;
import org.example.ebankservice.repositories.EBankRepository;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class EbankService {

    private EBankRepository eBankRepository;
    private CustomerRestClient customerRestClient;

@McpTool(description = "get All BankAccounts")
    public List<BankAccount> getAllBankAccounts(){
        List<BankAccount> bankAccounts = new ArrayList<>();
        eBankRepository.findAll().forEach(bankAccount -> {
            Customer customer = customerRestClient.getCustomerById(bankAccount.getIdclient());
            bankAccount.setCustomer(customer);
            bankAccounts.add(bankAccount);
        });

        return bankAccounts ;   }

    @McpTool(description = "get Account By Id ")
    public BankAccount getAllBankAccountByID(@McpToolParam(description = "Id of Banckaccount") Long id ){
        BankAccount bankAccount = eBankRepository.findById(id).orElseThrow(()->new RuntimeException("BankAccount Not Found"));
        Customer customer = customerRestClient.getCustomerById(bankAccount.getIdclient());
        bankAccount.setCustomer(customer);

        return bankAccount;

    }

    @McpTool(description = "Create a new bank account")
    public BankAccount save(
            @McpToolParam(description = "Customer id") Long customerId,
            @McpToolParam(description = "Initial balance") double balance,
            @McpToolParam(description = "Account type: CURRENT_ACCOUNT or SAVING_ACCOUNT")
            String accountType
    ) {

        try {
            customerRestClient.getCustomerById(customerId);
        } catch (Exception e) {
            throw new RuntimeException("Customer not found: " + customerId);
        }

        BankAccount bankAccount = new BankAccount();
        bankAccount.setCreatedAt(new Date());
        bankAccount.setBalance(balance);
        bankAccount.setType(accountType);
        bankAccount.setIdclient(customerId);

        return eBankRepository.save(bankAccount);
    }
}
