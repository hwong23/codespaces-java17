package com.torneotenismesa.gestiontorneos.modelos;

import com.torneotenismesa.gestiontorneos.enums.EstadoTorneo;
import com.torneotenismesa.gestiontorneos.enums.TipoTorneo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

// --- Importaciones de clases de OTROS SUBSISTEMAS (conceptuales por ahora) ---
// import com.torneotenismesa.gestionparticipantes.modelos.Inscripcion;
// import com.torneotenismesa.gestionpartidos.modelos.Ronda;
// import com.torneotenismesa.gestionpartidos.modelos.Grupo;
// import com.torneotenismesa.gestionusuarios.modelos.Usuario;

/**
 * Entidad principal que representa un torneo de tenis de mesa.
 * Contiene la información general del torneo, su estado, formato y
 * referencias a las entidades relacionadas.
 */
public class Torneo {

    private final UUID idTorneo;
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private String lugar;
    private TipoTorneo tipoTorneo;
    private FormatoTorneo formato; // Referencia a la interfaz del formato
    private EstadoTorneo estado;
    private String reglasEspecificas;

    // --- Relaciones con otros subsistemas (Tipos conceptuales) ---
    // private com.torneotenismesa.gestionusuarios.modelos.Usuario organizador;
    // private List<com.torneotenismesa.gestionparticipantes.modelos.Inscripcion> inscripciones;
    // private List<Object> fasesTorneo; // Podría ser List<Ronda> o List<Grupo> o una abstracción común

    // Atributo de ejemplo para el organizador (se reemplazaría con la entidad Usuario real)
    private UUID idOrganizador; // Temporalmente, hasta integrar con subsistema de Usuarios

    public Torneo(String nombre, Date fechaInicio, String lugar, TipoTorneo tipoTorneo, FormatoTorneo formato) {
        this.idTorneo = UUID.randomUUID();
        this.nombre = Objects.requireNonNull(nombre, "El nombre del torneo no puede ser nulo");
        this.fechaInicio = Objects.requireNonNull(fechaInicio, "La fecha de inicio no puede ser nula");
        this.lugar = lugar;
        this.tipoTorneo = Objects.requireNonNull(tipoTorneo, "El tipo de torneo no puede ser nulo");
        this.formato = Objects.requireNonNull(formato, "El formato del torneo no puede ser nulo");
        this.estado = EstadoTorneo.PLANIFICADO; // Estado inicial por defecto
        // this.inscripciones = new ArrayList<>();
        // this.fasesTorneo = new ArrayList<>();
    }

    // --- Getters y Setters ---

    public UUID getIdTorneo() {
        return idTorneo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public TipoTorneo getTipoTorneo() {
        return tipoTorneo;
    }

    public void setTipoTorneo(TipoTorneo tipoTorneo) {
        this.tipoTorneo = tipoTorneo;
    }

    public FormatoTorneo getFormato() {
        return formato;
    }

    public void setFormato(FormatoTorneo formato) {
        this.formato = formato;
    }

    public EstadoTorneo getEstado() {
        return estado;
    }

    public void setEstado(EstadoTorneo estado) {
        this.estado = estado;
    }

    public String getReglasEspecificas() {
        return reglasEspecificas;
    }

    public void setReglasEspecificas(String reglasEspecificas) {
        this.reglasEspecificas = reglasEspecificas;
    }

    // Getter y Setter para el ID del organizador (temporal)
    public UUID getIdOrganizador() {
        return idOrganizador;
    }

    public void setIdOrganizador(UUID idOrganizador) {
        // En un sistema real, aquí se asignaría un objeto Usuario
        this.idOrganizador = idOrganizador;
    }

    // --- Métodos para gestionar relaciones (conceptuales por ahora) ---

    // public void agregarInscripcion(com.torneotenismesa.gestionparticipantes.modelos.Inscripcion inscripcion) {
    //     this.inscripciones.add(inscripcion);
    // }

    // public List<com.torneotenismesa.gestionparticipantes.modelos.Inscripcion> getInscripciones() {
    //     return new ArrayList<>(inscripciones); // Devuelve copia para evitar modificación externa
    // }

    // public void agregarFase(Object fase) { // Fase puede ser Ronda o Grupo
    //     this.fasesTorneo.add(fase);
    // }

    // public List<Object> getFasesTorneo() {
    //     return new ArrayList<>(fasesTorneo);
    // }

    // --- Métodos de Lógica de Negocio (ejemplos) ---

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
            System.out.println("Inscripciones cerradas para el torneo: " + nombre);
            // Aquí se podría disparar la lógica para generar cuadros/grupos vía el FormatoTorneo
        } else {
            System.err.println("No se pueden cerrar inscripciones. Estado actual: " + estado);
        }
    }

    public void iniciarTorneo() {
        if (this.estado == EstadoTorneo.INSCRIPCION_CERRADA /* && cuadrosGenerados */) {
            this.estado = EstadoTorneo.EN_CURSO;
            System.out.println("El torneo '" + nombre + "' ha comenzado.");
        } else {
            System.err.println("El torneo no puede iniciar. Estado actual: " + estado + ". Asegúrese que las inscripciones estén cerradas y los cuadros generados.");
        }
    }

    public void finalizarTorneo() {
        if (this.estado == EstadoTorneo.EN_CURSO /* && todosLosPartidosFinalizados */) {
            this.estado = EstadoTorneo.FINALIZADO;
            System.out.println("El torneo '" + nombre + "' ha finalizado.");
        } else {
            System.err.println("El torneo no puede finalizar. Estado actual: " + estado + ". Asegúrese que todos los partidos hayan concluido.");
        }
    }


    // --- Sobrescritura de equals, hashCode y toString ---

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
                ", fechaInicio=" + fechaInicio +
                ", estado=" + estado +
                ", tipoTorneo=" + tipoTorneo +
                ", formato=" + (formato != null ? formato.getNombreFormato() : "N/A") +
                '}';
    }
}