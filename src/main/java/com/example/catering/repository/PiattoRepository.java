package com.example.catering.repository;

import com.example.catering.model.Piatto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PiattoRepository extends JpaRepository<Piatto, Long> {


}
