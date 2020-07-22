package dev.tatineni.controllers;

import java.util.Set;

import com.google.gson.Gson;

import dev.tatineni.services.CustomerService;
import dev.tatineni.services.CustomerServiceImpl;
import dev.tatineni.entities.Customer;
import io.javalin.http.Handler;


public class CustomerController {
	public static CustomerService sserv = new CustomerServiceImpl();
	private static Gson gson = new Gson();
	
	public static Handler getAllCustomers = (ctx) -> {
		Set<Customer> customers = sserv.getAllCustomers();
		String json = gson.toJson(customers);			
		String un = ctx.queryParam("username");
		if(un != null) {
			for(Customer c: customers) {
				String currUsername = c.getUsername();
				int currId = c.getcId();
				System.out.println(currId);
				if(currUsername.equals(un)) {
					System.out.println(currId);
					Customer cu = sserv.getCustomerById(currId);
					System.out.println(cu);
					ctx.result(gson.toJson(cu));	
					break;
				}
				else {
					ctx.result(json);	

				}
			
			}
		}
		else {
			ctx.result(json);	

		}
	
	
	};
	
	// your services should not have to deal with JSONs only your controllers
	public static Handler createCustomer= (ctx) ->{
		//context object ctx is an object that contains the http request and response objects
		// it contains a whole bunch of method for dealing with getting information from the request
		// and sending information in the http response
		
		String CustomerJson = ctx.body();
		Customer Customer = gson.fromJson(CustomerJson, Customer.class); // transform the json into a Shcool object
		sserv.establishCustomer(Customer);
		ctx.status(201); // 201 is the status code to return if you successfully add something
		
		//usually you want to return the created object
		ctx.result(gson.toJson(Customer));
	};
	
	
	public static Handler getCustomerById = (ctx)->{
		String id = ctx.pathParam("cid");
		Customer Customer = sserv.getCustomerById(Integer.parseInt(id));
		//Customer object but I want it sent back as a JSON		
		String json = gson.toJson(Customer);
		ctx.result(json);	
	};
	
	public static Handler updateCustomer = (ctx)->{
		String CustomerJson = ctx.body();
		Customer Customer = gson.fromJson(CustomerJson, Customer.class);
		sserv.updateCustomer(Customer);	
		ctx.result(gson.toJson(Customer));
	};
	
	public static Handler deleteCustomer = (ctx) ->{
		String id = ctx.pathParam("cid");
		boolean result = sserv.deleteCustomerById(Integer.parseInt(id));
		
	};
	

}
