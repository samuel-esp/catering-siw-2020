package com.example.catering.model.enumeration;


import lombok.AllArgsConstructor;
import lombok.Getter;

        ;
@Getter @AllArgsConstructor
public enum TipologiaPiatto {

    PRIMO("Primo"),
    SECONDO("Secondo"),
    DOLCE("Dolce");

    private final String displayName;


}
