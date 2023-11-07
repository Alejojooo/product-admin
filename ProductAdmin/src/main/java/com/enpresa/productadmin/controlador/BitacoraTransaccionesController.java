package com.enpresa.productadmin.controlador;

import com.enpresa.productadmin.modelo.dao.BitacoraTransaccionesDAO;
import com.enpresa.productadmin.modelo.dto.RegistroTransaccionBusquedaDTO;
import com.enpresa.productadmin.modelo.dto.RegistroTransaccionDTO;
import com.enpresa.productadmin.vistas.gui.BitacoraTransaccionesVista;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 *
 * @author jmdub
 */
public class BitacoraTransaccionesController extends ComprobadorTemporal {

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
        RegistroTransaccionBusquedaDTO campos = vista.obtenerCampos();
        try {
            comprobarFechas(campos.getFechaInicial(), campos.getFechaFinal());
            comprobarHoras(campos.getHoraInicial(), campos.getHoraFinal());

            List<RegistroTransaccionDTO> registros = modelo.buscar(campos);
            vista.mostrarRegistros(registros);
            return 1;
        } catch (BusquedaInvalidaException e) {
            return -1;
        }
    }
}
