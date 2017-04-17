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
import edu.gatech.csedbs.team073.model.SearchClient;
import edu.gatech.csedbs.team073.model.ServiceInfo;


/**
 * @author jgeorge
 *
 */
@Controller
@SessionAttributes({"client","serviceName","serviceObj"})
public class ClientController {

	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
	
	@Autowired
	private ClientDAO clientDAO;
	
	@Autowired
	private LogEntryDAO logEntryDAO;
	
	@RequestMapping(value="/ClientSearchForm", method = RequestMethod.GET)
	public ModelAndView clientSearch(ModelAndView model) {
		SearchClient searchClient = new SearchClient();
		model.addObject("searchClient", searchClient);
		model.setViewName("ClientSearchForm");
		
		return model;
	}
	
	@RequestMapping(value="/ClientSearchSubmit", method = RequestMethod.POST, params={ "clientSearch" })
	//public String clientSearchSubmit(@RequestParam("searchClient") String searchClient, BindingResult result, Model model) {
	public String clientSearchSubmit(@ModelAttribute("searchClient") SearchClient searchClient, BindingResult result, Model model) {
		
		ClientSearchValidator searchValidator = new ClientSearchValidator();
		searchValidator.validate(searchClient, result);
		
		if (result.hasErrors()) {
			
		} else {
			if (StringUtils.isNotBlank(searchClient.getSearchParms())) {
				try {
					List<Client> results = clientDAO.searchClientsByName(searchClient.getSearchParms());
					model.addAttribute("searchResults", results);
					
					if (results.isEmpty()) {
						result.rejectValue(null,"error.noresults", "No results found" );
					}
					
				} catch (Exception e) {
					
					model.addAttribute("searchClient", searchClient);
					result.rejectValue(null,"error.toomanyresults", e.getMessage());
				}
			}
			
			if (StringUtils.isNotBlank(searchClient.getSearchDescription())) {
				try {
					List<Client> results = clientDAO.searchClientsByDescription(searchClient.getSearchDescription());
					model.addAttribute("searchResults", results);
					
					if (results.isEmpty()) {
						result.rejectValue(null,"error.noresults", "No results found" );
					}
					
				} catch (Exception e) {
					
					model.addAttribute("searchClient", searchClient);
					result.rejectValue(null,"error.toomanyresults", e.getMessage());
				}
			}
		}
		

		
		return "ClientSearchForm";
	}
	
	@RequestMapping(value="/ClientSearchSubmit", method = RequestMethod.POST, params={ "goToAdd" })	
	//public ModelAndView clientGoToAdd(@ModelAttribute Client client, @RequestParam String goToAdd, BindingResult result, Model model) {
	public ModelAndView clientGoToAdd(@RequestParam String goToAdd, Model model) {			
		
		Client client = new Client();
		//model.addAttribute("client", client);
		ModelAndView model1 = new ModelAndView("ClientAddForm");
		model1.addObject("client", client);
		
		//return "ClientAddForm";
		return model1;
	}
	
	
	
	@RequestMapping(value="/ClientAddForm", method = RequestMethod.GET)
	public ModelAndView clientAdd(ModelAndView model) {
		Client client = new Client();
		model.addObject("client", client);
		//model.addObject("serviceName", "TEST");
		model.setViewName("ClientAddForm");
		
		return model;
	}
	
	@RequestMapping(value="/ClientAddSubmit", method = RequestMethod.POST)	
	public ModelAndView clientAddSubmit(@ModelAttribute Client client, BindingResult result, final RedirectAttributes redirectAttributes) {
				
		ModelAndView model = null;
				
		Client insertedClient = clientDAO.addClient(client);
		
		if (insertedClient.getClientId() > 0) {
			
			model = new ModelAndView("ClientViewForm");
			
			model.addObject("client", insertedClient);
			redirectAttributes.addFlashAttribute("msg","Client added successfully");
						
		} else {
	
			model = new ModelAndView("ClientAddForm");
			model.addObject("client", client);
			result.rejectValue(null,"error.clientadderror", "Error adding client");
		}
		
		return model;
	}
	
