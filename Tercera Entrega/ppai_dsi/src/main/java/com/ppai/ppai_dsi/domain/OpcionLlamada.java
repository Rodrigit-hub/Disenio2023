package com.ppai.ppai_dsi.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "opcion_llamada")
public class OpcionLlamada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_opcion_llamada")
    private Integer id_opcion_llamada;

    @Column(name = "audio_mensaje_opciones")
    private String audioMensajeOpciones;

    // @Column(name = "mensaje_subopciones")
    // private String mensajeSubOpciones;   Se reemplaza con la relacion con subOpcionLlamada

    // @Column(name = "nro_orden")
    // private Integer nroOrden;    Se reemplaza con id

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
    
    // public String getDescripcionConSubOpcion() {
    //     return subOpcionLlamada.getNombre();
    // }

    // public List<Validacion> buscarValidaciones() {
    //     return subOpcionLlamada.buscarValidaciones();
    // }



}
