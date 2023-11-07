package com.enpresa.productadmin.controlador;

import com.enpresa.productadmin.modelo.RegistroTransaccion;
import com.enpresa.productadmin.modelo.dao.BitacoraTransaccionesDAO;
import com.enpresa.productadmin.vistas.gui.BitacoraTransaccionesVista;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jmdub
 */
public class BitacoraTransaccionesController {

    private final BitacoraTransaccionesDAO modelo;
    private final BitacoraTransaccionesVista vista;

    public BitacoraTransaccionesController(BitacoraTransaccionesDAO modelo, BitacoraTransaccionesVista vista) {
        this.modelo = modelo;
        this.vista = vista;

        mapearAcciones();
        vista.mostrarVista("Bitácora de Transacciones");
    }

    private void mapearAcciones() {
        vista.getBtnBuscar().addActionListener((ActionEvent e) -> {
            filtrarRegistros();
        });
    }

    private int filtrarRegistros() {
        // Campos
        String fechaInicial;
        String fechaFinal;
        String horaInicial;
        String horaFinal;
        String objeto;
        String usuario;
        String accion;
        String modulo;

        HashMap<String, String> campos = new HashMap<>();
        try {
            // Fecha
            fechaInicial = vista.getFechaInicial();
            fechaFinal = vista.getFechaFinal();
            comprobarFechas(fechaInicial, fechaFinal);
            campos.put("fechaInicial", fechaInicial);
            campos.put("fechaFinal", fechaFinal);

            // Hora
            
            horaInicial = vista.getHoraInicial();
            horaFinal = vista.getHoraFinal();
            comprobarHoras(horaInicial, horaFinal);
            campos.put("horaInicial", horaInicial);
            campos.put("horaFinal", horaFinal);

            List<RegistroTransaccion> registros = modelo.buscar(campos);
            
            vista.mostrarRegistros(registros);
            return 1;
        } catch (BusquedaInvalidaException e) {
            return -1;
        }
    }

    private LocalDate comprobarFechaIndividual(Pattern pattern, String fecha) throws BusquedaInvalidaException {
        Matcher matcher = pattern.matcher(fecha);
        if (!matcher.matches()) {
            vista.mostrarError("La fecha introducida no es válida.");
            throw new BusquedaInvalidaException();
        }
        return LocalDate.parse(fecha);
    }

    private boolean comprobarFechas(String fechaInicial, String fechaFinal) throws BusquedaInvalidaException {
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

    private LocalTime comprobarHoraIndividual(Pattern pattern, String hora) throws BusquedaInvalidaException {
        Matcher matcher = pattern.matcher(hora);
        if (!matcher.matches()) {
            vista.mostrarError("La hora introducida no es válida.");
            throw new BusquedaInvalidaException();
        }
        return LocalTime.parse(hora);
    }

    private boolean comprobarHoras(String horaInicial, String horaFinal) throws BusquedaInvalidaException {
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
