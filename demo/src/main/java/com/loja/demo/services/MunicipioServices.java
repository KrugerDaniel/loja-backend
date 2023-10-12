package com.loja.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loja.demo.exceptions.ObjectNotFoundException;
import com.loja.demo.model.Municipio;
import com.loja.demo.repository.MunicipioRepository;

@Service
public class MunicipioServices {
    
    @Autowired
    private MunicipioRepository municipioRepository;

    public Municipio insertMunicipio(Municipio municipio) {
        municipio.setCd_munipio(null);
        
        return municipioRepository.save(municipio);
    }

    public void deleteMunicipio(Integer id) {
        municipioRepository.findById(id)
            .orElseThrow(
                () -> new ObjectNotFoundException("Municipio " + id + " não encontrado")
            );
        municipioRepository.deleteById(id);
    }

    public Municipio updateMunicipio(Integer id, Municipio municipio) {
        Municipio municipioUpdated = this.findMunicipio(id);

        municipioUpdated.setNm_municipio(municipio.getNm_municipio());
        municipioUpdated.setSg_uf(municipio.getSg_uf());

        return municipioRepository.save(municipioUpdated);
    }

    public Municipio updateMunicipioName(Integer id, String name) {
        Municipio municipio = this.findMunicipio(id);

        municipio.setNm_municipio(name);

        return municipioRepository.save(municipio);
    }

    public Municipio updateMunicipioUF(Integer id, String uf) {
        Municipio municipio = this.findMunicipio(id);

        municipio.setSg_uf(uf);

        return municipioRepository.save(municipio);
    }

    private Municipio findMunicipio(Integer id) {
        return municipioRepository.findById(id)
            .orElseThrow(
                () -> new ObjectNotFoundException("Municipio " + id + " não encontrado")
            );
    }
}
