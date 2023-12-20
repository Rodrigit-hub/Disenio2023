package com.ppai.ppai_dsi.domain;

import com.ppai.ppai_dsi.domain.iterators.IAgregado;
import com.ppai.ppai_dsi.domain.iterators.IIterador;
import com.ppai.ppai_dsi.domain.iterators.IteradorOpcionLlamada;

import jakarta.persistence.*;

@Entity
@Table(name = "opcion_llamada")
public class OpcionLlamada implements IAgregado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_opcion_llamada")
    private Integer id_opcion_llamada;

    @Column(name = "audio_mensaje_opciones")
    private String audioMensajeOpciones;

    @Column(name = "nombre")
    private String nombre;

    @OneToOne
    @JoinColumn(name = "sub_opcion_llamada_id", referencedColumnName = "id_subOpcion", nullable = false)
    private SubOpcionLlamada subOpcionLlamada;

    public OpcionLlamada() {
    }

    public OpcionLlamada(String audioMensajeOpciones, String nombre, SubOpcionLlamada subOpcionLlamada) {
        this.audioMensajeOpciones = audioMensajeOpciones;
        this.nombre = nombre;
        this.subOpcionLlamada = subOpcionLlamada;
    }

    public Integer getId_opcion_llamada() {
        return id_opcion_llamada;
    }


    public String getAudioMensajeOpciones() {
        return audioMensajeOpciones;
    }

    public void setAudioMensajeOpciones(String audioMensajeSubOpciones) {
        this.audioMensajeOpciones = audioMensajeSubOpciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public SubOpcionLlamada getSubOpcionLlamada() {
        return subOpcionLlamada;
    }

    public void setSubOpcionLlamada(SubOpcionLlamada subOpcion) {
        this.subOpcionLlamada = subOpcion;
    }

    @Override
    public IIterador crearIterador() {
        return new IteradorOpcionLlamada(this);
    }

    public Object mostarDatos() {
        return getNombre();
    }

}
