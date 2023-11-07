package com.enpresa.productadmin.controlador;

import com.enpresa.productadmin.vistas.VistaGraficaConRegistros;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Alejo
 */
public class ComprobadorTemporal {

    private VistaGraficaConRegistros vista;

    public LocalDate comprobarFechaIndividual(Pattern pattern, String fecha) throws BusquedaInvalidaException {
        Matcher matcher = pattern.matcher(fecha);
        if (!matcher.matches()) {
            vista.mostrarError("La fecha introducida no es válida.");
            throw new BusquedaInvalidaException();
        }
        return LocalDate.parse(fecha);
    }

    public boolean comprobarFechas(String fechaInicial, String fechaFinal) throws BusquedaInvalidaException {
        String regex = "^(19\\d{2}|20[0-9]{2})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
        Pattern pattern = Pattern.compile(regex);
        LocalDate fechaInicialDate = comprobarFechaIndividual(pattern, fechaInicial);
        LocalDate fechaFinalDate = comprobarFechaIndividual(pattern, fechaFinal);
        if (fechaInicialDate.isAfter(fechaFinalDate)) {
            vista.mostrarError("La fecha inicial debe ser menor que la fecha final");
            throw new BusquedaInvalidaException();
        }
        return true;
    }

    public LocalTime comprobarHoraIndividual(Pattern pattern, String hora) throws BusquedaInvalidaException {
        Matcher matcher = pattern.matcher(hora);
        if (!matcher.matches()) {
            vista.mostrarError("La hora introducida no es válida.");
            throw new BusquedaInvalidaException();
        }
        return LocalTime.parse(hora);
    }

    public boolean comprobarHoras(String horaInicial, String horaFinal) throws BusquedaInvalidaException {
        String regex = "^([01]\\d|2[0-3]):([0-5]\\d)$";
        Pattern pattern = Pattern.compile(regex);
        LocalTime horaInicialTime = comprobarHoraIndividual(pattern, horaInicial);
        LocalTime horaFinalTime = comprobarHoraIndividual(pattern, horaFinal);
        if (horaInicialTime.isAfter(horaFinalTime)) {
            vista.mostrarError("La hora inicial debe ser menor que la hora final");
            throw new BusquedaInvalidaException();
        }
        return true;
    }
}

class BusquedaInvalidaException extends Exception {

}
