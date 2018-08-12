package com.trusting.csv.utils;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.trusting.csv.settings.PhoneCSVSettings;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvRoutines;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

public abstract class BasePhoneUtils {
	
	@Autowired
	protected PhoneCSVSettings phoneCSVSettings;
	
	/**
	 * Init CSVRoutines
	 * @return
	 */
	public CsvRoutines initCSVRoutines() {
		CsvParserSettings parserSettings = new CsvParserSettings();
		phoneCSVSettings.setInputPhoneCSVSettings(parserSettings);

		// Here we create an instance of our routines object.
		return new CsvRoutines(parserSettings);
	}
	
	/**
	 * Init CSVWriter
	 * @return
	 * @throws IOException 
	 */
	public CsvWriter initCSVWriter() throws IOException {
		CsvWriterSettings settings = new CsvWriterSettings();
		return phoneCSVSettings.setOutputPhoneCSV(settings);
	}

}
