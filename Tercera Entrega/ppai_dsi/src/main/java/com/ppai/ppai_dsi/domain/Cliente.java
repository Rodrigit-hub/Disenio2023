package com.ppai.ppai_dsi.domain;

import java.math.BigInteger;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer id_cliente;

    @Column(name = "dni")
    private BigInteger dni;

    @Column(name = "nombreCompleto")
    private String nombreCompleto;

    @Column(name = "nro_celular", length = 20)
    private String nroCelular;

    // @ManyToOne
    // @JoinColumn(name = "informacion_cliente", referencedColumnName = "id_infoCliente", nullable = false)
    // private InformacionCliente infoCliente;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<InformacionCliente> infoCliente;

    public Cliente() {
    }

    public Cliente(BigInteger dni, String nombreCompleto, String nroCelular, List<InformacionCliente> infoCliente) {
        this.dni = dni;
        this.nombreCompleto = nombreCompleto;
        this.nroCelular = nroCelular;
        this.infoCliente = infoCliente;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public BigInteger getDni() {
        return dni;
    }

    public void setDni(BigInteger dni) {
        this.dni = dni;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNroCelular() {
        return nroCelular;
    }

    public void setNroCelular(String nroCelular) {
        this.nroCelular = nroCelular;
    }

    public List<InformacionCliente> getInfoCliente() {
        return infoCliente;
    }

    public void setInfoCliente(List<InformacionCliente> infoCliente) {
        this.infoCliente = infoCliente;
    }

    // PASO 22
    public boolean esInformacionCorrecta(String idValidacion, String valorIngresado) {
        for (InformacionCliente informacionCliente : infoCliente) {
            if (informacionCliente.esValidacion(informacionCliente, idValidacion)) {
                String valorCorrecto = informacionCliente.getDatoAValidar();
                return informacionCliente.esInformacionCorrecta(valorIngresado, valorCorrecto);
            }
        }
        return false;

    }
}