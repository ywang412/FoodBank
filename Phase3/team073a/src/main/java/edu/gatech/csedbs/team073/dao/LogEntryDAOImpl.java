/**
 * 
 */
package edu.gatech.csedbs.team073.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import edu.gatech.csedbs.team073.model.LogEntry;


/**
 * @author jgeorge
 *
 */
public class LogEntryDAOImpl implements LogEntryDAO {

	private static final Logger logger = LoggerFactory.getLogger(LogEntryDAOImpl.class);

	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 
	 */
	public LogEntryDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/* (non-Javadoc)
	 * @see edu.gatech.csedbs.team073.dao.LogEntryDAO#addLogEntry(int, java.lang.String, java.lang.String)
	 */
	@Override
	public int addLogEntry(int clientId, String logEntry, String logUsage) {
		String sql = "INSERT INTO cs6400_sp17_team073.Log_Entry" +
					 "(log_entry_string, timestamp, log_usage, client_id) "+
					 "values(?,?,?,?)";
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		int result = jdbcTemplate.update(sql,logEntry,currentTimestamp,logUsage, clientId);
		return result;
	}

	/* (non-Javadoc)
	 * @see edu.gatech.csedbs.team073.dao.LogEntryDAO#viewLog(int)
	 */
	@Override
	public LogEntry viewLog(int logId) {
		
		String sql = "SELECT * FROM cs6400_sp17_team073.Log_Entry WHERE log_id = ?";
		
		 
		
		 LogEntry logEntry = jdbcTemplate.query(sql, new Integer[]{logId}, new ResultSetExtractor<LogEntry>() {

			@Override
			public LogEntry extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				LogEntry rsLogEntry = null;
				
				while (rs.next()) {
					rsLogEntry = new LogEntry();

					rsLogEntry.setLogId(rs.getInt("log_id"));
					rsLogEntry.setClientId(rs.getInt("client_id"));
					rsLogEntry.setLogEntry(rs.getString("log_entry_string"));
					rsLogEntry.setLogUsage(rs.getString("log_usage"));
					rsLogEntry.setLogDate(rs.getTimestamp("timestamp"));
				
					
				}
				 
				return rsLogEntry;
			}

		});
		
		
		
		return logEntry;
		
	}

	/* (non-Javadoc)
	 * @see edu.gatech.csedbs.team073.dao.LogEntryDAO#viewAllLogsByClient(int)
	 */
	@Override
	public List<LogEntry> viewAllLogsByClient(int clientId) {
		
		String sql = "SELECT * FROM cs6400_sp17_team073.Log_Entry WHERE client_id = ?";
		
		 
		
		 List<LogEntry> logs = jdbcTemplate.query(sql, new Integer[]{clientId}, new ResultSetExtractor<List<LogEntry>>() {

			@Override
			public List<LogEntry> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<LogEntry> rsLogs = new ArrayList<LogEntry>();
				LogEntry rsLogEntry = null;
				
				while (rs.next()) {
					rsLogEntry = new LogEntry();

					rsLogEntry.setLogId(rs.getInt("log_id"));
					rsLogEntry.setClientId(rs.getInt("client_id"));
					rsLogEntry.setLogEntry(rs.getString("log_entry_string"));
					rsLogEntry.setLogUsage(rs.getString("log_usage"));
					rsLogEntry.setLogDate(rs.getTimestamp("timestamp"));
				
					rsLogs.add(rsLogEntry);
				}
				if (rsLogEntry == null) {
					rsLogs = Collections.emptyList();
				} 
				return rsLogs;
			}

		});
		
		
		
		return logs;
		
	}

	
}
