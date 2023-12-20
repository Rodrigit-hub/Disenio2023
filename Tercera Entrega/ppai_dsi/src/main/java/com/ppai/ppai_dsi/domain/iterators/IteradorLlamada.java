package com.ppai.ppai_dsi.domain.iterators;

import com.ppai.ppai_dsi.domain.Llamada;

// IteradorLlamada.java
public class IteradorLlamada implements IIterador {
    private Llamada llamada;
    private int indice;

    public IteradorLlamada(Llamada llamada) {
        this.llamada = llamada;
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
        return llamada.getCliente().getNombreCompleto();
    }
}
