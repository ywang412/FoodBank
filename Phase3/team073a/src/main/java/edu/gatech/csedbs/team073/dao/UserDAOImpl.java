/**
 * 
 */
package edu.gatech.csedbs.team073.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import edu.gatech.csedbs.team073.model.User;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author jgeorge
 *
 */
public class UserDAOImpl implements UserDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	private NamedParameterJdbcTemplate jdbc;
	private JdbcTemplate jdbcTemplate;
	
	public UserDAOImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	/* (non-Javadoc)
	 * @see edu.gatech.csedbs.team073.dao.UserDAO#login(java.lang.String, java.lang.String)
	 */
	@Override
	public User login(String userName, String password) {
		String sql = "SELECT * FROM cs6400_sp17_team073.User WHERE username = ? AND password = ?";
		/*User user = jdbcTemplate.query(sql, new String[]{userName, password}, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User rsUser = new User();
	
				rsUser.setSiteId(rs.getInt("site_id"));
				rsUser.setFullName(rs.getString("full_name"));
				rsUser.setUserEmail(rs.getString("user_email"));
				rsUser.setUserName(userName);
				
				return rsUser;
			}
			
		});*/
		
		User user = jdbcTemplate.query(sql, new String[]{userName, password}, new ResultSetExtractor<User>() {
			
			@Override
			public User extractData(ResultSet rs) throws SQLException, DataAccessException {
				User rsUser = null;
				//final String rsUserName = userName;
				while (rs.next()) {
					rsUser = new User();
					
					rsUser.setSiteId(rs.getInt("site_id"));
					rsUser.setFullName(rs.getString("full_name"));
					rsUser.setUserEmail(rs.getString("user_email"));
					rsUser.setUserName(rs.getString("username"));
					rsUser.setPassword(rs.getString("password"));


					//rsUser.setUserName(rsUserName);
					 
				}
				return rsUser;
			}
			
		});
		
		return user;
	}


	public class UserMapper implements RowMapper<User> {
		public User mapRow(ResultSet row, int rowNum) throws SQLException {
			User user = new User();

			user.setUserName(row.getString("username" ) );
			user.setUserEmail(row.getString("user_email" ) );
			user.setPassword(row.getString("password" ) );
			user.setFullName(row.getString("full_name" ) );
			user.setSiteId(row.getInt("site_id" ) );

			return user;
		}
	}


	public User getUser(String username) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", username);

		String sql = "select User.username,User.user_email, User.password, User.full_name, User.site_id FROM User WHERE User.username=:username";


		return jdbc.queryForObject(sql, params, new UserMapper());
	}

}
