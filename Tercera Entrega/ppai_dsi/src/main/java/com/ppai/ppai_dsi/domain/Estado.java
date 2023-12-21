package com.ppai.ppai_dsi.domain;

import java.util.Date;

import com.ppai.ppai_dsi.interfaceServices.ICambioEstadoServices;
import com.ppai.ppai_dsi.interfaceServices.IEstadoServices;

import jakarta.persistence.*;

@Entity
@Table(name = "estado")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_estado", discriminatorType = DiscriminatorType.STRING)
public abstract class Estado {

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

    public abstract boolean esFinalizada();

    public abstract boolean esIniciada();

    public abstract boolean esEnCurso();

    public abstract boolean esCancelada();

    public abstract void tomadaPorOperador(Date fechaHoraActual, Llamada llamada,
    ICambioEstadoServices servicesCambioEstado, IEstadoServices servicesEstado);

    public abstract void finalizarLlamada(Date fechaHoraActual, Llamada llamada, CambioEstado ultimoCambioEstado, 
    ICambioEstadoServices servicesCambioEstado, IEstadoServices servicesEstado);

    public abstract void cancelarLlamada(Date fechaHoraActual, Llamada llamada, CambioEstado ultimoCambioEstado, 
    ICambioEstadoServices servicesCambioEstado, IEstadoServices servicesEstado);
}
