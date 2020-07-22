package dev.tatineni.daos;

import java.util.Set;

import dev.tatineni.entities.Customer;

public interface CustomerDAO {
	
	// Create
	Customer createCustomer(Customer customer);
	
	
	// Read
	Customer getCustomerById(int id);
	Set<Customer> getAllCustomers();
	
	// Update
	Customer updateCustomer(Customer customer);
	
	// Delete
	boolean deleteCustomer(int id);

}
