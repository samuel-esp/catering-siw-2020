package com.example.catering.service;

import com.example.catering.model.Ordine;
import com.example.catering.model.Utente;
import com.example.catering.repository.OrdineRepository;
import com.example.catering.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdineService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private OrdineRepository ordineRepository;

    public Ordine getOrdineById(Long id){
        return ordineRepository.findById(id).get();
    }

    public List<Ordine> getOrdineByUserId(Long id){
        Utente utente = utenteRepository.findById(id).get();
        return utente.getOrdiniEffettuati();
    }


}
