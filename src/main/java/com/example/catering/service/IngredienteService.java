package com.example.catering.service;

import com.example.catering.model.Ingrediente;
import com.example.catering.model.Piatto;
import com.example.catering.repository.IngredienteRepository;
import com.example.catering.repository.PiattoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    public void createIngrediente(String nome){
        Ingrediente i = new Ingrediente();
        i.setNome(nome);
        ingredienteRepository.save(i);
    }

    public List<Ingrediente> getAllIngredients(){
        List<Ingrediente> ingredienteList = ingredienteRepository.findAll();
        return ingredienteList;
    }

    public Ingrediente getIngredienteById(Long id){
        Ingrediente i = ingredienteRepository.findById(id).get();
        return i;
    }

    public void deleteIngredienteById(Long id){
        ingredienteRepository.deleteById(id);
    }

}
