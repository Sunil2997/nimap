package com.bountyregister.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component

public class GlobalFunctions {

	public final static String CUSTUM_ATTRIBUTE_USER_ID = "X-user-id";
	public final static String CUSTUM_ATTRIBUTE_USER_ROLES = "X-user-roles";
	public final static String CUSTUM_ATTRIBUTE_USER_PERMISSIONS = "X-user-permission";

	public static String getFileUrl(String url) {
		if (url == null) {
			return null;
		}

		String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/").path(url)
				.toUriString();

		String urls = fileUrl.replace("http", "https");
		return urls;
	}

	public static String date(Date date) {
		return date != null ? (String) date.toString().subSequence(0, 10) : null;
	}

	public static String dateTimestamp(Date date) {
		return date != null ? (String) date.toString() : null;
	}

	public static Timestamp dateConvertIntoTimestamp(Date date, int hours, int mins, int seconds) {
		// Convert Date to LocalDateTime
		LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		// Set the time component to 00:00:00
		LocalDateTime startOfDay = localDateTime.withHour(hours).withMinute(mins).withSecond(seconds);
		// Create a Timestamp from LocalDateTime
		return Timestamp.valueOf(startOfDay);
	}
}
