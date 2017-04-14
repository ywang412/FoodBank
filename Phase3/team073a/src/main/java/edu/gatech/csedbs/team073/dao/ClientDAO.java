/**
 * 
 */
package edu.gatech.csedbs.team073.dao;

import java.util.List;

import edu.gatech.csedbs.team073.model.Client;

/**
 * @author jgeorge
 *
 */
public interface ClientDAO {

	public List<Client> searchClientsByName(String name);
	public Client searchClientById(int clientId);
	public Client addClient(Client inClient);
	public int modifyClient(Client inClient);
	
	
	
}
