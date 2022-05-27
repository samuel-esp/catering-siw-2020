package com.example.catering.service;

import com.example.catering.model.Buffet;
import com.example.catering.model.Utente;
import com.example.catering.repository.BuffetRepository;
import com.example.catering.repository.UtenteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class UtenteService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private BuffetRepository buffetRepository;

    public Utente getUserById(Long id){
        return utenteRepository.findById(id).get();
    }

    public Utente getUserByEmail(String email){
        return utenteRepository.findByEmail(email);
    }

    public List<Utente> getAllUsers(){
        return utenteRepository.findAll();
    }

    public void saveUser(Utente utente){
        String standardPassword = utente.getPassword();
        String encodedPassword = passwordEncoder.encode(standardPassword);
        utente.setPassword(encodedPassword);
        utenteRepository.save(utente);
    }

    public Set<Buffet> getOrdersByUser(Long id){
        return utenteRepository.findById(id).get().getBuffetPrenotati();
    }

    public void prenotaBuffet(Long id){
        Buffet buffet = buffetRepository.findById(id).get();
        if(buffet!=null){
            log.info("ARRIVED");
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Utente utente = this.getUserByEmail(userDetails.getUsername());
            log.info(utente.getEmail());
            utente.getBuffetPrenotati().add(buffet);
        }
    }

}
