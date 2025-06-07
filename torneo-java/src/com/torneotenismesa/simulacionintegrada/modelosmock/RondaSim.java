package com.torneotenismesa.simulacionintegrada.modelosmock;

import com.torneotenismesa.gestionparticipantes.modelos.Participante;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RondaSim {
    private final int numeroRonda;
    private final List<PartidoSim> partidos;

    public RondaSim(int numeroRonda) {
        this.numeroRonda = numeroRonda;
        this.partidos = new ArrayList<>();
    }

    public void agregarPartido(PartidoSim partido) {
        this.partidos.add(partido);
    }
    
    public List<PartidoSim> getPartidos() {
        return partidos;
    }

    public int getNumeroRonda() {
        return numeroRonda;
    }

    public void jugarRonda() {
        partidos.forEach(PartidoSim::jugarPartido);
    }

    public List<Participante> getGanadores() {
        return partidos.stream()
                .map(PartidoSim::getGanador)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}