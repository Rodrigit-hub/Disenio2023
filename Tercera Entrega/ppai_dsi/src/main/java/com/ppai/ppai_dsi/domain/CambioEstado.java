package com.ppai.ppai_dsi.domain;

import java.util.Date;

import com.ppai.ppai_dsi.domain.states.EnCurso;

import jakarta.persistence.*;

@Entity
@Table(name = "cambio_estado")
public class CambioEstado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cambioEstado")
    private Integer id_cambioEstado;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_hora_inicio_cambio")
    private Date fechaHoraInicioCambio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_hora_fin_cambio")
    private Date fechaHoraFinCambio;

// AGREGAR FECHA HORA FIN CAMBIO ESTADO

    @ManyToOne
    @JoinColumn(name = "estado_id", referencedColumnName = "id_estado", nullable = false)
    private Estado estado;


    public CambioEstado() {
    }

    public CambioEstado(Date fechaHoraInicioCambio, Estado estado ) {
        this.fechaHoraInicioCambio = fechaHoraInicioCambio;
        this.estado = estado;
    }

    public Integer getId() {
        return id_cambioEstado;
    }


    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Date getFechaHoraInicioCambio() {
        return fechaHoraInicioCambio;
    }

    public void setFechaHoraInicioCambio(Date fechaHoraInicioCambio) {
        this.fechaHoraInicioCambio = fechaHoraInicioCambio;
    }

    public Date getFechaHoraFinCambio() {
        return fechaHoraFinCambio;
    }

    public void setFechaHoraFinCambio(Date fechaHoraFinCambio) {
        this.fechaHoraFinCambio = fechaHoraFinCambio;
    }

    public boolean esEstadoActual(EnCurso enCurso) {
        return enCurso.esEnCurso();
    }

}
