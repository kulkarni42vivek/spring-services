package com.kulkarni42vivek.auth.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.kulkarni42vivek.auth.exception.CustomSQLException;
import com.kulkarni42vivek.auth.model.LoginModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;

@Repository
public class LoginDao {
	
	private final PasswordEncoder passwordEncoder;
	private final DataSource datasource;
	
	LoginDao(DataSource datasource, PasswordEncoder passwordEncoder, PasswordEncoder pe){
		this.datasource = datasource;
		this.passwordEncoder = pe;
	}

	public boolean isEmailPresent(String email) {
		String query = "select email from users where email =?";
		Connection conn=  DataSourceUtils.getConnection(this.datasource);
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ps.setString(1, email);
			
			ResultSet rs = ps.executeQuery();
			
			return rs.next();
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new CustomSQLException(e.getMessage());
		}
		finally { DataSourceUtils.releaseConnection(conn, datasource); }
	}

	public void saverUserDetails(LoginModel loginmodel) {
		String query = "INSERT INTO users ( password, role, email) VALUES (?, ?,?)";
		loginmodel.setPassword(passwordEncoder.encode(loginmodel.getPassword()));
		Connection conn = DataSourceUtils.getConnection(this.datasource) ; 
		try(PreparedStatement ps = conn.prepareStatement(query)){
			int i = 0;
			ps.setString(++i, loginmodel.getPassword());
			ps.setString(++i, loginmodel.getRole());
			ps.setString(++i, loginmodel.getEmail());
			
			ps.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new CustomSQLException(e.getMessage());
		}
		finally { DataSourceUtils.releaseConnection(conn, datasource); }
	}

	public Optional<LoginModel> getUserInformation(String email) {

	    String query = """
	            SELECT  email, password, role
	            FROM users
	            WHERE email = ?
	            """;

	    Connection conn = DataSourceUtils.getConnection(datasource);

	    try (PreparedStatement ps = conn.prepareStatement(query)) {
	        ps.setString(1, email);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                LoginModel loginModel = LoginModel.builder()
                        .email(rs.getString("email"))
                        .role(rs.getString("role"))
                        .build();

	                return Optional.of(loginModel);
	            }

	            return Optional.empty();
	        }

	    } catch (SQLException e) {
	    	e.printStackTrace();
	        throw new CustomSQLException("Failed to fetch user information");

	    } finally {
	        DataSourceUtils.releaseConnection(conn, datasource);
	    }
	}

}
