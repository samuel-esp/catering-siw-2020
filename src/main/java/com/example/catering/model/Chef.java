package com.example.catering.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@ToString @EqualsAndHashCode
public class Chef {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String cognome;

    private String nazionalita;

    @OneToMany(mappedBy = "chef")
    private Set<Buffet> buffet = new java.util.LinkedHashSet<>();


}
