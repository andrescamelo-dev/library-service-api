package com.ceiba.biblioteca.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Utilidades {
 
    public static final int USUARIO_AFILIADO = 1;
    public static final int USUARIO_EMPLEADO = 2;
    public static final int USUARIO_INVITADO = 3;
    
    
    public static LocalDate agregaDiasFecha(LocalDate date, int workdays) {
        if (workdays < 1) {
            return date;
        }
    
        LocalDate result = date;
        int addedDays = 0;
        while (addedDays < workdays) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY ||
                  result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++addedDays;
            }
        }
    
        return result;
    }

    public static Date convertToLocalDateToDate(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
          .atZone(ZoneId.systemDefault())
          .toInstant());
    }
    
    public static String formatDate(Date fecha,String formato) throws ParseException {
		SimpleDateFormat dateFormatWithOutHours = new SimpleDateFormat(formato);
		return  dateFormatWithOutHours.format(fecha);
	}


    public static Date calculaFechaPrestamo(Integer tipoUsuario) throws Exception {
        LocalDate calcalaFecha = null;
        Date fechaActual = new Date();
        LocalDate startDate = fechaActual.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        switch (tipoUsuario) {
        case USUARIO_AFILIADO:
            calcalaFecha = agregaDiasFecha(startDate, 10);
            break;
        case USUARIO_EMPLEADO:
            calcalaFecha = agregaDiasFecha(startDate, 8);
            break;
        case USUARIO_INVITADO:
            calcalaFecha = agregaDiasFecha(startDate, 7);
            break;
        default:
            calcalaFecha = agregaDiasFecha(startDate, 0);
            break;
        }

        return convertToLocalDateToDate(calcalaFecha);
    }


    public static Boolean existeTipoUsuario(int tipoUsuario) throws Exception {
         if (USUARIO_AFILIADO == tipoUsuario || USUARIO_EMPLEADO == tipoUsuario || USUARIO_INVITADO == tipoUsuario) {
             return true;
         }else{
            return false;
         }
    }

}
