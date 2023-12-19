package com.ppai.ppai_dsi.interfaceServices;

import java.util.List;
import java.util.Optional;

import com.ppai.ppai_dsi.domain.CategoriaLlamada;

public interface ICategoriaLlamadaServices {
    public List<CategoriaLlamada> listarCategoriaLlamadas();
    public Optional<CategoriaLlamada> listarIdCategoriaLlamadas(int id);
    public int save (CategoriaLlamada categoriaLlamada);
    public void delete(int id);
}
