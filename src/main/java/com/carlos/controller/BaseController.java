package com.carlos.controller;

import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crivero.arq.module.configuration.AppProperties;
import com.crivero.arq.module.configuration.domain.Application;

@Controller
public class BaseController {

	private static final String VIEW_INDEX = "index";
	final static Logger logger = LogManager.getLogger(BaseController.class.getName());

	@Autowired
	ApplicationContext context;

	@Autowired
	private Application application;

	@Autowired
	private AppProperties appProperties;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(ModelMap model) {

		application = (Application) context.getBean("application");

		String name = context.getMessage("customer.name", new Object[] { 28, "google.com" }, Locale.US);
		String name2 = context.getMessage("customer.name", new Object[] { 28, "google.com" }, new Locale("ES"));

		String msg = "" + application + "\n" + appProperties.getProperty("hello.db") + "\n" + name + "" + name2;

		model.addAttribute("message", msg);
		logger.debug("debug");

		return VIEW_INDEX;

	}
}