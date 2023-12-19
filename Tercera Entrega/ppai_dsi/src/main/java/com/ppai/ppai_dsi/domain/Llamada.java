package com.ppai.ppai_dsi.domain;


import java.util.Date;

import com.ppai.ppai_dsi.interfaceServices.ICambioEstadoServices;

import jakarta.persistence.*;

@Entity
@Table(name = "llamada")
public class Llamada {

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

    public void tomadaPorOperador(Date fechaCreacion, Estado estadoActual, ICambioEstadoServices servicesCambioEstado) {
        //PASO 6
        //Crear cambio estado
        CambioEstado cambioEstado = new CambioEstado(fechaCreacion, estadoActual);
        // Guardar el cambio de estado en la base de datos
        servicesCambioEstado.save(cambioEstado);
        // Asignar el cambio de estado a la llamada
        setCambioEstado(cambioEstado);
    }

    //USAMOS EL MISMO METODO PARA CANCELAR LA LLAMADA PASANDOLE EL ESTADO "CANCELADA"
    public void finalizarLlamada(Date fechaHoraActual, Estado estadoFinal, ICambioEstadoServices servicesCambioEstado) {
        //Finalizar llamada (o cancelar)
        setEstadoActual(estadoFinal);
        //Crear cambio estado
        CambioEstado cambioEstado = new CambioEstado(fechaHoraActual, estadoFinal);
        // Guardar el cambio de estado en la base de datos
        servicesCambioEstado.save(cambioEstado);
        // Asignar el cambio de estado a la llamada
        setCambioEstado(cambioEstado);
    }

    public void calcularDuracion(CambioEstado cambioEstadoAnterior) {
        CambioEstado cambioEstadoActual = getCambioEstado();

        // Verificar que ambos cambios de estado existan
        if (cambioEstadoActual != null && cambioEstadoAnterior != null) {
            // Obtener las fechas de los cambios de estado
            Date fechaFinal = cambioEstadoActual.getFechaHoraCambio();
            Date fechaAnterior = cambioEstadoAnterior.getFechaHoraCambio();

            // Calcular la diferencia en milisegundos
            long diferenciaEnMilisegundos = fechaFinal.getTime() - fechaAnterior.getTime();

            // Convertir la diferencia a un formato más legible
            String duracionFormateada = formatearDuracion(diferenciaEnMilisegundos);

            // Asignar la duración calculada
            setDuracion(duracionFormateada);
        } else {
            setDuracion("0");
        }

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

}