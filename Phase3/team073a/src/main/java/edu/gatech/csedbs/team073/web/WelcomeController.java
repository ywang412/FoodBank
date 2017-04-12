package edu.gatech.csedbs.team073.web;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.gatech.csedbs.team073.service.Team073StartupService;

@Controller
public class WelcomeController {

	private final Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	private final Team073StartupService team073StartupService;
	

	@Autowired
	public WelcomeController(Team073StartupService team073StartupService) {
		this.team073StartupService = team073StartupService;
	}

//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String index(Map<String, Object> model) {
//
//		logger.debug("index() is executed!");
//
//		model.put("title", team073StartupService.getTitle(""));
//		model.put("msg", team073StartupService.getDesc());
//
//		return "index";
//	}

	@RequestMapping(value = "/echo/{name:.+}", method = RequestMethod.GET)
	public ModelAndView echo(@PathVariable("name") String name) {

		logger.debug("echo() is executed - $name {}", name);

		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		
		model.addObject("title", team073StartupService.getTitle(name));
		model.addObject("msg", team073StartupService.getDesc());
		
		return model;

	}


	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(Map<String, Object> model) {

		logger.debug("test is executed!");

		model.put("title", team073StartupService.getTitle(""));
		model.put("msg", team073StartupService.getDesc());

		return "test";
	}

}