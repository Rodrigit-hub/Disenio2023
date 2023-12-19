package com.ppai.ppai_dsi.services;


import com.ppai.ppai_dsi.domain.Validacion;
import com.ppai.ppai_dsi.interfaceServices.IValidacionServices;
import com.ppai.ppai_dsi.repository.ValidacionRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidacionServices implements IValidacionServices {
    
    @Autowired 
    private ValidacionRepository data;
    
    
    @Override
    public List<Validacion> listarValidaciones() {
        return (List<Validacion>)data.findAll();
    }

    @Override
    public Optional<Validacion> listarIdValidaciones(int id) {
        return data.findById(id); 
    }

    @Override
    public Optional<Validacion> obtenerValidacionPorNombre(String nombre) {
        return data.findByNombre(nombre);
    }

    @Override
    public int save(Validacion e) {
       int res=0;
       Validacion Validacion = data.save(e);
       if(!Validacion.equals(null)){
           return 1; 
       }
       
       return res;
       
    }

    @Override
    public void delete(int id) {
        data.deleteById(id);
    }
    
}
