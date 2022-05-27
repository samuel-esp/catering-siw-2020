package com.example.catering.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Chef {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String cognome;

    @NotNull
    private String nazionalita;

    @OneToMany(mappedBy = "chef")
    private Set<Buffet> buffet = new java.util.LinkedHashSet<>();


}
