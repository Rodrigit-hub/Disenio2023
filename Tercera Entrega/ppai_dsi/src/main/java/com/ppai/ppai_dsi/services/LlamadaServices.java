package com.ppai.ppai_dsi.services;


import com.ppai.ppai_dsi.domain.Llamada;
import com.ppai.ppai_dsi.interfaceServices.ILlamadaServices;
import com.ppai.ppai_dsi.repository.LlamadaRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LlamadaServices implements ILlamadaServices {
    
    @Autowired 
    private LlamadaRepository data;
    
    
    @Override
    public List<Llamada> listarLlamadas() {
        return (List<Llamada>)data.findAll();
    }

    @Override
    public Optional<Llamada> listarIdLlamadas(int id) {
        return data.findById(id); 
    }

    @Override
    public int save(Llamada l) {
       int res=0;
       Llamada llamada = data.save(l);
       if(!llamada.equals(null)){
           return 1; 
       }
       
       return res;
       
    }

    @Override
    public void delete(int id) {
        data.deleteById(id);
    }
    
}
