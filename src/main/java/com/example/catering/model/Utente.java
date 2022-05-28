package com.example.catering.model;

import lombok.*;
import org.hibernate.validator.constraints.Range;

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
    @Size(min=6, message = "la password deve avere almeno 6 caratteri")
    private String password;

    @NotNull @Size(min=10, max =10, message = "Il numero di telefono deve avere esattamente 10 cifre")
    private String cellulare;

    @NotNull
    private String indirizzo;

    @NotNull
    private String role;

    /*@OneToMany(mappedBy = "utente")
    private Set<Buffet> buffetPrenotati = new java.util.LinkedHashSet<>();*/

    @OneToMany(mappedBy = "utente")
    private List<Ordine> ordiniEffettuati = new java.util.LinkedList<>();


}
