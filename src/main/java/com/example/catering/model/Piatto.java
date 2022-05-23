package com.example.catering.model;

import com.example.catering.model.enumeration.TipologiaPiatto;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Piatto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    @ManyToMany(mappedBy = "piatti")
    private Set<Buffet> buffet = new java.util.LinkedHashSet<>();

    @OneToMany
    @JoinColumn(name = "piatto_id")
    private Set<Ingrediente> ingredienti = new java.util.LinkedHashSet<>();

    @Enumerated(EnumType.STRING)
    private TipologiaPiatto tipologiaPiatto;

}
