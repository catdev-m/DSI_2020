/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Miguel
 */
@ManagedBean
@ViewScoped
public class Certificaion implements Serializable {

    public void generarCertifiacion(String formDue, String formNombres, String formApellidos, String formCarrera) throws FileNotFoundException {
        try {
            String usuario = System.getProperty("user.name");
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\"+usuario+"\\Downloads\\certificacion.pdf"));
            document.open();
            Paragraph parrafo = new Paragraph("Suscritos jefes de la Unidad de proyección Social y Vicedecano de la Facultad de Jurisprudencia y Ciencias Sociales de la Universidad de El Salvador"
                    + " CERTIFICAN: Que el (la) Bachiller: "+formApellidos+", "+formNombres+" con carnet estudiantil "+formDue+", estudiante de la "
                            + formCarrera+", ha concluido satisfactoriamente QUINIENTAS (500) HORAS "
                                    + "de Servicio Social, de conformidad a lo establecido en los artículos"
                                    + " sesenta y uno de la Constitución de la República, diecinueve de la Ley"
                                    + " de Educación Superior, cuarenta y dos Ley Orgánica de la Universidad de "
                                    + "El Salvador, sesenta del Reglamento General de la Ley Orgánica de la"
                                    + " Universidad de El Salvador, articulo doscientos dieciocho, romano I"
                                    + " parte segunda, literal “e” del Reglamento de la Gestión Académico "
                                    + "-Administrativa de la Universidad de El Salvador, Manual de "
                                    + "Procedimientos para la Ejecución del Servicio Social, como requisito "
                                    + "previo para optar a su respectivo grado Académico, desarrollando la "
                                    + "actividad: ” “, habiendo iniciado el día xxxx y finalizado el xxx .- "
                                    + "POR TANTO: se extiende y firma la presenta CERTIFICACIÓN para los consiguientes"
                                    + " trámites de graduación, en Ciudad Universitaria, a los fecha.-");
            parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(parrafo);
            // AQUÍ COMPLETAREMOS NUESTRO CÓDIGO PARA GENERAR EL PDF
            document.close();
            System.out.println("Your PDF file has been generated!(¡Se ha generado tu hoja PDF!");
        } catch (DocumentException documentException) {
            System.out.println("The file not exists (Se ha producido un error al generar un documento): " + documentException);
        }

    }
}
