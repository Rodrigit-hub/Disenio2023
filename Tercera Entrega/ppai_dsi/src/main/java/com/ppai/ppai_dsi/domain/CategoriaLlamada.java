package com.ppai.ppai_dsi.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "categoria_llamada")
public class CategoriaLlamada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria_llamada")
    private Integer id_categoria_llamada;

    @Column(name = "audio_mensaje_categoria")
    private String audioMensajeCategoria;

    // @Lob
    // @Column(name = "mensaje_opciones")
    // private String mensajeOpciones;     Reemplaza con relacion de opciones

    // @Column(name = "nro_orden")
    // private int nroOrden;        Reemplaza con id

    @Column(name = "nombre")
    private String nombre;

    @OneToOne
    @JoinColumn(name = "opcion_llamada_id", referencedColumnName = "id_opcion_llamada", nullable = false)
    private OpcionLlamada opcionLlamada;

    public CategoriaLlamada() {
    }

    public CategoriaLlamada(String audioMensajeCategoria, String nombre, OpcionLlamada opcionLlamada) {
        this.audioMensajeCategoria = audioMensajeCategoria;
        this.nombre = nombre;
        this.opcionLlamada = opcionLlamada;
    }


    public Integer getId_categoria_llamada() {
        return id_categoria_llamada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public OpcionLlamada getOpcionLlamada() {
        return opcionLlamada;
    }

    public void setOpcionLlamada(OpcionLlamada opcionLlamada) {
        this.opcionLlamada = opcionLlamada;
    }


    // public String getDescripcionCompletaCategoriaYOpcion(){
    //     return opcionLlamada.getDescripcionConSubOpcion();

    // }
    
    // public List<Validacion> buscarValidaciones() {
    //     return opcionLlamada.buscarValidaciones();
    // }



}
