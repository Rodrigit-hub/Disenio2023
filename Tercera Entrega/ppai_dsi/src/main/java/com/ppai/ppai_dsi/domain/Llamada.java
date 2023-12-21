package com.ppai.ppai_dsi.domain;


import java.util.Date;

import com.ppai.ppai_dsi.domain.iterators.IAgregado;
import com.ppai.ppai_dsi.domain.iterators.IIterador;
import com.ppai.ppai_dsi.domain.iterators.IteradorLlamada;
import com.ppai.ppai_dsi.interfaceServices.ICambioEstadoServices;
import com.ppai.ppai_dsi.interfaceServices.IEstadoServices;

import jakarta.persistence.*;

@Entity
@Table(name = "llamada")
public class Llamada implements IAgregado{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_llamada")
    private Integer id_llamada;

    @Column(name = "descripcion_operador", length = 255)
    private String descripcionOperador;

    @Column(name = "detalle_accion_requerida")
    private String detalleAccionRequerida;

    @Column(name = "duracion", length = 50)
    private String duracion;

    @Column(name = "encuesta_enviada")
    private Boolean encuestaEnviada;

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "estado_actual_id", referencedColumnName = "id_estado", nullable = false)
    private Estado estadoActual;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cambio_estado_id", referencedColumnName = "id_cambioEstado", nullable = false)
    private CambioEstado cambioEstado;

    @ManyToOne
    @JoinColumn(name = "categoria_llamada_id", referencedColumnName = "id_categoria_llamada", nullable = false)
    private CategoriaLlamada categoriaLlamada;


    public Llamada(){

    }

    public Llamada(String descripcionOperador, String detalleAccionRequerida, String duracion, Boolean encuestaEnviada, Cliente cliente, Estado estadoActual, CambioEstado cambioEstado, CategoriaLlamada categoriaLlamada) {
        this.descripcionOperador = descripcionOperador;
        this.detalleAccionRequerida = detalleAccionRequerida;
        this.duracion = duracion;
        this.encuestaEnviada = encuestaEnviada;
        this.cliente = cliente;
        this.estadoActual = estadoActual;
        this.cambioEstado = cambioEstado;
        this.categoriaLlamada = categoriaLlamada;
    }

    public Integer getId_llamada() {
        return id_llamada;
    }

    public String getDescripcionOperador() {
        return descripcionOperador;
    }

    public void setDescripcionOperador(String descripcionOperador) {
        this.descripcionOperador = descripcionOperador;
    }

    public String getDetalleAccionRequerida() {
        return detalleAccionRequerida;
    }

    public void setDetalleAccionRequerida(String detalleAccionRequerida) {
        this.detalleAccionRequerida = detalleAccionRequerida;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public Boolean getEncuestaEnviada() {
        return encuestaEnviada;
    }

    public void setEncuestaEnviada(Boolean encuestaEnviada) {
        this.encuestaEnviada = encuestaEnviada;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Estado getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(Estado estadoActual) {
        this.estadoActual = estadoActual;
    }

    public CambioEstado getCambioEstado() {
        return cambioEstado;
    }

    public void setCambioEstado(CambioEstado cambioEstado) {
        this.cambioEstado = cambioEstado;
    }

    public CategoriaLlamada getCategoriaLlamada() {
        return categoriaLlamada;
    }

    public void setCategoriaLlamada(CategoriaLlamada categoriaLlamada) {
        this.categoriaLlamada = categoriaLlamada;
    }

    public void tomadaPorOperador(Date fechaCreacion, Estado estadoIniciada, ICambioEstadoServices servicesCambioEstado, IEstadoServices servicesEstado) {
        estadoIniciada.tomadaPorOperador(fechaCreacion, this, servicesCambioEstado,servicesEstado);
    }

    public void finalizarLlamada(Date fechaHoraActual, Estado estadoCurso, ICambioEstadoServices servicesCambioEstado, IEstadoServices servicesEstado) {
        CambioEstado cambioEstado = getCambioEstado();
        estadoCurso.finalizarLlamada(fechaHoraActual, this, cambioEstado, servicesCambioEstado, servicesEstado);

    }

    public void cancelarLlamada(Date fechaHoraActual, Estado estadoCurso, ICambioEstadoServices servicesCambioEstado, IEstadoServices servicesEstado) {
        CambioEstado cambioEstado = getCambioEstado();
        estadoCurso.cancelarLlamada(fechaHoraActual, this, cambioEstado, servicesCambioEstado, servicesEstado);

    }

    public void calcularDuracion(CambioEstado cambioEstadoAnterior) {

        // Obtener las fechas de los cambios de estado
        Date fechaFinal = cambioEstadoAnterior.getFechaHoraFinCambio();
        Date fechaAnterior = cambioEstadoAnterior.getFechaHoraInicioCambio();

        // Calcular la diferencia en milisegundos
        long diferenciaEnMilisegundos = fechaFinal.getTime() - fechaAnterior.getTime();

        // Convertir la diferencia a un formato más legible
        String duracionFormateada = formatearDuracion(diferenciaEnMilisegundos);

        // Asignar la duración calculada
        setDuracion(duracionFormateada);

    }

    // Método auxiliar para formatear la duración
    private String formatearDuracion(long duracionEnMilisegundos) {
        long segundos = duracionEnMilisegundos / 1000;
        long minutos = segundos / 60;
        segundos %= 60;
        long horas = minutos / 60;
        minutos %= 60;

        return String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }
    
    //ITERADOR
    @Override
    public IIterador crearIterador() {
        return new IteradorLlamada(this);
    }

}