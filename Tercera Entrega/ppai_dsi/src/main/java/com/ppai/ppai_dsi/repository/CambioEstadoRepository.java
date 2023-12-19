package com.ppai.ppai_dsi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ppai.ppai_dsi.domain.CambioEstado;

@Repository 
public interface CambioEstadoRepository extends CrudRepository<CambioEstado, Integer> {
    
}
