package com.torneotenismesa.gestiontorneos.enums;

/**
 * Define los tipos de torneo que se pueden organizar.
 * Ej: INDIVIDUAL, DOBLES, EQUIPOS.
 */
public enum TipoTorneo {
    INDIVIDUAL("Individual"),
    DOBLES("Dobles"),
    EQUIPOS("Por Equipos");

    private final String descripcion;

    TipoTorneo(String descripcion) {
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