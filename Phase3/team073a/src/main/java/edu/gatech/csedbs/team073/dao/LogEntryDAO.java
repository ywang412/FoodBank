/**
 * 
 */
package edu.gatech.csedbs.team073.dao;

import java.util.List;

import edu.gatech.csedbs.team073.model.LogEntry;

/**
 * @author jgeorge
 *
 */
public interface LogEntryDAO {

	public int addLogEntry(int clientId, String logEntry, String logUsage);
	public LogEntry viewLog(int logId);
	public List<LogEntry> viewAllLogsByClient(int clientId);
}
