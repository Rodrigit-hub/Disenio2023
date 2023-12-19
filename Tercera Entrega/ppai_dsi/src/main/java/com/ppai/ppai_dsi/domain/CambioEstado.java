package com.ppai.ppai_dsi.domain;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "cambio_estado")
public class CambioEstado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cambioEstado")
    private Integer id_cambioEstado;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_hora_cambio")
    private Date fechaHoraCambio;

    @ManyToOne
    @JoinColumn(name = "estado_id", referencedColumnName = "id_estado", nullable = false)
    private Estado estado;


    public CambioEstado() {
    }

    public CambioEstado(Date fechaHoraCambio, Estado estado ) {
        this.fechaHoraCambio = fechaHoraCambio;
        this.estado = estado;
    }

    public Integer getId() {
        return id_cambioEstado;
    }

    public Date getFechaHoraCambio() {
        return fechaHoraCambio;
    }

    public void setFechaHoraCambio(Date fechaHoraCambio) {
        this.fechaHoraCambio = fechaHoraCambio;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

}
