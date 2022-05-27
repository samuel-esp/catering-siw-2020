package com.example.catering.controller;

import com.example.catering.model.Buffet;
import com.example.catering.model.Utente;
import com.example.catering.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@Controller
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping("/ordini")
    public String getAllOrdersByUser(Model model){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Utente utente = utenteService.getUserByEmail(userDetails.getUsername());
        model.addAttribute("ordini", utente.getOrdiniEffettuati());

        return "allOrdersUser";
    }

}
