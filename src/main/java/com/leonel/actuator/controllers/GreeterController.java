package com.leonel.actuator.controllers;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonel.actuator.models.Greet;
import com.leonel.actuator.tools.TPSHealth;



@RestController
public class GreeterController {
	
	@Autowired
	CounterService counterService;
	
	@Autowired
	GaugeService gaugeService;
	
	private static final Logger logger = LoggerFactory.getLogger(GreeterController.class);
	
	TPSHealth health;
	
	public GreeterController(TPSHealth health) {
		this.health = health;
	}
	
	@RequestMapping("/")
	public Greet greet() {
		logger.info("Serving Request....!!!");
	    health.updateTx();
	    this.counterService.increment("greet.txnCount");
	    this.gaugeService.submit("greet.customgauge", 1.0);

		return new Greet("hello world");
		
	}

}
