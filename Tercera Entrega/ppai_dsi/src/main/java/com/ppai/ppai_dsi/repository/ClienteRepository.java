package com.ppai.ppai_dsi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ppai.ppai_dsi.domain.Cliente;

@Repository 
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
    
}
