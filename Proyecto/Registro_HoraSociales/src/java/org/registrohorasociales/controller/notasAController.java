/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.primefaces.component.focus.FocusBase.PropertyKeys.context;
import org.registrohorasociales.config.ApplicationContextProvider;
import org.registrohorasociales.dto.EstudianteInfoDto;
import org.registrohorasociales.repository.EstudianteRepository;

/**
 *
 * @author denisse_mejia
 */
@ViewScoped
@ManagedBean
public class notasAController implements Serializable{
   private EstudianteRepository estudiantesRepository;
   private List<EstudianteInfoDto> estudianteList;
   private EstudianteInfoDto estudianteSelect;
   
@PostConstruct
    public void init() {
        estudiantesRepository = ApplicationContextProvider.getApplicationContext().getBean(EstudianteRepository.class);
        loadEstudiantes();
    }

    public void loadEstudiantes() {
        estudianteList = new ArrayList<>();

        estudiantesRepository.estudiantesInfoList().forEach(o -> {
            EstudianteInfoDto i = new EstudianteInfoDto();
            i.setDue(o[0].toString());
            i.setNombres(o[1].toString());
            i.setApellidos(o[2].toString());
            i.setCorreo(o[5].toString());
            i.setNombrecarrera(o[9].toString());
            i.setNom_institucion(o[11].toString());
            estudianteList.add(i);
        });

    }
    
    public void crearPdf(String n) throws FileNotFoundException, DocumentException{
        Document documento = new Document();
        String usrSystem = System.getProperty("user.name");
        SimpleDateFormat a = new SimpleDateFormat("ddMMYYYY");
        String fecha = a.format(new Date());
        
        String ruta      = "C:\\Users\\" + usrSystem + "\\Downloads\\"+n+"_"+fecha+".pdf";
        FileOutputStream ficheroPdf = new FileOutputStream(ruta);
        PdfWriter.getInstance(documento, ficheroPdf);
        
        documento.open();
        Paragraph titulo = new Paragraph("Apreciable licenciada Melara:\n" +
" El suscrito Jefe de la Unidad de Proyección Social de la Facultad de Jurisprudencia y\n" +
"Ciencias Sociales de la Universidad de El Salvador, por este medio le informa que la Bachiller\n" +
"LILA MARIBEL BERRIOS AGUILAR, con Documento Único Estudiantil Número BA17008,\n" +
"estudiante de la carrera de LICENCIATURA EN RELACIONES INTERNACIONALES, cumple\n" +
"con lo establecido en el artículo 33 literal a) del Reglamento de Proyección Social, para iniciar\n" +
"la prestación del servicio social.\n" +
" Por lo anterior y con base en su oficio de fecha veintiséis de octubre del corriente año en el\n" +
"que solicita se le autorice a la estudiante LILA MARIBEL BERRIOS AGUILAR desarrollar su\n" +
"servicio social en su institución; procedo a autorizar a la estudiante antes relacionada para que\n" +
"cumpla con QUINIENTAS (500) horas de servicio social, en la National Center for State\n" +
"Courts, en ese sentido y con el debido respeto solicito un espacio para el desarrollo de las\n" +
"actividades.\n" +
" Las acciones serán supervisadas por el tutor nombrado por la Dirección de Escuela de\n" +
"Relaciones Internacionales y el encargado en su institución y, la ponderación será por medio\n" +
"de resultados obtenidos, en un periodo no menor a tres meses, ni mayor a dieciocho a partir de\n" +
"este oficio.\n" +
" En ese orden le rogamos que al finalizar las actividades extienda constancia de finalización\n" +
"en la que se consigne fecha de inicio y de finalización así como la cantidad de horas acreditadas.\n" +
"La estudiante puede ser contactada en el correo electrónico BA17008@ues.edu.sv\n" +
"Sin más por el momento, agradezco su atención y expreso mis más altas muestras de\n" +
"consideración y estima.\n" +
"Atentamente");
        
        documento.add(titulo);
        
        documento.close();
        
        String msj = "El archivo ha sido creado correctamente en la siguiente ruta: "+ruta;
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msj, "") );
        
    }
    

    public EstudianteRepository getEstudiantesRepository() {
        return estudiantesRepository;
    }

    public void setEstudiantesRepository(EstudianteRepository estudiantesRepository) {
        this.estudiantesRepository = estudiantesRepository;
    }

    public List<EstudianteInfoDto> getEstudianteList() {
        return estudianteList;
    }

    public void setEstudianteList(List<EstudianteInfoDto> estudianteList) {
        this.estudianteList = estudianteList;
    }

    public EstudianteInfoDto getEstudianteSelect() {
        return estudianteSelect;
    }

    public void setEstudianteSelect(EstudianteInfoDto estudianteSelect) {
        this.estudianteSelect = estudianteSelect;
    }
    
    
}
