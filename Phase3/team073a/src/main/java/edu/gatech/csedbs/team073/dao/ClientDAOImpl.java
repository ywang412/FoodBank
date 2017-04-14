/**
 * 
 */
package edu.gatech.csedbs.team073.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import edu.gatech.csedbs.team073.model.Client;


/**
 * @author jgeorge
 *
 */
public class ClientDAOImpl implements ClientDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(ClientDAOImpl.class);

	private JdbcTemplate jdbcTemplate;
	
	public ClientDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/* (non-Javadoc)
	 * @see edu.gatech.csedbs.team073.dao.ClientDAO#searchClientsByName(java.lang.String)
	 */
	@Override
	public List<Client> searchClientsByName(String name) {
		String sql = "SELECT * FROM cs6400_sp17_team073.Client WHERE full_name = ?";
		
		if (StringUtils.contains(name, "%")) {
			sql = "SELECT * FROM cs6400_sp17_team073.Client WHERE full_name LIKE ?";
			String countSql = "SELECT count(*) FROM cs6400_sp17_team073.Client WHERE full_name LIKE ?";
			int count = jdbcTemplate.queryForObject(countSql, new String[]{name}, Integer.class);
			
			if (count > 4) {
				throw new RuntimeException(" Count is > 4 for search. Please modify query to return fewer results");
			}
		}
		
		  
		
		 List<Client> clients = jdbcTemplate.query(sql, new String[]{name}, new ResultSetExtractor<List<Client>>() {

			@Override
			public List<Client> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Client> rsClients = new ArrayList<Client>();
				Client rsClient = null;
				
				while (rs.next()) {
					rsClient = new Client();

					rsClient.setHeadOfHousehold(rs.getBoolean("head_of_household"));
					rsClient.setClientId(rs.getInt("client_id"));
					rsClient.setDescription(rs.getString("description_string"));
					rsClient.setFullName(rs.getString("full_name"));
					rsClient.setPhoneNumber(rs.getString("phone_number"));
					
					rsClients.add(rsClient);
				
					
				}
				
				if (rsClient == null) {
					rsClients = Collections.emptyList();
				} 
				return rsClients;
			}

		});
		
		
		
		return clients;
	}

	/* (non-Javadoc)
	 * @see edu.gatech.csedbs.team073.dao.ClientDAO#searchClientById(int)
	 */
	@Override
	public Client searchClientById(int clientId) {
		String sql = "SELECT * FROM cs6400_sp17_team073.Client WHERE client_id = ?";
		
		 
		
		 Client client = jdbcTemplate.query(sql, new Integer[]{clientId}, new ResultSetExtractor<Client>() {

			@Override
			public Client extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				Client rsClient = null;
				
				while (rs.next()) {
					rsClient = new Client();

					rsClient.setHeadOfHousehold(Boolean.valueOf(rs.getString("head_of_household")));
					rsClient.setClientId(rs.getInt("client_id"));
					rsClient.setDescription(rs.getString("description_string"));
					rsClient.setFullName(rs.getString("full_name"));
					rsClient.setPhoneNumber(rs.getString("phone_number"));
				
					
				}
				 
				return rsClient;
			}

		});
		
		
		
		return client;
	}

	/* (non-Javadoc)
	 * @see edu.gatech.csedbs.team073.dao.ClientDAO#addClient(Client inClient)
	 */
	@Override
	public Client addClient(Client inClient) {
		String sql = "INSERT INTO cs6400_sp17_team073.Client" +
				 "(full_name, description_string, head_of_household, phone_number) "+
				 "values(?,?,?,?)";
		
/*		int result = jdbcTemplate.update(sql,inClient.getFullName(),
				inClient.getDescription(),inClient.isHeadOfHousehold(),
				inClient.getPhoneNumber());*/
		
		PreparedStatementCreator psc = new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, inClient.getFullName());
				ps.setString(2, inClient.getDescription());
				ps.setBoolean(3, inClient.isHeadOfHousehold());
				ps.setString(4, inClient.getPhoneNumber());
				return ps;
				
			}
			
		};
		GeneratedKeyHolder holder = new GeneratedKeyHolder();
		int result = jdbcTemplate.update(psc, holder);
		
		Client client = inClient;
		
		if (result > 0) {
			int id = holder.getKey().intValue();
			client.setClientId(id);
		}
		
		return client;
	}

	/* (non-Javadoc)
	 * @see edu.gatech.csedbs.team073.dao.ClientDAO#modifyClient(Client inClient)
	 */
	@Override
	public int modifyClient(Client inClient) {
		
		int clientId = inClient.getClientId(); 
		String name = inClient.getFullName();
		String description = inClient.getDescription(); 
		boolean headOfHousehold = inClient.isHeadOfHousehold(); 
		String phoneNumber = inClient.getPhoneNumber();
		
		Client originalClient = searchClientById(clientId);
		final String logUsage = "Field Modified";
		StringBuffer modified = new StringBuffer();
		modified.append("Original ");
		
		String sql = "UPDATE cs6400_sp17_team073.Client SET ";
		List<Object> parms = new ArrayList<Object>();
		
		
		if (StringUtils.isNotBlank(name)) {
			sql = sql + "full_name = ? ";
			parms.add(name);
			modified.append("name ");
			modified.append(originalClient.getFullName());
			
			if (StringUtils.isNotBlank(description)) {
				sql = sql + ", description_string = ? ";
				parms.add(description);
				modified.append(" description ");
				modified.append(originalClient.getDescription());
			}
			
			if (StringUtils.isNotBlank(String.valueOf(headOfHousehold))) {
				sql = sql + ", head_of_household = ? ";
				parms.add(Boolean.valueOf(headOfHousehold));
				modified.append(" head of household ");
				modified.append(String.valueOf(originalClient.isHeadOfHousehold()));
			}
			
			if (StringUtils.isNotBlank(phoneNumber)) {
				sql = sql + ", phone_number = ? ";
				parms.add(phoneNumber);
				modified.append(" phone number ");
				modified.append(originalClient.getPhoneNumber());
			}
		} else {

			if (StringUtils.isNotBlank(description)) {
				sql = sql + " description_string = ? ";
				parms.add(description);
				modified.append(" description ");
				modified.append(originalClient.getDescription());
			}

			if (StringUtils.isNotBlank(String.valueOf(headOfHousehold))) {
				sql = sql + ", head_of_household = ? ";
				parms.add(Boolean.valueOf(headOfHousehold));
				modified.append(" head of household ");
				modified.append(String.valueOf(originalClient.isHeadOfHousehold()));
			}
			
			if (StringUtils.isNotBlank(phoneNumber)) {
				sql = sql + ", phone_number = ? ";
				parms.add(phoneNumber);
				modified.append(" phone number ");
				modified.append(originalClient.getPhoneNumber());
			}
		}		 
		sql = sql +	" WHERE client_id = " + clientId;
		
		Object[] parmArray = new Object[parms.size()];
		parms.toArray(parmArray);
		
		int result = jdbcTemplate.update(sql,parmArray);
		
		if (result > 0) {
			logChanges(clientId, modified.toString(), logUsage);
		}
		
		return result;
	}

	
	public void logChanges(int clientId, String logEntry, String logUsage) {

		LogEntryDAO logEntryDAO = new LogEntryDAOImpl(jdbcTemplate.getDataSource());
		
		logEntryDAO.addLogEntry(clientId,logEntry,logUsage);
	}
}
