package com.example.shoponline.common;

public class Validate {

	public static boolean checkStringNotEmptyOrNull(String value) {
		if (value == null) {
			return false;
		}
		return !value.isEmpty();
	}
}
