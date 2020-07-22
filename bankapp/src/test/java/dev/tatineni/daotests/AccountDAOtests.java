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

import dev.tatineni.daos.AccountDAO;
import dev.tatineni.daos.AccountDAOMaria;
import dev.tatineni.daos.CustomerDAO;
import dev.tatineni.daos.CustomerDAOMaria;
import dev.tatineni.entities.Account;
import dev.tatineni.entities.Customer;
import dev.tatineni.utils.ConnectionUtil;


@TestMethodOrder(OrderAnnotation.class)
class AccountDAOtests {
	
	public static AccountDAO adao = AccountDAOMaria.getAccountDAOMaria();
	public static CustomerDAO cdao = CustomerDAOMaria.getCustomerDAOMaria();

	@BeforeAll
	static void setUp() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "CALL set_up_bankappdb";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			// Create Customer first
			Customer aPerson = new Customer(0,"French","Fry");
			cdao.createCustomer(aPerson);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	
	
	@Test
	@Order(1)
	void createAccount() {
		
		// Create Customer first
		//Customer aPerson = new Customer(0,"French","Fry");

		Account someAccount = new Account(0,1,"someAccount",100);
		adao.createAccount(someAccount);
		Assertions.assertNotEquals(0,someAccount.getaId());
		Assertions.assertEquals(1, someAccount.getaId());
	}

	@Test
	@Order(2)
	void getAccountById() {		
		Account someAccount = adao.getAccountById(1);
		Assertions.assertEquals(1, someAccount.getaId());
	}
	
	@Test
	@Order(3)
	void getAllAccounts() {
		Account someBigAccount = new Account(0,1,"someAccount",100);

		adao.createAccount(someBigAccount);
		Set<Account> accounts = adao.getAllAccounts();
		Assertions.assertEquals(2,accounts.size());
	}
	
	@Test
	@Order(4)
	void updateAccount() {
		Account mon = adao.getAccountById(1);
		mon.setAccountName("big money");
		mon = adao.updateAccount(mon); //saves the changes to that Customer
		Assertions.assertEquals("big money", mon.getAccountName());
		
	}
	
	@Test
	@Order(5)
	void deleteAccount() {
		boolean result = adao.deleteAccount(1);
		Assertions.assertEquals(true, result);
	}
	
	@Test
	@Order(6)
	void deleteAccountNegative() {
		boolean result = adao.deleteAccount(10);
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
