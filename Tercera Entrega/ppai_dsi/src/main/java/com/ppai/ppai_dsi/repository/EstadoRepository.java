package com.ppai.ppai_dsi.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ppai.ppai_dsi.domain.Estado;

@Repository 
public interface EstadoRepository extends CrudRepository<Estado, Integer> {
    Optional<Estado> findByNombre(String nombre);
}
