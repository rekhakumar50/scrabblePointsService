package com.example.demo.util;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.example.demo.constant.Constants.STRING_REGEX_PATTERN;

public class Utility {
	private static final Logger LOG = LoggerFactory.getLogger(Utility.class);
	
	
	/**
	 * Check the given word is valid or not
	 * @param word
	 * @return
	 */
	public static boolean validateWord(final String word) {
		boolean isValid = false;
		if(StringUtils.isNotEmpty(word)) {
			isValid = Pattern.compile(STRING_REGEX_PATTERN)
							.matcher(word)
							.matches();
		}
		LOG.debug("Word validation: {}", isValid);
		return isValid;
	}

}
