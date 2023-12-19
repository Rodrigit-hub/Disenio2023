package com.ppai.ppai_dsi.interfaceServices;

import java.util.List;
import java.util.Optional;

import com.ppai.ppai_dsi.domain.Validacion;

public interface IValidacionServices {
    public List<Validacion> listarValidaciones();
    public Optional<Validacion> listarIdValidaciones(int id);
    public Optional<Validacion> obtenerValidacionPorNombre(String nombre);
    public int save (Validacion validacion);
    public void delete(int id);
}
