package com.enpresa.productadmin.controlador;

import com.enpresa.productadmin.modelo.dao.BitacoraAccesoDAO;
import com.enpresa.productadmin.modelo.dto.RegistroAccesoBusquedaDTO;
import com.enpresa.productadmin.modelo.dto.RegistroAccesoDTO;
import com.enpresa.productadmin.vistas.gui.BitacoraAccesoVista;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 *
 * @author jmdub
 */
public class BitacoraAccesoController extends ComprobadorTemporal {

    private final BitacoraAccesoDAO modelo;
    private final BitacoraAccesoVista vista;

    public BitacoraAccesoController(BitacoraAccesoDAO modelo, BitacoraAccesoVista vista) {
        this.modelo = modelo;
        this.vista = vista;

        vista.mostrarVista("Bitácora de Acceso");
        mapearAcciones();
    }

    private void mapearAcciones() {
        vista.getBtnBuscar().addActionListener((ActionEvent e) -> {
            filtrarRegistros();
        });
    }

    private int filtrarRegistros() {
        RegistroAccesoBusquedaDTO campos = vista.obtenerCampos();
        try {
            comprobarFechas(campos.getFechaInicial(), campos.getFechaFinal());
            comprobarHoras(campos.getHoraInicial(), campos.getHoraFinal());

            List<RegistroAccesoDTO> registros = modelo.buscar(campos);
            vista.mostrarRegistros(registros);
            return 1;
        } catch (BusquedaInvalidaException e) {
            return -1;
        }
    }
}
