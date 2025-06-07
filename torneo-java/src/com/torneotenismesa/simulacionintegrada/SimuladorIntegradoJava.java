package com.torneotenismesa.simulacionintegrada;

import com.torneotenismesa.gestiontorneos.enums.TipoTorneo;
import com.torneotenismesa.gestiontorneos.modelos.Torneo;
import com.torneotenismesa.gestionparticipantes.enums.Genero;
import com.torneotenismesa.gestionparticipantes.modelos.Jugador;
import com.torneotenismesa.gestionparticipantes.modelos.Inscripcion;
import com.torneotenismesa.gestionparticipantes.modelos.Participante;
import com.torneotenismesa.simulacionintegrada.implementaciones.EliminatoriaDirectaFormatoSim;
import com.torneotenismesa.simulacionintegrada.modelosmock.RondaSim;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class SimuladorIntegradoJava {

    public static void main(String[] args) {
        System.out.println("--- INICIO DE LA SIMULACIÓN INTEGRADA (Java) ---");

        // --- 1. CONFIGURACIÓN INICIAL ---
        System.out.println("\n--- FASE 1: Configuración Inicial ---");

        // Crear Jugadores (Subsistema GestiónParticipantes)
        Jugador jugador1 = new Jugador("Rafael", "Nadal", getDate(1986, Calendar.JUNE, 3), Genero.MASCULINO);
        Jugador jugador2 = new Jugador("Serena", "Williams", getDate(1981, Calendar.SEPTEMBER, 26), Genero.FEMENINO);
        Jugador jugador3 = new Jugador("Roger", "Federer", getDate(1981, Calendar.AUGUST, 8), Genero.MASCULINO);
        Jugador jugador4 = new Jugador("Naomi", "Osaka", getDate(1997, Calendar.OCTOBER, 16), Genero.FEMENINO);
        Jugador jugador5 = new Jugador("Andy", "Murray", getDate(1987, Calendar.MAY, 15), Genero.MASCULINO);
        
        System.out.println("Jugadores Creados: " + jugador1.getNombreDescriptivo() + ", " + jugador2.getNombreDescriptivo() + ", ...");

        // Crear Formato de Torneo (Simulación)
        EliminatoriaDirectaFormatoSim formatoSim = new EliminatoriaDirectaFormatoSim();

        // Crear Torneo (Subsistema GestiónTorneos)
        Torneo miTorneo = new Torneo(
                "Abierto de Java Sim",
                new Date(), // Hoy
                "Cancha Central Virtual",
                TipoTorneo.INDIVIDUAL,
                formatoSim
        );
        System.out.println("Torneo Creado: " + miTorneo.getNombre() + ", Estado: " + miTorneo.getEstado());

        // --- 2. PROCESO DE INSCRIPCIÓN ---
        System.out.println("\n--- FASE 2: Proceso de Inscripción ---");
        miTorneo.abrirInscripciones();

        // Crear e intentar agregar inscripciones
        Inscripcion inscripcion1 = new Inscripcion(jugador1, miTorneo.getIdTorneo());
        miTorneo.agregarInscripcion(inscripcion1);

        Inscripcion inscripcion2 = new Inscripcion(jugador2, miTorneo.getIdTorneo());
        miTorneo.agregarInscripcion(inscripcion2);

        Inscripcion inscripcion3 = new Inscripcion(jugador3, miTorneo.getIdTorneo());
        miTorneo.agregarInscripcion(inscripcion3);

        Inscripcion inscripcion4 = new Inscripcion(jugador4, miTorneo.getIdTorneo());
        miTorneo.agregarInscripcion(inscripcion4);

        Inscripcion inscripcion5 = new Inscripcion(jugador5, miTorneo.getIdTorneo());
        // No agregamos la inscripción 5 para la simulación
        // miTorneo.agregarInscripcion(inscripcion5);

        miTorneo.cerrarInscripciones();

        // --- 3. INICIO Y DESARROLLO DEL TORNEO ---
        System.out.println("\n--- FASE 3: Inicio y Desarrollo del Torneo ---");
        miTorneo.iniciarTorneo();

        if (miTorneo.getEstado() == com.torneotenismesa.gestiontorneos.enums.EstadoTorneo.EN_CURSO) {
            List<Participante> participantesActivos = new ArrayList<>(miTorneo.getParticipantesInscritosConfirmados());
            if (participantesActivos.isEmpty()) {
                 System.out.println("No hay participantes suficientes para continuar el torneo.");
            } else {
                int numeroRonda = 1;
                while (participantesActivos.size() > 1) {
                    if (participantesActivos.size() % 2 != 0 && participantesActivos.size() > 1) {
                        Participante jugadorConBye = participantesActivos.get(0);
                        System.out.println("\n--- JUGANDO RONDA " + numeroRonda + " (con BYE) ---");
                        System.out.println("  " + jugadorConBye.getNombreDescriptivo() + " obtiene un BYE y avanza automáticamente.");
                        
                        List<Participante> jugadoresParaEmparejar = participantesActivos.stream().skip(1).collect(Collectors.toList());
                        
                        if (jugadoresParaEmparejar.size() < 2 && !jugadoresParaEmparejar.isEmpty()) {
                            participantesActivos.clear();
                            participantesActivos.add(jugadorConBye);
                            participantesActivos.addAll(jugadoresParaEmparejar);
                            break; 
                        } else if (jugadoresParaEmparejar.isEmpty()) {
                            participantesActivos.clear();
                            participantesActivos.add(jugadorConBye);
                            break;
                        }

                        RondaSim rondaActual = formatoSim.generarRondaSimulada(jugadoresParaEmparejar, numeroRonda);
                        System.out.println("Generada Ronda " + numeroRonda + " con " + rondaActual.getPartidos().size() + " partidos.");
                        rondaActual.jugarRonda();
                        
                        List<Participante> ganadoresDePartidos = rondaActual.getGanadores();
                        participantesActivos.clear();
                        participantesActivos.add(jugadorConBye);
                        participantesActivos.addAll(ganadoresDePartidos);
                    } else {
                        System.out.println("\n--- JUGANDO RONDA " + numeroRonda + " ---");
                        RondaSim rondaActual = formatoSim.generarRondaSimulada(participantesActivos, numeroRonda);
                        System.out.println("Generada Ronda " + numeroRonda + " con " + rondaActual.getPartidos().size() + " partidos.");
                        rondaActual.jugarRonda();
                        participantesActivos = rondaActual.getGanadores();
                    }

                    if (participantesActivos.isEmpty() && numeroRonda > 0) {
                        System.err.println("Error: No quedaron participantes activos después de la ronda.");
                        break;
                    }
                    numeroRonda++;
                }

                // --- 4. FINALIZACIÓN ---
                System.out.println("\n--- FASE 4: Finalización del Torneo ---");
                miTorneo.finalizarTorneo();

                if (participantesActivos.size() == 1) {
                    Participante campeon = participantesActivos.get(0);
                    System.out.println("\n=============================================");
                    System.out.println("¡EL CAMPEÓN DEL TORNEO '" + miTorneo.getNombre() + "' ES: " + campeon.getNombreDescriptivo() + "!");
                    System.out.println("=============================================");
                } else {
                    System.out.println("\nEl torneo ha finalizado sin un campeón único claro o con un error.");
                     System.out.println("Participantes restantes: " + 
                        participantesActivos.stream().map(Participante::getNombreDescriptivo).collect(Collectors.joining(", ")));
                }
            }
        } else {
             System.out.println("El torneo '" + miTorneo.getNombre() + "' no pudo iniciarse. Estado actual: " + miTorneo.getEstado());
        }

        System.out.println("\nEstado final del torneo: " + miTorneo.getEstado());
        System.out.println("--- FIN DE LA SIMULACIÓN INTEGRADA (Java) ---");
    }

    private static Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        return cal.getTime();
    }
}