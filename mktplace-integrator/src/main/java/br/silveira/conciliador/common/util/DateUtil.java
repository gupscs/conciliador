package br.silveira.conciliador.common.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StringUtils;

public class DateUtil {
	
	private static final Logger log = LogManager.getLogger(DateUtil.class);

	public static LocalDate convertDateToLocalDate(Date dt) {
		if(dt == null) return null;
		return dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
	}

	public static LocalTime StringToLocalTime(String time) {
		if(time == null || time.isEmpty()) return null;
		return LocalTime.parse(time);
	}

	public static LocalDateTime convertStringToLocalDateTime(String strDate) {
		try {
			if(StringUtils.hasText(strDate)) {
				return LocalDateTime.parse(strDate, DateTimeFormatter.ISO_DATE_TIME);
			}
			return null;
		}catch(Exception e) {
			log.error("Error to convert String: "+strDate+" to LocalDateTime", e);
		}
		return null;
	}

	public static LocalDateTime convertDateToLocalDateTime(Date dt) {
		if(dt == null) return null;
		return Instant.ofEpochMilli(dt.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public static boolean isExpiredToken(Date lastUpdate, Integer expireInSeconds) {
		Calendar cal  = Calendar.getInstance();
		cal.setTime(lastUpdate);
		cal.add(Calendar.SECOND, expireInSeconds);
		return cal.getTime().before(new Date());
	}

}
