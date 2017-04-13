package edu.gatech.csedbs.team073.dao;

import java.util.List;

import edu.gatech.csedbs.team073.model.Client;
import edu.gatech.csedbs.team073.model.LogEntry;

/**
 * Created by jgeorge on 4/9/17.
 */
public class ClientDAOImpl implements ClientDAO {

	@Override
	public List<Client> searchClientByName(String searchString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client findClientById(int clientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addClient(Client inClient) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateClient(Client inClient) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addClientToService(int clientId, int serviceId, String serviceName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<LogEntry> getLogsByClientId(int clientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addClientLog(int clientId, LogEntry inClientLog) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
