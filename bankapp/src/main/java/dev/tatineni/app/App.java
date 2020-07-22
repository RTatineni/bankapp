package dev.tatineni.app;

import dev.tatineni.controllers.AccountController;
import dev.tatineni.controllers.CustomerController;
import io.javalin.Javalin;

public class App {
	
	
		public static void main(String[] args) {
		
		
	
		Javalin app = Javalin.create().start(7000);
	
	
		// CRUD operations
				// read operations
		app.get("/customers", CustomerController.getAllCustomers);

		app.get("/customers/:cid", CustomerController.getCustomerById);
		
		// create operation

		app.post("/customers", CustomerController.createCustomer);

		//update operation
		app.put("/customers", CustomerController.updateCustomer);
		
		//delete operation
		app.delete("/customers/:cid", CustomerController.deleteCustomer);
		
		//
		app.post("/customers/:cid/accounts", AccountController.createAccount);//
		
		app.put("/customers/:cid/accounts", AccountController.updateAccount);// 
		
		app.delete("/customers/:cid/accounts/:aid", AccountController.deleteAccount);// 
		
		app.get("/customers/:cid/accounts", AccountController.getAllAccountsFromCustomerId);
		
		app.get("/customers/:cid/accounts/:aid", AccountController.getAccountById);//r
		app.get("/accounts/:aid", AccountController.getAccountById); 

	}
	
}
