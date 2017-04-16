/**
 * 
 */
package edu.gatech.csedbs.team073.web;


import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.gatech.csedbs.team073.dao.ClientDAO;
import edu.gatech.csedbs.team073.dao.LogEntryDAO;
import edu.gatech.csedbs.team073.model.Client;
import edu.gatech.csedbs.team073.model.LogEntry;
import edu.gatech.csedbs.team073.model.ServiceInfo;


/**
 * @author jgeorge
 *
 */
@Controller
@SessionAttributes({"client","serviceName","serviceObj"})
public class LogEntryController {

	private static final Logger logger = LoggerFactory.getLogger(LogEntryController.class);
	

	
	@Autowired
	private LogEntryDAO logEntryDAO;
	

	
	
	@RequestMapping(value="/LogAddForm", method = RequestMethod.GET)
	public ModelAndView clientAdd(ModelAndView model, HttpServletRequest request) {
		Client client = new Client();
		model.addObject("client", client);
		
		LogEntry logEntry = new LogEntry();
		logEntry.setLogDate(new Timestamp(System.currentTimeMillis()));
		
		if (null != request.getSession(false).getAttribute("serviceObj")) {
			ServiceInfo serviceInfo = (ServiceInfo)request.getSession(false).getAttribute("serviceObj");
			logEntry.setLogUsage(serviceInfo.getDescription());
		}
		
		logEntry.setLogEntry("From LogAddForm 1");
		
		model.addObject("logEntry", logEntry);
		
		model.setViewName("LogAddForm");
		
		return model;
	}
	
	@RequestMapping(value="/LogAddSubmit", method = RequestMethod.POST)	
	public ModelAndView clientAddSubmit(@ModelAttribute LogEntry logEntry, BindingResult result, 
			final RedirectAttributes redirectAttributes, HttpServletRequest request) {
				
		ModelAndView model = null;
		
	
		
		Client client = (Client) request.getSession(false).getAttribute("client");
				
		int rc = logEntryDAO.addLogEntry(client.getClientId(), logEntry.getLogEntry(), logEntry.getLogUsage());
		
		if (rc > 0) {
			
			model = new ModelAndView("ClientViewForm");
			model.addObject("client", client);
			
			redirectAttributes.addFlashAttribute("msg","Log added successfully");
						
		} else {
	
			model = new ModelAndView("LogAddForm");
			model.addObject("client", client);
			model.addObject("logEntry", logEntry);
			result.rejectValue(null,"error.logadderror", "Error adding log");
		}
		
		return model;
	}
	

}
