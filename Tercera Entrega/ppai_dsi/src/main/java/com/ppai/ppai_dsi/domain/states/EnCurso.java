package com.ppai.ppai_dsi.domain.states;

import java.util.Date;

import com.ppai.ppai_dsi.domain.CambioEstado;
import com.ppai.ppai_dsi.domain.Estado;
import com.ppai.ppai_dsi.domain.Llamada;
import com.ppai.ppai_dsi.interfaceServices.ICambioEstadoServices;
import com.ppai.ppai_dsi.interfaceServices.IEstadoServices;

import jakarta.persistence.*;

@Entity
@Table(name = "enCurso")
public class EnCurso extends Estado{

    public EnCurso() {
    }

    public EnCurso(String nombre) {
        super(nombre);
    }

    @Override
    public boolean esFinalizada() {
        return false;
    }

    @Override
    public boolean esIniciada() {
        return false;
    }

    @Override
    public boolean esEnCurso() {
        return true;
    }

    @Override
    public boolean esCancelada() {
        return false;
    }

    @Override
    public void tomadaPorOperador(Date fechaHoraActual, Llamada llamada,
            ICambioEstadoServices servicesCambioEstado, IEstadoServices servicesEstado) {
        throw new UnsupportedOperationException("Unimplemented method 'tomadaPorOperador'");
    }

    @Override
    public void finalizarLlamada(Date fechaHoraActual, Llamada llamada, CambioEstado ultimoCambioEstado,
            ICambioEstadoServices servicesCambioEstado, IEstadoServices servicesEstado) {
                
        
        //Comprobar si el cambioEstado esta en estado "EnCurso"
        if (buscarCambioEstado(ultimoCambioEstado)) {

            //Setear y guardar en BD la fechaHoraFinCambio del cambioEstadoAnterior
            ultimoCambioEstado.setFechaHoraFinCambio(fechaHoraActual);
            servicesCambioEstado.save(ultimoCambioEstado);

            //Crear estado finalizado
            Estado estadoFinalizada = crearEstadoFinalizado();
            servicesEstado.save(estadoFinalizada);

            //Crear cambio estado
            CambioEstado nuevoCambioEstado = crearCambioEstado(fechaHoraActual, estadoFinalizada);
            // Guardar el cambio de estado en la base de datos
            servicesCambioEstado.save(nuevoCambioEstado);

            // Asignar el cambio de estado a la llamada
            llamada.setCambioEstado(nuevoCambioEstado);   
            //Asignar estado finalizado a llamada
            llamada.setEstadoActual(estadoFinalizada);    
        }

    }

    @Override
    public void cancelarLlamada(Date fechaHoraActual, Llamada llamada, CambioEstado ultimoCambioEstado,
            ICambioEstadoServices servicesCambioEstado, IEstadoServices servicesEstado) {

        if (buscarCambioEstado(ultimoCambioEstado)) {
            //Setear y guardar en BD la fechaHoraFinCambio
            ultimoCambioEstado.setFechaHoraFinCambio(fechaHoraActual);
            servicesCambioEstado.save(ultimoCambioEstado);

            //Crear estado cancelado
            Estado estadoCancelada = crearEstadoCancelada();
            servicesEstado.save(estadoCancelada);

            //Crear cambio estado
            CambioEstado nuevoCambioEstado = crearCambioEstado(fechaHoraActual, estadoCancelada);
            // Guardar el cambio de estado en la base de datos
            servicesCambioEstado.save(nuevoCambioEstado);

            // Asignar el cambio de estado a la llamada
            llamada.setCambioEstado(nuevoCambioEstado);   
            //Asignar estado finalizado a llamada
            llamada.setEstadoActual(estadoCancelada);
        }
    }
    
    public Boolean buscarCambioEstado(CambioEstado cambioEstado) {
        return cambioEstado.esEstadoActual(this);
    }

    public Estado crearEstadoFinalizado() {
        return new Finalizada("Finalizada");
    }

    public Estado crearEstadoCancelada() {
        return new Cancelada("Cancelada");
    }

    public CambioEstado crearCambioEstado(Date fechaHoraActual,Estado estado) {
        return new CambioEstado(fechaHoraActual, estado);
    }


}
