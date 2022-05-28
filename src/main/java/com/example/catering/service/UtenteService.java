package com.example.catering.service;

import com.example.catering.model.Buffet;
import com.example.catering.model.Ordine;
import com.example.catering.model.Utente;
import com.example.catering.model.dto.UtenteEditDto;
import com.example.catering.repository.BuffetRepository;
import com.example.catering.repository.OrdineRepository;
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

    @Autowired
    private OrdineRepository ordineRepository;

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


    public void prenotaBuffet(Long id){

        Buffet buffet = buffetRepository.findById(id).get();

        if(buffet!=null){
            log.info(buffet.getNome());
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Utente utente = this.getUserByEmail(userDetails.getUsername());
            log.info(utente.getEmail());
            Ordine ordine = new Ordine();
            ordine.setBuffet(buffet);
            ordine.setUtente(utente);
            ordineRepository.save(ordine);
        }
    }

    public void editUtente(UtenteEditDto utenteEdit, Utente utente) {

        utente.setIndirizzo(utenteEdit.getIndirizzo());
        utente.setCellulare(utenteEdit.getCellulare());
        utente.setNome(utenteEdit.getNome());
        utente.setCognome(utenteEdit.getCognome());
        utenteRepository.save(utente);

    }

    public List<Utente> allUsers(){
        return utenteRepository.findAll();
    }
}
