package com.ppai.ppai_dsi.domain.iterators;

import com.ppai.ppai_dsi.domain.OpcionLlamada;

// IteradorOpcionLlamada.java
public class IteradorOpcionLlamada implements IIterador {
    private OpcionLlamada opcionLlamada;
    private int indice;

    public IteradorOpcionLlamada(OpcionLlamada opcionLlamada) {
        this.opcionLlamada = opcionLlamada;
        this.indice = 0;
    }

    @Override
    public void primero() {
        indice = 0;
    }

    @Override
    public void siguiente() {
        indice++;
    }

    @Override
    public boolean haTerminado() {
        return indice >= 1; // Modifica según la lógica adecuada
    }

    @Override
    public Object actual() {
        if (haTerminado()) {
            return null; // O manejo de error
        }
        return opcionLlamada.mostarDatos();
    }
}
