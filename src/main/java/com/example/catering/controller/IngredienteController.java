package com.example.catering.controller;

import com.example.catering.model.Ingrediente;
import com.example.catering.repository.IngredienteRepository;
import com.example.catering.service.IngredienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class IngredienteController {

    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping("/admin/ingredienteForm")
    public String getIngredienteForm(Model model){
        model.addAttribute("ingrediente", new Ingrediente());
        return "ingredienteForm";
    }

    @PostMapping("/admin/ingredienteForm")
    public String addIngrediente(@ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "errorPage";
        }

        ingredienteService.createIngrediente(ingrediente);
        log.info("Ingrediente Created");

        return "redirect:/adminDashboard";
    }

}
