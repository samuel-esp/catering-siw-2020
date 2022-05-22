package com.example.catering.model;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString @EqualsAndHashCode
public class Buffet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String descrizione;

    private Integer prezzoInt;

    @ManyToOne
    @JoinColumn(name = "chef_id")
    private Chef chef;

    @ManyToMany
    @JoinTable(name = "buffet_piattoes",
            joinColumns = @JoinColumn(name = "buffet_id", referencedColumnName = "piattoes_id"))
    private Set<Piatto> piatti = new LinkedHashSet<>();

}

