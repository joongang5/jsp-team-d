package bbs.util;

import java.util.Map;

public class ErrorUtil {
	
	public static void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if (value == null || value.isEmpty()) {
			errors.put(fieldName, Boolean.TRUE);
		}
	}
}
