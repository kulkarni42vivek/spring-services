package com.kulkarni42vivek.auth.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kulkarni42vivek.auth.exception.CustomSQLException;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final DataSource ds;

	public CustomUserDetailsService(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String query = "select * from users where email=?";
		Connection conn = DataSourceUtils.getConnection(ds);

		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();

			if (!rs.next()) {
                throw new UsernameNotFoundException(
                        "User not found: " + username);
            }
			
			String[] roles = rs.getString("role").split(",");

			List<SimpleGrantedAuthority> glist = List.of(roles).stream().map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());

			UserDetailsModel response = UserDetailsModel.builder()
					.username(rs.getString("email"))
					.password(rs.getString("password"))
					.authorities(glist).build();

			return response;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new CustomSQLException("Failed to load user details");
		} finally {
			DataSourceUtils.releaseConnection(conn, ds);
		}
	}

}
