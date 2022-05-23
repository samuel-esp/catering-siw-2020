package com.example.catering.repository;

import com.example.catering.model.Piatto;
import com.example.catering.model.enumeration.TipologiaPiatto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PiattoRepository extends JpaRepository<Piatto, Long> {

    List<Piatto> findAllByTipologiaPiatto(TipologiaPiatto tipologia);

}
