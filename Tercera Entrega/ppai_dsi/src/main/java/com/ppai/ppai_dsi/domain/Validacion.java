package com.ppai.ppai_dsi.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "validacion")
public class Validacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_validacion")
    private Integer id_validacion;

    @Column(name = "audio_mensaje_validacion", length = 255)
    private String audioMensajeValidacion;

    @Column(name = "nombre", length = 100)
    private String nombre;

    public Validacion() {
    }

    public Validacion(String audioMensajeValidacion, String nombre) {
        this.audioMensajeValidacion = audioMensajeValidacion;
        this.nombre = nombre;
    }

    public Integer getId_validacion() {
        return id_validacion;
    }

    public String getAudioMensajeValidacion() {
        return audioMensajeValidacion;
    }

    public void setAudioMensajeValidacion(String nuevoAudioMensajeValidacion) {
        this.audioMensajeValidacion = nuevoAudioMensajeValidacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }

}
