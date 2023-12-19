package com.ppai.ppai_dsi.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ppai.ppai_dsi.domain.Validacion;

@Repository 
public interface ValidacionRepository extends CrudRepository<Validacion, Integer> {
    Optional<Validacion> findByNombre(String nombre);
}
