package dev.tatineni.daos;

import java.util.Set;

import dev.tatineni.entities.Account;

public interface AccountDAO {

	// Create
	Account createAccount(Account account);
	
	
	// Read
	Account getAccountById(int id);
	Set<Account> getAllAccounts();
	Set<Account> getAccountsByCustomerId(int id);
	
	// Update
	Account updateAccount(Account account);
	
	// Delete
	boolean deleteAccount(int id);
		
}
