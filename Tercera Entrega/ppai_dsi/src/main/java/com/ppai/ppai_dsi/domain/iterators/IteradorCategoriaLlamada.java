package com.ppai.ppai_dsi.domain.iterators;

import com.ppai.ppai_dsi.domain.CategoriaLlamada;

// IteradorCategoriaLlamada.java
public class IteradorCategoriaLlamada implements IIterador {
    private CategoriaLlamada categoriaLlamada;
    private int indice;

    public IteradorCategoriaLlamada(CategoriaLlamada categoriaLlamada) {
        this.categoriaLlamada = categoriaLlamada;
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
        return categoriaLlamada.mostarDatos();
    }
}
