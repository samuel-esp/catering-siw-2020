package com.example.catering.service;

import com.example.catering.model.Chef;
import com.example.catering.repository.BuffetRepository;
import com.example.catering.repository.ChefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class ChefService {

    @Autowired
    private ChefRepository chefRepository;

    public void createChef(String nome, String cognome, String nazionalita){
        Chef c = new Chef();
        c.setNome(nome);
        c.setCognome(cognome);
        c.setNazionalita(nazionalita);
        chefRepository.save(c);
    }

    public List<Chef> getAllChefs(){
        List<Chef> chefList = chefRepository.findAll();
        return chefList;
    }

    public Chef getChefById(Long id){
        Chef c = chefRepository.findById(id).get();
        return c;
    }

    public List<Chef> getChefByName(String nome){
        List<Chef> chefList = chefRepository.findChefByNome(nome);
        return chefList;
    }

    public List<Chef> getChefByCognome(String cognome){
        List<Chef> chefList = chefRepository.findChefByCognome(cognome);
        return chefList;
    }

    public List<Chef> getChefByNazionalita(String nazionalita){
        List<Chef> chefList = chefRepository.findChefByNazionalita(nazionalita);
        return chefList;
    }

    public void deleteChefById(Long id){
        chefRepository.deleteById(id);
    }

}
