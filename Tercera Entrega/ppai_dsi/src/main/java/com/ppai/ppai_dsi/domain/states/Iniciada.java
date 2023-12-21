package com.ppai.ppai_dsi.domain.states;

import java.util.Date;

import com.ppai.ppai_dsi.domain.CambioEstado;
import com.ppai.ppai_dsi.domain.Estado;
import com.ppai.ppai_dsi.domain.Llamada;
import com.ppai.ppai_dsi.interfaceServices.ICambioEstadoServices;
import com.ppai.ppai_dsi.interfaceServices.IEstadoServices;

import jakarta.persistence.*;

@Entity
@Table(name = "iniciada")
public class Iniciada extends Estado{

    public Iniciada() {
    }

    public Iniciada(String nombre) {
        super(nombre);
    }

    @Override
    public boolean esFinalizada() {
        return false;
    }

    @Override
    public boolean esIniciada() {
        return true;
    }

    @Override
    public boolean esEnCurso() {
        return false;
    }

    @Override
    public boolean esCancelada() {
        return false;
    }

    @Override
    public void tomadaPorOperador(Date fechaHoraActual, Llamada llamada,
            ICambioEstadoServices servicesCambioEstado, IEstadoServices servicesEstado) {

        //Crear estado finalizado
        Estado estadoEnCurso = crearEstadoEnCurso();
        servicesEstado.save(estadoEnCurso);

        //Crear cambio estado
        CambioEstado nuevoCambioEstado = crearCambioEstado(fechaHoraActual, estadoEnCurso);
        // Guardar el cambio de estado en la base de datos
        servicesCambioEstado.save(nuevoCambioEstado);

        // Asignar el cambio de estado a la llamada
        llamada.setCambioEstado(nuevoCambioEstado);   
        //Asignar estado finalizado a llamada
        llamada.setEstadoActual(estadoEnCurso);    
        
    }

    @Override
    public void finalizarLlamada(Date fechaHoraActual, Llamada llamada, CambioEstado cambioEstado,
            ICambioEstadoServices servicesCambioEstado, IEstadoServices servicesEstado) {
        throw new UnsupportedOperationException("Unimplemented method 'finalizarLlamada'");
    }
    
    @Override
    public void cancelarLlamada(Date fechaHoraActual, Llamada llamada, CambioEstado cambioEstado,
            ICambioEstadoServices servicesCambioEstado, IEstadoServices servicesEstado) {
        throw new UnsupportedOperationException("Unimplemented method 'cancelarLlamada'");
    }
    
    public Estado crearEstadoEnCurso() {
        return new EnCurso("EnCurso");
    }
    
    public CambioEstado crearCambioEstado(Date fechaHoraActual,Estado estado) {
        return new CambioEstado(fechaHoraActual, estado);
    }
}
