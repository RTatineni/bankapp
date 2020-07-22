package dev.tatineni.services;

import java.util.Set;

import dev.tatineni.daos.AccountDAO;
import dev.tatineni.daos.AccountDAOMaria;
import dev.tatineni.entities.Account;
import dev.tatineni.entities.Customer;

public class AccountServiceImpl implements AccountService {
	
	private static AccountDAO accdao = AccountDAOMaria.getAccountDAOMaria();

	@Override
	public Account establishAccount(Account account) {
		// TODO Auto-generated method stub
		return accdao.createAccount(account);
	}

	@Override
	public Account getAccountById(int id) {
		// TODO Auto-generated method stub
		return accdao.getAccountById(id);
	}

	@Override
	public Set<Account> getAccountsByCustomerId(int id) {
		// TODO Auto-generated method stub
		return accdao.getAccountsByCustomerId(id);
		
	}

	@Override
	public Set<Account> getAccountsByCustomerId(Customer customer) {
		// TODO Auto-generated method stub
		return this.getAccountsByCustomerId(customer.getcId());
	
	}

	@Override
	public Set<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return accdao.getAllAccounts();
	}

	@Override
	public Account updateAccount(Account account) {
		// TODO Auto-generated method stub
		return accdao.updateAccount(account);
	}

	@Override
	public boolean deleteAccount(int id) {
		// TODO Auto-generated method stub
		return accdao.deleteAccount(id);
	}

	@Override
	public boolean deleteAccount(Account account) {
		// TODO Auto-generated method stub
		return this.deleteAccount(account.getaId());
	}
	
	

}
