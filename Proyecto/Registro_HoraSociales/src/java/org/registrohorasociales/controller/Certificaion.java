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
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.registrohorasociales.entity.Datoscertificacion;

/**
 *
 * @author Miguel
 */
@ManagedBean
@ViewScoped
public class Certificaion implements Serializable {
    carreraController carController = new carreraController();
    certificacionContrller cerController = new certificacionContrller(); 
    public void generarCertifiacion(String formDue, String formNombres, String formApellidos, String formCarrera) throws FileNotFoundException, BadElementException, IOException {
       
        try {
            Month mes = LocalDate.now().getMonth();
            Calendar c = Calendar.getInstance();
            Datoscertificacion jefe = cerController.findDatosById(1);
            Datoscertificacion vice = cerController.findDatosById(2);
            int idcar = Integer.parseInt(formCarrera);
            String car = carController.findCarreraById(idcar);
            String usuario = System.getProperty("user.name");
            Document document = new Document();
            FileOutputStream file = new FileOutputStream("C:\\Users\\" + usuario + "\\Downloads\\certificacion.pdf");
            PdfWriter.getInstance(document, file );
            document.open();
            Image imagen = Image.getInstance("C:\\Users\\miguel\\Documents\\prueba\\DSI_2020\\Proyecto\\Registro_HoraSociales\\src\\java\\org\\registrohorasociales\\utils\\encabezado.png");
            imagen.setAlignment(Element.ALIGN_CENTER);
            imagen.scaleAbsolute(500,160);
            document.add(imagen);
            BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.WINANSI, false);
            Font font1 = new Font(bf,14);
            font1.setFamily("ITALIC");
            Paragraph parrafo = new Paragraph("Suscritos jefes de la Unidad de proyección Social y Vicedecano de la"
                    + " Facultad de Jurisprudencia y Ciencias Sociales de la Universidad de El Salvador"
                    + " CERTIFICAN: Que el (la) Bachiller: " + formApellidos.toUpperCase() + ", " + formNombres.toUpperCase()
                    + " con carnet estudiantil " + formDue + ", estudiante de la "
                    + car.toUpperCase() + ", ha concluido satisfactoriamente QUINIENTAS (500) HORAS "
                    + "de Servicio Social, de conformidad a lo establecido en los artículos"
                    + " sesenta y uno de la Constitución de la República, diecinueve de la Ley"
                    + " de Educación Superior, cuarenta y dos Ley Orgánica de la Universidad de "
                    + "El Salvador, sesenta del Reglamento General de la Ley Orgánica de la"
                    + " Universidad de El Salvador, articulo doscientos dieciocho, romano I"
                    + " parte segunda, literal “e” del Reglamento de la Gestión Académico "
                    + "-Administrativa de la Universidad de El Salvador, Manual de "
                    + "Procedimientos para la Ejecución del Servicio Social, como requisito "
                    + "previo para optar a su respectivo grado Académico, desarrollando la "
                    + "actividad: \"\", habiendo iniciado el día xxxx y finalizado el xxx .- "
                    + "POR TANTO: se extiende y firma la presenta CERTIFICACIÓN para los consiguientes"
                    + " trámites de graduación, en Ciudad Universitaria, a los "+c.get(Calendar.DATE)+" días del mes de "+mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES")) 
                    +" del "+c.get(Calendar.YEAR)+".-\n\n\n",font1);
            parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
            Paragraph p2 = new Paragraph("\"Hacia la Libertad por la Cultura\"\n\n\n\n\n\n");
            p2.setAlignment(Element.ALIGN_CENTER);
            Paragraph p3 = new Paragraph(jefe.getTitulo()+" "+jefe.getNombre()+ "             "+vice.getTitulo()+" "+vice.getNombre());
            p3.setAlignment(Element.ALIGN_LEFT);
            Paragraph p4 = new Paragraph(jefe.getCargo()+"                                            "+vice.getCargo());
            p4.setAlignment(Element.ALIGN_LEFT);
            document.add(parrafo);
            document.add(p2);
            document.add(p3);
            document.add(p4);
            // AQUÍ COMPLETAREMOS NUESTRO CÓDIGO PARA GENERAR EL PDF
            document.close();
            System.out.println("Your PDF file has been generated!(¡Se ha generado tu hoja PDF!");
        } catch (DocumentException documentException) {            System.out.println("The file not exists (Se ha producido un error al generar un documento): " + documentException);

        }
    }
}
