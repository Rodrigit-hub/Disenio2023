package com.ppai.ppai_dsi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ppai.ppai_dsi.domain.Llamada;

@Repository 
public interface LlamadaRepository extends CrudRepository<Llamada, Integer> {
    
}
