package com.loja.demo.repository.categoria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.loja.demo.model.entity.categoria.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    @Query("SELECT categoria FROM Categoria categoria WHERE categoria.nm_categoria LIKE %:nmCategoria%")
    List<Categoria> findByName(@Param(value = "nmCategoria") String nmCategoria);
}
