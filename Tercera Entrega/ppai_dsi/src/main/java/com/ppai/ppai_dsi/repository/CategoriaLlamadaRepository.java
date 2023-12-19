package com.ppai.ppai_dsi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ppai.ppai_dsi.domain.CategoriaLlamada;

@Repository 
public interface CategoriaLlamadaRepository extends CrudRepository<CategoriaLlamada, Integer> {
    
}
