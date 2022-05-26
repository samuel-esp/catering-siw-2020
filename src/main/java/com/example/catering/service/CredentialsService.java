package com.example.catering.service;

import com.example.catering.model.Credentials;
import com.example.catering.repository.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class CredentialsService {

    //@Autowired
    //private PasswordEncoder passwordEncoder;

    @Autowired
    private CredentialsRepository credentialsRepository;


    public Credentials getCredentials(Long id) {
        Optional<Credentials> result = credentialsRepository.findById(id);
        return result.orElse(null);
    }

    public Credentials getCredentials(String email) {
        Optional<Credentials> result = credentialsRepository.findByEmail(email);
        return result.orElse(null);
    }

    public Credentials saveCredentials(Credentials credentials) {
        credentials.setRole(Credentials.ADMIN_ROLE);
        //credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
        return this.credentialsRepository.save(credentials);
    }


}
