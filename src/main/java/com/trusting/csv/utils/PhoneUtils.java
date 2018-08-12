package com.trusting.csv.utils;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trusting.csv.entities.input.Phone;
import com.trusting.csv.solutions.PhoneSolutions;
import com.univocity.parsers.csv.CsvRoutines;
import com.univocity.parsers.csv.CsvWriter;

@Component
public class PhoneUtils extends BasePhoneUtils {
	
	@Autowired
	private PhoneSolutions phoneSolutions;
	
	public List<com.trusting.csv.entities.output.Phone> getRealActivateDatePhones() throws IOException {
		CsvWriter writer = initCSVWriter();

		// Implementing n solutions here for comparing
		return phoneSolutions.writePhonesToList(initCSVRoutines(), writer);
	}
	
	public List<Phone> viewInputPhones() {
		CsvRoutines routines = initCSVRoutines();
		return routines.parseAll(Phone.class, phoneSolutions.getInputFile());
	}
	
	public List<Phone> viewOutputPhones() {
		CsvRoutines routines = initCSVRoutines();
		return routines.parseAll(Phone.class, phoneSolutions.getOutputFile());
	}
	
	

}
