package com.example.catering.service;

import com.example.catering.model.Buffet;
import com.example.catering.model.Piatto;
import com.example.catering.repository.BuffetRepository;
import com.example.catering.repository.PiattoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PiattoService {

    @Autowired
    private PiattoRepository piattoRepository;

    public void createPiatto(String nome){
        Piatto p = new Piatto();
        p.setNome(nome);
        piattoRepository.save(p);
    }

    public List<Piatto> getAllBuffets(){
        List<Piatto> piattoList = piattoRepository.findAll();
        return piattoList;
    }

    public Piatto getPiattoById(Long id){
        Piatto p = piattoRepository.findById(id).get();
        return p;
    }


    public void deletePiattoById(Long id){
        piattoRepository.deleteById(id);
    }


}
