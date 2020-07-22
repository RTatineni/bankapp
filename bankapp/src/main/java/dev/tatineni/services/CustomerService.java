package dev.tatineni.services;

import java.util.Set;

import dev.tatineni.entities.Customer;

public interface CustomerService {
	
	// CRUD Like operations 
	Customer establishCustomer(Customer customer);
	Customer getCustomerById(int id);
	Customer updateCustomer(Customer customer);
	boolean deleteCustomerById(int id);
	Set<Customer> getAllCustomers();
	

}