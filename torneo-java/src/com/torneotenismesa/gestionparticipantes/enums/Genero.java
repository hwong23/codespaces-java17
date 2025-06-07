package com.torneotenismesa.gestionparticipantes.enums;

/**
 * Define los géneros para jugadores y categorías.
 */
public enum Genero {
    MASCULINO("Masculino"),
    FEMENINO("Femenino"),
    MIXTO("Mixto"), // Para categorías o equipos dobles mixtos
    OTRO("Otro");   // Por inclusividad

    private final String descripcion;

    Genero(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}