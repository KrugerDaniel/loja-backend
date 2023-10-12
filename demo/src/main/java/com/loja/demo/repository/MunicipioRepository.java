package com.loja.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.loja.demo.model.Municipio;

public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {

    @Query("SELECT municipio FROM Municipio municipio WHERE municipio.nm_municipio LIKE %:nmMunicipio%")
    List<Municipio> findMunicipioByName(@Param(value = "nmMunicipio") String nmMunicipio);

    @Query("SELECT municipio FROM Municipio municipio WHERE municipio.sg_uf = :sgUF")
    List<Municipio> findMunicipioByUF(@Param(value = "sgUF") String sgUF);
}
