package com.trusting.csv.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trusting.csv.entities.input.Phone;
import com.trusting.csv.service.PhoneService;

@RestController
@RequestMapping("/api")
public class PhoneResource {
	
	@Autowired
	private PhoneService phoneService;
	
	@RequestMapping(value = "/phone/parser", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<com.trusting.csv.entities.output.Phone>> getRealActivatePhones() throws IOException {
		return new ResponseEntity<>(phoneService.getRealActivatePhones(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/phone/view/input", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Phone>> viewInputPhones() {
		return new ResponseEntity<>(phoneService.viewInputPhones(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/phone/view/output", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Phone>> viewOutputPhones() {
		return new ResponseEntity<>(phoneService.viewOutputPhones(), HttpStatus.OK);
	}

}
