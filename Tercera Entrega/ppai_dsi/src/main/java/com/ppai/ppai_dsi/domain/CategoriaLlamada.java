package com.ppai.ppai_dsi.domain;

import com.ppai.ppai_dsi.domain.iterators.IAgregado;
import com.ppai.ppai_dsi.domain.iterators.IIterador;
import com.ppai.ppai_dsi.domain.iterators.IteradorCategoriaLlamada;

import jakarta.persistence.*;

@Entity
@Table(name = "categoria_llamada")
public class CategoriaLlamada implements IAgregado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria_llamada")
    private Integer id_categoria_llamada;

    @Column(name = "audio_mensaje_categoria")
    private String audioMensajeCategoria;

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

    public Object mostarDatos() {
        return getNombre();
    }

    @Override
    public IIterador crearIterador() {
        return new IteradorCategoriaLlamada(this);
    }


}
