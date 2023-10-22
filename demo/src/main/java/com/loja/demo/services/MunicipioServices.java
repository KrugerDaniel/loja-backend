package com.loja.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.loja.demo.exceptions.ObjectNotFoundException;
import com.loja.demo.model.Municipio;
import com.loja.demo.repository.MunicipioRepository;

import enums.HttpStatusCode;

@Service
public class MunicipioServices extends GeneralService {
    
    @Autowired
    private MunicipioRepository municipioRepository;

    public ResponseEntity<?> findAll() {
        try {
            List<Municipio> municipios = municipioRepository.findAll();

            if (municipios.isEmpty()) {
                throw new ObjectNotFoundException("Não há municípios");
            }
            return ResponseEntity.status(HttpStatusCode.OK.getCode()).body(municipios);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> findById(Integer id) {
        try {
            Municipio municipio = this.findMunicipio(id);

            return ResponseEntity.status(HttpStatusCode.OK.getCode()).body(municipio);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> findMunicipioByName(String name) {
        try {
            List<Municipio> municipios = municipioRepository.findMunicipioByName(name);

            if (municipios.isEmpty()) {
                throw new ObjectNotFoundException("Município " + name + " não encontrado!");
            }
            return ResponseEntity.status(HttpStatusCode.OK.getCode()).body(municipios);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> findMunicipioByUF(String uf) {
        try {
            List<Municipio> municipios = municipioRepository.findMunicipioByUF(uf);

            if (municipios.isEmpty()) {
                throw new ObjectNotFoundException("Unidade Federativa " + uf + " não encontrado!");
            }
            return ResponseEntity.status(HttpStatusCode.OK.getCode()).body(municipios);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> insertMunicipio(Municipio municipio) {
        try {
            municipio.setCd_munipio(null);
            
            Municipio newMunicipio = municipioRepository.save(municipio);
            return ResponseEntity.status(HttpStatusCode.CREATED.getCode())
                .headers(this.getHeaders(newMunicipio.getCd_municipio()))
                .body(newMunicipio);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> deleteMunicipio(Integer id) {
        try {
            this.findMunicipio(id);
            
            municipioRepository.deleteById(id);
            return ResponseEntity.status(HttpStatusCode.NO_CONTENT.getCode()).build();
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> updateMunicipio(Integer id, Municipio municipio) {
        try {
            Municipio municipioUpdated = this.findMunicipio(id);

            municipioUpdated.setNm_municipio(municipio.getNm_municipio());
            municipioUpdated.setSg_uf(municipio.getSg_uf());

            municipioUpdated = municipioRepository.save(municipioUpdated);
            return ResponseEntity.status(HttpStatusCode.CREATED.getCode())
                .headers(this.getHeaders(id))
                .body(municipioUpdated);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> updateMunicipioName(Integer id, String name) {
        try {
            Municipio municipio = this.findMunicipio(id);

            municipio.setNm_municipio(name);

            municipio = municipioRepository.save(municipio);
            return ResponseEntity.status(HttpStatusCode.CREATED.getCode())
                .headers(this.getHeaders(id))
                .body(municipio);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    public ResponseEntity<?> updateMunicipioUF(Integer id, String uf) {
        try {
            Municipio municipio = this.findMunicipio(id);

            municipio.setSg_uf(uf);

            municipio = municipioRepository.save(municipio);
            return ResponseEntity.status(HttpStatusCode.CREATED.getCode())
                .headers(this.getHeaders(id))
                .body(municipio);
        } catch (ObjectNotFoundException notFound) {
            return ResponseEntity.status(HttpStatusCode.NOT_FOUND.getCode())
                .body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.BAD_REQUEST.getCode()).build();
        }
    }

    private Municipio findMunicipio(Integer id) {
        return municipioRepository.findById(id)
            .orElseThrow(
                () -> new ObjectNotFoundException("Municipio " + id + " não encontrado")
            );
    }
}
