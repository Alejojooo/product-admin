/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.enpresa.productadmin.utils;

import java.awt.Image;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JTable;
import javax.swing.text.Document;

/**
 *
 * @author jmdub
 */
public class ExportarAPDF {

    public static void exportarTablaAReportePDF(JTable tabla, String nombreArchivoPDF) {
        
    String fecha = "":
    

    public static void main(String[] args) {
        Plantilla miPlantilla = new Plantilla(
                "10:11_11_04_2021",
                "C:\\Users\\Navi\\Pictures\\logooooo.png");
        miPlantilla.crearPlantilla();
    }

    public String getFecha() {
        GregorianCalendar cal = new GregorianCalendar();
        int hora = cal.get(Calendar.HOUR_OF_DAY);
        int minuto = cal.get(Calendar.MINUTE);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONTH);
        int anio = cal.get(Calendar.YEAR);
        return hora + minuto + "_" + mes + "_" + dia + "_" + anio;
    }
    
    public String generarNombreArchivo(){
        String nombreArchivo = "";
        String fecha = fecha.getFecha();
        
        
            nombreArchivo = cadenas[0].toUpperCase() + "_" + cedula.getText().toUpperCase();
        return nombreArchivo;
    }
    
    public void abrir(String nombreDirector) {
        try {
            File path = new File(nombreDirector + ".pdf");
            Desktop.getDesktop().open(path);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Atención", 2);
        }
    }

    private static class Plantilla {
        String fecha;
        String rutaImagen;

        Document documento;
        FileOutputStream archivo;

        public Plantilla(
                String fecha,
                String rutaImagen) {
            this.fecha = fecha;
            this.rutaImagen = rutaImagen;
            documento = new Document();
        }

        public void crearPlantilla(String nombreArchivo) {
            try {

                archivo = new FileOutputStream(nombreArchivo + ".pdf");
                PdfWriter.getInstance(documento, archivo);
                documento.open();
                titulo.setAlignment(1);

                Image image = null;
                try {
                    image = Image.getInstance(rutaImagen);//carga imagen
                    image.scaleAbsolute(150, 100);//cambia tamaño
                    image.setAbsolutePosition(415, 750);//coloca imagen en la posicion

                } catch (Exception e) {
                }

                documento.add(image);

                documento.add(titulo);

                documento.add(new Paragraph("Reporte Generado Gastos Ganancias"));

                documento.add(Chunk.NEWLINE);

                Paragraph texto = new Paragraph("Reporte generado " + fecha + "");
                texto.setAlignment(Element.ALIGN_JUSTIFIED);
                documento.add(texto);

                documento.add(Chunk.NEWLINE);

                PdfPTable tabla = new PdfPTable(4);
                tabla.setWidthPercentage(100);
                PdfPCell name = new PdfPCell(new Phrase("Nombre"));
                name.setBackgroundColor(BaseColor.ORANGE);
                PdfPCell age = new PdfPCell(new Phrase("Cantidad"));
                age.setBackgroundColor(BaseColor.ORANGE);
                PdfPCell tel = new PdfPCell(new Phrase("Precio"));
                tel.setBackgroundColor(BaseColor.ORANGE);
                PdfPCell address = new PdfPCell(new Phrase("Gasto/Ganancia"));
                address.setBackgroundColor(BaseColor.ORANGE);

                
                documento.add(tabla);
                documento.add(Chunk.NEWLINE);
                documento.add(new Paragraph("Fecha: " + fecha));

                documento.close();
                JOptionPane.showMessageDialog(null, "El archivo PDF se a creado correctamente!");
            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
            } catch (DocumentException e) {
                System.err.println(e.getMessage());
            }

        }
    }
}
