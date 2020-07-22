package dev.tatineni.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.Gson;

import dev.tatineni.entities.Account;
import dev.tatineni.services.AccountService;
import dev.tatineni.services.AccountServiceImpl;
import dev.tatineni.services.CustomerService;
import dev.tatineni.services.CustomerServiceImpl;
import io.javalin.http.Handler;

public class AccountController {
	
	private static AccountService accServ = new AccountServiceImpl();
	private static CustomerService cusServ= new CustomerServiceImpl();
	private static Gson gson = new Gson();
	
	// ctx Context object that Javalin gives us to get information from the HTTP request and form an HTTP response
	public static Handler getAllAccountsFromCustomerId = (ctx)->{
		
		int CustomerId = Integer.parseInt(ctx.pathParam("cid"));	
		Set<Account> Accounts = accServ.getAccountsByCustomerId(CustomerId);
		
		String balanceLessThan = ctx.queryParam("balancelessthan");
		String balanceGreaterThan = ctx.queryParam("balancegreaterthan");
		
		Set<Account> lessThanAccounts = new HashSet();
		Set<Account> greaterThanAccounts = new HashSet();
		Set<Account> middleThanAccounts = new HashSet();
		
		
		if(balanceGreaterThan != null && balanceLessThan != null) 
		{
			for(Account a: Accounts) {
				if(a.getBalance() > Integer.parseInt(balanceGreaterThan) && a.getBalance() < Integer.parseInt(balanceLessThan)) {
					middleThanAccounts.add(a);
				}
			}
			String middleThanJson = gson.toJson(lessThanAccounts);
			ctx.result(middleThanJson);
			
		}
		else if(balanceLessThan != null) {
			for(Account a: Accounts) {
				if(a.getBalance() < Integer.parseInt(balanceLessThan)) {
					// Add to list and return
					lessThanAccounts.add(a);
				}
				
			}
			String lessThanJson = gson.toJson(lessThanAccounts);
			ctx.result(lessThanJson);
		}
		else if(balanceGreaterThan != null)
		{
			for(Account a: Accounts) {
				if(a.getBalance() > Integer.parseInt(balanceGreaterThan)) {
					greaterThanAccounts.add(a);

				}
			}
			String greaterThanJson = gson.toJson(lessThanAccounts);
			ctx.result(greaterThanJson);
		
		
		}
		else {
			String json = gson.toJson(Accounts);
			
			ctx.result(json);
		}
	
	};
	
	public static Handler getAccountById = (ctx)->{
		int AccountId = Integer.parseInt(ctx.pathParam("aid"));
		Account Account = accServ.getAccountById(AccountId);
		String json = gson.toJson(Account);
		
		ctx.result(json);
	};
	
	public static Handler createAccount = (ctx)->{
		int CustomerId = Integer.parseInt(ctx.pathParam("cid"));	
		Account Account = gson.fromJson(ctx.body(), Account.class); // convert the JSON body of the request into an object
		Account.setcId(CustomerId);
		accServ.establishAccount(Account);
		
		String json = gson.toJson(Account);
		ctx.status(201);
		
		ctx.result(json);	
	};
	
	public static Handler updateAccount = (ctx)->{
		Account Account = gson.fromJson(ctx.body(), Account.class);
		accServ.updateAccount(Account);
		String json = gson.toJson(Account);
		ctx.result(json);
	};
	
	
	public static Handler deleteAccount = (ctx) ->{
		int AccountId = Integer.parseInt(ctx.pathParam("aid"));
		Boolean result = accServ.deleteAccount(AccountId);
		ctx.result(result.toString());
		
	};
	


}