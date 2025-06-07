package com.torneotenismesa.gestiontorneos.enums;

/**
 * Define los posibles estados de un torneo a lo largo de su ciclo de vida.
 * Ej: PLANIFICADO, INSCRIPCION_ABIERTA, EN_CURSO, FINALIZADO, CANCELADO.
 */
public enum EstadoTorneo {
    PLANIFICADO("Planificado"),
    INSCRIPCION_ABIERTA("Inscripción Abierta"),
    INSCRIPCION_CERRADA("Inscripción Cerrada"),
    EN_CURSO("En Curso"),
    FINALIZADO("Finalizado"),
    CANCELADO("Cancelado");

    private final String descripcion;

    EstadoTorneo(String descripcion) {
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