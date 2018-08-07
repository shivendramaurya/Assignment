package com.shiv.test.controller;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shiv.test.service.ICommanWordService;


@RestController
public class CommanWordsController {

	private static final Logger log = LoggerFactory.getLogger(CommanWordsController.class);
	
	@Autowired
	ICommanWordService commanWordSerivce;

	@RequestMapping("/commanWords")
    public Set<String> greeting(@RequestParam(value="filePath",required=true ) Set<String> filePaths) {
    	
		log.debug("Recieved file paths :"+filePaths);
    	
    	Set<String> commanWords = commanWordSerivce.getCommanWords(filePaths);
    	
    	log.debug("Comman Words in files "+filePaths +" are :" + commanWords);
    	
    	
    	return commanWords;
    }
}
