package com.torneotenismesa.gestionparticipantes.modelos;

import java.util.Objects;
import java.util.UUID;

/**
 * Clase abstracta base para cualquier entidad que pueda participar en un torneo.
 * Puede ser un Jugador individual o un Equipo.
 */
public abstract class Participante {
    protected final UUID idParticipante;

    protected Participante() {
        this.idParticipante = UUID.randomUUID();
    }

    public UUID getIdParticipante() {
        return idParticipante;
    }

    /**
     * Devuelve un nombre descriptivo del participante.
     * Debe ser implementado por las subclases.
     * @return Nombre del participante (ej. nombre del jugador o nombre del equipo).
     */
    public abstract String getNombreDescriptivo();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participante that = (Participante) o;
        return idParticipante.equals(that.idParticipante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idParticipante);
    }

    @Override
    public String toString() {
        return "Participante{id=" + idParticipante + '}';
    }
}
