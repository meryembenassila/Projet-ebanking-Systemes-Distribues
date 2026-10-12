package org.example.ebankservice.web;

import lombok.AllArgsConstructor;
import org.example.ebankservice.entities.BankAccount;
import org.example.ebankservice.service.EbankService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@AllArgsConstructor
public class EbanController {
    private EbankService ebankService;

    @GetMapping("/BankAccounts")
    public List<BankAccount> getAllBankAccounts(){
        return ebankService.getAllBankAccounts() ;   }

    @GetMapping("/BankAccounts/{id}")
    public BankAccount getAllBankAccountByID(@PathVariable Long id ){
        return ebankService.getAllBankAccountByID(id);

    }
    @PostMapping("/BankAccounts")
    public BankAccount save(@RequestBody BankAccount BankAccount){
        return ebankService.save(BankAccount.getIdclient(),BankAccount.getBalance(),BankAccount.getType());

    }
}
