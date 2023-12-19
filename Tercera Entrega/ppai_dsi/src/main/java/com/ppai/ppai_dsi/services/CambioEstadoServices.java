package com.ppai.ppai_dsi.services;


import com.ppai.ppai_dsi.domain.CambioEstado;
import com.ppai.ppai_dsi.interfaceServices.ICambioEstadoServices;
import com.ppai.ppai_dsi.repository.CambioEstadoRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CambioEstadoServices implements ICambioEstadoServices {
    
    @Autowired 
    private CambioEstadoRepository data;
    
    
    @Override
    public List<CambioEstado> listarCambioEstados() {
        return (List<CambioEstado>)data.findAll();
    }

    @Override
    public Optional<CambioEstado> listarIdCambioEstados(int id) {
        return data.findById(id); 
    }

    @Override
    public int save(CambioEstado c) {
       int res=0;
       CambioEstado cambioEstado = data.save(c);
       if(!cambioEstado.equals(null)){
           return 1; 
       }
       
       return res;
       
    }

    @Override
    public void delete(int id) {
        data.deleteById(id);
    }
    
}
