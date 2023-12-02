package com.loja.demo.services.categoria;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.loja.demo.enums.HttpStatusCode;
import com.loja.demo.exceptions.ObjectNotFoundException;
import com.loja.demo.model.entity.categoria.Categoria;
import com.loja.demo.repository.categoria.CategoriaRepository;
import com.loja.demo.services.GeneralService;

@Service
public class CategoriaServices extends GeneralService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    public ResponseEntity<?> findAll() {
        try {
            List<Categoria> categorias = categoriaRepository.findAll();

            if (categorias.isEmpty()) {
                throw new ObjectNotFoundException("Não há categorias");
            }
            return ResponseEntity.status(HttpStatusCode.OK.getCode()).body(categorias);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> findById(Integer id) {
        try {
            Categoria categoria = this.findCategoria(id);

            return ResponseEntity.status(HttpStatusCode.OK.getCode()).body(categoria);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> insertCategoria(Categoria categoria) {
        try {
            categoria.setCd_categoria(null);
            Categoria newCategoria = categoriaRepository.save(categoria);

            return ResponseEntity.status(HttpStatusCode.CREATED.getCode())
                .headers(this.getHeaders(newCategoria.getCd_categoria()))
                .body(newCategoria);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> deleteCategoria(Integer id) {
        try {
            this.findCategoria(id);

            categoriaRepository.deleteById(id);
            return ResponseEntity.status(HttpStatusCode.NO_CONTENT.getCode()).build();
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> updateCategoria(Integer id, Map<String, Object> categoria) {
        try {
            Categoria categoriaUpdated = this.findCategoria(id);

            categoria.forEach((campo, valor) -> {
                switch (campo) {
                    case "nm_categoria":
                        categoriaUpdated.setNm_categoria((String) valor);
                        break;
                }
            });

            categoriaRepository.save(categoriaUpdated);

            return ResponseEntity.status(HttpStatusCode.CREATED.getCode())
                .headers(this.getHeaders(id))
                .body(categoriaUpdated);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public Categoria findCategoria(Integer id) {
        return categoriaRepository.findById(id)
            .orElseThrow(
                () -> new ObjectNotFoundException("Categoria " + id + " não encontrada!")
            );
    }
}
