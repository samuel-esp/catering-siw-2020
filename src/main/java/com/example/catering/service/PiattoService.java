package com.example.catering.service;

import com.example.catering.model.Buffet;
import com.example.catering.model.Piatto;
import com.example.catering.model.enumeration.TipologiaPiatto;
import com.example.catering.repository.BuffetRepository;
import com.example.catering.repository.PiattoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class PiattoService {

    @Autowired
    private PiattoRepository piattoRepository;

    public void createPiatto(Piatto piatto){
        piattoRepository.save(piatto);
        log.info(piatto.toString());
    }

    public List<Piatto> getAllBuffets(){
        List<Piatto> piattoList = piattoRepository.findAll();
        return piattoList;
    }

    public Piatto getPiattoById(Long id){
        Piatto p = piattoRepository.findById(id).get();
        return p;
    }

    public List<Piatto> getPiattiByTipologia(TipologiaPiatto tipologiaPiatto){
        return piattoRepository.findAllByTipologiaPiatto(tipologiaPiatto);
    }


    public void deletePiattoById(Long id){
        piattoRepository.deleteById(id);
    }


}
