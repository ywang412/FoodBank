/**
 * 
 */
package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.SoupKitchen;
import edu.gatech.csedbs.team073.model.User;

/**
 * @author jgeorge
 *
 */
public interface UserDAO {

	public User login(String userName, String password);

}




