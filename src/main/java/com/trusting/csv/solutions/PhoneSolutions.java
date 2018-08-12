package com.trusting.csv.solutions;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.trusting.csv.TrustingPhoneApplication;
import com.trusting.csv.constants.PhoneConstants;
import com.trusting.csv.entities.input.Phone;
import com.univocity.parsers.csv.CsvRoutines;
import com.univocity.parsers.csv.CsvWriter;

@Component
public class PhoneSolutions {
	
	// If phoneInFile has connection with phoneInMap, take the phone with activate Date lesser
	BiPredicate<Phone, Phone> phoneInMapEqualPhoneInFile = (phoneInMap,
			phoneInFile) -> Objects.nonNull(phoneInMap.getDeactiveDate())
					&& Objects.nonNull(phoneInFile.getDeactiveDate())
					&& (phoneInFile.getActivateDate().compareTo(phoneInFile.getDeactiveDate()) == 0
							|| phoneInFile.getDeactiveDate().compareTo(phoneInMap.getActivateDate()) == 0)
					&& phoneInFile.getActivateDate().compareTo(phoneInMap.getActivateDate()) < 0;
					
	// If phoneInFile has no connection with phoneInMap, take the phone with activate Date greater
	BiPredicate<Phone, Phone> phoneInMapNotEqualPhoneInFile = (phoneInMap,
			phoneInFile) -> Objects.nonNull(phoneInMap.getDeactiveDate())
					&& Objects.nonNull(phoneInFile.getDeactiveDate())
					&& phoneInFile.getActivateDate().compareTo(phoneInMap.getDeactiveDate()) != 0
					&& phoneInFile.getDeactiveDate().compareTo(phoneInMap.getActivateDate()) != 0
					&& phoneInFile.getActivateDate().compareTo(phoneInMap.getActivateDate()) > 0;
			
	/**
	 * Idea for this solutions 1:
	 * 1/ Parse CSV File to HashMap based the coding logics
	 * 2/ loop the map and write to new file.
	 * @param routines
	 * @param writer
	 * @return
	 */
	public List<com.trusting.csv.entities.output.Phone> writePhonesToList(CsvRoutines routines, CsvWriter writer) {
		// Routin the file and set to HashTable (search faster than HashMap)
		Hashtable<String, Phone> pastPlan = new Hashtable<>();
		
		for(Phone phoneInFile : routines.iterate(Phone.class, getInputFile())) {
			Phone phoneInMap = pastPlan.get(phoneInFile.getPhoneNumber());
			
			if (Objects.isNull(phoneInMap)) {
				pastPlan.put(phoneInFile.getPhoneNumber(), phoneInFile);
			} else {
				// Validate startDate & endDate, then update new record in hashTable
				if (Objects.isNull(phoneInFile.getDeactiveDate())) 
					phoneInFile.setDeactiveDate(new Date());
				
				// If phoneInFile has connection with phoneInMap, take the phone with activate Date lesser
				if (phoneInMapEqualPhoneInFile.test(phoneInMap, phoneInFile))
					pastPlan.replace(phoneInFile.getPhoneNumber(), phoneInFile);
					
				// If phoneInFile has no connection with phoneInMap, take the phone with activate Date greater
				if (phoneInMapNotEqualPhoneInFile.test(phoneInMap, phoneInFile))
					pastPlan.replace(phoneInFile.getPhoneNumber(), phoneInFile);
					
			}
			
		}
		
		List<com.trusting.csv.entities.output.Phone> phones = pastPlan.values().stream().map(
				phone -> new com.trusting.csv.entities.output.Phone(phone.getPhoneNumber(), phone.getActivateDate()))
				.collect(Collectors.toList());
		writer.writeRow(pastPlan);

		return phones;
	}


	public Reader getInputFile() {
		try {
			return new InputStreamReader(TrustingPhoneApplication.class.getResourceAsStream(PhoneConstants.FILE_INPUT_LOCATION),
					"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Reader getOutputFile() {
		try {
			return new InputStreamReader(TrustingPhoneApplication.class.getResourceAsStream(PhoneConstants.FILE_OUTPUT_LOCATION),
					"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
