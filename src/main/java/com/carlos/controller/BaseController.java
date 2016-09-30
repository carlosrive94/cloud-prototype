package com.carlos.controller;

import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crivero.arq.module.configuration.AppProperties;
import com.crivero.arq.module.configuration.domain.Application;
import com.crivero.arq.module.literals.LiteralsProvider;

@Controller
public class BaseController {

	private static final String VIEW_INDEX = "index";
	final static Logger logger = LogManager.getLogger(BaseController.class.getName());

	@Autowired
	private Application application;

	@Autowired
	private AppProperties appProperties;
	
	@Autowired
	private LiteralsProvider literalsProvider;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(ModelMap model) {

		String literal1 = literalsProvider.getLiteral("customer.name", new Object[] { 28, "google.com" }, Locale.US);		
		String literal2 = literalsProvider.getLiteral("customer.namer", new Object[] { 28, "google.com" }, new Locale("ES"));
		String property1 = appProperties.getProperty("hello.db");
		String property2 = appProperties.getProperty("error.lalala");

		String msg = "" + application + "\n" +  property1 + property2 + "\n" + literal1 + " " + literal2;

		model.addAttribute("message", msg);
		logger.debug("debug");

		return VIEW_INDEX;

	}
}