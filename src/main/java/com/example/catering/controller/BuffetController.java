package com.example.catering.controller;

import com.example.catering.model.Buffet;
import com.example.catering.model.Chef;
import com.example.catering.service.BuffetService;
import com.example.catering.service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BuffetController {

    @Autowired
    private BuffetService buffetService;

    @GetMapping("/sideBar")
    public String getSideBar(){
        return "index";
    }

    @GetMapping("/admin/buffetForm")
    public String getChefForm(Model model){
        model.addAttribute("buffet", new Buffet());
        return "buffetForm";
    }

    @PostMapping("/admin/buffetForm")
    public String addIngrediente(@ModelAttribute("buffet") Buffet buffet, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "errorPage";
        }

        //chefService.createChef(chef);

        return "redirect:/adminDashboard";
    }

}
