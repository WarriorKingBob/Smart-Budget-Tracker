package com.example.smartbudgettracker.Account;

import com.example.smartbudgettracker.User.User;
import com.example.smartbudgettracker.User.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public Account createAccount(UUID userId, String bankName, Account.AccountType accountType, int balance) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Account a = new Account();
        a.setUser(user);
        a.setBankName(bankName);
        a.setAccountType(accountType);
        a.setCurrency(Account.SupportedCurrency.CAD);
        a.setBalance(balance);

        return accountRepository.save(a);
    }

    public Optional<Account> getAccountById(UUID user_id, UUID account_id) {
        Account account = accountRepository.findById(account_id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        if (accountRepository.findByUserId(user_id).contains(account)) {
            return accountRepository.findById(account_id);
        }
        else {
            throw new RuntimeException("Account not found");
        }
    }

    public List<Account> getAccountsByUserId(UUID id) {
        return accountRepository.findByUserId(id);
    }

    public Account updateBankName(UUID id, String newBankName) {
        Account a = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        a.setBankName(newBankName);
        return accountRepository.save(a);
    }

    public Account updateAccountType(UUID id, Account.AccountType newAccountType) {
        Account a = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        a.setAccountType(newAccountType);
        return accountRepository.save(a);
    }

    public Account updateBalance(UUID id, int newBalance) {
        Account a = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        a.setBalance(newBalance);
        return accountRepository.save(a);
    }

    public void deleteAccount(UUID id) { accountRepository.deleteById(id); }
}
