package com.example.catering.model;

import com.example.catering.model.enumeration.TipologiaPiatto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Piatto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String nome;

    @ManyToMany(mappedBy = "piatti")
    private List<Buffet> buffet = new java.util.LinkedList<>();

    @OneToMany
    @JoinColumn(name = "piatto_id")
    private List<Ingrediente> ingredienti = new java.util.LinkedList<>();

    @Enumerated(EnumType.STRING)
    private TipologiaPiatto tipologiaPiatto;

}
