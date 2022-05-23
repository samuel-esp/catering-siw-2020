package com.example.catering.controller;

import com.example.catering.model.Buffet;
import com.example.catering.model.Chef;
import com.example.catering.model.Piatto;
import com.example.catering.model.enumeration.TipologiaPiatto;
import com.example.catering.service.BuffetService;
import com.example.catering.service.ChefService;
import com.example.catering.service.PiattoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BuffetController {

    @Autowired
    private BuffetService buffetService;

    @Autowired
    private PiattoService piattoService;

    @Autowired
    private ChefService chefService;

    @GetMapping("/sideBar")
    public String getSideBar(Model model){
        model.addAttribute("buffet", new Buffet());
        model.addAttribute("chefList", chefService.getAllChefs());
        model.addAttribute("primo11", piattoService.getPiattiByTipologia(TipologiaPiatto.PRIMO));
        model.addAttribute("primo12", piattoService.getPiattiByTipologia(TipologiaPiatto.PRIMO));
        model.addAttribute("secondo11", piattoService.getPiattiByTipologia(TipologiaPiatto.SECONDO));
        model.addAttribute("secondo12", piattoService.getPiattiByTipologia(TipologiaPiatto.SECONDO));
        model.addAttribute("dolce11", piattoService.getPiattiByTipologia(TipologiaPiatto.DOLCE));
        model.addAttribute("dolce12", piattoService.getPiattiByTipologia(TipologiaPiatto.DOLCE));
        return "sideBar";
    }

    @GetMapping("/admin/allBuffet")
    public String getAllBuffet(Model model){
        model.addAttribute("buffetList", buffetService.getAllBuffets());
        return "allBuffet";
    }

    @GetMapping("/admin/buffetForm")
    public String getBuffetForm(Model model){
        model.addAttribute("buffet", new Buffet());
        model.addAttribute("chefList", chefService.getAllChefs());
        model.addAttribute("primo11", piattoService.getPiattiByTipologia(TipologiaPiatto.PRIMO));
        model.addAttribute("primo12", piattoService.getPiattiByTipologia(TipologiaPiatto.PRIMO));
        model.addAttribute("secondo11", piattoService.getPiattiByTipologia(TipologiaPiatto.SECONDO));
        model.addAttribute("secondo12", piattoService.getPiattiByTipologia(TipologiaPiatto.SECONDO));
        model.addAttribute("dolce11", piattoService.getPiattiByTipologia(TipologiaPiatto.DOLCE));
        model.addAttribute("dolce12", piattoService.getPiattiByTipologia(TipologiaPiatto.DOLCE));
        return "buffetForm";
    }

    @PostMapping("/admin/buffetForm")
    public String addIngrediente(@ModelAttribute("buffet") Buffet buffet, @RequestParam("primo11") Piatto primo11,
                                 @RequestParam("primo12") Piatto primo12, @RequestParam("secondo11") Piatto secondo11,
                                 @RequestParam("secondo12") Piatto secondo12, @RequestParam("dolce11") Piatto dolce11,
                                 @RequestParam("dolce12") Piatto dolce12, @RequestParam("chefSelected") Chef chef,
                                         BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "errorPage";
        }

        buffet.setChef(chef);
        buffet.getPiatti().add(primo11);
        buffet.getPiatti().add(primo12);
        buffet.getPiatti().add(secondo11);
        buffet.getPiatti().add(secondo12);
        buffet.getPiatti().add(dolce11);
        buffet.getPiatti().add(dolce12);

        buffetService.createBuffet(buffet);

        return "redirect:/adminDashboard";
    }

}
