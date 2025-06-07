package com.torneotenismesa.simulacion.modelos_mock;

import java.util.Objects;
import java.util.UUID;

// Mock de la clase Jugador del subsistema de Participantes
public class Jugador {
    private final UUID id;
    private final String nombre;

    public Jugador(String nombre) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return id.equals(jugador.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}