// src/com/torneotenismesa/gestiontorneos/modelos/Torneo.java
package com.torneotenismesa.gestiontorneos.modelos;

import com.torneotenismesa.gestiontorneos.enums.EstadoTorneo;
import com.torneotenismesa.gestiontorneos.enums.TipoTorneo;
// --- IMPORTACIONES DE OTROS SUBSISTEMAS ---
import com.torneotenismesa.gestionparticipantes.modelos.Inscripcion; // <--- AÑADIR
import com.torneotenismesa.gestionparticipantes.modelos.Participante; // <--- AÑADIR
import com.torneotenismesa.gestionparticipantes.enums.EstadoInscripcion; // <--- AÑADIR


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;


public class Torneo {

    private final UUID idTorneo;
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private String lugar;
    private TipoTorneo tipoTorneo;
    private FormatoTorneo formato;
    private EstadoTorneo estado;
    private String reglasEspecificas;
    private UUID idOrganizador; // Temporal

    // --- NUEVAS ADICIONES PARA GESTIONAR INSCRIPCIONES ---
    private final List<Inscripcion> inscripcionesRegistradas;

    public Torneo(String nombre, Date fechaInicio, String lugar, TipoTorneo tipoTorneo, FormatoTorneo formato) {
        this.idTorneo = UUID.randomUUID();
        this.nombre = Objects.requireNonNull(nombre, "El nombre del torneo no puede ser nulo");
        this.fechaInicio = Objects.requireNonNull(fechaInicio, "La fecha de inicio no puede ser nula");
        this.lugar = lugar;
        this.tipoTorneo = Objects.requireNonNull(tipoTorneo, "El tipo de torneo no puede ser nulo");
        this.formato = Objects.requireNonNull(formato, "El formato del torneo no puede ser nulo");
        this.estado = EstadoTorneo.PLANIFICADO;
        this.inscripcionesRegistradas = new ArrayList<>(); // <--- INICIALIZAR LISTA
    }

    // Getters y Setters existentes... (los mantengo por brevedad)

    public UUID getIdTorneo() { return idTorneo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Date getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(Date fechaInicio) { this.fechaInicio = fechaInicio; }
    public Date getFechaFin() { return fechaFin; }
    public void setFechaFin(Date fechaFin) { this.fechaFin = fechaFin; }
    public String getLugar() { return lugar; }
    public void setLugar(String lugar) { this.lugar = lugar; }
    public TipoTorneo getTipoTorneo() { return tipoTorneo; }
    public void setTipoTorneo(TipoTorneo tipoTorneo) { this.tipoTorneo = tipoTorneo; }
    public FormatoTorneo getFormato() { return formato; }
    public void setFormato(FormatoTorneo formato) { this.formato = formato; }
    public EstadoTorneo getEstado() { return estado; }
    // estado se modifica internamente
    public String getReglasEspecificas() { return reglasEspecificas; }
    public void setReglasEspecificas(String reglasEspecificas) { this.reglasEspecificas = reglasEspecificas; }
    public UUID getIdOrganizador() { return idOrganizador; }
    public void setIdOrganizador(UUID idOrganizador) { this.idOrganizador = idOrganizador; }


    // --- NUEVOS MÉTODOS PARA GESTIONAR INSCRIPCIONES ---
    public boolean agregarInscripcion(Inscripcion inscripcion) {
        if (this.estado != EstadoTorneo.INSCRIPCION_ABIERTA) {
            System.err.println("Error: No se puede agregar inscripción. El torneo '" + nombre + "' no está abierto para inscripciones. Estado actual: " + estado);
            return false;
        }
        if (!inscripcion.getIdTorneo().equals(this.idTorneo)) {
            System.err.println("Error: La inscripción no pertenece a este torneo.");
            return false;
        }
        if (this.inscripcionesRegistradas.stream().anyMatch(i -> 
            i.getIdInscripcion().equals(inscripcion.getIdInscripcion()) ||
            i.getParticipante().getIdParticipante().equals(inscripcion.getParticipante().getIdParticipante()))) {
            System.err.println("Error: El participante '" + inscripcion.getParticipante().getNombreDescriptivo() + "' ya está inscrito o la inscripción ya existe.");
            return false;
        }

        this.inscripcionesRegistradas.add(inscripcion);
        inscripcion.confirmarInscripcion(); // Marcar la inscripción como confirmada
        System.out.println("Inscripción de '" + inscripcion.getParticipante().getNombreDescriptivo() + "' registrada y confirmada para el torneo '" + nombre + "'.");
        return true;
    }

    public List<Inscripcion> getInscripcionesRegistradas() {
        return Collections.unmodifiableList(inscripcionesRegistradas);
    }

    public List<Participante> getParticipantesInscritosConfirmados() {
        return this.inscripcionesRegistradas.stream()
                .filter(i -> i.getEstadoInscripcion() == EstadoInscripcion.CONFIRMADA)
                .map(Inscripcion::getParticipante)
                .collect(Collectors.toList());
    }

    // Métodos de Lógica de Negocio (abrirInscripciones, cerrarInscripciones, etc.)
    public void abrirInscripciones() {
        if (this.estado == EstadoTorneo.PLANIFICADO) {
            this.estado = EstadoTorneo.INSCRIPCION_ABIERTA;
            System.out.println("Inscripciones abiertas para el torneo: " + nombre);
        } else {
            System.err.println("No se pueden abrir inscripciones. Estado actual: " + estado);
        }
    }

    public void cerrarInscripciones() {
        if (this.estado == EstadoTorneo.INSCRIPCION_ABIERTA) {
            this.estado = EstadoTorneo.INSCRIPCION_CERRADA;
            long countConfirmados = this.inscripcionesRegistradas.stream()
                                        .filter(i -> i.getEstadoInscripcion() == EstadoInscripcion.CONFIRMADA)
                                        .count();
            System.out.println("Inscripciones cerradas para el torneo: " + nombre + ". Total inscritos confirmados: " + countConfirmados);
        } else {
            System.err.println("No se pueden cerrar inscripciones. Estado actual: " + estado);
        }
    }

    public void iniciarTorneo() {
        if (this.estado == EstadoTorneo.INSCRIPCION_CERRADA) {
            if (getParticipantesInscritosConfirmados().size() < 2) {
                System.err.println("El torneo '" + nombre + "' no puede iniciar con menos de 2 participantes confirmados.");
                return;
            }
            this.estado = EstadoTorneo.EN_CURSO;
            System.out.println("El torneo '" + nombre + "' ha comenzado.");
        } else {
            System.err.println("El torneo no puede iniciar. Estado actual: " + estado + ". Asegúrese que las inscripciones estén cerradas.");
        }
    }

    public void finalizarTorneo() {
        if (this.estado == EstadoTorneo.EN_CURSO) {
            this.estado = EstadoTorneo.FINALIZADO;
            System.out.println("El torneo '" + nombre + "' ha finalizado.");
        } else {
            System.err.println("El torneo no puede finalizar. Estado actual: " + estado);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Torneo torneo = (Torneo) o;
        return idTorneo.equals(torneo.idTorneo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTorneo);
    }

    @Override
    public String toString() {
        return "Torneo{" +
                "idTorneo=" + idTorneo +
                ", nombre='" + nombre + '\'' +
                ", estado=" + estado +
                '}';
    }
}