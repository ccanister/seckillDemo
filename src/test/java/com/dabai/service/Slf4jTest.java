package com.dabai.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jTest {
	//private Logger logger = LoggerFactory.getLogger(Slf4jTest.class);
	public static void main(String[] args) {
		Logger log = LoggerFactory.getLogger(Slf4jTest.class);  
	    log.info("Hello World");  
	}
}
