package com.loja.demo.services.municipio;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.loja.demo.enums.HttpStatusCode;
import com.loja.demo.exceptions.ObjectNotFoundException;
import com.loja.demo.model.entity.municipio.Municipio;
import com.loja.demo.repository.municipio.MunicipioRepository;
import com.loja.demo.services.GeneralService;

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

    public ResponseEntity<?> updateMunicipio(Integer id, Map<String, Object> municipio) {
        try {
            final Municipio municipioUpdated = this.findMunicipio(id);

            municipio.forEach((campo, valor) -> {
                switch (campo) {
                    case "nm_municipio":
                        municipioUpdated.setNm_municipio((String) valor);
                        break;
                    case "sg_uf":
                        municipioUpdated.setSg_uf((String) valor);
                        break;
                }
            });

            municipioRepository.save(municipioUpdated);
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

    private Municipio findMunicipio(Integer id) {
        return municipioRepository.findById(id)
            .orElseThrow(
                () -> new ObjectNotFoundException("Municipio " + id + " não encontrado")
            );
    }
}
