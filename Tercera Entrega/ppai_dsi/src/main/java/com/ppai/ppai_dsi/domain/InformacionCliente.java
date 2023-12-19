package com.ppai.ppai_dsi.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "informacion_cliente")
public class InformacionCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_infoCliente")
    private Integer id_infoCliente;

    @Column(name = "dato_a_validar", length = 255)
    private String datoAValidar;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
    
    @OneToOne
    @JoinColumn(name = "validacion_id", referencedColumnName = "id_validacion", nullable = false)
    private Validacion validacion;

    public InformacionCliente() {
    }

    public InformacionCliente(String datoAValidar, Validacion validacion) {
        this.datoAValidar = datoAValidar;
        this.validacion = validacion;
    }

    public Integer getId_infoCliente() {
        return id_infoCliente;
    }

    public String getDatoAValidar() {
        return datoAValidar;
    }

    public void setDatoAValidar(String nuevoDato) {
        this.datoAValidar = nuevoDato;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Validacion getValidacion() {
        return validacion;
    }

    public void setValidacion(Validacion validacion) {
        this.validacion = validacion;
    }

    // PASO 23
    public boolean esValidacion(InformacionCliente informacionCliente, String idValidacion) {
        return informacionCliente.getValidacion().getId_validacion().toString().equals(idValidacion);
    }

    // PASO 24
    public boolean esInformacionCorrecta(String valorCorrecto, String valorIngresado) {
        return valorCorrecto.equals(valorIngresado);
    }
}
