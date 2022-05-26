package com.example.catering.repository;

import com.example.catering.model.Credentials;
import com.example.catering.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialsRepository extends JpaRepository<Credentials, Long> {

    Optional<Credentials> findByEmail(String email);

}
