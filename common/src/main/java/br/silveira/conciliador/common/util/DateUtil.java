package br.silveira.conciliador.common.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {

	public static LocalDate convertDateToLocalDate(Date dt) {
		if(dt == null) return null;
		return dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
	}

	public static LocalTime StringToLocalTime(String time) {
		if(time == null || time.isEmpty()) return null;
		return LocalTime.parse(time);
	}

}
