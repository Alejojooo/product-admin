package com.enpresa.productadmin.controlador;

import com.enpresa.productadmin.modelo.TipoOperacion;
import com.enpresa.productadmin.modelo.dao.OperacionDAO;
import com.enpresa.productadmin.modelo.dto.OperacionReporteDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Alejo
 */
public class ReporteGastosGanancias implements Controller {

    private final OperacionDAO modelo;

    public ReporteGastosGanancias(OperacionDAO modelo) {
        this.modelo = modelo;
    }

    @Override
    public void start() {
        String imagePath = "resources/logoEmpresa.jpg";
        String jasperFilePath = "resources/ProductAdmin_ReporteGastosGanancias.jasper";
        String pdfPath = "reportes/GastosGanancias " + LocalDate.now().toString() + ".pdf";

        List<OperacionReporteDTO> registrosCompras = modelo.consultar(TipoOperacion.Compra);
        List<OperacionReporteDTO> registrosVentas = modelo.consultar(TipoOperacion.Venta);
        try {
            InputStream logoEmpresa;
            try (InputStream jasperStream = new FileInputStream(jasperFilePath)) {
                File imageFile = new File(imagePath);
                logoEmpresa = (InputStream) ImageIO.createImageInputStream(imageFile);

                JRBeanArrayDataSource dsGastos = new JRBeanArrayDataSource(registrosCompras.toArray());
                JRBeanArrayDataSource dsGanancias = new JRBeanArrayDataSource(registrosVentas.toArray());

                BigDecimal subtotalGastos = calcularSubtotal(registrosCompras);
                BigDecimal subtotalGanancias = calcularSubtotal(registrosVentas);
                BigDecimal total = calcularTotal(subtotalGastos, subtotalGanancias);

                Map<String, Object> parameters = new HashMap();
                parameters.put("logoEmpresa", logoEmpresa);
                parameters.put("dsGastos", dsGastos);
                parameters.put("dsGanancias", dsGanancias);
                parameters.put("subtotalGastos", subtotalGastos);
                parameters.put("subtotalGanancias", subtotalGanancias);
                parameters.put("total", total);

                JasperReport reporte = (JasperReport) JRLoader.loadObject(jasperStream);
                JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parameters);
                JasperExportManager.exportReportToPdfFile(jasperPrint, pdfPath);
            }
            logoEmpresa.close();
            JOptionPane.showMessageDialog(null, "Se ha creado un nuevo reporte en la carpeta reportes", "Reportes", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException | JRException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al generar el reporte", "Reportes", JOptionPane.ERROR_MESSAGE);
        }
    }

    private BigDecimal calcularSubtotal(List<OperacionReporteDTO> registros) {
        BigDecimal subtotal = BigDecimal.ZERO;
        for (OperacionReporteDTO registro : registros) {
            subtotal.add(registro.getTotal());
        }
        return subtotal;
    }

    private BigDecimal calcularTotal(BigDecimal gastos, BigDecimal ganancias) {
        return ganancias.subtract(gastos);
    }
}
