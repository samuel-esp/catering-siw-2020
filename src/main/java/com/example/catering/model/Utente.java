package com.example.catering.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String cognome;

    @OneToMany
    private Set<Buffet> buffetPrenotati = new java.util.LinkedHashSet<>();

    @OneToMany
    @JoinColumn(name = "utente_id")
    private List<Ordine> ordiniEffettuati = new java.util.LinkedList<>();

}
