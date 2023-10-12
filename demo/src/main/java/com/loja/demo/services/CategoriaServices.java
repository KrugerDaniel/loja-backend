package com.loja.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loja.demo.exceptions.ObjectNotFoundException;
import com.loja.demo.model.Categoria;
import com.loja.demo.repository.CategoriaRepository;

@Service
public class CategoriaServices {
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria insertCategoria(Categoria categoria) {
        categoria.setCd_categoria(null);
        return categoriaRepository.save(categoria);
    }

    public void deleteCategoria(Integer id) {
        categoriaRepository.findById(id)
            .orElseThrow(
                () -> new ObjectNotFoundException("Categoria " + id + " não encontrada!")
            );

        categoriaRepository.deleteById(id);
    }

    public Categoria updateCategoria(Integer id, Categoria categoria) {
        Categoria categoriaUpdated = this.findCategoria(id);

        categoriaUpdated.setNm_categoria(categoria.getNm_categoria());

        return categoriaRepository.save(categoriaUpdated);
    }

    private Categoria findCategoria(Integer id) {
        return categoriaRepository.findById(id)
            .orElseThrow(
                () -> new ObjectNotFoundException("Categoria " + id + " não encontrada!")
            );
    }
}
