package dev.tatineni.daotests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.tatineni.daos.CustomerDAO;
import dev.tatineni.daos.CustomerDAOMaria;
import dev.tatineni.entities.Customer;
import dev.tatineni.utils.ConnectionUtil;

@TestMethodOrder(OrderAnnotation.class)
class CustomerDAOtests {
	
	public static CustomerDAO cdao = CustomerDAOMaria.getCustomerDAOMaria();
	
	@BeforeAll
	static void setUp() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL set_up_bankappdb";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	
	
	@Test
	@Order(1)
	void createCustomer() {
		
		
		Customer somePerson = new Customer(0,"French","Fry");
		cdao.createCustomer(somePerson);
		Assertions.assertNotEquals(0,somePerson.getcId());
		Assertions.assertEquals(1, somePerson.getcId());
	}

	@Test
	@Order(2)
	void getCustomerById() {		
		Customer monHigh = cdao.getCustomerById(1);
		Assertions.assertEquals(1, monHigh.getcId());
	}
	
	@Test
	@Order(3)
	void getAllCustomers() {
		Customer someUser = new Customer(0,"admin","password");
		cdao.createCustomer(someUser);
		Set<Customer> Customers = cdao.getAllCustomers();
		Assertions.assertEquals(2,Customers.size());
	}
	
	@Test
	@Order(4)
	void updateCustomer() {
		Customer mon = cdao.getCustomerById(1);
		mon.setUsername("Downtown HighCustomer");
		mon = cdao.updateCustomer(mon); //saves the changes to that Customer
		Assertions.assertEquals("Downtown HighCustomer", mon.getUsername());
		
	}
	
	@Test
	@Order(5)
	void deleteCustomer() {
		boolean result = cdao.deleteCustomer(1);
		Assertions.assertEquals(true, result);
	}
	
	@Test
	@Order(6)
	void deleteCustomerNegative() {
		boolean result = cdao.deleteCustomer(10);
		Assertions.assertEquals(false, result);
	}
	
	
	@AfterAll
	static void tearDown() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL tear_down_bankappdb";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
