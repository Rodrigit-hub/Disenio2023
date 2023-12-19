package com.ppai.ppai_dsi.interfaceServices;

import java.util.List;
import java.util.Optional;

import com.ppai.ppai_dsi.domain.Llamada;

public interface ILlamadaServices {
    public List<Llamada> listarLlamadas();
    public Optional<Llamada> listarIdLlamadas(int id);
    public int save (Llamada llamada);
    public void delete(int id);
}
