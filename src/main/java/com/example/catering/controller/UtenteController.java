package com.example.catering.controller;

import com.example.catering.model.Buffet;
import com.example.catering.model.Utente;
import com.example.catering.model.dto.UtenteEditDto;
import com.example.catering.service.UtenteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Set;

@Controller
@Slf4j
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

    @GetMapping("/profilo")
    public String getUserInfo(Model model){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Utente utente = utenteService.getUserByEmail(userDetails.getUsername());
        model.addAttribute("utente", utente);

        return "userCard";
    }

    @GetMapping("/editUser")
    public String getEditUserPage(Model model){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Utente utente = utenteService.getUserByEmail(userDetails.getUsername());
        model.addAttribute("utente", utente);
        model.addAttribute("utenteEdit", new UtenteEditDto());

        return "editUser";
    }

    @PostMapping("/editUser")
    public String editUser(@Valid @ModelAttribute UtenteEditDto utenteEdit, BindingResult bindingResult,
                           RedirectAttributes redirAttrs){

        if(bindingResult.hasErrors()){
            log.info(bindingResult.toString());
            redirAttrs.addFlashAttribute("error", "submit the form again, make sure your phone number has exactly 10 numbers");
            return "redirect:/editUser";
        }

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Utente utente = utenteService.getUserByEmail(userDetails.getUsername());
        utenteService.editUtente(utenteEdit, utente);
        return "redirect:/profilo";
    }

}
