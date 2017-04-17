package edu.gatech.csedbs.team073.web;

import edu.gatech.csedbs.team073.model.SearchClient;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * 
 * @author jgeorge
 *
 */

public class ClientSearchValidator implements Validator {


    @Override
    public boolean supports(Class clazz) {

        return SearchClient.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    	SearchClient searchClient = (SearchClient) target;
    	
    	if (StringUtils.isBlank(searchClient.getSearchParms()) && 
    			StringUtils.isBlank(searchClient.getSearchDescription())) {
    		errors.rejectValue("searchParms", "error.searchclient.onefieldrequired", "Both search fields cannot be blank");
  //  		errors.rejectValue("searchDescription", "error.searchclient.onefieldrequired", "Both search fields cannot be blank");
    	}
  
    	if (StringUtils.isNotBlank(searchClient.getSearchParms()) && 
    			StringUtils.isNotBlank(searchClient.getSearchDescription())) {
    		errors.rejectValue("searchParms", "error.searchclient.toomanyfields", "Both search fields cannot be filled");
  //  		errors.rejectValue("searchDescription", "error.searchclient.onefieldrequired", "Both search fields cannot be blank");
    	}

    }

}
