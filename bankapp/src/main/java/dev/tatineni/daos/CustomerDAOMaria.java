package dev.tatineni.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.tatineni.entities.Customer;
import dev.tatineni.utils.ConnectionUtil;

public class CustomerDAOMaria implements CustomerDAO {
	
	private static CustomerDAO cdao = null;
	
	private CustomerDAOMaria() {
		
	}
	public static CustomerDAO getCustomerDAOMaria() {
		if(cdao == null) {
			cdao = new CustomerDAOMaria();
		}
		
		return cdao;
	}

	@Override
	public Customer createCustomer(Customer customer) {
		// try with resources. Will auto close the connection for us when we finish our interaction
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO bankapp.customer VALUES (?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 0);
			ps.setString(2, customer.getUsername());
			ps.setString(3, customer.getPassword());
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int key = rs.getInt("c_id");
			customer.setcId(key);
			
			return customer;			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public Customer getCustomerById(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bankapp.customer WHERE c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			// ResultSet returns a table. the intial record it points is before the first record
			rs.next();// move the cursor to the first actual record
			
			Customer Customer = new Customer();
			Customer.setcId(rs.getInt("c_id"));
			Customer.setUsername(rs.getString("username"));
			Customer.setPassword(rs.getString("password"));
			
			return Customer;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Set<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bankapp.customer";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			// ResultSet returns a table. the intial record it points is before the first record
			
			Set<Customer> Customers = new HashSet<Customer>();
			
			while(rs.next()) {
				Customer Customer = new Customer();
				Customer.setcId(rs.getInt("c_id"));
				Customer.setUsername(rs.getString("username"));
				Customer.setPassword(rs.getString("password"));
				Customers.add(Customer);
			}
			
			return Customers;
							
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE bankapp.customer SET username  = ?, password = ? WHERE c_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, customer.getUsername());
			ps.setString(2, customer.getPassword());
			ps.setInt(3, customer.getcId());
			ps.execute();
				
			return customer;				
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteCustomer(int id) {
		// TODO Auto-generated method stub
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM bankapp.customer WHERE c_id = ?";
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
