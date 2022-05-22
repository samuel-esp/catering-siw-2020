package com.example.catering.repository;

import com.example.catering.model.Chef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChefRepository extends JpaRepository<Chef, Long> {

    public List<Chef> findChefByNome(String nome);

    public List<Chef> findChefByCognome(String cognome);

    public List<Chef> findChefByNazionalita(String nazionalita);

}
