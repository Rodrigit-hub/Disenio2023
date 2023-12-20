package com.ppai.ppai_dsi.domain.iterators;

import com.ppai.ppai_dsi.domain.SubOpcionLlamada;

// IteradorOpcionLlamada.java
public class IteradorSubOpcionLlamada implements IIterador {
    private SubOpcionLlamada subOpcionLlamada;
    private int indice;

    public IteradorSubOpcionLlamada(SubOpcionLlamada subOpcionLlamada) {
        this.subOpcionLlamada = subOpcionLlamada;
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
        return subOpcionLlamada.mostarDatos();
    }
}
