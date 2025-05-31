package com.torneotenismesa.simulacion.modelos_mock;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Mock de la clase Ronda del subsistema de Partidos y Resultados
public class Ronda {
    private final int numeroRonda;
    private final List<Partido> partidos;

    public Ronda(int numeroRonda) {
        this.numeroRonda = numeroRonda;
        this.partidos = new ArrayList<>();
    }

    public void agregarPartido(Partido partido) {
        this.partidos.add(partido);
    }
    
    public List<Partido> getPartidos() {
        return partidos;
    }

    public int getNumeroRonda() {
        return numeroRonda;
    }

    /**
     * Simula jugar todos los partidos de la ronda.
     */
    public void jugarRonda() {
        partidos.forEach(Partido::jugarPartido);
    }

    /**
     * Recolecta los ganadores de todos los partidos de esta ronda.
     * @return Lista de jugadores ganadores.
     */
    public List<Jugador> getGanadores() {
        return partidos.stream()
                .map(Partido::getGanador)
                .collect(Collectors.toList());
    }
}