package com.ppai.ppai_dsi.domain;

import com.ppai.ppai_dsi.domain.iterators.IAgregado;
import com.ppai.ppai_dsi.domain.iterators.IIterador;
import com.ppai.ppai_dsi.domain.iterators.IteradorSubOpcionLlamada;

import jakarta.persistence.*;

@Entity
@Table(name = "sub_opcion_llamada")
public class SubOpcionLlamada implements IAgregado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subOpcion")
    private Integer id_subOpcion;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "audio_mensaje_subopciones")
    private String audioMensajeSubOpciones;

    // @Column(name = "nro_orden")
    // private int nroOrden;    Reemplazo con ID

    public SubOpcionLlamada() {
    }

    public SubOpcionLlamada(Integer id_subOpcion, String nombre, String audioMensajeSubOpciones) {
        this.id_subOpcion = id_subOpcion;
        this.nombre = nombre;
        this.audioMensajeSubOpciones = audioMensajeSubOpciones;
    }

    public Integer getId() {
        return id_subOpcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }

    @Override
    public IIterador crearIterador() {
        return new IteradorSubOpcionLlamada(this);
    }

    public Object mostarDatos() {
        return getNombre();
    }
    
}
