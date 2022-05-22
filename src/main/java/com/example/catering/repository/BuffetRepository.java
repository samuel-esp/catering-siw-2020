package com.example.catering.repository;

import com.example.catering.model.Buffet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuffetRepository extends JpaRepository<Buffet, Long> {

    public List<Buffet> findBuffetByNome(String nome);

}
