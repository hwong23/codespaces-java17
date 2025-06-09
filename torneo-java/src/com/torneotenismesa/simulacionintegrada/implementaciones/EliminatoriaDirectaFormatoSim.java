package com.torneotenismesa.simulacionintegrada.implementaciones;

import com.torneotenismesa.gestiontorneos.modelos.FormatoTorneo;
// import com.torneotenismesa.gestiontorneos.modelos.Torneo; // No se usa en esta simulación simple
import com.torneotenismesa.gestionparticipantes.modelos.Participante;
import com.torneotenismesa.simulacionintegrada.modelosmock.PartidoSim;
import com.torneotenismesa.simulacionintegrada.modelosmock.RondaSim;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.ArrayList;


public class EliminatoriaDirectaFormatoSim implements FormatoTorneo {
    private final UUID idFormato = UUID.randomUUID();
    private static final Random rng = new Random();

    @Override
    public UUID getIdFormato() { return idFormato; }

    @Override
    public String getNombreFormato() { return "Eliminatoria Directa (Sim)"; }

    @Override
    public String getDescripcion() { return "Los participantes compiten en rondas, el perdedor es eliminado."; }

    public RondaSim generarRondaSimulada(List<Participante> participantes, int numeroRonda) {
        RondaSim ronda = new RondaSim(numeroRonda);
        
        List<Participante> participantesBarajados = new ArrayList<>(participantes);
        Collections.shuffle(participantesBarajados, rng);

        for (int i = 0; i < participantesBarajados.size() - (participantesBarajados.size() % 2); i += 2) {
            if (i + 1 < participantesBarajados.size()) {
                Participante p1 = participantesBarajados.get(i);
                Participante p2 = participantesBarajados.get(i + 1);
                ronda.agregarPartido(new PartidoSim(p1, p2));
            }
        }
        return ronda;
    }
    
    // Métodos conceptuales de la interfaz no implementados en detalle para esta simulación.
    // En un sistema real, Torneo invocaría estos métodos.
    // @Override
    public Object generarCuadro(/*Torneo torneo, List<Inscripcion> participantesInscritos*/) {
        // La lógica real estaría aquí, usando los parámetros.
        // Para esta simulación, el SimuladorIntegradoJava llama a generarRondaSimulada directamente.
        throw new UnsupportedOperationException("Método conceptual no implementado para la simulación directa.");
    }

    // @Override
    public Object determinarSiguienteRonda(/*Torneo torneo, Object faseActual*/) {
        throw new UnsupportedOperationException("Método conceptual no implementado para la simulación directa.");
    }
}