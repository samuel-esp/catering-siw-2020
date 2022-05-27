package com.example.catering.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Utente {

    public static final String DEFAULT_ROLE = "DEFAULT";
    public static final String ADMIN_ROLE = "ADMIN";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String cognome;

    @Column(unique = true)
    @NotNull @Email
    private String email;

    @NotNull
    @Size(min=6)
    private String password;

    @NotNull
    private String role;

    /*@OneToMany(mappedBy = "utente")
    private Set<Buffet> buffetPrenotati = new java.util.LinkedHashSet<>();*/

    @OneToMany(mappedBy = "utente")
    private List<Ordine> ordiniEffettuati = new java.util.LinkedList<>();


}
