/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.controller;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.registrohorasociales.entity.Datoscertificacion;
import org.registrohorasociales.entity.Institucion;
import org.registrohorasociales.entity.Proyecto;
import org.registrohorasociales.entity.RelacionEstudianteProyecto;

/**
 *
 * @author Miguel
 */
@ManagedBean
@ViewScoped
public class Certificaion implements Serializable {

    carreraController carController = new carreraController();
    certificacionContrller cerController = new certificacionContrller();
    RelacionEstudianteProyectoController repController = new RelacionEstudianteProyectoController();
    ProyectoController proyController = new ProyectoController();
    InstitucionController instiController = new InstitucionController();
    String MES[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

    public void generarCertifiacion(String formDue, String formNombres, String formApellidos, String formCarrera) throws FileNotFoundException, BadElementException, IOException {
        try {
            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD);
            Month mes = LocalDate.now().getMonth();
            Calendar c = Calendar.getInstance();
            Datoscertificacion jefe = cerController.findDatosById(1);
            Datoscertificacion vice = cerController.findDatosById(2);
            RelacionEstudianteProyecto rep = repController.findRelacionByDue(formDue);
            String[] fecha = rep.getFechaInicio().split("/");
            String mes1 = MES[Integer.parseInt(fecha[1]) - 1];
            String[] fechaf = rep.getFechaFinal().split("/");
            String mes2 = MES[Integer.parseInt(fechaf[1]) - 1];
            Proyecto proy = proyController.findProyectoById(rep.getIdProyecto());
            Institucion ins = instiController.obtenerInstitucionById(proy.getIdInstitucion());
            String car = carController.findCarreraById(Integer.parseInt(formCarrera));
            String usuario = System.getProperty("user.name");

            
            Document document = new Document();
            FileOutputStream file = new FileOutputStream("C:\\Users\\" + usuario + "\\Downloads\\certificacion.pdf");
            PdfWriter.getInstance(document, file);
            document.open();

            Image imagen = Image.getInstance("encabezado.jpeg");
            imagen.setAlignment(Element.ALIGN_CENTER);
            imagen.scaleAbsolute(500, 100);
            document.add(imagen);
            BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.WINANSI, false);
            PdfPTable table1 = new PdfPTable(2);
            Paragraph pname = new Paragraph("NOMBRE", font);
            Paragraph pdue = new Paragraph("DUE", font);
            Paragraph pcarrera = new Paragraph("CARRERA", font);
            table1.addCell(pname);
            table1.addCell(formNombres + ", " + formApellidos);
            table1.addCell(pdue);
            table1.addCell(formDue);
            table1.addCell(pcarrera);
            table1.addCell(car);
            float[] medidaCeldas = {25f, 75f};
            table1.setWidths(medidaCeldas);
            table1.setWidthPercentage(100);
            PdfPTable table2 = new PdfPTable(1);
            table2.addCell(proy.getNomProyecto());
            table2.setWidthPercentage(100);
            PdfPTable table3 = new PdfPTable(2);
            table3.getDefaultCell().setBorder(0);
            Paragraph pcell = new Paragraph(vice.getTitulo().toUpperCase() + " " + vice.getNombre().toUpperCase()+"\n"+vice.getCargo().toUpperCase(),font2);
            Paragraph pcell2 = new Paragraph(jefe.getTitulo().toUpperCase() + " " + jefe.getNombre().toUpperCase()+"\n"+jefe.getCargo().toUpperCase(),font2);
            pcell.setAlignment(Element.ALIGN_CENTER);
            pcell2.setAlignment(Element.ALIGN_CENTER);
            PdfPCell cell = new PdfPCell();
            PdfPCell cell2 = new PdfPCell();
            PdfPCell cell3 = new PdfPCell();
            PdfPCell cell4 = new PdfPCell();
            cell.setBorder(0);
            cell2.setBorder(0);
            cell3.setBorder(0);
            cell4.setBorder(0);
            cell.addElement(pcell);
            cell2.addElement(pcell2);
            table3.addCell(cell);
            table3.addCell(cell2);
            table3.addCell(cell3);
            table3.addCell(cell4);
            table3.setWidthPercentage(100);
            PdfPTable table4 = new PdfPTable(2);
            table4.setWidths(medidaCeldas);
            table4.setWidthPercentage(100);
            table4.addCell("LUGAR");
            table4.addCell(ins.getNomInstitucion());
            table4.addCell("FECHA DE INICIO");
            table4.addCell(fecha[0] + " de " + mes1 + " de " + fecha[2]);
            table4.addCell("FECHA DE FIN");
            table4.addCell(fechaf[0] + " de " + mes2 + " de " + fechaf[2]);
            Paragraph p = new Paragraph("CERTIFICACIÓN DE SERVICIO SOCIAL\n\n", font);
            p.setAlignment(Element.ALIGN_CENTER);
            Paragraph p1 = new Paragraph("Para efectos legales y administrativos, los suscritos Vicedecano y Jefe de Proyección Social\n");
            Paragraph pc = new Paragraph("CERTIFICA QUE:\n\n", font);
            p1.setAlignment(Element.ALIGN_JUSTIFIED);
            Paragraph p2 = new Paragraph("Ha cumplido (500) horas sociales, de conformidad a lo establecido en los artículos sesenta y uno de "
                    + "la Constitución de la República, diecinueve de la Ley de Educación Superior, cuarenta y dos Ley "
                    + "Orgánica de la Universidad de El Salvador, sesenta del Reglamento General de la Ley Orgánica de "
                    + "la Universidad de El Salvador, artículo doscientos dieciocho, romano I parte segunda, literal \"e\" del "
                    + "Reglamento de la Gestión Académico-Administrativa de la Universidad de El Salvador, treinta y uno "
                    + "y siguientes del Reglamento General de Proyección Social de la Universidad de El Salvador, Manual "
                    + "de Procedimientos para la Ejecución del Servicio Social, como requisito previo para optar a su "
                    + "respectivo grado Académico, desarrollando el Proyecto:\n\n");
            p2.setAlignment(Element.ALIGN_JUSTIFIED);
            Paragraph frase = new Paragraph("CERTIFICACIÓN", font);
            Paragraph p3 = new Paragraph("\nSe extiende y firma la presente " + frase.getContent() + " para los consiguientes trámites de"
                    + " graduación, en Ciudad Universitaria, a los " + c.get(Calendar.DATE) + " días de " + mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"))
                    + " del " + c.get(Calendar.YEAR) + ". –\n\n");
            p3.setAlignment(Element.ALIGN_JUSTIFIED);
            Paragraph p4 = new Paragraph("\"HACIA LA LIBERTAD POR LA CULTURA\"\n\n\n\n\n\n", font);
            p4.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            document.add(p1);
            document.add(pc);
            document.add(table1);
            document.add(p2);
            document.add(table2);
            document.add(new Paragraph(" "));
            document.add(table4);
            document.add(p3);
            document.add(p4);
            document.add(table3);
            // AQUÍ COMPLETAREMOS NUESTRO CÓDIGO PARA GENERAR EL PDF
            document.close();
            System.out.println("Your PDF file has been generated!(¡Se ha generado tu hoja PDF!");
        } catch (DocumentException documentException) {
            System.out.println("The file not exists (Se ha producido un error al generar un documento): " + documentException);

        }
    }
}
