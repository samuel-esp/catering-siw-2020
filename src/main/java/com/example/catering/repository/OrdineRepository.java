package com.example.catering.repository;

import com.example.catering.model.Ordine;
import com.example.catering.model.Utente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Long> {

    List<Ordine> findByUtente(Utente utente);

}
