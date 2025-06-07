package com.torneotenismesa.simulacion;

import com.torneotenismesa.gestionparticipantes.modelos.Jugador;
import com.torneotenismesa.gestiontorneos.enums.TipoTorneo;
import com.torneotenismesa.gestiontorneos.modelos.Torneo;
import com.torneotenismesa.simulacion.implementaciones.EliminatoriaDirectaFormato;
import com.torneotenismesa.simulacion.modelos_mock.Ronda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SimuladorTorneo {

    public static void main(String[] args) {
        System.out.println("--- INICIO DE LA SIMULACIÓN DE TORNEO ---");

        // 1. CREAR EL TORNEO
        // =========================================================
        System.out.println("\n[CASO DE USO: CREAR TORNEO]");
        EliminatoriaDirectaFormato formato = new EliminatoriaDirectaFormato();
        Torneo torneo = new Torneo(
                "Copa de Verano 2024",
                new Date(),
                "Polideportivo Central",
                TipoTorneo.INDIVIDUAL,
                formato
        );
        System.out.println("Torneo Creado: " + torneo);

        // Simular inscripción de jugadores
        List<Jugador> jugadoresInscritos = new ArrayList<>();
        jugadoresInscritos.add(new Jugador("Ana"));
        jugadoresInscritos.add(new Jugador("Beto"));
        jugadoresInscritos.add(new Jugador("Carla"));
        jugadoresInscritos.add(new Jugador("David"));
        jugadoresInscritos.add(new Jugador("Elena"));
        jugadoresInscritos.add(new Jugador("Fernando"));
        jugadoresInscritos.add(new Jugador("Gabi"));
        jugadoresInscritos.add(new Jugador("Hugo"));
        
        System.out.println(jugadoresInscritos.size() + " jugadores inscritos.");
        
        // Cambiar estados del torneo
        torneo.abrirInscripciones();
        torneo.cerrarInscripciones();
        
        // 2. JUGAR EL TORNEO
        // =========================================================
        System.out.println("\n[CASO DE USO: JUGAR TORNEO]");
        torneo.iniciarTorneo();

        List<Jugador> jugadoresActivos = new ArrayList<>(jugadoresInscritos);
        int numeroRonda = 1;

        while (jugadoresActivos.size() > 1) {
            System.out.println("\n--- JUGANDO RONDA " + numeroRonda + " ---");
            Ronda rondaActual = formato.generarRonda(jugadoresActivos, numeroRonda);
            
            rondaActual.jugarRonda();
            
            jugadoresActivos = rondaActual.getGanadores();
            numeroRonda++;
        }
        
        // 3. FINALIZAR EL TORNEO
        // =========================================================
        System.out.println("\n[CASO DE USO: FINALIZAR TORNEO]");
        torneo.finalizarTorneo();
        
        if (!jugadoresActivos.isEmpty()) {
            Jugador campeon = jugadoresActivos.get(0);
            System.out.println("\n=============================================");
            System.out.println("¡EL CAMPEÓN DEL TORNEO '" + torneo.getNombre() + "' ES: " + campeon.getNombre() + "!");
            System.out.println("=============================================");
        } else {
            System.out.println("El torneo ha finalizado sin un campeón claro.");
        }
        
        System.out.println("\nEstado final del torneo: " + torneo.getEstado());
        System.out.println("--- FIN DE LA SIMULACIÓN ---");
    }
}