	@RequestMapping(value="/ClientViewForm", method = RequestMethod.GET)
	public ModelAndView clientView(@RequestParam(value="clientId") String clientId, ModelAndView model) {
		
		Client client = null;
		
		if (StringUtils.isNotBlank(clientId)) {
			int id = Integer.valueOf(clientId);
			client = clientDAO.searchClientById(id);
		} else {
			client = new Client();
		}
		
		
		model.addObject("client", client);
		//model.getModel().put("client", client);
		//model.addObject("serviceName", "TEST");
		model.setViewName("ClientViewForm");
		
		return model;
	}
	
	@RequestMapping(value="/ClientViewSubmit", method = RequestMethod.POST, params={ "addLog" })	
	public ModelAndView clientViewToAddLogSubmit(@ModelAttribute Client client, @RequestParam String addLog, BindingResult result,HttpServletRequest request) {
				
		ModelAndView model = new ModelAndView("LogAddForm");
		model.addObject("client", client);
		LogEntry logEntry = new LogEntry();
		logEntry.setClientId(client.getClientId());
		logEntry.setLogDate(new Timestamp(System.currentTimeMillis()));
		
		if (null != request.getSession(false).getAttribute("serviceObj")) {
			ServiceInfo serviceInfo = (ServiceInfo)request.getSession(false).getAttribute("serviceObj");
			StringBuffer sb = new StringBuffer("Site: ");
			sb.append(serviceInfo.getSiteId());
			sb.append("; Service: ");
			sb.append(serviceInfo.getServiceId());
			sb.append("; Description: ");
			sb.append(serviceInfo.getDescription());
			logEntry.setLogUsage(sb.toString());
			logEntry.setLogEntry(serviceInfo.getDescription());
		}
		
		model.addObject("logEntry", logEntry);
		return model;
	}
	
	@RequestMapping(value="/ClientViewSubmit", method = RequestMethod.POST, params={ "editClient" })	
	public ModelAndView clientViewToEditClientSubmit(@ModelAttribute Client client, @RequestParam String editClient, BindingResult result) {
				
		ModelAndView model = new ModelAndView("ClientEditForm");
		model.addObject("client", client);	
		return model;
	}
	
	@RequestMapping(value="/ClientViewSubmit", method = RequestMethod.POST, params={ "viewClientWaitlist" })	
	public ModelAndView clientViewToClientWaitList(@ModelAttribute Client client, @RequestParam String viewClientWaitlist, BindingResult result) {
				
		ModelAndView model = new ModelAndView("ClientWaitlistForm");
		model.addObject("client", client);	
		return model;
	}
	
	@RequestMapping(value="/ClientEditForm", method = RequestMethod.GET)
	//public ModelAndView clientEdit(@RequestParam(value="clientId") String clientId, ModelAndView model) {
	public ModelAndView clientEdit(ModelAndView model) {	
		Client client = (Client)model.getModel().get("client");
		
		if (null == client) {
			client = new Client();
		}
		
		
		model.addObject("client", client);

		model.setViewName("ClientEditForm");
		
		return model;
	}
	
	@RequestMapping(value="/ClientEditSubmit", method = RequestMethod.POST)	
	public ModelAndView clientEditSubmit(@ModelAttribute Client client, BindingResult result, final RedirectAttributes redirectAttributes) {
				
		ModelAndView model = null;
				
		int rc = clientDAO.modifyClient(client);
		
		if (rc > 0) {
			
			model = new ModelAndView("ClientViewEditForm");
			model.addObject("client", client);
			redirectAttributes.addFlashAttribute("msg","Client modified successfully");
			model.setViewName("ClientViewForm");
						
		} else {
	
			model = new ModelAndView("ClientViewEditForm");
			model.addObject("client", client);
			result.rejectValue(null,"error.clientviewediterror", "Error modifying client");
		}
		
		return model;
	}
	
	@RequestMapping(value="/ClientLogForm", method = RequestMethod.GET)
	public ModelAndView clientLog(ModelAndView model) {
		List<LogEntry> logEntries = logEntryDAO.viewAllLogsByClient(101);
		model.addObject("logEntries", logEntries);
		model.setViewName("ClientLogForm");
		
		return model;
	}
}
