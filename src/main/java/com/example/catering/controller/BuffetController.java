package com.example.catering.controller;

import com.example.catering.model.Buffet;
import com.example.catering.model.Chef;
import com.example.catering.model.Ingrediente;
import com.example.catering.model.Piatto;
import com.example.catering.model.enumeration.TipologiaPiatto;
import com.example.catering.service.BuffetService;
import com.example.catering.service.ChefService;
import com.example.catering.service.PiattoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Controller
public class BuffetController {

    @Autowired
    private BuffetService buffetService;

    @Autowired
    private PiattoService piattoService;

    @Autowired
    private ChefService chefService;

    @GetMapping("/admin/allBuffet")
    public String getAllBuffet(Model model){
        model.addAttribute("buffetList", buffetService.getAllBuffets());
        return "allBuffet";
    }

    @GetMapping("/allBuffet")
    public String getAllBuffetUser(Model model){
        model.addAttribute("buffetList", buffetService.getAllBuffets());
        return "allBuffetUser";
    }

    @GetMapping("/allBuffet/chef/{id}")
    public String getAllBuffetByChef(@PathVariable("id") String id, Model model){
        Chef chef = chefService.getChefById(Long.parseLong(id));
        Set<Buffet> buffetSet = chef.getBuffet();
        List<Buffet> buffetList = new LinkedList<>();
        buffetList.addAll(buffetSet);
        model.addAttribute("buffetList", buffetList);
        return "allBuffetUser";
    }

    @GetMapping("/buffet/{id}")
    public String getBuffetCard(@PathVariable("id") String id, Model model){
        Buffet buffet = buffetService.getBuffetById(Long.parseLong(id));
        Set<Piatto> piattiSet = buffet.getPiatti();

        List<Piatto> piattiLista = new LinkedList<>();
        piattiLista.addAll(piattiSet);

        List<Piatto> primi = new LinkedList<>();
        List<Piatto> secondi = new LinkedList<>();
        List<Piatto> dolci = new LinkedList<>();

        for(Piatto piatto: piattiLista){
            int i = 0;
            int j = 0;
            int t = 0;
            if(piatto.getTipologiaPiatto() == TipologiaPiatto.PRIMO ){
                primi.add(piatto);
                List<Ingrediente> listaIngredienti = new LinkedList<>();
                listaIngredienti.addAll(piatto.getIngredienti());
                model.addAttribute("primoIngredienti" + i, listaIngredienti);
                i = i+1;
            }
            if(piatto.getTipologiaPiatto() == TipologiaPiatto.SECONDO){
                secondi.add(piatto);
                List<Ingrediente> listaIngredienti = new LinkedList<>();
                listaIngredienti.addAll(piatto.getIngredienti());
                model.addAttribute("secondoIngredienti" + j, listaIngredienti);
                j = j+1;
            }
            if(piatto.getTipologiaPiatto() == TipologiaPiatto.DOLCE){
                dolci.add(piatto);
                List<Ingrediente> listaIngredienti = new LinkedList<>();
                listaIngredienti.addAll(piatto.getIngredienti());
                model.addAttribute("dolceIngredienti" + t, listaIngredienti);
                t = t+1;
            }
        }

        model.addAttribute("buffet", buffet);
        model.addAttribute("primi", primi);
        model.addAttribute("secondi", secondi);
        model.addAttribute("dolci", dolci);

        return "buffetCard";
    }

    @GetMapping("/chef/{id}/allBuffetUser")
    public String getAllBuffetByChefUser(@PathVariable("id") String id, Model model){
        model.addAttribute("buffetList", buffetService.getAllBuffets());
        return "allBuffetByChef";
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

    @GetMapping("/admin/editBuffetForm/{id}")
    public String editBuffetId(@PathVariable("id") String id, Model model){

        model.addAttribute("buffet", buffetService.getBuffetById(Long.parseLong(id)));
        model.addAttribute("chefList", chefService.getAllChefs());
        model.addAttribute("primo11", piattoService.getPiattiByTipologia(TipologiaPiatto.PRIMO));
        model.addAttribute("primo12", piattoService.getPiattiByTipologia(TipologiaPiatto.PRIMO));
        model.addAttribute("secondo11", piattoService.getPiattiByTipologia(TipologiaPiatto.SECONDO));
        model.addAttribute("secondo12", piattoService.getPiattiByTipologia(TipologiaPiatto.SECONDO));
        model.addAttribute("dolce11", piattoService.getPiattiByTipologia(TipologiaPiatto.DOLCE));
        model.addAttribute("dolce12", piattoService.getPiattiByTipologia(TipologiaPiatto.DOLCE));

        return "editBuffetForm";
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

        return "redirect::/admin/allBuffet";
    }

    @PostMapping("/admin/editBuffetForm/{id}")
    public String editBuffetIdPost(@ModelAttribute("buffet") Buffet buffet, @PathVariable("id") String id, @RequestParam("primo11") Piatto primo11,
                                   @RequestParam("primo12") Piatto primo12, @RequestParam("secondo11") Piatto secondo11,
                                   @RequestParam("secondo12") Piatto secondo12, @RequestParam("dolce11") Piatto dolce11,
                                   @RequestParam("dolce12") Piatto dolce12, @RequestParam("chefSelected") Chef chef,
                                   BindingResult bindingResult){

        buffet.setChef(chef);
        buffet.getPiatti().clear();
        buffet.getPiatti().add(primo11);
        buffet.getPiatti().add(primo12);
        buffet.getPiatti().add(secondo11);
        buffet.getPiatti().add(secondo12);
        buffet.getPiatti().add(dolce11);
        buffet.getPiatti().add(dolce12);


        buffetService.updateBuffet(buffet);

        return "redirect:/admin/allBuffet";
    }

}
