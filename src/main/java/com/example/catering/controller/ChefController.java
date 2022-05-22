package com.example.catering.controller;

import com.example.catering.model.Chef;
import com.example.catering.model.Ingrediente;
import com.example.catering.service.ChefService;
import com.example.catering.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChefController {

    @Autowired
    private ChefService chefService;

    @GetMapping("/admin/chefForm")
    public String getChefForm(Model model){
        model.addAttribute("chef", new Chef());
        return "chefForm";
    }

    @PostMapping("/admin/chefForm")
    public String addIngrediente(@ModelAttribute("chef") Chef chef, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "errorPage";
        }

        chefService.createChef(chef);

        return "redirect:/adminDashboard";
    }


}
