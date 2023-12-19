package com.ppai.ppai_dsi.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "estado")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Integer id_estado;

    @Column(name = "nombre")
    private String nombre;
 
    
    public Estado() {
    }

    public Estado(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id_estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }

    public boolean esFinalizada() {
        return getNombre().equalsIgnoreCase("Finalizada");
    }

    public boolean esIniciada() {
        return getNombre().equalsIgnoreCase("Iniciada");
    }

    public boolean esEnCurso() {
        return getNombre().equalsIgnoreCase("En Curso");
    }

    public boolean esCancelada() {
        return getNombre().equalsIgnoreCase("Cancelada");
    }


}
