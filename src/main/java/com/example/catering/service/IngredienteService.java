package com.example.catering.service;

import com.example.catering.model.Ingrediente;
import com.example.catering.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

}
