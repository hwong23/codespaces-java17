package com.torneotenismesa.simulacionintegrada.modelosmock;

import com.torneotenismesa.gestionparticipantes.modelos.Participante;
import java.util.Random;

public class PartidoSim {
    private static final Random random = new Random();
    private final Participante participante1;
    private final Participante participante2;
    private Participante ganador;

    public PartidoSim(Participante participante1, Participante participante2) {
        this.participante1 = participante1;
        this.participante2 = participante2;
    }

    public Participante getParticipante1() { return participante1; }
    public Participante getParticipante2() { return participante2; }
    public Participante getGanador() { return ganador; }

    public void jugarPartido() {
        System.out.println("  -> Jugando partido: " + participante1.getNombreDescriptivo() + " vs " + participante2.getNombreDescriptivo());
        this.ganador = (random.nextDouble() < 0.5) ? participante1 : participante2;
        System.out.println("     Ganador: " + this.ganador.getNombreDescriptivo());
    }
}