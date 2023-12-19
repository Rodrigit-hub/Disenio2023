package com.ppai.ppai_dsi.interfaceServices;

import java.util.List;
import java.util.Optional;

import com.ppai.ppai_dsi.domain.CambioEstado;

public interface ICambioEstadoServices {
    public List<CambioEstado> listarCambioEstados();
    public Optional<CambioEstado> listarIdCambioEstados(int id);
    public int save (CambioEstado cambioEstado);
    public void delete(int id);
}
