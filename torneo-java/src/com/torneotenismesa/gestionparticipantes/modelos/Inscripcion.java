package com.torneotenismesa.gestionparticipantes.modelos;

import com.torneotenismesa.gestionparticipantes.enums.EstadoInscripcion;
// import com.torneotenismesa.gestiontorneos.modelos.Torneo; // Para vincular con Torneo

import java.time.LocalDateTime; // Usar LocalDateTime para fecha y hora
import java.util.Objects;
import java.util.UUID;

/**
 * Representa la inscripción de un Participante (Jugador o Equipo) a un Torneo,
 * posiblemente en una Categoria específica.
 */
public class Inscripcion {
    private final UUID idInscripcion;
    private final LocalDateTime fechaInscripcion; // Usar LocalDateTime
    private EstadoInscripcion estadoInscripcion;
    private final Participante participante;
    private final UUID idTorneo; // Referencia al ID del Torneo, la entidad Torneo vendría de otro paquete
    private Categoria categoria; // Opcional, si el torneo tiene categorías

    public Inscripcion(Participante participante, UUID idTorneo) {
        this.idInscripcion = UUID.randomUUID();
        this.fechaInscripcion = LocalDateTime.now();
        this.estadoInscripcion = EstadoInscripcion.PENDIENTE; // Estado inicial
        this.participante = Objects.requireNonNull(participante, "El participante no puede ser nulo.");
        this.idTorneo = Objects.requireNonNull(idTorneo, "El ID del torneo no puede ser nulo.");
    }

    // Getters y Setters
    public UUID getIdInscripcion() {
        return idInscripcion;
    }

    public LocalDateTime getFechaInscripcion() {
        return fechaInscripcion;
    }

    public EstadoInscripcion getEstadoInscripcion() {
        return estadoInscripcion;
    }

    public void setEstadoInscripcion(EstadoInscripcion estadoInscripcion) {
        this.estadoInscripcion = estadoInscripcion;
    }

    public Participante getParticipante() {
        return participante;
    }

    public UUID getIdTorneo() {
        return idTorneo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void confirmarInscripcion() {
        this.estadoInscripcion = EstadoInscripcion.CONFIRMADA;
    }

    public void rechazarInscripcion() {
        this.estadoInscripcion = EstadoInscripcion.RECHAZADA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inscripcion that = (Inscripcion) o;
        return idInscripcion.equals(that.idInscripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInscripcion);
    }

    @Override
    public String toString() {
        return "Inscripcion{" +
                "idInscripcion=" + idInscripcion +
                ", participante=" + participante.getNombreDescriptivo() +
                ", idTorneo=" + idTorneo +
                ", estado=" + estadoInscripcion +
                (categoria != null ? ", categoria=" + categoria.getNombreCategoria() : "") +
                '}';
    }
}