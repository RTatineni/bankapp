package dev.tatineni.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.tatineni.entities.Account;
import dev.tatineni.utils.ConnectionUtil;

public class AccountDAOMaria implements AccountDAO {
	
	private static AccountDAO accdao = null;

	private AccountDAOMaria() {
		
	}
	public static AccountDAO getAccountDAOMaria() {
		if(accdao == null) {
			accdao = new AccountDAOMaria();
		}
		return accdao;
	}
	
	@Override
	public Account createAccount(Account account) {
		// TODO Auto-generated method stub
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO bankapp.account VALUES (?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 0);
			ps.setInt(2, account.getcId());
			ps.setString(3, account.getAccountName());
			ps.setInt(4, account.getBalance());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys(); // returns a virtual table
			rs.next();// moves you to the first record in that table
			int key = rs.getInt("a_id");
			account.setaId(key);
			
	
			return account;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Account getAccountById(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bankapp.account WHERE a_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery(); // get us back a table with all the information
			rs.next();// move table to first record
			
			Account account = new Account();
			account.setaId(rs.getInt("a_id"));
			account.setcId(rs.getInt("c_id"));
			account.setAccountName(rs.getString("account_name"));
			account.setBalance(rs.getInt("balance"));
			
			return account;
		
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	
		
	}

	@Override
	public Set<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bankapp.account";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			Set<Account> accounts = new HashSet<Account>();
			
			while(rs.next()) {
				
				Account account = new Account();
				account.setaId(rs.getInt("a_id"));
				account.setcId(rs.getInt("c_id"));			
				account.setAccountName(rs.getString("account_name"));
				account.setBalance(rs.getInt("balance"));
				accounts.add(account);
				
			}
			
			return accounts;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Set<Account> getAccountsByCustomerId(int id) {
		// TODO Auto-generated method stub
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bankapp.account WHERE c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			Set<Account> accounts = new HashSet<Account>();
			
			while(rs.next()) {
				
				Account account = new Account();
				account.setaId(rs.getInt("a_id"));
				account.setcId(rs.getInt("c_id"));	
				account.setAccountName(rs.getString("account_name"));
				account.setBalance(rs.getInt("balance"));
				accounts.add(account);
				
			}
			
			return accounts;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Account updateAccount(Account account) {
		// TODO Auto-generated method stub
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE bankapp.account SET c_id = ?, account_name = ?, balance = ? WHERE a_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, account.getcId());
			ps.setString(2, account.getAccountName());
			ps.setInt(3, account.getBalance());
			ps.setInt(4, account.getaId());
			ps.executeUpdate();
			
			return account;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteAccount(int id) {
		// TODO Auto-generated method stub
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM bankapp.account WHERE a_id =?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int rows = ps.executeUpdate();
			if(rows>0) {
				return true;
			}else {
				return false;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	

}
