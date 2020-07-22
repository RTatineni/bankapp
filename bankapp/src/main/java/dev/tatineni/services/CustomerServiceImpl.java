package dev.tatineni.services;

import java.util.Set;

import dev.tatineni.daos.AccountDAO;
import dev.tatineni.daos.AccountDAOMaria;
import dev.tatineni.daos.CustomerDAO;
import dev.tatineni.daos.CustomerDAOMaria;
import dev.tatineni.entities.Account;
import dev.tatineni.entities.Customer;

public class CustomerServiceImpl implements CustomerService {
	
	private static CustomerDAO cdao = CustomerDAOMaria.getCustomerDAOMaria(); // we already have our basic CRUD operations
	private static AccountDAO accdao = AccountDAOMaria.getAccountDAOMaria();
	
	@Override
	public Customer establishCustomer(Customer customer) {
		return cdao.createCustomer(customer);
	}


	@Override
	public Customer getCustomerById(int id) {
		Customer Customer = cdao.getCustomerById(id);
		Set<Account> Accounts = accdao.getAccountsByCustomerId(id);
		Customer.setAccounts(Accounts);		
		return Customer;
	}

	@Override
	public Customer updateCustomer(Customer customer) {		
		return cdao.updateCustomer(customer);
	}

	@Override
	public boolean deleteCustomerById(int id) {	
		return cdao.deleteCustomer(id);
	}

	@Override
	public Set<Customer> getAllCustomers() {
		return cdao.getAllCustomers();
	}





}