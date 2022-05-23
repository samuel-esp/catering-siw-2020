package com.example.catering.controller;

import com.example.catering.model.Ingrediente;
import com.example.catering.model.Piatto;
import com.example.catering.model.enumeration.TipologiaPiatto;
import com.example.catering.service.IngredienteService;
import com.example.catering.service.PiattoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class PiattoController {

    @Autowired
    private PiattoService piattoService;

    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping("/admin/piattoForm")
    public String getIngredienteForm(Model model){
        model.addAttribute("piatto", new Piatto());
        model.addAttribute("ingredienti1", ingredienteService.getAllIngredients());
        model.addAttribute("ingredienti2", ingredienteService.getAllIngredients());
        model.addAttribute("ingredienti3", ingredienteService.getAllIngredients());
        model.addAttribute("ingredienti4", ingredienteService.getAllIngredients());
        model.addAttribute("ingredienti5", ingredienteService.getAllIngredients());

        return "piattoForm";
    }

    @PostMapping("/admin/piattoForm")
    public String addIngrediente(@ModelAttribute("piatto") Piatto piatto, @RequestParam("ingrediente1") String id1,
                                 @RequestParam("ingrediente2") String id2, @RequestParam("ingrediente3") String id3,
                                 @RequestParam("ingrediente4") String id4, @RequestParam("ingrediente5") String id5,
                                 @RequestParam ("tipologia") TipologiaPiatto tipologia, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "errorPage";
        }

        log.info(id1);
        log.info(id2);
        log.info(id3);

        piatto.setTipologiaPiatto(tipologia);

        if(!id1.equals("Non Selezionato")){
            piatto.getIngredienti().add(ingredienteService.getIngredienteById(Long.parseLong(id1)));
        }

        if(!id2.equals("Non Selezionato")){
            piatto.getIngredienti().add(ingredienteService.getIngredienteById(Long.parseLong(id2)));
        }

        if(!id3.equals("Non Selezionato")){
            piatto.getIngredienti().add(ingredienteService.getIngredienteById(Long.parseLong(id3)));
        }

        if(!id4.equals("Non Selezionato")){
            piatto.getIngredienti().add(ingredienteService.getIngredienteById(Long.parseLong(id4)));
        }

        if(!id5.equals("Non Selezionato")){
            piatto.getIngredienti().add(ingredienteService.getIngredienteById(Long.parseLong(id5)));
        }

        piattoService.createPiatto(piatto);

        return "redirect:/admin/allBuffet";
    }

}
