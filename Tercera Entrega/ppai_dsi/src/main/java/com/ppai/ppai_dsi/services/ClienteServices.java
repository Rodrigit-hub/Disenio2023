package com.ppai.ppai_dsi.services;


import com.ppai.ppai_dsi.domain.Cliente;
import com.ppai.ppai_dsi.interfaceServices.IClienteServices;
import com.ppai.ppai_dsi.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServices implements IClienteServices {
    
    @Autowired 
    private ClienteRepository data;
    
    
    @Override
    public List<Cliente> listarClientes() {
        return (List<Cliente>)data.findAll();
    }

    @Override
    public Optional<Cliente> listarIdClientes(int id) {
        return data.findById(id); 
    }

    @Override
    public int save(Cliente c) {
       int res=0;
       Cliente cliente = data.save(c);
       if(!cliente.equals(null)){
           return 1; 
       }
       
       return res;
       
    }

    @Override
    public void delete(int id) {
        data.deleteById(id);
    }

}
