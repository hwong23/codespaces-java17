package com.torneotenismesa.simulacion.implementaciones;

import com.torneotenismesa.gestionparticipantes.modelos.Jugador;
import com.torneotenismesa.gestiontorneos.modelos.FormatoTorneo;
import com.torneotenismesa.simulacion.modelos_mock.Partido;
import com.torneotenismesa.simulacion.modelos_mock.Ronda;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class EliminatoriaDirectaFormato implements FormatoTorneo {
    private final UUID idFormato = UUID.randomUUID();

    @Override
    public UUID getIdFormato() { return idFormato; }

    @Override
    public String getNombreFormato() { return "Eliminatoria Directa"; }

    @Override
    public String getDescripcion() { return "Los jugadores compiten en rondas, el perdedor es eliminado."; }

    /**
     * Genera la ronda inicial a partir de una lista de jugadores.
     * Para simplificar, asume que el número de jugadores es una potencia de 2.
     * @param jugadores Lista de jugadores inscritos.
     * @param numeroRonda El número de la ronda a generar (ej. 1 para la primera).
     * @return La ronda generada con sus partidos.
     */
    public Ronda generarRonda(List<Jugador> jugadores, int numeroRonda) {
        Ronda ronda = new Ronda(numeroRonda);
        
        // Barajamos a los jugadores para que los emparejamientos sean aleatorios
        Collections.shuffle(jugadores);

        for (int i = 0; i < jugadores.size(); i += 2) {
            Jugador jugador1 = jugadores.get(i);
            Jugador jugador2 = jugadores.get(i + 1);
            ronda.agregarPartido(new Partido(jugador1, jugador2));
        }
        return ronda;
    }
}