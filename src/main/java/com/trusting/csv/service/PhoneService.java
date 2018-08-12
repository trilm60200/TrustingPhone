package com.trusting.csv.service;

import java.io.IOException;
import java.util.List;

import com.trusting.csv.entities.input.Phone;

public interface PhoneService {
	/**
	 * Get Real activate date 
	 * @return boolean
	 */
	public List<com.trusting.csv.entities.output.Phone> getRealActivatePhones() throws IOException ;
	
	public List<Phone> viewInputPhones();
	public List<Phone> viewOutputPhones();
}
