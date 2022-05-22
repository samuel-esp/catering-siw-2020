package com.example.catering.service;

import com.example.catering.model.Buffet;
import com.example.catering.model.Chef;
import com.example.catering.repository.BuffetRepository;
import com.example.catering.repository.ChefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuffetService {

    @Autowired
    private BuffetRepository buffetRepository;

    public void createBuffer(String nome, String descrizione, Integer prezzo){
        Buffet b = new Buffet();
        b.setNome(nome);
        b.setDescrizione(descrizione);
        b.setPrezzoInt(prezzo);
        buffetRepository.save(b);
    }

    public List<Buffet> getAllBuffets(){
        List<Buffet> buffetList = buffetRepository.findAll();
        return buffetList;
    }

    public Buffet getBuffetById(Long id){
        Buffet c = buffetRepository.findById(id).get();
        return c;
    }

    public List<Buffet> getChefByName(String nome){
        List<Buffet> buffetList = buffetRepository.findBuffetByNome(nome);
        return buffetList;
    }

    public void deleteBuffetById(Long id){
        buffetRepository.deleteById(id);
    }

}
