package com.loja.demo.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.loja.demo.enums.HttpStatusCode;
import com.loja.demo.exceptions.ObjectNotFoundException;
import com.loja.demo.model.Categoria;
import com.loja.demo.model.Produto;
import com.loja.demo.repository.ProdutoRepository;

@Service
public class ProdutoServices extends GeneralService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaServices categoriaServices;

    public ResponseEntity<?> findAll() {
        try {
            List<Produto> produtos = produtoRepository.findAll();

            if (produtos.isEmpty()) {
                throw new ObjectNotFoundException("Não há produtos");
            }
            return ResponseEntity.status(HttpStatusCode.OK.getCode()).body(produtos);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> findById(Integer id) {
        try {
            Produto produto = this.findPedido(id);

            return ResponseEntity.status(HttpStatusCode.OK.getCode()).body(produto);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> findByName(String name) {
        try {
            List<Produto> produtos = produtoRepository.findByName(name);

            if (produtos.isEmpty()) {
                throw new ObjectNotFoundException("Produto " + name + " não encontrado");
            }
            return ResponseEntity.status(HttpStatusCode.OK.getCode()).body(produtos);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> insertProduto(Map<String, Object> produto) {
        try {
            Produto newProduto = new Produto();
            this.updatingProduto(newProduto, produto);

            produtoRepository.save(newProduto);

            return ResponseEntity.status(HttpStatusCode.CREATED.getCode())
                .headers(this.getHeaders(newProduto.getCd_produto()))
                .body(newProduto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).body(e.getMessage());
        }
    }

    public ResponseEntity<?> deleteProduto(Integer id) {
        try {
            this.findPedido(id);

            produtoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatusCode.NO_CONTENT.getCode()).build();
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> updateProduto(Integer id, Map<String, Object> produtoUpdated) {
        try {
            Produto produto = this.findPedido(id);
            this.updatingProduto(produto, produtoUpdated);

            return ResponseEntity.status(HttpStatusCode.CREATED.getCode())
                .headers(this.getHeaders(id))
                .body(produto);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).body(e.getMessage());
        }
    }

    private void updatingProduto(Produto produto, Map<String, Object> newValues) {
        newValues.forEach((campo, valor) -> {
            switch (campo) {
                case "nm_produto":
                    produto.setNm_produto((String) valor);
                    break;
                case "qt_estoque":
                    produto.setQt_estoque((Integer) valor);
                    break;
                case "vl_produto":
                    produto.setVl_produto((Double) valor);
                    break;
                case "categoria":
                    Categoria categoria = categoriaServices.findCategoria((Integer) valor);
                    produto.setCategoria(categoria);
                    break;
            }
        });
    }

    public Produto findPedido(Integer id) {
        return produtoRepository.findById(id)
            .orElseThrow(
                () -> new ObjectNotFoundException("Produto " + id + " não encontrado!")
            );
    }
}
