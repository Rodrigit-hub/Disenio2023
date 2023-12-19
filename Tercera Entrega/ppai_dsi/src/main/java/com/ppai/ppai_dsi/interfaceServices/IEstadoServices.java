package com.ppai.ppai_dsi.interfaceServices;

import java.util.List;
import java.util.Optional;

import com.ppai.ppai_dsi.domain.Estado;

public interface IEstadoServices {
    public List<Estado> listarEstados();
    public Optional<Estado> listarIdEstados(int id);
    public Optional<Estado> obtenerEstadoPorNombre(String nombre);
    public int save (Estado estado);
    public void delete(int id);
}
