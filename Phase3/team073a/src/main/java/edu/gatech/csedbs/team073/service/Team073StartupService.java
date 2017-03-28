package edu.gatech.csedbs.team073.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class Team073StartupService {

	private static final Logger logger = LoggerFactory.getLogger(Team073StartupService.class);

	public String getDesc() {

		logger.debug("getDesc() is executed!");

		return "Welcome Example";

	}

	public String getTitle(String name) {

		logger.debug("getTitle() is executed! $name : {}", name);

		if(StringUtils.isEmpty(name)){
			return "Startup Successful !!";
		}else{
			return "Startup Successful !! Hello " + name;
		}
		
	}

}
