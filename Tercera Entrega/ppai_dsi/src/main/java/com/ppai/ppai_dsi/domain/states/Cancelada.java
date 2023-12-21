package com.ppai.ppai_dsi.domain.states;

import java.util.Date;

import com.ppai.ppai_dsi.domain.CambioEstado;
import com.ppai.ppai_dsi.domain.Estado;
import com.ppai.ppai_dsi.domain.Llamada;
import com.ppai.ppai_dsi.interfaceServices.ICambioEstadoServices;
import com.ppai.ppai_dsi.interfaceServices.IEstadoServices;

import jakarta.persistence.*;

@Entity
@Table(name = "cancelada")
public class Cancelada extends Estado{

    public Cancelada() {
    }

    public Cancelada(String nombre) {
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
        return false;
    }

    @Override
    public boolean esCancelada() {
        return true;
    }
    
    @Override
    public void tomadaPorOperador(Date fechaHoraActual, Llamada llamada,
            ICambioEstadoServices servicesCambioEstado, IEstadoServices servicesEstado) {
        throw new UnsupportedOperationException("Unimplemented method 'tomadaPorOperador'");
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

}
