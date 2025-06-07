package com.torneotenismesa.gestionparticipantes.enums;

/**
 * Define los posibles estados de una inscripción a un torneo.
 */
public enum EstadoInscripcion {
    PENDIENTE("Pendiente"),
    CONFIRMADA("Confirmada"),
    RECHAZADA("Rechazada"),
    LISTA_ESPERA("En Lista de Espera"); // Añadido por completitud

    private final String descripcion;

    EstadoInscripcion(String descripcion) {
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