/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import org.registrohorasociales.config.ApplicationContextProvider;
import org.registrohorasociales.entity.Finalizado;
import org.registrohorasociales.repository.IFinalizadoRepository;

/**
 *
 * @author Miguel
 */
@ManagedBean
@ViewScoped
@SessionScoped
public class FinalizadoController implements Serializable {

    private IFinalizadoRepository finRepo;
    private List<Finalizado> finalizados;
    private Finalizado finSelector;
    private String formDue, formNombres, formApellidos;

    @PostConstruct
    public void initialize() {
        finRepo = ApplicationContextProvider.getApplicationContext().getBean(IFinalizadoRepository.class);
        loadFinalizados();
    }

    private void loadFinalizados() {
        finalizados = new ArrayList<>();
        finalizados = finRepo.FinalizadoList();
    }

    public void createFinalizado(String due, String noms, String apel, Integer car) {
        Finalizado fin = new Finalizado();
        fin.setDue(due);
        fin.setNombres(noms);
        fin.setApellidos(apel);
        fin.setCarrera(car);
        finRepo.save(fin);
        deleteEstudiante(due);
    }

    public void editFinalizado() {
        Finalizado fin = new Finalizado();
        Finalizado fin2 = new Finalizado();
        fin2 = finRepo.getOne(formDue);
        if (!(fin2 == null)) {
            fin.setDue(formDue);
            fin.setNombres(formNombres);
            fin.setApellidos(formApellidos);
            fin.setCarrera(fin2.getCarrera());
            finRepo.save(fin);
        } else {
        }
    }
    

    public void obtenerDatos() {
        setFormDue(finSelector.getDue());
        setFormNombres(finSelector.getNombres());
        setFormApellidos(finSelector.getApellidos());
    }

    public void clearForm() {
        formDue = null;
        formNombres = null;
        formApellidos = null;
        setFormDue("");
        setFormNombres("");
        setFormApellidos("");
    }

    public void deleteEstudiante(String due) {
        EstudianteController estuController = new EstudianteController();
        estuController.eliminarEstudiante(due);
    }

    public List<Finalizado> getFinalizados() {
        return finalizados;
    }

    public void setFinalizados(List<Finalizado> finalizados) {
        this.finalizados = finalizados;
    }

    public Finalizado getFinSelector() {
        return finSelector;
    }

    public void setFinSelector(Finalizado finSelector) {
        this.finSelector = finSelector;
    }

    public boolean gloalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }

        Finalizado fin = (Finalizado) value;
        return fin.getDue().toLowerCase().contains(filterText)
                || fin.getNombres().toLowerCase().contains(filterText)
                || fin.getApellidos().toLowerCase().contains(filterText);
    }

    public String getFormDue() {
        return formDue;
    }

    public void setFormDue(String formDue) {
        this.formDue = formDue;
    }

    public String getFormNombres() {
        return formNombres;
    }

    public void setFormNombres(String formNombres) {
        this.formNombres = formNombres;
    }

    public String getFormApellidos() {
        return formApellidos;
    }

    public void setFormApellidos(String formApellidos) {
        this.formApellidos = formApellidos;
    }

}
