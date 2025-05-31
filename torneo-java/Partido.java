package com.torneotenismesa.simulacion.modelos_mock;

// Mock de la clase Partido del subsistema de Partidos y Resultados
public class Partido {
    private final Jugador jugador1;
    private final Jugador jugador2;
    private Jugador ganador;

    public Partido(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
    }

    public Jugador getJugador1() { return jugador1; }
    public Jugador getJugador2() { return jugador2; }
    public Jugador getGanador() { return ganador; }

    /**
     * Simula la ejecución de un partido, eligiendo un ganador al azar.
     */
    public void jugarPartido() {
        System.out.println("  -> Jugando partido: " + jugador1 + " vs " + jugador2);
        this.ganador = (Math.random() < 0.5) ? jugador1 : jugador2;
        System.out.println("     Ganador: " + this.ganador);
    }
}