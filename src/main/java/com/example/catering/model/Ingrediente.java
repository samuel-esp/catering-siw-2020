package com.example.catering.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString @EqualsAndHashCode
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String nome;

    private String origine;

    private String descrizione;

}
