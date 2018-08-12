package com.trusting.csv.settings;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.trusting.csv.constants.PhoneConstants;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

@Component
public class PhoneCSVSettings {

	public CsvParserSettings setInputPhoneCSVSettings(CsvParserSettings csvParserSettings) {
		// The file used in this test uses "\n" as the line separator
		csvParserSettings.getFormat().setLineSeparator(PhoneConstants.SETTING_INPUT_LINES_SEPARATOR);
		csvParserSettings.setHeaderExtractionEnabled(PhoneConstants.SETTING_HEADER_EXTRACTION_ENABLED);

		return csvParserSettings;
	}

	public CsvWriter setOutputPhoneCSV(CsvWriterSettings settings) throws IOException {
		// Encloses all records within quotes even when they are not required.
		settings.setQuoteAllFields(PhoneConstants.SETTING_OUTPUT_QUOTE_ALL_FIELDS_TRUE);

		// Sets the file headers (used for selection, these values won't be written automatically)
		settings.setHeaders(PhoneConstants.HEADER_OUTPUT_PHONE_NUMBER, PhoneConstants.HEADER_OUTPUT_REAL_ACTIVATION_DATE);

		// Creates a writer with the above settings;
		File file = new File("output.csv");
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

		
		CsvWriter writer = new CsvWriter(bufferedWriter, settings);

		// Writes the headers specified in the settings
		writer.writeHeaders();

		return writer;
	}

}
