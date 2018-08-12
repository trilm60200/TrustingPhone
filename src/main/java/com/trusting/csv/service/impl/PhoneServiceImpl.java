package com.trusting.csv.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trusting.csv.entities.input.Phone;
import com.trusting.csv.service.PhoneService;
import com.trusting.csv.utils.PhoneUtils;

@Service("phoneService")
public class PhoneServiceImpl implements PhoneService {
	
	@Autowired
	private PhoneUtils phoneUtils;

	@Override
	public List<com.trusting.csv.entities.output.Phone> getRealActivatePhones() throws IOException {
		return phoneUtils.getRealActivateDatePhones();
	}
	
	@Override
	public List<Phone> viewInputPhones() {
		return phoneUtils.viewInputPhones();
	}

	@Override
	public List<Phone> viewOutputPhones() {
		return phoneUtils.viewOutputPhones();
	}
	
	

}
