package edu.gatech.csedbs.team073.dao;

import java.util.List;

import edu.gatech.csedbs.team073.model.Client;
import edu.gatech.csedbs.team073.model.LogEntry;

/**
 * 
 */
public interface ClientDAO {
	
	public List<Client> searchClientByName(String searchString);
	public Client findClientById(int clientId);
	public int addClient(Client inClient);
	public int updateClient(Client inClient);
	public int addClientToService(int clientId, int serviceId, String serviceName);
	public List<LogEntry> getLogsByClientId(int clientId);
	public int addClientLog(int clientId, LogEntry inClientLog);
	
}
