package br.com.pm.clinicasaracura;

import java.util.Date;
import java.util.List;

import br.com.pm.clinicasaracura.entity.AgendaMedica;

public class Utils {
	// The standard methods DON'T WORK
		@SuppressWarnings("deprecation")
		public static boolean datesAreEqual(Date a, Date b) {
			if (  a.getDate()     == b.getDate() 
		       && a.getMonth()   == b.getMonth()
		       && a.getYear()    == b.getYear()
		       && a.getSeconds() == b.getSeconds()
		       && a.getMinutes() == b.getMinutes()
		       && a.getHours()   == b.getHours()) {
				return true;
			}
			
			return false;
		}
		
		public static int getDayId(String date) {
			String day = date.substring(0, 3);
			int dayid = 0;
			
			switch (day) {
				case "Sun" :
					dayid = 1;
					break;
				case "Mon" :
					dayid = 2;
					break;
				case "Tue" :
					dayid = 3;
					break;
				case "Wed" :
					dayid = 4;
					break;
				case "Thu" :
					dayid = 5;
					break;
				case "Fri" :
					dayid = 6;
					break;
				case "Sat" :
					dayid = 7;
					break;
			}
			
			return dayid;
		}

		public static boolean thisHorarioIsFree (List<AgendaMedica> agenda, Date horario) {
			for (AgendaMedica a : agenda) {
				if(datesAreEqual(horario, a.getDiaAgendamento()))
					return false;
			}
			
			return true;
		}
}
