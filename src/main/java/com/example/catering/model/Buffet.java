package com.example.catering.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Buffet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String descrizione;

    @NotNull
    @Positive
    private Integer prezzoInt;

    @ManyToOne
    @JoinColumn(name = "chef_id")
    private Chef chef;

    @ManyToMany
    @JoinTable(name = "buffet_piatti",
            joinColumns = @JoinColumn(name = "buffet_id", referencedColumnName = "id"))
    private List<Piatto> piatti = new LinkedList<>();

    @OneToMany(mappedBy = "buffet")
    private List<Ordine> ordine = new LinkedList<>();

}

