/**
 * 
 */
package edu.gatech.csedbs.team073.model;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author JGeorge
 *
 */
public class LogEntry {

	private int logId;
	private String logEntry;
	private String logUsage;
	private Timestamp logDate;
	private int clientId;
	
	/**
	 * @return the logId
	 */
	public int getLogId() {
		return logId;
	}
	/**
	 * @param logId the logId to set
	 */
	public void setLogId(int logId) {
		this.logId = logId;
	}
	/**
	 * @return the logEntry
	 */
	public String getLogEntry() {
		return logEntry;
	}
	/**
	 * @param logEntry the logEntry to set
	 */
	public void setLogEntry(String logEntry) {
		this.logEntry = logEntry;
	}
	/**
	 * @return the logUsage
	 */
	public String getLogUsage() {
		return logUsage;
	}
	/**
	 * @param logUsage the logUsage to set
	 */
	public void setLogUsage(String logUsage) {
		this.logUsage = logUsage;
	}
	/**
	 * @return the date
	 */
	public Timestamp getLogDate() {
		return logDate;
	}
	/**
	 * @param date the date to set
	 */
	public void setLogDate(Timestamp date) {
		this.logDate = date;
	}
	/**
	 * @return the clientId
	 */
	public int getClientId() {
		return clientId;
	}
	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	
	
}
