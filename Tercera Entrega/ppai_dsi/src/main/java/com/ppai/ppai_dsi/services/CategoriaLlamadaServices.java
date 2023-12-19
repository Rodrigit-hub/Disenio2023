package com.ppai.ppai_dsi.services;


import com.ppai.ppai_dsi.domain.CategoriaLlamada;
import com.ppai.ppai_dsi.interfaceServices.ICategoriaLlamadaServices;
import com.ppai.ppai_dsi.repository.CategoriaLlamadaRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaLlamadaServices implements ICategoriaLlamadaServices {
    
    @Autowired 
    private CategoriaLlamadaRepository data;
    
    
    @Override
    public List<CategoriaLlamada> listarCategoriaLlamadas() {
        return (List<CategoriaLlamada>)data.findAll();
    }

    @Override
    public Optional<CategoriaLlamada> listarIdCategoriaLlamadas(int id) {
        return data.findById(id); 
    }

    @Override
    public int save(CategoriaLlamada c) {
       int res=0;
       CategoriaLlamada cateogoriaLlamada = data.save(c);
       if(!cateogoriaLlamada.equals(null)){
           return 1; 
       }
       
       return res;
       
    }

    @Override
    public void delete(int id) {
        data.deleteById(id);
    }
    
}
