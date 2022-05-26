package com.example.catering.service;

import com.example.catering.model.Buffet;
import com.example.catering.model.Utente;
import com.example.catering.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    public Utente getUserById(Long id){
        return utenteRepository.findById(id).get();
    }

    public List<Utente> getAllUsers(){
        return utenteRepository.findAll();
    }

    public void saveUser(Utente utente){
        utenteRepository.save(utente);
    }

    public Set<Buffet> getOrdersByUser(Long id){
        return utenteRepository.findById(id).get().getBuffetPrenotati();
    }

}
