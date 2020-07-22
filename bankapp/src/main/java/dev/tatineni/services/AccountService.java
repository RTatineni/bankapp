package dev.tatineni.services;

import java.util.Set;

import dev.tatineni.entities.Account;
import dev.tatineni.entities.Customer;

public interface AccountService {
	
	
	//CRUD
		//Create
		Account establishAccount(Account account);
		
		//Read
		Account getAccountById(int id);

		Set<Account> getAccountsByCustomerId(int id);
		Set<Account> getAccountsByCustomerId(Customer customer);
		Set<Account> getAllAccounts();
		
		//update
		Account updateAccount(Account account);
		
		//delete
		boolean deleteAccount(int id);
		boolean deleteAccount(Account account);
		

}
