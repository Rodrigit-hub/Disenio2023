package com.ppai.ppai_dsi.services;


import com.ppai.ppai_dsi.domain.Estado;
import com.ppai.ppai_dsi.interfaceServices.IEstadoServices;
import com.ppai.ppai_dsi.repository.EstadoRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoServices implements IEstadoServices {
    
    @Autowired 
    private EstadoRepository data;
    
    
    @Override
    public List<Estado> listarEstados() {
        return (List<Estado>)data.findAll();
    }

    @Override
    public Optional<Estado> listarIdEstados(int id) {
        return data.findById(id); 
    }

    @Override
    public Optional<Estado> obtenerEstadoPorNombre(String nombre) {
        return data.findByNombre(nombre);
    }

    @Override
    public int save(Estado e) {
       int res=0;
       Estado estado = data.save(e);
       if(!estado.equals(null)){
           return 1; 
       }
       
       return res;
       
    }

    @Override
    public void delete(int id) {
        data.deleteById(id);
    }
    
}
