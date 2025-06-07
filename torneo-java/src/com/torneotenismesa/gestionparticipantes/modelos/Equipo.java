package com.torneotenismesa.gestionparticipantes.modelos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Representa a un equipo de jugadores. Hereda de Participante.
 */
public class Equipo extends Participante {
    private String nombreEquipo;
    private final List<Jugador> miembros;

    public Equipo(String nombreEquipo) {
        super();
        this.nombreEquipo = Objects.requireNonNull(nombreEquipo, "El nombre del equipo no puede ser nulo.");
        this.miembros = new ArrayList<>();
    }

    // Getters y Setters
    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public List<Jugador> getMiembros() {
        return Collections.unmodifiableList(miembros); // Devuelve una vista de solo lectura
    }

    public void agregarMiembro(Jugador jugador) {
        Objects.requireNonNull(jugador, "El jugador a agregar no puede ser nulo.");
        if (!this.miembros.contains(jugador)) {
            this.miembros.add(jugador);
        }
    }

    public boolean removerMiembro(Jugador jugador) {
        return this.miembros.remove(jugador);
    }

    /**
     * Valida si el equipo cumple con el mínimo de miembros requerido.
     * @param minimoRequerido El número mínimo de miembros.
     * @return true si el equipo tiene al menos el mínimo de miembros, false en caso contrario.
     */
    public boolean validarMinimoMiembros(int minimoRequerido) {
        return this.miembros.size() >= minimoRequerido;
    }

    @Override
    public String getNombreDescriptivo() {
        return nombreEquipo;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "idParticipante=" + idParticipante + // Heredado de Participante
                ", nombreEquipo='" + nombreEquipo + '\'' +
                ", numeroMiembros=" + miembros.size() +
                '}';
    }
}